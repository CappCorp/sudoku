package com.cappcorp.sudoku.sample;

import org.junit.Test;

import com.cappcorp.sudoku.reader.IntArrayGridReader;
import com.cappcorp.sudoku.resolver.GridResolverImpl;

public class GridSampleIntArrayEasy1 extends GridSampleIntArray {

    private static final int U = IntArrayGridReader.UNKNOWN;

    public static final int[][] INITIAL = new int[][] {
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

    public static final int[][] SOLUTION = new int[][] {
// @formatter:off
    { 7, 5, 8,  3, 4, 1,  0, 2, 6 },
    { 6, 0, 2,  7, 5, 8,  3, 4, 1 },
    { 3, 4, 1,  2, 0, 6,  8, 7, 5 },

    { 1, 2, 7,  5, 8, 4,  6, 3, 0 },
    { 0, 8, 4,  1, 6, 3,  7, 5, 2 },
    { 5, 6, 3,  0, 7, 2,  4, 1, 8 },

    { 4, 3, 5,  6, 1, 0,  2, 8, 7 },
    { 8, 1, 0,  4, 2, 7,  5, 6, 3 },
    { 2, 7, 6,  8, 3, 5,  1, 0, 4 }};
// @formatter:on

    public GridSampleIntArrayEasy1() {
        super(9, INITIAL, SOLUTION);
    }

    @Test
    public void withGridResolverImpl() {
        resolveAndAssert(new GridResolverImpl());
    }
}
