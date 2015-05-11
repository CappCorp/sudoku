package com.cappcorp.sudoku.util;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.function.BiFunction;
import java.util.stream.IntStream;

import com.cappcorp.sudoku.model.ReadableGrid;
import com.cappcorp.sudoku.model.Universe;
import com.cappcorp.sudoku.util.CellKey.CellKeys;

public class GridChecker {

    private final Universe universe;
    private final CellKeys cellKeys;

    public GridChecker(Universe universe, CellKeys cellKeys) {
        this.universe = universe;
        this.cellKeys = cellKeys;
    }

    public String checkIsValid(ReadableGrid grid) {
        StringBuilder builder = new StringBuilder();

        int cardinal = universe.getCardinal();
        IntStream.range(0, cardinal).forEach(row -> checkNoDuplicates("row " + (row + 1), builder, cellKeys.getRowKeys(row), grid));
        IntStream.range(0, cardinal).forEach(col -> checkNoDuplicates("col " + (col + 1), builder, cellKeys.getColKeys(col), grid));
        IntStream.range(0, cardinal).forEach(box -> checkNoDuplicates("box " + (box + 1), builder, cellKeys.getBoxKeys(box), grid));

        return builder.length() == 0 ? null : builder.toString();
    }

    private void checkNoDuplicates(String groupName, StringBuilder builder, List<CellKey> groupKeys, ReadableGrid grid) {
        Set<Integer> resolvedValues = new HashSet<>(universe.getCardinal());
        for (CellKey cellKey : groupKeys) {
            Integer cellValue = grid.getCellValueIfResolved(cellKey.getRow(), cellKey.getCol());
            if (cellValue != null && !resolvedValues.add(cellValue)) {
                builder.append("Error in ").append(groupName);
                builder.append(", value used more than once: ").append(universe.map(cellValue.intValue()));
                builder.append(System.lineSeparator());
            }
        }
    }

    public String checkContains(ReadableGrid container, ReadableGrid containee) {
        return checkCellByCellCondition((containerValue, containeeValue) -> containerValue == null && containeeValue == null || containerValue != null
                && (containeeValue == null || containerValue.equals(containeeValue)), container, containee);
    }

    public String checkAreEqual(ReadableGrid expected, ReadableGrid actual) {
        return checkCellByCellCondition(
                (expectedValue, actualValue) -> expectedValue == null && actualValue == null || expectedValue != null && expectedValue.equals(actualValue),
                expected, actual);
    }

    private String checkCellByCellCondition(BiFunction<Integer, Integer, Boolean> condition, ReadableGrid expected, ReadableGrid actual) {
        Map<CellKey, CellDiff> diffMap = new TreeMap<>();
        IntStream.range(0, universe.getCardinal()).forEach(
                row -> IntStream.range(0, universe.getCardinal()).forEach(col -> checkCondition(condition, expected, actual, diffMap, row, col)));

        if (diffMap.isEmpty()) {
            return null;
        }

        StringBuilder builder = new StringBuilder();
        diffMap.forEach((cellKey, cellDiff) -> cellDiff.display(universe,
                builder.append("Error in row=").append(cellKey.getRow() + 1).append(", col=").append(cellKey.getCol() + 1).append(": ")).append(
                System.lineSeparator()));
        return builder.toString();

    }

    private void checkCondition(BiFunction<Integer, Integer, Boolean> condition, ReadableGrid container, ReadableGrid containee,
            Map<CellKey, CellDiff> diffMap, int row, int col) {
        Integer expectedValue = container.getCellValueIfResolved(row, col);
        Integer actualValue = containee.getCellValueIfResolved(row, col);
        if (!condition.apply(expectedValue, actualValue)) {
            diffMap.put(cellKeys.getKey(row, col), new CellDiff(expectedValue, actualValue));
        }

    }
}
