package com.cappcorp.sudoku.resolver;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.cappcorp.sudoku.model.ReadableGrid;
import com.cappcorp.sudoku.model.WritableGrid;
import com.cappcorp.sudoku.util.CellKey;
import com.cappcorp.sudoku.util.CellKey.CellKeys;

public class PartialTupleFinder extends AbstractTupleFinder {

    public PartialTupleFinder(CellKeys cellKeys, ReadableGrid readableGrid, WritableGrid writableGrid, ResolvedCells resolvedCells) {
        super(cellKeys, readableGrid, writableGrid, resolvedCells);
    }

    @Override
    protected boolean findSimilarCells(List<CellKey> unresolvedCellKeys, Map<CellKey, Set<Integer>> possibleValues, Set<Integer> similarValues,
            List<CellKey> similarCells) {
        // TODO Auto-generated method stub
        return false;
    }

}
