package com.cappcorp.sudoku.writter;

import com.cappcorp.sudoku.model.Grid;
import com.cappcorp.sudoku.model.Universe;

public class CharArrayGridWriter implements GridWriter<char[][]> {

    @Override
    public char[][] writeGrid(Grid grid) {
        Universe universe = grid.getUniverse();
        int cardinal = universe.getCardinal();
        char[][] values = new char[cardinal][cardinal];

        for (int row = 0; row < cardinal; row++) {
            values[row] = new char[cardinal];

            for (int col = 0; col < cardinal; col++) {
                Integer valueIfResolved = grid.getCellValueIfResolved(row, col);
                char charValue = valueIfResolved == null ? Universe.BLANK : universe.map(valueIfResolved.intValue());
                values[row][col] = charValue;
            }
        }
        return values;
    }

}
