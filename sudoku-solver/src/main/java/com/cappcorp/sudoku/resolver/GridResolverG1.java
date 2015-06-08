package com.cappcorp.sudoku.resolver;

import java.util.Arrays;
import java.util.Collection;

import com.cappcorp.sudoku.listener.ListeningGrid;
import com.cappcorp.sudoku.model.ReadableGrid;
import com.cappcorp.sudoku.model.WritableGrid;
import com.cappcorp.sudoku.util.CellKey.CellKeys;

public class GridResolverG1 extends AlgorithmToolboxGridResolver {

    @Override
    protected Collection<Algorithm> buildAlgorithms(ReadableGrid readableGrid, WritableGrid writableGrid, ListeningGrid listeningGrid, CellKeys cellKeys,
            ResolvedCells resolvedCells) {
        ResolvedValuesRemover resolvedValuesRemover = new ResolvedValuesRemover(writableGrid, resolvedCells);
        ExactTupleFinder exactTupleFinder = new ExactTupleFinder(cellKeys, readableGrid, writableGrid, resolvedCells);
        return Arrays.asList(resolvedValuesRemover, exactTupleFinder);
    }

}
