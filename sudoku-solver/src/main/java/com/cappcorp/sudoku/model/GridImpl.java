package com.cappcorp.sudoku.model;

import java.util.Set;

import com.cappcorp.sudoku.util.GridHelper;

public class GridImpl implements Grid {

    static Group[] createGroups(int cardinal) {
        Group[] groups = new Group[cardinal];
        for (int i = 0; i < cardinal; i++) {
            groups[i] = new Group(cardinal);
        }
        return groups;
    }

    private final Universe universe;
    private final Cell[][] cells;
    private final Group[] rows;
    private final Group[] columns;
    private final Group[] boxes;

    public GridImpl(int cardinal) {
        this(new Universe(cardinal));
    }

    public GridImpl(char[] characters) {
        this(new Universe(characters));
    }

    public GridImpl(Universe universe) {
        this.universe = universe;
        int cardinal = universe.getCardinal();
        int sqrt = universe.getSqrt();

        this.cells = new Cell[cardinal][cardinal];
        this.rows = createGroups(cardinal);
        this.columns = createGroups(cardinal);
        this.boxes = createGroups(cardinal);

        for (int row = 0; row < cardinal; row++) {
            for (int col = 0; col < cardinal; col++) {
                int box = GridHelper.computeBoxNumber(sqrt, row, col);
                int positionInBox = GridHelper.computeBoxPosition(sqrt, row, col);

                Cell cell = new Cell(cardinal);
                cells[row][col] = cell;
                rows[row].setCell(col, cell);
                columns[col].setCell(row, cell);
                boxes[box].setCell(positionInBox, cell);
            }
        }
    }

    @Override
    public Universe getUniverse() {
        return universe;
    }

    public void setInitialValue(int row, int col, int value) {
        cells[row][col].setPossibleValues(value);
    }

    @Override
    public boolean isResolved(int row, int col) {
        return cells[row][col].isResolved();
    }

    @Override
    public Integer getCellValueIfResolved(int row, int col) {
        return cells[row][col].getValueIfResolved();
    }

    @Override
    public Set<Integer> getCellPossibleValues(int row, int col) {
        return cells[row][col].getPossibleValues();
    }

    @Override
    public void setCellPossibleValues(int row, int col, int... values) {
        cells[row][col].setPossibleValues(values);
    }

    @Override
    public void removeCellPossibleValues(int row, int col, int... values) {
        cells[row][col].removePossibleValues(values);
    }

    @Override
    public void removeRowPossibleValues(int row, int... values) {
        rows[row].removePossibleValues(values);
    }

    @Override
    public void removeColumnPossibleValues(int col, int... values) {
        columns[col].removePossibleValues(values);
    }

    @Override
    public void removeBoxPossibleValues(int row, int col, int... values) {
        getBox(row, col).removePossibleValues(values);
    }

    private Group getBox(int row, int col) {
        return boxes[GridHelper.computeBoxNumber(universe.getSqrt(), row, col)];
    }

    @Override
    public String toString() {
        return GridHelper.displayGrid(this);
    }

}
