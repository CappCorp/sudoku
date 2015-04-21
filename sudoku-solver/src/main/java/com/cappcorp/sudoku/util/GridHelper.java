package com.cappcorp.sudoku.util;

import com.cappcorp.sudoku.model.Grid;
import com.cappcorp.sudoku.writter.StringGridWriter;

public enum GridHelper {
    ;

    private static final StringGridWriter WRITER = new StringGridWriter(true, true, true);

    public static int computeBoxNumber(int sqrt, int row, int col) {
        return (row / sqrt) * sqrt + (col / sqrt);
    }

    public static int computeBoxPosition(int sqrt, int row, int col) {
        return (row % sqrt) * sqrt + col % sqrt;
    }

    public static int computeBoxTopRowFromBox(int box, int sqrt) {
        return box / sqrt;
    }

    public static int computeBoxTopRowFromRow(int sqrt, int row) {
        return (row / sqrt) * sqrt;
    }

    public static int computeBoxTopColFromBox(int box, int sqrt) {
        return (box % sqrt) * sqrt;
    }

    public static int computeBoxTopColFromCol(int sqrt, int col) {
        return (col / sqrt) * sqrt;
    }

    public static int computeRow(int index, int cardinal) {
        return index / cardinal;
    }

    public static int computeCol(int index, int cardinal) {
        return index % cardinal;
    }

    public static String displayGrid(Grid grid) {
        return WRITER.writeGrid(grid);
    }
}
