package com.cappcorp.sudoku.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

import com.cappcorp.sudoku.model.Universe;

public class CellKey implements Comparable<CellKey> {

    public static class CellKeys {

        private final int cardinal;
        private final int sqrt;
        private final CellKey[][] keys;

        public CellKeys(Universe universe) {
            this.cardinal = universe.getCardinal();
            this.sqrt = universe.getSqrt();
            this.keys = new CellKey[cardinal][cardinal];

            IntStream.range(0, cardinal).forEach(row -> IntStream.range(0, cardinal).forEach(col -> keys[row][col] = new CellKey(row, col)));
        }

        public CellKey getKey(int row, int col) {
            return keys[row][col];
        }

        public List<CellKey> getRowKeys(int row) {
            return Arrays.asList(keys[row]);
        }

        public List<CellKey> getColKeys(int col) {
            List<CellKey> colKeys = new ArrayList<>(cardinal);
            for (CellKey[] rowKeys : keys) {
                colKeys.add(rowKeys[col]);
            }
            return Collections.unmodifiableList(colKeys);
        }

        public List<CellKey> getBoxKeys(int box) {
            List<CellKey> boxKeys = new ArrayList<>(cardinal);
            int boxTopRow = GridHelper.computeBoxTopRowFromBox(box, sqrt);
            int boxTopCol = GridHelper.computeBoxTopColFromBox(box, sqrt);
            for (int row = boxTopRow; row < boxTopRow + sqrt; row++) {
                for (int col = boxTopCol; col < boxTopCol + sqrt; col++) {
                    boxKeys.add(keys[row][col]);
                }
            }
            return Collections.unmodifiableList(boxKeys);

        }
    }

    private final int row;
    private final int col;

    private CellKey(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + col;
        result = prime * result + row;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        CellKey other = (CellKey) obj;
        if (col != other.col)
            return false;
        if (row != other.row)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "CellKey [row=" + row + ", col=" + col + "]";
    }

    @Override
    public int compareTo(CellKey other) {
        return row == other.row ? col - other.col : row - other.row;
    }

}
