package com.cappcorp.sudoku.model;

import java.util.Set;

public interface Grid {

    Universe getUniverse();

    boolean isResolved(int row, int col);

    Integer getCellValueIfResolved(int row, int col);

    Set<Integer> getCellPossibleValues(int row, int col);

    void setCellPossibleValues(int row, int col, int... values);

    void removeCellPossibleValues(int row, int col, int... values);

    Set<Integer> getRowUnresolvedValues(int row);

    void removeRowPossibleValues(int row, int... values);

    Set<Integer> getColumnUnresolvedValues(int col);

    void removeColumnPossibleValues(int col, int... values);

    Set<Integer> getBoxUnresolvedValues(int row, int col);

    void removeBoxPossibleValues(int row, int col, int... values);

}