package com.cappcorp.sudoku.resolver;

import com.cappcorp.sudoku.listener.ListeningGrid;
import com.cappcorp.sudoku.model.ReadableGrid;
import com.cappcorp.sudoku.model.WritableGrid;
import com.cappcorp.sudoku.util.CellKey.CellKeys;

public class GridResolverImpl extends ReadWriteListenGridResolver {

    @Override
    protected void resolveGrid(ReadableGrid readableGrid, WritableGrid writableGrid, ListeningGrid listeningGrid) {
        CellKeys cellKeys = new CellKeys(readableGrid.getUniverse());
        ResolvedCells resolvedCells = new ResolvedCells(cellKeys, readableGrid);
        listeningGrid.addListener(resolvedCells);

        ResolvedValuesRemover resolvedValuesRemover = new ResolvedValuesRemover(writableGrid, resolvedCells);
        AbstractTupleFinder tupleFinder = new ExactTupleFinder(cellKeys, readableGrid, writableGrid, resolvedCells);

        boolean didSomething = true;

        int iteration = 0;
        while (didSomething) {
            System.out.println("Iteration " + iteration + ": starting removeResolvedValues()");
            boolean valuesRemoved = resolvedValuesRemover.removeResolvedValues();
            System.out.println("Iteration " + iteration + ": removeResolvedValues() -> " + valuesRemoved);
            System.out.println("Iteration " + iteration + ": starting findGroupTuples()");
            boolean groupTuplesFound = tupleFinder.findGroupTuples();
            System.out.println("Iteration " + iteration + ": findGroupTuples() -> " + groupTuplesFound);

            didSomething = valuesRemoved || groupTuplesFound;
            iteration++;
        }
    }

}
