package com.cappcorp.sudoku.reader;

import com.cappcorp.sudoku.model.Grid;
import com.cappcorp.sudoku.model.GridImpl;
import com.cappcorp.sudoku.model.Universe;

/**
 * Reader using a 2 dimensions array of integers and a standard universe. The -1 value represents a missing value in the grid.
 */
public class IntArrayGridReader implements GridReader<int[][]> {

    public static final int UNKNOWN = -1;

    private final Universe universe;

    public IntArrayGridReader(char[] characters) {
        this(new Universe(characters));
    }

    public IntArrayGridReader(int cardinal) {
        this(Universe.fromCardinal(cardinal));
    }

    public IntArrayGridReader(Universe universe) {
        this.universe = universe;
    }

    @Override
    public Grid readGrid(int[][] values) {
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

        GridImpl grid = new GridImpl(universe);
        for (int row = 0; row < cardinal; row++) {
            for (int col = 0; col < cardinal; col++) {
                int value = values[row][col];
                if (value != UNKNOWN) {
                    grid.setInitialValue(row, col, value);
                }
            }
        }
        return grid;
    }
}
