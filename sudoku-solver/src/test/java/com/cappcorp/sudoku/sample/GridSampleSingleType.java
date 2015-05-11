package com.cappcorp.sudoku.sample;

import com.cappcorp.sudoku.model.Universe;
import com.cappcorp.sudoku.reader.GridReader;

public abstract class GridSampleSingleType<T> extends GridSample<T, T> {

    private final GridReader<T> gridReader;

    public GridSampleSingleType(Universe universe, GridReader<T> gridReader, T initial, T solution) {
        super(universe, gridReader, gridReader, initial, solution);
        this.gridReader = gridReader;
    }

    @Override
    protected GridReader<T> getInitialGridReader() {
        return getGridReader();
    }

    @Override
    protected GridReader<T> getSolutionGridReader() {
        return getGridReader();
    }

    protected final GridReader<T> getGridReader() {
        return gridReader;
    }

}
