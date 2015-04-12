package com.cappcorp.sudoku.model;

import java.util.Set;

class Cell {

    private final PossibleValues possibleValues;

    Cell(int cardinal) {
        this.possibleValues = new PossibleValues(cardinal);
    }

    Set<Integer> getPossibleValues() {
        return possibleValues.getPossibleValues();
    }

    void setPossibleValues(int... values) {
        possibleValues.setValues(values);
    }

    void removeValues(int... values) {
        possibleValues.removeValues(values);
    }

    Integer getValueIfResolved() {
        return possibleValues.getValueIfResolved();
    }

    boolean isResolved() {
        return possibleValues.isResolved();
    }
}
