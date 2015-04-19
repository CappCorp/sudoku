package com.cappcorp.sudoku.resolver;

import com.cappcorp.sudoku.listener.ListeningGrid;
import com.cappcorp.sudoku.model.ReadableGrid;
import com.cappcorp.sudoku.model.WritableGrid;

public class GridResolverImpl extends ReadWriteListenGridResolver {

    @Override
    protected void resolveGrid(ReadableGrid readableGrid, WritableGrid writableGrid, ListeningGrid listeningGrid) {
        // TODO Auto-generated method stub
        ResolvedCells resolvedCells = new ResolvedCells(readableGrid);
        listeningGrid.addListener(resolvedCells);

        CellKey key;
        while ((key = resolvedCells.consumeNextNewResolvedKey()) != null) {
            int row = key.getRow();
            int col = key.getCol();
            int cellValue = resolvedCells.getCellValueIfResolved(row, col).intValue();
            writableGrid.removeRowPossibleValues(row, cellValue);
            writableGrid.removeColumnPossibleValues(col, cellValue);
            writableGrid.removeBoxPossibleValues(row, col, cellValue);
        }
    }

}
