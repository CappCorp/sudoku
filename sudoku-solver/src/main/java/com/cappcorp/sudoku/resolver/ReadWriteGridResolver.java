package com.cappcorp.sudoku.resolver;

import com.cappcorp.sudoku.model.Grid;
import com.cappcorp.sudoku.model.ReadableGrid;
import com.cappcorp.sudoku.model.WritableGrid;

public abstract class ReadWriteGridResolver implements GridResolver {

    @Override
    public void resolveGrid(Grid grid) {
        resolveGrid(grid, grid);
    }

    protected abstract void resolveGrid(ReadableGrid readableGrid, WritableGrid writableGrid);

}
