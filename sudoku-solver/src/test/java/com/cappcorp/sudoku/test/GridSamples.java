package com.cappcorp.sudoku.test;

import com.cappcorp.sudoku.reader.IntArrayGridReader;

public class GridSamples {

    private static final int U = IntArrayGridReader.UNKNOWN;

    public static final int[][] GRID_9_EASY_1 = new int[][] {
// @formatter:off
    { U, U, U,  3, U, U,  U, U, 6 },
    { 6, 0, 2,  7, U, U,  U, U, U },
    { U, U, 1,  U, 0, U,  U, 7, 5 },

    { U, 2, 7,  5, 8, U,  U, U, U },
    { U, U, 4,  U, U, U,  7, U, U },
    { U, U, U,  U, 7, 2,  4, 1, U },

    { 4, 3, U,  U, 1, U,  2, U, U },
    { U, U, U,  U, U, 7,  5, 6, 3 },
    { 2, U, U,  U, U, 5,  U, U, U }};
// @formatter:on

}
