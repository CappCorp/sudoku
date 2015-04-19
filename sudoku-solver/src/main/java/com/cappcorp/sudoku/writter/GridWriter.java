package com.cappcorp.sudoku.writter;

import com.cappcorp.sudoku.model.Grid;

public interface GridWriter<T> {

    T writeGrid(Grid grid);
}
