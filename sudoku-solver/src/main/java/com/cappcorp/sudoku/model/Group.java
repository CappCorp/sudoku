package com.cappcorp.sudoku.model;

import java.util.Arrays;

class Group {

    private final Cell[] cells;

    Group(int cardinal) {
        this.cells = new Cell[cardinal];
    }

    void setCell(int position, Cell cell) {
        cells[position] = cell;
    }

    void removePossibleValues(int... values) {
        for (Cell cell : cells) {
            if (!cell.isResolved()) {
                cell.removePossibleValues(values);
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Cells: ").append(Arrays.asList(cells));
        return builder.toString();
    }
}
