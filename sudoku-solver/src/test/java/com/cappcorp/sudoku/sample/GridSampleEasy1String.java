package com.cappcorp.sudoku.sample;

import org.junit.Assert;
import org.junit.Test;

import com.cappcorp.sudoku.reader.StringGridReader;
import com.cappcorp.sudoku.resolver.GridResolverImpl;
import com.cappcorp.sudoku.writter.StringGridWriter;

public class GridSampleEasy1String extends GridSample<String, String> {

    private static final String LINE_SEP = System.lineSeparator();

    public static final String INITIAL =

// @formatter:off
"-------------" +
"|   |4  |  7|" +
"|713|8  |   |" +
"|  2| 1 | 86|" +
"-------------" +
"| 38|69 |   |" +
"|  5|   |8  |" +
"|   | 83|52 |" +
"-------------" +
"|54 | 2 |3  |" +
"|   |  8|674|" +
"|3  |  6|   |" +
"-------------";

// @formatter:on

    public static final String SOLUTION = 
// @formatter:off
"-------------" + LINE_SEP +
"|869|452|137|" + LINE_SEP +
"|713|869|452|" + LINE_SEP +
"|452|317|986|" + LINE_SEP +
"-------------" + LINE_SEP +
"|238|695|741|" + LINE_SEP +
"|195|274|863|" + LINE_SEP +
"|674|183|529|" + LINE_SEP +
"-------------" + LINE_SEP +
"|546|721|398|" + LINE_SEP +
"|921|538|674|" + LINE_SEP +
"|387|946|215|" + LINE_SEP +
"-------------" + LINE_SEP;
// @formatter:on

    public GridSampleEasy1String() {
        super(new StringGridReader(9, false, true), new StringGridWriter(), INITIAL, SOLUTION);
    }

    @Override
    protected void assertEqual(String solution, String output) {
        Assert.assertEquals(solution, output);
    }

    @Test
    public void withGridResolverImpl() {
        resolveAndAssert(new GridResolverImpl());
    }
}
