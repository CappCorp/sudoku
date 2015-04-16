package com.cappcorp.sudoku.resolver;

public class CellKey {

    public static class CellKeys {

        private final CellKey[][] keys;

        public CellKeys(int cardinal) {
            this.keys = new CellKey[cardinal][cardinal];

            for (int row = 0; row < cardinal; row++) {
                for (int col = 0; col < cardinal; col++) {
                    keys[row][col] = new CellKey(row, col);
                }
            }
        }

        public CellKey getKey(int row, int col) {
            return keys[row][col];
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

}
