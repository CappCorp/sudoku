package com.cappcorp.sudoku.reader;

import com.cappcorp.sudoku.model.Grid;
import com.cappcorp.sudoku.model.Universe;

/**
 * Reader using a 2 dimensions array of integers and a standard universe. The -1 value represents a missing value in the grid.
 */
public class IntArrayGridReader implements GridReader {

    public static final int UNKNOWN = -1;

    private final Universe universe;
    private final int[][] values;

    public IntArrayGridReader(char[] characters, int[][] values) {
        this(new Universe(characters), values);
    }

    public IntArrayGridReader(int cardinal, int[][] values) {
        this(new Universe(cardinal), values);
    }

    public IntArrayGridReader(Universe universe, int[][] values) {
        int cardinal = universe.getCardinal();
        if (values.length != cardinal) {
            throw new IllegalArgumentException("Wrong number of rows [" + values.length + "], expecting the same number as the cardinal [" + cardinal + "]");
        }
        for (int i = 0; i < values.length; i++) {
            int[] rowValues = values[i];
            if (rowValues.length != cardinal) {
                throw new IllegalArgumentException("Wrong number of columns [" + values.length + "] in row [" + i
                        + "] , expecting the same number as the cardinal [" + cardinal + "]");
            }
        }

        this.universe = universe;
        this.values = values;
    }

    @Override
    public Grid readGrid() {
        Grid grid = new Grid(universe);
        int cardinal = universe.getCardinal();
        for (int row = 0; row < cardinal; row++) {
            for (int col = 0; col < cardinal; col++) {
                int value = values[row][col];
                if (value != UNKNOWN) {
                    grid.setCell(row, col, value);
                }
            }
        }
        return grid;
    }
}
