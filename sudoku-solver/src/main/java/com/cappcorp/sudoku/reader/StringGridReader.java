package com.cappcorp.sudoku.reader;

import com.cappcorp.sudoku.model.Grid;
import com.cappcorp.sudoku.model.GridImpl;
import com.cappcorp.sudoku.model.Universe;

public class StringGridReader implements GridReader<String> {

    public static final char UNKNOWN = Universe.BLANK;

    private final Universe universe;

    private final boolean hasSeparators;
    private final boolean isMultiLine;

    public StringGridReader(char[] characters, boolean isMultiLine, boolean hasSeparators) {
        this(new Universe(characters), isMultiLine, hasSeparators);
    }

    public StringGridReader(int cardinal, boolean isMultiLine, boolean hasSeparators) {
        this(new Universe(cardinal), isMultiLine, hasSeparators);
    }

    public StringGridReader(Universe universe, boolean isMultiLine, boolean hasSeparators) {
        this.universe = universe;
        this.hasSeparators = hasSeparators;
        this.isMultiLine = isMultiLine;
    }

    @Override
    public Grid readGrid(String gridString) {
        int cardinal = universe.getCardinal();
        int sqrt = universe.getSqrt();
        int expectedCharCount = cardinal * cardinal;
        int rowLength = cardinal;
        if (isMultiLine) {
            expectedCharCount += cardinal;
            rowLength++;
        }
        if (hasSeparators) {
            expectedCharCount += (cardinal + sqrt + 1) * (sqrt + 1); // row separators, full line
            expectedCharCount += cardinal * (sqrt + 1); // col separators, line minus row separators
            rowLength += sqrt + 1;
        }
        if (gridString.length() != expectedCharCount) {
            throw new IllegalArgumentException("Invalid input string, expected " + expectedCharCount + " characters but found only " + gridString.length());
        }

        GridImpl grid = new GridImpl(universe);
        for (int row = 0; row < cardinal; row++) {
            for (int col = 0; col < cardinal; col++) {
                int actualRow = row;
                if (hasSeparators) {
                    actualRow += row / sqrt + 1;
                }
                int actualCol = col;
                if (hasSeparators) {
                    actualCol += col / sqrt + 1;
                }
                int index = actualRow * rowLength + actualCol;
                char character = gridString.charAt(index);
                if (character != UNKNOWN) {
                    grid.setInitialValue(row, col, universe.map(character));
                }
            }
        }
        return grid;
    }
}
