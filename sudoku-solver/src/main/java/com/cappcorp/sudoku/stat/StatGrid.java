package com.cappcorp.sudoku.stat;

import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;

import com.cappcorp.sudoku.model.Grid;
import com.cappcorp.sudoku.model.Universe;

public class StatGrid implements Grid {

    private final Grid grid;
    private final AtomicLong reads;
    private final AtomicLong writes;

    public StatGrid(Grid grid) {
        this.grid = grid;
        this.reads = new AtomicLong();
        this.writes = new AtomicLong();
    }

    public long getReads() {
        return reads.get();
    }

    public long getWrites() {
        return writes.get();
    }

    private void addRead() {
        reads.incrementAndGet();
    }

    private void addWrite() {
        writes.incrementAndGet();
    }

    @Override
    public Universe getUniverse() {
        return grid.getUniverse();
    }

    @Override
    public boolean isResolved(int row, int col) {
        addRead();
        return grid.isResolved(row, col);
    }

    @Override
    public Integer getCellValueIfResolved(int row, int col) {
        addRead();
        return grid.getCellValueIfResolved(row, col);
    }

    @Override
    public Set<Integer> getCellPossibleValues(int row, int col) {
        addRead();
        return grid.getCellPossibleValues(row, col);
    }

    @Override
    public void setCellPossibleValues(int row, int col, int... values) {
        addWrite();
        grid.setCellPossibleValues(row, col, values);
    }

    @Override
    public void removeCellPossibleValues(int row, int col, int... values) {
        addWrite();
        grid.removeCellPossibleValues(row, col, values);
    }

    @Override
    public Set<Integer> getRowUnresolvedValues(int row) {
        addRead();
        return grid.getRowUnresolvedValues(row);
    }

    @Override
    public void removeRowPossibleValues(int row, int... values) {
        addWrite();
        grid.removeRowPossibleValues(row, values);
    }

    @Override
    public Set<Integer> getColumnUnresolvedValues(int col) {
        addRead();
        return grid.getColumnUnresolvedValues(col);
    }

    @Override
    public void removeColumnPossibleValues(int col, int... values) {
        addWrite();
        grid.removeColumnPossibleValues(col, values);
    }

    @Override
    public Set<Integer> getBoxUnresolvedValues(int row, int col) {
        addRead();
        return grid.getBoxUnresolvedValues(row, col);
    }

    @Override
    public void removeBoxPossibleValues(int row, int col, int... values) {
        addWrite();
        grid.removeBoxPossibleValues(row, col, values);
    }

    @Override
    public String toString() {
        return grid.toString();
    }
}
