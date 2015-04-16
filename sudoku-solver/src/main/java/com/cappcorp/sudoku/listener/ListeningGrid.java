package com.cappcorp.sudoku.listener;

public interface ListeningGrid {

    void addListener(GridListener listener);

    void removeListener(GridListener listener);
}
