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
import com.cappcorp.sudoku.resolver.CellKey.CellKeys;

public class TupleFinder {

    private static int[] toIntArray(Collection<Integer> collection) {
        int[] intArray = new int[collection.size()];
        int index = 0;
        for (Integer integer : collection) {
            intArray[index++] = integer.intValue();
        }
        return intArray;
    }

    private final CellKeys cellKeys;
    private final ReadableGrid readableGrid;
    private final WritableGrid writableGrid;
    private final ResolvedCells resolvedCells;

    public TupleFinder(CellKeys cellKeys, ReadableGrid readableGrid, WritableGrid writableGrid, ResolvedCells resolvedCells) {
        this.cellKeys = cellKeys;
        this.readableGrid = readableGrid;
        this.writableGrid = writableGrid;
        this.resolvedCells = resolvedCells;
    }

    public boolean findGroupTuples() {
        boolean tuplesFound = false;
        int cardinal = readableGrid.getUniverse().getCardinal();
        for (int row = 0; row < cardinal; row++) {
            tuplesFound |= findRowTuples(row);
        }
        for (int col = 0; col < cardinal; col++) {
            tuplesFound |= findColTuples(col);
        }
        for (int box = 0; box < cardinal; box++) {
            tuplesFound |= findBoxTuples(box);
        }
        return tuplesFound;
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
        boolean tuplesFound = false;
        List<CellKey> unresolvedCellKeys = new ArrayList<>();
        for (CellKey cellKey : groupCellKeys) {
            if (!resolvedCells.isResolved(cellKey.getRow(), cellKey.getCol())) {
                unresolvedCellKeys.add(cellKey);
            }
        }

        Map<CellKey, Set<Integer>> possibleValues = computePossibleValues(unresolvedCellKeys);

        List<CellKey> similarCells;

        while ((similarCells = findSimilarCells(unresolvedCellKeys, possibleValues)) != null) {
            tuplesFound = true;
            Set<Integer> similarValuesSet = possibleValues.get(similarCells.get(0));
            int[] similarValues = toIntArray(similarValuesSet);
            unresolvedCellKeys.removeAll(similarCells);
            for (CellKey cellKey : unresolvedCellKeys) {
                writableGrid.removeCellPossibleValues(cellKey.getRow(), cellKey.getCol(), similarValues);
                possibleValues.get(cellKey).removeAll(similarValuesSet);
            }
            for (CellKey cellKey : similarCells) {
                possibleValues.remove(cellKey);
            }
        }
        return tuplesFound;
    }

    private Map<CellKey, Set<Integer>> computePossibleValues(List<CellKey> unresolvedCellKeys) {
        Map<CellKey, Set<Integer>> possibleValues = new HashMap<>(unresolvedCellKeys.size());
        for (CellKey cellKey : unresolvedCellKeys) {
            possibleValues.put(cellKey, new HashSet<>(readableGrid.getCellPossibleValues(cellKey.getRow(), cellKey.getCol())));
        }
        return possibleValues;
    }

    private List<CellKey> findSimilarCells(List<CellKey> groupCellKeys, Map<CellKey, Set<Integer>> possibleValues) {
        List<CellKey> similarCells = new ArrayList<CellKey>(possibleValues.size());

        for (int index = 0; index < groupCellKeys.size() - 1; index++) {
            CellKey cellKey = groupCellKeys.get(index);
            similarCells.add(cellKey);
            Set<Integer> similarValues = possibleValues.get(cellKey);

            for (int otherIndex = index + 1; otherIndex < groupCellKeys.size(); otherIndex++) {
                CellKey otherCellKey = groupCellKeys.get(otherIndex);
                if (similarValues.equals(possibleValues.get(otherCellKey))) {
                    similarCells.add(otherCellKey);
                }
            }

            int size = similarCells.size();
            if (size != 1 && size < groupCellKeys.size() && size == similarValues.size()) {
                return similarCells;
            }
            similarCells.clear();
        }

        return null;
    }
}
