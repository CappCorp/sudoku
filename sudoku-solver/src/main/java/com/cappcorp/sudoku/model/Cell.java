package com.cappcorp.sudoku.model;

public class Cell {

    private final PossibleValues possibleValues;

    public Cell(int cardinal) {
        this.possibleValues = new PossibleValues(cardinal);
    }

    public void setValue(int value) {
        possibleValues.set(value);
    }

    public void removeValues(int... values) {
        possibleValues.remove(values);
    }
}
