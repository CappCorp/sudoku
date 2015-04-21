package com.cappcorp.sudoku.reader;

import java.util.Random;

import org.junit.Test;

import com.cappcorp.sudoku.model.Grid;

public class IntArrayGridReaderTest {

    @Test
    public void readSimpleGrid() {
        Random random = new Random();

        int[][] values = ReaderTestHelper.buildSimpleIntGrid(4);
        for (int row = 0; row < 4; row++) {
            values[row] = new int[4];
            for (int col = 0; col < 4; col++) {
                values[row][col] = random.nextBoolean() ? random.nextInt(4) : -1;
            }
        }

        IntArrayGridReader reader = new IntArrayGridReader(4);
        Grid grid = reader.readGrid(values);

        ReaderTestHelper.assertGrid(values, grid);
    }

    @Test(expected = IllegalArgumentException.class)
    public void wrongRowNumber_exception() {
        int[][] values = new int[][] { { 0, 1 }, { 2 } };
        IntArrayGridReader reader = new IntArrayGridReader(9);
        reader.readGrid(values);
    }

    @Test(expected = IllegalArgumentException.class)
    public void wrongColNumber_exception() {
        int[][] values = new int[][] { { 0, 1 } };
        IntArrayGridReader reader = new IntArrayGridReader(1);
        reader.readGrid(values);
    }

    @Test(expected = IllegalArgumentException.class)
    public void wrongIntValue_exception() {
        int[][] values = new int[][] { { 12 } };
        IntArrayGridReader reader = new IntArrayGridReader(1);
        reader.readGrid(values);
    }

}
