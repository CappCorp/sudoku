package com.cappcorp.sudoku.resolver;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.cappcorp.sudoku.model.ReadableGrid;
import com.cappcorp.sudoku.model.WritableGrid;
import com.cappcorp.sudoku.util.CellKey;
import com.cappcorp.sudoku.util.CellKey.CellKeys;

public class ExactTupleFinder extends AbstractTupleFinder {

    public ExactTupleFinder(CellKeys cellKeys, ReadableGrid readableGrid, WritableGrid writableGrid, ResolvedCells resolvedCells) {
        super(cellKeys, readableGrid, writableGrid, resolvedCells);
    }

    @Override
    protected boolean findSimilarCells(List<CellKey> unresolvedCellKeys, Map<CellKey, Set<Integer>> possibleValues, List<CellKey> similarCellKeys,
            Set<Integer> similarValues) {
        for (int index = 0; index < unresolvedCellKeys.size() - 1; index++) {
            similarCellKeys.clear();
            similarValues.clear();

            CellKey cellKey = unresolvedCellKeys.get(index);
            similarCellKeys.add(cellKey);
            Set<Integer> cellPossibleValues = possibleValues.get(cellKey);

            for (int otherIndex = index + 1; otherIndex < unresolvedCellKeys.size(); otherIndex++) {
                CellKey otherCellKey = unresolvedCellKeys.get(otherIndex);
                if (cellPossibleValues.equals(possibleValues.get(otherCellKey))) {
                    similarCellKeys.add(otherCellKey);
                }
            }

            int size = similarCellKeys.size();
            if (size != 1 && size < unresolvedCellKeys.size() && size == cellPossibleValues.size()) {
                similarValues.addAll(cellPossibleValues);
                return true;
            }
        }

        return false;
    }
}
