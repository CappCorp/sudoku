package com.cappcorp.sudoku.sample;

import org.junit.Assert;

import com.cappcorp.sudoku.model.Universe;
import com.cappcorp.sudoku.reader.IntArrayGridReader;
import com.cappcorp.sudoku.writter.IntArrayGridWriter;

public class GridSampleIntArray extends GridSample<int[][], int[][]> {

    public GridSampleIntArray(int cardinal, int[][] initial, int[][] solution) {
        super(new IntArrayGridReader(new Universe(cardinal)), new IntArrayGridWriter(), initial, solution);
    }

    public GridSampleIntArray(char[] characters, int[][] initial, int[][] solution) {
        super(new IntArrayGridReader(new Universe(characters)), new IntArrayGridWriter(), initial, solution);
    }

    public GridSampleIntArray(Universe universe, int[][] initial, int[][] solution) {
        super(new IntArrayGridReader(universe), new IntArrayGridWriter(), initial, solution);
    }

    @Override
    protected void assertEqual(int[][] solution, int[][] output) {
        int cardinal = solution.length;
        for (int row = 0; row < cardinal; row++) {
            for (int col = 0; col < cardinal; col++) {
                Assert.assertEquals("Wrong value for cell[row=" + row + ", col=" + col + "]", solution[row][col], output[row][col]);
            }
        }
    }

}
