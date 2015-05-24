package com.cappcorp.sudoku.resolver;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.PrimitiveIterator.OfInt;
import java.util.Set;
import java.util.stream.IntStream;

import com.cappcorp.sudoku.model.ReadableGrid;
import com.cappcorp.sudoku.model.WritableGrid;
import com.cappcorp.sudoku.util.CellKey;
import com.cappcorp.sudoku.util.CellKey.CellKeys;
import com.cappcorp.sudoku.util.CombinationStream;

public class PartialTupleFinder extends AbstractTupleFinder {

    public PartialTupleFinder(CellKeys cellKeys, ReadableGrid readableGrid, WritableGrid writableGrid, ResolvedCells resolvedCells) {
        super(cellKeys, readableGrid, writableGrid, resolvedCells);
    }

    @Override
    protected boolean findSimilarCells(List<CellKey> unresolvedCellKeys, Map<CellKey, Set<Integer>> possibleValues, List<CellKey> similarCellKeys,
            Set<Integer> similarValues) {
        Set<Integer> groupPossibleValues = new HashSet<>();
        possibleValues.values().forEach(cellPossibleValues -> groupPossibleValues.addAll(cellPossibleValues));

        if (groupPossibleValues.size() < 2) {
            // cannot even find pairs
            return false;
        }

        OfInt intIterator = IntStream.range(2, groupPossibleValues.size()).iterator();

        while (intIterator.hasNext()) {
            int count = intIterator.nextInt();
            Iterator<Set<Integer>> combinationIterator = CombinationStream.streamOf(groupPossibleValues, count).iterator();
            while (combinationIterator.hasNext()) {
                Set<Integer> combination = combinationIterator.next();
                similarValues.clear();
                similarValues.addAll(combination);
                if (findSimilarCellsForValues(unresolvedCellKeys, possibleValues, similarCellKeys, similarValues)) {
                    return true;
                }
            }
        }

        return false;
    }

    private boolean findSimilarCellsForValues(List<CellKey> unresolvedCellKeys, Map<CellKey, Set<Integer>> possibleValues, List<CellKey> similarCellKeys,
            Set<Integer> similarValues) {
        boolean isNewTuple = false;
        similarCellKeys.clear();
        for (CellKey cellKey : unresolvedCellKeys) {
            Set<Integer> cellPossibleValues = possibleValues.get(cellKey);
            if (cellPossibleValues.containsAll(similarValues)) {
                similarCellKeys.add(cellKey);
                if (cellPossibleValues.size() != similarValues.size()) {
                    isNewTuple = true;
                }
            }
        }

        return isNewTuple && similarCellKeys.size() == similarValues.size();
    }

}
