package com.cappcorp.sudoku.util;

import com.cappcorp.sudoku.model.Universe;

class CellDiff {

    private final Integer expected;
    private final Integer actual;

    CellDiff(Integer expected, Integer actual) {
        this.expected = expected;
        this.actual = actual;
    }

    StringBuilder display(Universe universe, StringBuilder builder) {
        builder.append("expected [");
        builder.append(expected == null ? "NONE" : universe.map(expected.intValue()));
        builder.append("] but was [");
        builder.append(actual == null ? "NONE" : universe.map(actual.intValue()));
        builder.append(']');
        return builder;
    }
}
