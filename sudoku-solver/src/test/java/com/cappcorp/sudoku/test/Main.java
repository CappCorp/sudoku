package com.cappcorp.sudoku.test;

import com.cappcorp.sudoku.model.Grid;
import com.cappcorp.sudoku.reader.GridReader;
import com.cappcorp.sudoku.reader.IntArrayGridReader;
import com.cappcorp.sudoku.resolver.GridResolver;
import com.cappcorp.sudoku.resolver.GridResolverImpl;
import com.cappcorp.sudoku.stat.StatGrid;
import com.cappcorp.sudoku.util.GridHelper;

public class Main {

    public static void main(String[] args) {
        GridReader gridReader = new IntArrayGridReader(GridSamples.GRID_9_EASY_1.length, GridSamples.GRID_9_EASY_1);

        Grid grid = gridReader.readGrid();

        System.out.println(GridHelper.displayGrid(grid));

        StatGrid statGrid = new StatGrid(grid);

        GridResolver resolver = new GridResolverImpl();
        resolver.resolveGrid(statGrid);

        System.out.println(GridHelper.displayGrid(grid));

        System.out.println("Reads: " + statGrid.getReads());
        System.out.println("Writes: " + statGrid.getWrites());
    }
}
