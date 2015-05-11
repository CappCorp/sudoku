package com.cappcorp.sudoku.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

import org.junit.Assert;
import org.junit.Test;

import com.cappcorp.sudoku.model.ReadableGrid;
import com.cappcorp.sudoku.model.Universe;
import com.cappcorp.sudoku.reader.StringGridReader;
import com.cappcorp.sudoku.util.CellKey.CellKeys;
import com.cappcorp.sudoku.util.GridCheckerTest.CheckValidAssertPattern.Type;

public class GridCheckerTest {

    private static final String GRID_FILLED_4_4 = "1234" + "3421" + "2143" + "4312";

    private static final String GRID_WITH_GAPS_4_4;

    static {
        StringBuilder builder = new StringBuilder(GRID_FILLED_4_4);
        builder.setCharAt(1, Universe.BLANK);
        builder.setCharAt(2, Universe.BLANK);
        builder.setCharAt(3, Universe.BLANK);
        builder.setCharAt(5, Universe.BLANK);
        builder.setCharAt(7, Universe.BLANK);
        builder.setCharAt(11, Universe.BLANK);
        builder.setCharAt(13, Universe.BLANK);
        GRID_WITH_GAPS_4_4 = builder.toString();
    }

    private static abstract class AssertPattern {

        abstract void verify(List<String> errorLines);
    }

    static final class CheckValidAssertPattern extends AssertPattern {

        static enum Type {

            ROW("row"), COL("col"), BOX("box");

            Type(String display) {
                this.display = display;
            }

            String display;
        }

        final Type type;
        final int number;
        final char value;
        final int times;

        CheckValidAssertPattern(Type type, int number, char value, int times) {
            this.type = type;
            this.number = number;
            this.value = value;
            this.times = times;
        }

        @Override
        void verify(List<String> errorLines) {
            String expectedLine = "Error in " + type.display + " " + number + ", value used more than once: " + value;

            IntStream.range(0, times).forEach(
                    time -> Assert.assertTrue("Pattern [" + expectedLine + "] could not be found more than " + time + " times.",
                            errorLines.remove(expectedLine)));
        }
    }

    static final class CheckConditionAssertPattern extends AssertPattern {

        private final int row;
        private final int col;
        private final Character expected;
        private final Character actual;

        CheckConditionAssertPattern(int row, int col, Character expected, Character actual) {
            this.row = row;
            this.col = col;
            this.expected = expected;
            this.actual = actual;
        }

        @Override
        void verify(List<String> errorLines) {
            String expectedLine = "Error in " + "row=" + row + ", col=" + col + ": " + "expected [";
            expectedLine += expected == null ? "NONE" : expected.charValue();
            expectedLine += "] but was [";
            expectedLine += actual == null ? "NONE" : actual.charValue();
            expectedLine += "]";

            Assert.assertTrue("Pattern [" + expectedLine + "] could not be found", errorLines.remove(expectedLine));
        }
    }

    @Test
    public void checkIsValid_validGrids() {
        assertPatterns(checkIsValid("1"));
        assertPatterns(checkIsValid("1               "));
        assertPatterns(checkIsValid(GRID_FILLED_4_4));
        assertPatterns(checkIsValid(GRID_WITH_GAPS_4_4));
    }

    @Test
    public void checkIsValid_invalidGrid_row1_2occ() {
        assertPatterns(checkIsValid("1 1 " + "    " + "    " + "    "), new CheckValidAssertPattern(Type.ROW, 1, '1', 1));
    }

    @Test
    public void checkIsValid_invalidGrid_col2_2occ() {
        assertPatterns(checkIsValid(" 3  " + "    " + " 3  " + "    "), new CheckValidAssertPattern(Type.COL, 2, '3', 1));
    }

    @Test
    public void checkIsValid_invalidGrid_box3_2occ() {
        assertPatterns(checkIsValid("    " + "    " + " 2  " + "2   "), new CheckValidAssertPattern(Type.BOX, 3, '2', 1));
    }

    @Test
    public void checkIsValid_invalidGrid_multi() {
        assertPatterns(checkIsValid("44  " + " 4  " + "    " + "    "), new CheckValidAssertPattern(Type.ROW, 1, '4', 1),

        new CheckValidAssertPattern(Type.COL, 2, '4', 1),

        new CheckValidAssertPattern(Type.BOX, 1, '4', 2));
    }

