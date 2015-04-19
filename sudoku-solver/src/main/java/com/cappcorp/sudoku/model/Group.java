package com.cappcorp.sudoku.model;

import java.util.Arrays;
import java.util.Set;

class Group {

    private final PossibleValues possibleValues;
    private final Cell[] cells;

    Group(int cardinal) {
        this.cells = new Cell[cardinal];
        this.possibleValues = new PossibleValues(cardinal);
    }

    void setCell(int position, Cell cell) {
        cells[position] = cell;
    }

    void removePossibleValues(int... values) {
        possibleValues.removeValues(values);
        for (Cell cell : cells) {
            if (!cell.isResolved()) {
                cell.removePossibleValues(values);
            }
        }
    }

    Set<Integer> getUnresolvedValues() {
        return possibleValues.getPossibleValues();
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Cells: ").append(Arrays.asList(cells));
        builder.append(", PossibleValues: ").append(possibleValues);
        return builder.toString();
    }
}
