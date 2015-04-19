package com.cappcorp.sudoku.writter;

import com.cappcorp.sudoku.model.Grid;
import com.cappcorp.sudoku.util.GridHelper;

public class StringGridWriter implements GridWriter<String> {

    @Override
    public String writeGrid(Grid grid) {
        return GridHelper.displayGrid(grid);
    }

}
