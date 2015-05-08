package com.cappcorp.sudoku.listener;

import java.util.ArrayList;

import com.cappcorp.sudoku.model.WritableGrid;

public class ListeningGridImpl implements ListeningGrid, WritableGrid {

    private final WritableGrid grid;
    private final ArrayList<GridListener> listeners;

    public ListeningGridImpl(WritableGrid grid) {
        this.grid = grid;
        this.listeners = new ArrayList<>();
    }

    @Override
    public void addListener(GridListener listener) {
        listeners.add(listener);
    }

    @Override
    public void removeListener(GridListener listener) {
        listeners.remove(listener);
    }

    @Override
    public void setCellPossibleValues(int row, int col, int... values) {
        grid.setCellPossibleValues(row, col, values);
        listeners.forEach(l -> l.onSetCellPossibleValues(row, col, values));
    }

    @Override
    public void removeCellPossibleValues(int row, int col, int... values) {
        grid.removeCellPossibleValues(row, col, values);
        listeners.forEach(l -> l.onRemoveCellPossibleValues(row, col, values));
    }

    @Override
    public void removeRowPossibleValues(int row, int... values) {
        grid.removeRowPossibleValues(row, values);
        listeners.forEach(l -> l.onRemoveRowPossibleValues(row, values));
    }

    @Override
    public void removeColumnPossibleValues(int col, int... values) {
        grid.removeColumnPossibleValues(col, values);
        listeners.forEach(l -> l.onRemoveColumnPossibleValues(col, values));
    }

    @Override
    public void removeBoxPossibleValues(int row, int col, int... values) {
        grid.removeBoxPossibleValues(row, col, values);
        listeners.forEach(l -> l.onRemoveBoxPossibleValues(row, col, values));
    }

}
