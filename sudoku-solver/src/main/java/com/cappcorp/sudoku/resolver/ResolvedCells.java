package com.cappcorp.sudoku.resolver;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.stream.IntStream;

import com.cappcorp.sudoku.listener.GridListener;
import com.cappcorp.sudoku.model.ReadableGrid;
import com.cappcorp.sudoku.util.CellKey;
import com.cappcorp.sudoku.util.GridHelper;
import com.cappcorp.sudoku.util.CellKey.CellKeys;

public class ResolvedCells implements GridListener {

    private final CellKeys keys;
    private final ReadableGrid readableGrid;
    private final Map<CellKey, Integer> resolvedCells;
    private final Queue<CellKey> newResolvedKeys;

    public ResolvedCells(CellKeys cellKeys, ReadableGrid grid) {
        this.keys = cellKeys;
        this.readableGrid = grid;
        this.resolvedCells = new HashMap<>();
        this.newResolvedKeys = new LinkedList<CellKey>();

        int cardinal = grid.getUniverse().getCardinal();
        IntStream.range(0, cardinal).forEach(row -> IntStream.range(0, cardinal).forEach(col -> storeIfResolved(row, col, keys.getKey(row, col))));
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
        int cardinal = readableGrid.getUniverse().getCardinal();
        return cardinal * cardinal - resolvedCells.size();
    }

    @Override
    public void onRemoveRowPossibleValues(int row, int... values) {
        IntStream.range(0, readableGrid.getUniverse().getCardinal()).forEach(col -> checkCellResolved(row, col));
    }

    @Override
    public void onRemoveColumnPossibleValues(int col, int... values) {
        IntStream.range(0, readableGrid.getUniverse().getCardinal()).forEach(row -> checkCellResolved(row, col));
    }

    @Override
    public void onRemoveBoxPossibleValues(int row, int col, int... values) {
        int sqrt = readableGrid.getUniverse().getSqrt();
        int boxTopRow = GridHelper.computeBoxTopRowFromRow(sqrt, row);
        int boxLeftCol = GridHelper.computeBoxTopColFromCol(sqrt, col);

        IntStream.range(boxTopRow, boxTopRow + sqrt).forEach(
                boxRow -> IntStream.range(boxLeftCol, boxLeftCol + sqrt).forEach(boxCol -> checkCellResolved(boxRow, boxCol)));
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
        Integer resolvedValue = readableGrid.getCellValueIfResolved(row, col);
        if (resolvedValue != null) {
            resolvedCells.put(key, resolvedValue);
            newResolvedKeys.add(key);
        }
    }

    public CellKey consumeNextNewResolvedKey() {
        return newResolvedKeys.poll();
    }
}
