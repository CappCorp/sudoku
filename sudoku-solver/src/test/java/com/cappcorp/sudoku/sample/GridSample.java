package com.cappcorp.sudoku.sample;

import com.cappcorp.sudoku.model.Grid;
import com.cappcorp.sudoku.reader.GridReader;
import com.cappcorp.sudoku.resolver.GridResolver;
import com.cappcorp.sudoku.stat.StatGrid;
import com.cappcorp.sudoku.writter.GridWriter;

public abstract class GridSample<I, O> {

    private final GridReader<I> gridReader;
    private final GridWriter<O> gridWriter;
    private final I initial;
    private final O solution;

    public GridSample(GridReader<I> gridReader, GridWriter<O> gridWriter, I initial, O solution) {
        this.gridReader = gridReader;
        this.gridWriter = gridWriter;
        this.initial = initial;
        this.solution = solution;
    }

    public I getInitial() {
        return initial;
    }

    public Grid getInitialGrid() {
        return gridReader.readGrid(initial);
    }

    public O getSolution() {
        return solution;
    }

    public StatGrid resolveAndAssert(GridResolver resolver) {
        Grid grid = gridReader.readGrid(initial);
        StatGrid statGrid = new StatGrid(grid);
        resolver.resolveGrid(statGrid);
        O output = gridWriter.writeGrid(grid);
        assertEqual(solution, output);
        return statGrid;
    }

    protected abstract void assertEqual(O solution, O output);
    
    // TODO add grid validity check
    // TODO add matching between output with initial
}
