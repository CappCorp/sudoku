package com.cappcorp.sudoku.resolver;

import com.cappcorp.sudoku.listener.ListeningGrid;
import com.cappcorp.sudoku.model.ReadableGrid;
import com.cappcorp.sudoku.model.WritableGrid;

public class GridResolverImpl extends ReadWriteListenGridResolver {

    @Override
    protected void resolveGrid(ReadableGrid readableGrid, WritableGrid writableGrid, ListeningGrid listeningGrid) {
        // TODO Auto-generated method stub
        ResolvedCells resolvedCells = new ResolvedCells(readableGrid);
    }

}
