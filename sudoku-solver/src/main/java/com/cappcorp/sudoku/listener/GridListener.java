package com.cappcorp.sudoku.listener;

public interface GridListener {

    void onSetPossibleValues(int row, int col, int[] values);

    void onRemoveRowPossibleValues(int row, int[] values);

    void onRemoveColumnPossibleValues(int col, int[] values);

    void onRemoveBoxPossibleValues(int row, int col, int[] values);

    void onSetCellValues(int row, int col, int[] values);

    void onRemoveCellPossibleValues(int row, int col, int[] values);

}
