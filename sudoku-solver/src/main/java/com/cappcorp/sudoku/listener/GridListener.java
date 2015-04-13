package com.cappcorp.sudoku.listener;

public interface GridListener {

    void onRemoveRowPossibleValues(int row, int... values);

    void onRemoveColumnPossibleValues(int col, int... values);

    void onRemoveBoxPossibleValues(int row, int col, int... values);

    void onSetCellPossibleValues(int row, int col, int... values);

    void onRemoveCellPossibleValues(int row, int col, int... values);

}
