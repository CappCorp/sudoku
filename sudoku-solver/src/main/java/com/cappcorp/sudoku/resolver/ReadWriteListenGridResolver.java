package com.cappcorp.sudoku.resolver;

import com.cappcorp.sudoku.listener.ListeningGrid;
import com.cappcorp.sudoku.listener.ListeningGridImpl;
import com.cappcorp.sudoku.model.ReadableGrid;
import com.cappcorp.sudoku.model.WritableGrid;

public abstract class ReadWriteListenGridResolver extends ReadWriteGridResolver {

    @Override
    protected void resolveGrid(ReadableGrid readableGrid, WritableGrid writableGrid) {
        ListeningGridImpl listeningGrid = new ListeningGridImpl(writableGrid);
        resolveGrid(readableGrid, listeningGrid, listeningGrid);
    }

    protected abstract void resolveGrid(ReadableGrid readableGrid, WritableGrid writableGrid, ListeningGrid listeningGrid);

}
