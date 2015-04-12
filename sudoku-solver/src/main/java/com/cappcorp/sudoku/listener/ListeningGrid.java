package com.cappcorp.sudoku.listener;

import java.util.ArrayList;
import java.util.Set;

import com.cappcorp.sudoku.model.Grid;
import com.cappcorp.sudoku.model.Universe;

public class ListeningGrid implements Grid {

    private final Grid grid;
    private final ArrayList<GridListener> listeners;

    public ListeningGrid(Grid grid) {
        this.grid = grid;
        this.listeners = new ArrayList<>();
    }

    public void addListener(GridListener listener) {
        listeners.add(listener);
    }

    public void removeListener(GridListener listener) {
        listeners.remove(listener);
    }

    @Override
    public Universe getUniverse() {
        return grid.getUniverse();
    }

    @Override
    public Integer getCellValueIfResolved(int row, int col) {
        return grid.getCellValueIfResolved(row, col);
    }

    @Override
    public Set<Integer> getCellPossibleValues(int row, int col) {
        return grid.getCellPossibleValues(row, col);
    }

    @Override
    public void setCellPossibleValues(int row, int col, int... values) {
        grid.setCellPossibleValues(row, col, values);
        listeners.stream().forEach(l -> l.onSetCellValues(row, col, values));
    }

    @Override
    public void removeCellPossibleValues(int row, int col, int... values) {
        grid.removeCellPossibleValues(row, col, values);
        listeners.stream().forEach(l -> l.onRemoveCellPossibleValues(row, col, values));
    }

    @Override
    public Set<Integer> getRowUnresolvedValues(int row) {
        return grid.getRowUnresolvedValues(row);
    }

    @Override
    public void removeRowPossibleValues(int row, int... values) {
        grid.removeRowPossibleValues(row, values);
        listeners.stream().forEach(l -> l.onRemoveRowPossibleValues(row, values));
    }

    @Override
    public Set<Integer> getColumnUnresolvedValues(int col) {
        return grid.getColumnUnresolvedValues(col);
    }

    @Override
    public void removeColumnPossibleValues(int col, int... values) {
        grid.removeColumnPossibleValues(col, values);
        listeners.stream().forEach(l -> l.onRemoveColumnPossibleValues(col, values));
    }

    @Override
    public Set<Integer> getBoxUnresolvedValues(int row, int col) {
        return grid.getBoxUnresolvedValues(row, col);
    }

    @Override
    public void removeBoxPossibleValues(int row, int col, int... values) {
        grid.removeBoxPossibleValues(row, col, values);
        listeners.stream().forEach(l -> l.onRemoveBoxPossibleValues(row, col, values));
    }

}
