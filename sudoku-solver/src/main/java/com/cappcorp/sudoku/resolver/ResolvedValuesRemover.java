package com.cappcorp.sudoku.resolver;

import com.cappcorp.sudoku.model.WritableGrid;
import com.cappcorp.sudoku.util.CellKey;

public class ResolvedValuesRemover {

    private final WritableGrid writableGrid;
    private final ResolvedCells resolvedCells;

    public ResolvedValuesRemover(WritableGrid writableGrid, ResolvedCells resolvedCells) {
        this.writableGrid = writableGrid;
        this.resolvedCells = resolvedCells;
    }

    public boolean removeResolvedValues() {
        boolean valuesRemoved = false;
        CellKey key;
        while ((key = resolvedCells.consumeNextNewResolvedKey()) != null) {
            valuesRemoved = true;
            int row = key.getRow();
            int col = key.getCol();
            int cellValue = resolvedCells.getCellValueIfResolved(row, col).intValue();
            writableGrid.removeRowPossibleValues(row, cellValue);
            writableGrid.removeColumnPossibleValues(col, cellValue);
            writableGrid.removeBoxPossibleValues(row, col, cellValue);
        }
        return valuesRemoved;
    }
}