    private String checkIsValid(String gridString) {
        Universe universe = computeUniverse(gridString);
        GridChecker gridChecker = new GridChecker(universe, new CellKeys(universe));
        return gridChecker.checkIsValid(readGrid(gridString, universe));
    }

    @Test
    public void checkContains_containedGrids_4by4filledIncrementally() {
        int cardinal = GRID_FILLED_4_4.length();
        List<Integer> intList = new ArrayList<>();
        IntStream.range(0, cardinal).forEach(index -> intList.add(Integer.valueOf(index)));
        Collections.shuffle(intList);
        char[] chars = new char[cardinal];
        Arrays.fill(chars, Universe.BLANK);
        StringBuilder gridStringBuilder = new StringBuilder(new String(chars));

        intList.stream().mapToInt(index -> index.intValue()).forEach(index -> checkContains_containedGrid(GRID_FILLED_4_4, gridStringBuilder, index));
    }

    private void checkContains_containedGrid(String containerString, StringBuilder containeeStringBuilder, int index) {
        containeeStringBuilder.setCharAt(index, containerString.charAt(index));
        assertPatterns(checkContains(containerString, containeeStringBuilder.toString()));
    }

    @Test
    public void checkContains_notContained() {
        String container = "1234" + "    " + "  2 " + "    ";
        String containee = "123 " + " 4  " + "  4 " + "   1";

        assertPatterns(checkContains(container, containee),

        new CheckConditionAssertPattern(2, 2, null, '4'),

        new CheckConditionAssertPattern(3, 3, '2', '4'),

        new CheckConditionAssertPattern(4, 4, null, '1')

        );
    }

    private String checkContains(String containerString, String containeeString) {
        Assert.assertEquals(containerString.length(), containeeString.length());
        Universe universe = computeUniverse(containerString);
        ReadableGrid container = readGrid(containerString, universe);
        ReadableGrid containee = readGrid(containeeString, universe);
        GridChecker gridChecker = new GridChecker(universe, new CellKeys(universe));
        return gridChecker.checkContains(container, containee);
    }

    @Test
    public void checkAreEqual_equal() {
        assertPatterns(checkAreEqual(GRID_FILLED_4_4, GRID_FILLED_4_4));
        assertPatterns(checkAreEqual(GRID_WITH_GAPS_4_4, GRID_WITH_GAPS_4_4));
    }

    @Test
    public void checkAreEqual_notEqual() {
        String grid1 = "1234" + "    " + "  2 " + "    ";
        String grid2 = "123 " + " 4  " + "  4 " + "   1";

        assertPatterns(checkAreEqual(grid1, grid2),

        new CheckConditionAssertPattern(1, 4, '4', null),

        new CheckConditionAssertPattern(2, 2, null, '4'),

        new CheckConditionAssertPattern(3, 3, '2', '4'),

        new CheckConditionAssertPattern(4, 4, null, '1')

        );
    }

    private String checkAreEqual(String expectedString, String actualString) {
        Assert.assertEquals(expectedString.length(), actualString.length());
        Universe universe = computeUniverse(expectedString);
        ReadableGrid expected = readGrid(expectedString, universe);
        ReadableGrid actual = readGrid(actualString, universe);
        GridChecker gridChecker = new GridChecker(universe, new CellKeys(universe));
        return gridChecker.checkAreEqual(expected, actual);
    }

    private void assertPatterns(String errorMessage, AssertPattern... assertPatterns) {
        if (assertPatterns == null || assertPatterns.length == 0) {
            Assert.assertNull(errorMessage);
        } else {
            Assert.assertNotNull(errorMessage);
            String[] errorLinesArray = errorMessage.split(System.lineSeparator());
            List<String> errorLines = new ArrayList<String>(Arrays.asList(errorLinesArray));
            for (AssertPattern assertPattern : assertPatterns) {
                assertPattern.verify(errorLines);
            }
            Assert.assertTrue("Error lines not asserted: " + errorLines, errorLines.isEmpty());
        }
    }

    Universe computeUniverse(String gridString) {
        int cardinal = (int) Math.sqrt(gridString.length());
        return new Universe(cardinal);
    }

    private ReadableGrid readGrid(String gridString, Universe universe) {
        StringGridReader gridReader = new StringGridReader(universe, false, false);
        return gridReader.readGrid(gridString);
    }
}
