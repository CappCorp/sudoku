package com.cappcorp.sudoku.model;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

class PossibleValues {

    private final int cardinal;
    private final Set<Integer> possibleValues;

    PossibleValues(int cardinal) {
        this.cardinal = cardinal;
        possibleValues = new HashSet<>(cardinal);
        for (int i = 0; i < cardinal; i++) {
            possibleValues.add(Integer.valueOf(i));
        }
    }

    Set<Integer> getPossibleValues() {
        return possibleValues.isEmpty() ? Collections.emptySet() : Collections.unmodifiableSet(possibleValues);
    }

    void removeValues(int... values) {
        for (int value : values) {
            checkCardinal(value);
            possibleValues.remove(Integer.valueOf(value));
        }
    }

    void setValues(int... values) {
        possibleValues.clear();
        for (int value : values) {
            checkCardinal(value);
            possibleValues.add(Integer.valueOf(value));
        }
    }

    private void checkCardinal(int value) {
        if (value < 0) {
            throw new IllegalArgumentException("Possible value cannot be negative");
        }
        if (value >= cardinal) {
            throw new IllegalArgumentException("Possible value cannot be greater than or equal to cardinal (" + cardinal + "), value used: " + value);
        }
    }

    int count() {
        return possibleValues.size();
    }

    boolean isResolved() {
        return possibleValues.size() == 1;
    }

    Integer getValueIfResolved() {
        return possibleValues.size() == 1 ? possibleValues.iterator().next() : null;
    }

    @Override
    public String toString() {
        return possibleValues.toString();
    }

}
