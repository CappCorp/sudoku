package com.cappcorp.sudoku.test;

import com.cappcorp.sudoku.model.Grid;
import com.cappcorp.sudoku.reader.GridReader;
import com.cappcorp.sudoku.reader.IntArrayGridReader;
import com.cappcorp.sudoku.util.GridHelper;

public class TestIntArrayGrid {

    public static void main(String[] args) {
        GridReader gridReader = new IntArrayGridReader(GridSamples.GRID_9_EASY_1.length, GridSamples.GRID_9_EASY_1);

        Grid grid = gridReader.readGrid();

        System.out.println(GridHelper.displayGrid(grid));
    }
}
