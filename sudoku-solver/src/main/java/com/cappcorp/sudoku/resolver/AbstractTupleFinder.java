package com.cappcorp.sudoku.resolver;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.cappcorp.sudoku.model.ReadableGrid;
import com.cappcorp.sudoku.model.WritableGrid;
import com.cappcorp.sudoku.util.CellKey;
import com.cappcorp.sudoku.util.CellKey.CellKeys;

public abstract class AbstractTupleFinder {

    private static int[] toIntArray(Collection<Integer> collection) {
        int[] intArray = new int[collection.size()];
        int index = 0;
        for (Integer integer : collection) {
            intArray[index++] = integer.intValue();
        }
        return intArray;
    }

    protected final CellKeys cellKeys;
    protected final ReadableGrid readableGrid;
    protected final WritableGrid writableGrid;
    protected final ResolvedCells resolvedCells;

    public AbstractTupleFinder(CellKeys cellKeys, ReadableGrid readableGrid, WritableGrid writableGrid, ResolvedCells resolvedCells) {
        this.cellKeys = cellKeys;
        this.readableGrid = readableGrid;
        this.writableGrid = writableGrid;
        this.resolvedCells = resolvedCells;
    }

    public boolean findGroupTuples() {
        boolean newTuplesFound = false;
        int cardinal = readableGrid.getUniverse().getCardinal();
        for (int row = 0; row < cardinal; row++) {
            newTuplesFound |= findRowTuples(row);
        }
        for (int col = 0; col < cardinal; col++) {
            newTuplesFound |= findColTuples(col);
        }
        for (int box = 0; box < cardinal; box++) {
            newTuplesFound |= findBoxTuples(box);
        }
        return newTuplesFound;
    }

    private boolean findRowTuples(int row) {
        return findGroupTuples(cellKeys.getRowKeys(row));
    }

    private boolean findColTuples(int col) {
        return findGroupTuples(cellKeys.getColKeys(col));
    }

    private boolean findBoxTuples(int box) {
        return findGroupTuples(cellKeys.getBoxKeys(box));
    }

    private boolean findGroupTuples(List<CellKey> groupCellKeys) {
        List<CellKey> unresolvedCellKeys = new ArrayList<>();
        for (CellKey cellKey : groupCellKeys) {
            if (!resolvedCells.isResolved(cellKey.getRow(), cellKey.getCol())) {
                unresolvedCellKeys.add(cellKey);
            }
        }

        Map<CellKey, Set<Integer>> possibleValues = new HashMap<>(unresolvedCellKeys.size());
        unresolvedCellKeys
                .forEach(cellKey -> possibleValues.put(cellKey, new HashSet<>(readableGrid.getCellPossibleValues(cellKey.getRow(), cellKey.getCol()))));

        boolean newTuplesFound = false;

        List<CellKey> similarCells = new ArrayList<>(possibleValues.size());
        Set<Integer> similarValues = new HashSet<>(possibleValues.size());

        while (findSimilarCells(unresolvedCellKeys, possibleValues, similarValues, similarCells)) {
            int[] similarValuesArray = toIntArray(similarValues);
            for (CellKey cellKey : unresolvedCellKeys) {
                Set<Integer> cellPossibleValues = possibleValues.get(cellKey);
                if (similarCells.contains(cellKey)) {
                    if (cellPossibleValues.retainAll(similarValues)) {
                        writableGrid.setCellPossibleValues(cellKey.getRow(), cellKey.getCol(), similarValuesArray);
                        newTuplesFound = true;
                    }
                } else if (cellPossibleValues.removeAll(similarValues)) {
                    newTuplesFound = true;
                    writableGrid.removeCellPossibleValues(cellKey.getRow(), cellKey.getCol(), similarValuesArray);
                }
            }
            unresolvedCellKeys.removeAll(similarCells);
            similarCells.forEach(key -> possibleValues.remove(key));
        }
        return newTuplesFound;
    }

    protected abstract boolean findSimilarCells(List<CellKey> unresolvedCellKeys, Map<CellKey, Set<Integer>> possibleValues, Set<Integer> similarValues,
            List<CellKey> similarCells);

}