package com.cappcorp.sudoku.resolver;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

import com.cappcorp.sudoku.listener.GridListener;
import com.cappcorp.sudoku.model.ReadableGrid;
import com.cappcorp.sudoku.resolver.CellKey.CellKeys;

public class ResolvedCells implements GridListener {

    private final ReadableGrid grid;
    private final CellKeys keys;
    private final Map<CellKey, Integer> resolvedCells;
    private final Queue<CellKey> newResolvedKeys;

    public ResolvedCells(ReadableGrid grid) {
        this.grid = grid;
        int cardinal = grid.getUniverse().getCardinal();
        this.keys = new CellKeys(cardinal);
        this.resolvedCells = new HashMap<>();
        this.newResolvedKeys = new LinkedList<CellKey>();

        for (int row = 0; row < cardinal; row++) {
            for (int col = 0; col < cardinal; col++) {
                storeIfResolved(row, col, keys.getKey(row, col));
            }
        }
    }

    public boolean isResolved() {
        return countUnresolved() == 0;
    }

    public boolean isResolved(int row, int col) {
        return resolvedCells.containsKey(keys.getKey(row, col));
    }

    Integer getCellValueIfResolved(int row, int col) {
        return resolvedCells.get(keys.getKey(row, col));
    }

    public int countResolved() {
        return resolvedCells.size();
    }

    public int countUnresolved() {
        int cardinal = grid.getUniverse().getCardinal();
        return cardinal * cardinal - resolvedCells.size();
    }

    @Override
    public void onRemoveRowPossibleValues(int row, int... values) {
        int cardinal = grid.getUniverse().getCardinal();
        for (int col = 0; col < cardinal; col++) {
            checkCellResolved(row, col);
        }
    }

    @Override
    public void onRemoveColumnPossibleValues(int col, int... values) {
        int cardinal = grid.getUniverse().getCardinal();
        for (int row = 0; row < cardinal; row++) {
            checkCellResolved(row, col);
        }
    }

    @Override
    public void onRemoveBoxPossibleValues(int row, int col, int... values) {
        int sqrt = grid.getUniverse().getSqrt();
        int boxTopRow = (row / sqrt) * sqrt;
        int boxLeftCol = (col / sqrt) * sqrt;

        for (int boxRow = boxTopRow; boxRow < boxTopRow + sqrt; boxRow++) {
            for (int boxCol = boxLeftCol; boxCol < boxLeftCol + sqrt; boxCol++) {
                checkCellResolved(boxRow, boxCol);
            }
        }
    }

    @Override
    public void onSetCellPossibleValues(int row, int col, int... values) {
        if (values.length == 1) {
            CellKey key = keys.getKey(row, col);
            if (!resolvedCells.containsKey(key)) {
                resolvedCells.put(key, Integer.valueOf(values[0]));
                newResolvedKeys.add(key);
            }
        }
    }

    @Override
    public void onRemoveCellPossibleValues(int row, int col, int... values) {
        checkCellResolved(row, col);
    }

    private void checkCellResolved(int row, int col) {
        CellKey key = keys.getKey(row, col);
        if (!resolvedCells.containsKey(key)) {
            storeIfResolved(row, col, key);
        }
    }

    private void storeIfResolved(int row, int col, CellKey key) {
        Integer resolvedValue = grid.getCellValueIfResolved(row, col);
        if (resolvedValue != null) {
            resolvedCells.put(key, resolvedValue);
            newResolvedKeys.add(key);
        }
    }

    public CellKey consumeNextNewResolvedKey() {
        return newResolvedKeys.poll();
    }
}
