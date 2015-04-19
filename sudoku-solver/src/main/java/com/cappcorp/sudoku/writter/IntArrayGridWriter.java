package com.cappcorp.sudoku.writter;

import com.cappcorp.sudoku.model.Grid;
import com.cappcorp.sudoku.model.Universe;

public class IntArrayGridWriter implements GridWriter<int[][]> {

    @Override
    public int[][] writeGrid(Grid grid) {
        Universe universe = grid.getUniverse();
        int cardinal = universe.getCardinal();
        int[][] values = new int[cardinal][cardinal];

        for (int row = 0; row < cardinal; row++) {
            values[row] = new int[cardinal];

            for (int col = 0; col < cardinal; col++) {
                Integer valueIfResolved = grid.getCellValueIfResolved(row, col);
                int intValue = valueIfResolved == null ? -1 : valueIfResolved.intValue();
                values[row][col] = intValue;
            }
        }
        return values;
    }

}
