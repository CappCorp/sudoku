package com.cappcorp.sudoku.model;

public interface WritableGrid {

    void setCellPossibleValues(int row, int col, int... values);

    void removeCellPossibleValues(int row, int col, int... values);

    void removeRowPossibleValues(int row, int... values);

    void removeColumnPossibleValues(int col, int... values);

    void removeBoxPossibleValues(int row, int col, int... values);

}
