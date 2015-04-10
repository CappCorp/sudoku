package com.cappcorp.sudoku.model;

import java.util.HashSet;
import java.util.Set;

public class PossibleValues {

    private final int cardinal;
    private final Set<Integer> possibleValues;

    public PossibleValues(int cardinal) {
        this.cardinal = cardinal;
        possibleValues = new HashSet<>(cardinal);
        for (int i = 0; i < cardinal; i++) {
            possibleValues.add(Integer.valueOf(i));
        }
    }

    public void remove(int... values) {
        for (int value : values) {
            checkCardinal(value);
            possibleValues.remove(Integer.valueOf(value));
        }
    }

    public void add(int... values) {
        for (int value : values) {
            checkCardinal(value);
            possibleValues.add(Integer.valueOf(value));
        }
    }

    public void set(int value) {
        checkCardinal(value);
        possibleValues.clear();
        possibleValues.add(Integer.valueOf(value));
    }

    private void checkCardinal(int value) {
        if (value < 0) {
            throw new IllegalArgumentException("Possible value cannot be negative");
        }
        if (value >= cardinal) {
            throw new IllegalArgumentException("Possible value cannot be greater than or equal to cardinal (" + cardinal + "), value used: " + value);
        }
    }

    public int count() {
        return possibleValues.size();
    }

    public boolean isResolved() {
        return possibleValues.size() == 1;
    }
}
