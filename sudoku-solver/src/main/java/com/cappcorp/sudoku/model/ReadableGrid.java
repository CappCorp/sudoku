package com.cappcorp.sudoku.model;

import java.util.Set;

public interface ReadableGrid {

    Universe getUniverse();

    boolean isResolved(int row, int col);

    Integer getCellValueIfResolved(int row, int col);

    Set<Integer> getCellPossibleValues(int row, int col);

}
