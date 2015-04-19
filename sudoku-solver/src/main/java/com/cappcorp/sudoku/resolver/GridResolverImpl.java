package com.cappcorp.sudoku.resolver;

import com.cappcorp.sudoku.listener.ListeningGrid;
import com.cappcorp.sudoku.model.ReadableGrid;
import com.cappcorp.sudoku.model.WritableGrid;
import com.cappcorp.sudoku.resolver.CellKey.CellKeys;

public class GridResolverImpl extends ReadWriteListenGridResolver {

    @Override
    protected void resolveGrid(ReadableGrid readableGrid, WritableGrid writableGrid, ListeningGrid listeningGrid) {
        CellKeys cellKeys = new CellKeys(readableGrid.getUniverse());
        ResolvedCells resolvedCells = new ResolvedCells(cellKeys, readableGrid);
        listeningGrid.addListener(resolvedCells);

        ResolvedValuesRemover resolvedValuesRemover = new ResolvedValuesRemover(writableGrid, resolvedCells);
        TupleFinder tupleFinder = new TupleFinder(cellKeys, readableGrid, writableGrid, resolvedCells);

        boolean didSomething = true;

        while (didSomething) {
            boolean valuesRemoved = resolvedValuesRemover.removeResolvedValues();
            boolean groupTuplesFound = tupleFinder.findGroupTuples();

            didSomething = valuesRemoved || groupTuplesFound;
        }
    }

}
