package com.cappcorp.sudoku.model;

public class Group {

    public static Group[] createGroups(int cardinal) {
        Group[] groups = new Group[cardinal];
        for (int i = 0; i < cardinal; i++) {
            groups[i] = new Group(cardinal);
        }
        return groups;
    }

    private final Cell[] cells;

    public Group(int cardinal) {
        this.cells = new Cell[cardinal];
    }

    public void setCell(int position, Cell cell) {
        cells[position] = cell;
    }

    public void removeValues(int... values) {
        for (Cell cell : cells) {
            cell.removeValues(values);
        }
    }
}
