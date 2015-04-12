package com.cappcorp.sudoku.model;

import java.util.Collections;
import java.util.Set;

class Group {

    static Group[] createGroups(int cardinal) {
        Group[] groups = new Group[cardinal];
        for (int i = 0; i < cardinal; i++) {
            groups[i] = new Group(cardinal);
        }
        return groups;
    }

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
                cell.removeValues(values);
            }
        }
    }

    Set<Integer> getUnresolvedValues() {
        PossibleValues possibleValues = new PossibleValues(cells.length);
        for (Cell cell : cells) {
            possibleValues.removeValues(cell.getValueIfResolved().intValue());
        }
        return possibleValues.count() == 0 ? Collections.emptySet() : possibleValues.getPossibleValues();
    }
}
