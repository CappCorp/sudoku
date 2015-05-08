package com.cappcorp.sudoku.resolver;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.cappcorp.sudoku.model.ReadableGrid;
import com.cappcorp.sudoku.model.WritableGrid;
import com.cappcorp.sudoku.resolver.CellKey.CellKeys;

public class ExactTupleFinder extends AbstractTupleFinder {

    public ExactTupleFinder(CellKeys cellKeys, ReadableGrid readableGrid, WritableGrid writableGrid, ResolvedCells resolvedCells) {
        super(cellKeys, readableGrid, writableGrid, resolvedCells);
    }

    protected boolean findSimilarCells(List<CellKey> unresolvedCellKeys, Map<CellKey, Set<Integer>> possibleValues, Set<Integer> similarValues,
            List<CellKey> similarCells) {
        for (int index = 0; index < unresolvedCellKeys.size() - 1; index++) {
            similarCells.clear();
            similarValues.clear();

            CellKey cellKey = unresolvedCellKeys.get(index);
            similarCells.add(cellKey);
            Set<Integer> cellPossibleValues = possibleValues.get(cellKey);

            for (int otherIndex = index + 1; otherIndex < unresolvedCellKeys.size(); otherIndex++) {
                CellKey otherCellKey = unresolvedCellKeys.get(otherIndex);
                if (cellPossibleValues.equals(possibleValues.get(otherCellKey))) {
                    similarCells.add(otherCellKey);
                }
            }

            int size = similarCells.size();
            if (size != 1 && size < unresolvedCellKeys.size() && size == cellPossibleValues.size()) {
                similarValues.addAll(cellPossibleValues);
                return true;
            }
        }

        return false;
    }
}
