package com.cappcorp.sudoku.reader;

import com.cappcorp.sudoku.model.Grid;

public interface GridReader<T> {

    Grid readGrid(T grid);
}
