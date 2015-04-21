package com.cappcorp.sudoku.writter;

import com.cappcorp.sudoku.model.Grid;
import com.cappcorp.sudoku.model.Universe;

public class StringGridWriter implements GridWriter<String> {

    public static final char ROW_SEPARATOR = '-';
    public static final char COL_SEPARATOR = '|';
    public static final char SPACE = ' ';

    private final boolean isMultiLine;
    private final boolean hasSeparators;
    private final boolean addSpaces;

    public StringGridWriter(boolean isMultiLine, boolean hasSeparators, boolean addSpaces) {
        this.isMultiLine = isMultiLine;
        this.hasSeparators = hasSeparators;
        this.addSpaces = addSpaces;
    }

    @Override
    public String writeGrid(Grid grid) {
        Universe universe = grid.getUniverse();
        int cardinal = universe.getCardinal();
        int sqrt = universe.getSqrt();

        StringBuilder builder = new StringBuilder();
        for (int row = 0; row < cardinal; row++) {

            if (hasSeparators) {
                if (row % sqrt == 0) {
                    displaySeparatorRow(builder, cardinal, sqrt, addSpaces);
                }
            }
            for (int col = 0; col < cardinal; col++) {
                if (hasSeparators) {
                    checkAndDisplayColSeparator(builder, sqrt, col);
                }
                Integer valueIfResolved = grid.getCellValueIfResolved(row, col);
                char displayedValue = valueIfResolved == null ? Universe.BLANK : universe.map(valueIfResolved.intValue());
                builder.append(displayedValue);
                if (addSpaces) {
                    builder.append(SPACE);
                }
            }
            if (hasSeparators) {
                builder.append(COL_SEPARATOR);
            }
            if (isMultiLine) {
                builder.append(System.lineSeparator());
            }
        }
        if (hasSeparators) {
            displaySeparatorRow(builder, cardinal, sqrt, addSpaces);
        }
        return builder.toString();
    }

    private void displaySeparatorRow(StringBuilder builder, int cardinal, int sqrt, boolean addSpaces) {
        for (int col = 0; col < cardinal + sqrt; col++) {
            builder.append(ROW_SEPARATOR);
            if (addSpaces) {
                builder.append(ROW_SEPARATOR);
            }
        }
        builder.append(ROW_SEPARATOR);
        if (isMultiLine) {
            builder.append(System.lineSeparator());
        }
    }

    private void checkAndDisplayColSeparator(StringBuilder builder, int sqrt, int col) {
        if (col % sqrt == 0) {
            builder.append(COL_SEPARATOR);
            if (addSpaces) {
                builder.append(SPACE);
            }
        }
    }

}
