package com.cappcorp.sudoku.resolver;

import java.util.Arrays;
import java.util.Collection;

import com.cappcorp.sudoku.listener.ListeningGrid;
import com.cappcorp.sudoku.model.ReadableGrid;
import com.cappcorp.sudoku.model.WritableGrid;
import com.cappcorp.sudoku.util.CellKey.CellKeys;

public class GridResolverG2 extends AlgorithmToolboxGridResolver {

    @Override
    protected Collection<Algorithm> buildAlgorithms(ReadableGrid readableGrid, WritableGrid writableGrid, ListeningGrid listeningGrid, CellKeys cellKeys,
            ResolvedCells resolvedCells) {
        ResolvedValuesRemover resolvedValuesRemover = new ResolvedValuesRemover(writableGrid, resolvedCells);
        PartialTupleFinder partialTupleFinder = new PartialTupleFinder(cellKeys, readableGrid, writableGrid, resolvedCells);
        return Arrays.asList(resolvedValuesRemover, partialTupleFinder);
    }

}
