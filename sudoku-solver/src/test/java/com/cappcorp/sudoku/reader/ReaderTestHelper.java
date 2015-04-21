package com.cappcorp.sudoku.reader;

import java.util.Random;

import org.junit.Assert;

import com.cappcorp.sudoku.model.Grid;

public enum ReaderTestHelper {
    ;
    public static int[][] buildSimpleIntGrid(int cardinal) {
        Random random = new Random();

        int[][] values = new int[cardinal][cardinal];

        for (int row = 0; row < cardinal; row++) {
            values[row] = new int[cardinal];

            for (int col = 0; col < cardinal; col++) {
                if (random.nextBoolean()) {
                    values[row][col] = random.nextInt(cardinal);
                } else {
                    values[row][col] = -1;
                }
            }
        }

        return values;
    }

    public static void assertGrid(int[][] expectedValues, Grid grid) {
        int cardinal = grid.getUniverse().getCardinal();
        for (int row = 0; row < cardinal; row++) {
            for (int col = 0; col < cardinal; col++) {
                Integer cellValueIfResolved = grid.getCellValueIfResolved(row, col);
                int value = expectedValues[row][col];
                if (value == -1) {
                    Assert.assertFalse(grid.isResolved(row, col));
                } else {
                    Assert.assertEquals("Wrong value for cell[row=" + row + ", col=" + col + "]", value, cellValueIfResolved.intValue());
                }
            }
        }

    }
}
