package com.cappcorp.sudoku.util;

import com.cappcorp.sudoku.model.Grid;
import com.cappcorp.sudoku.model.Universe;

public enum GridHelper {
    ;

    private static final char ROW_SEPARATOR = '-';
    private static final char COL_SEPARATOR = '|';
    private static final char SPACE = ' ';

    public static String displayGrid(Grid grid) {
        Universe universe = grid.getUniverse();
        int cardinal = universe.getCardinal();
        int sqrt = universe.getSqrt();

        StringBuilder builder = new StringBuilder();
        for (int row = 0; row < cardinal; row++) {

            if (row % sqrt == 0) {
                displaySeparatorRow(builder, cardinal, sqrt);
            }
            for (int col = 0; col < cardinal; col++) {
                checkAndDisplayColSeparator(builder, sqrt, col);

                Integer valueIfResolved = grid.getCell(row, col).getValueIfResolved();
                char displayedValue = valueIfResolved == null ? Universe.BLANK : universe.map(valueIfResolved.intValue());
                builder.append(displayedValue).append(SPACE);
            }
            builder.append(COL_SEPARATOR).append(System.lineSeparator());
        }
        displaySeparatorRow(builder, cardinal, sqrt);
        return builder.toString();
    }

    private static void displaySeparatorRow(StringBuilder builder, int cardinal, int sqrt) {
        for (int col = 0; col < cardinal + sqrt; col++) {
            builder.append(ROW_SEPARATOR).append(ROW_SEPARATOR);
        }
        builder.append(ROW_SEPARATOR).append(System.lineSeparator());
    }

    private static void checkAndDisplayColSeparator(StringBuilder builder, int sqrt, int col) {
        if (col % sqrt == 0) {
            builder.append(COL_SEPARATOR).append(SPACE);
        }
    }

}