package com.cappcorp.sudoku.resolver;

import java.util.Collection;

import com.cappcorp.sudoku.listener.ListeningGrid;
import com.cappcorp.sudoku.listener.ListeningGridImpl;
import com.cappcorp.sudoku.model.Grid;
import com.cappcorp.sudoku.model.ReadableGrid;
import com.cappcorp.sudoku.model.WritableGrid;
import com.cappcorp.sudoku.util.CellKey.CellKeys;

public abstract class AlgorithmToolboxGridResolver implements GridResolver {

    @Override
    public void resolveGrid(Grid grid) {
        ReadableGrid readableGrid = grid;
        WritableGrid writableGrid = grid;

        ListeningGridImpl listeningGrid = new ListeningGridImpl(writableGrid);

        CellKeys cellKeys = new CellKeys(readableGrid.getUniverse());
        ResolvedCells resolvedCells = new ResolvedCells(cellKeys, readableGrid);
        listeningGrid.addListener(resolvedCells);

        Collection<Algorithm> algorithms = buildAlgorithms(readableGrid, writableGrid, listeningGrid, cellKeys, resolvedCells);

        boolean didSomething;

        int iteration = 0;
        do {
            didSomething = false;
            iteration++;

            for (Algorithm algorithm : algorithms) {
                boolean iterationResult = algorithm.performIteration();
                System.out.println("Iteration " + iteration + ", execution algorithm \"" + algorithm.getName() + "\": iteration result=" + iterationResult);
                didSomething |= iterationResult;
                if (iterationResult == true) {
                    break;
                }
            }
        } while (didSomething);
    }

    protected abstract Collection<Algorithm> buildAlgorithms(ReadableGrid readableGrid, WritableGrid writableGrid, ListeningGrid listeningGrid,
            CellKeys cellKeys, ResolvedCells resolvedCells);

}
