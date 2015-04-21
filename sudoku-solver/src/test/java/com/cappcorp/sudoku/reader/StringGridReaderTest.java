package com.cappcorp.sudoku.reader;

import org.junit.Test;

import com.cappcorp.sudoku.model.Grid;
import com.cappcorp.sudoku.model.Universe;
import com.cappcorp.sudoku.writter.StringGridWriter;

public class StringGridReaderTest {

    @Test
    public void readSimpleGrid_isNotMultiLine_hasNotSeparators() {
        readSimpleGrid(false, false);
    }

    @Test
    public void readSimpleGrid_isNotMultiLine_hasSeparators() {
        readSimpleGrid(false, true);
    }

    @Test
    public void readSimpleGrid_isMultiLine_hasNotSeparators() {
        readSimpleGrid(true, false);
    }

    @Test
    public void readSimpleGrid_isMultiLine_hasSeparators() {
        readSimpleGrid(true, true);
    }

    private void readSimpleGrid(boolean isMultiLine, boolean hasSeparators) {
        int[][] values = ReaderTestHelper.buildSimpleIntGrid(4);
        Universe universe = new Universe(4);

        StringBuilder builder = new StringBuilder();
        int lineLength = 4 + 3;
        for (int row = 0; row < 4; row++) {
            if (row % 2 == 0) {
                rowSeparatorLine(isMultiLine, hasSeparators, builder, lineLength);
            }

            for (int col = 0; col < 4; col++) {
                if (col % 2 == 0) {
                    colSeparator(hasSeparators, builder);
                }
                int value = values[row][col];
                if (value == -1) {
                    builder.append(StringGridWriter.SPACE);
                } else {
                    builder.append(universe.map(value));
                }
            }
            colSeparator(hasSeparators, builder);
            endOfLine(isMultiLine, builder);
        }
        rowSeparatorLine(isMultiLine, hasSeparators, builder, lineLength);

        StringGridReader reader = new StringGridReader(4, isMultiLine, hasSeparators);
        Grid grid = reader.readGrid(builder.toString());

        ReaderTestHelper.assertGrid(values, grid);
    }

    private void rowSeparatorLine(boolean isMultiLine, boolean hasSeparators, StringBuilder builder, int lineLength) {
        if (hasSeparators) {
            for (int i = 0; i < lineLength; i++) {
                builder.append(StringGridWriter.ROW_SEPARATOR);
            }
            endOfLine(isMultiLine, builder);
        }
    }

    private void colSeparator(boolean hasSeparators, StringBuilder builder) {
        if (hasSeparators) {
            builder.append(StringGridWriter.COL_SEPARATOR);
        }
    }

    private void endOfLine(boolean isMultiLine, StringBuilder builder) {
        if (isMultiLine) {
            builder.append(System.lineSeparator());
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void wrongSize_exception() {
        StringGridReader reader = new StringGridReader(1, false, false);
        reader.readGrid("012");
    }

    @Test(expected = IllegalArgumentException.class)
    public void wrongValue_exception() {
        StringGridReader reader = new StringGridReader(1, false, false);
        reader.readGrid("T");
    }
}
