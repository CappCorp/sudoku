package com.cappcorp.sudoku.sample;

import org.junit.Assert;
import org.junit.Test;

import com.cappcorp.sudoku.reader.StringGridReader;
import com.cappcorp.sudoku.resolver.GridResolverImpl;
import com.cappcorp.sudoku.writter.StringGridWriter;

public class GridSampleStringFormattedEasy1 extends GridSample<String, String> {

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
"-------------" +
"|869|452|137|" +
"|713|869|452|" +
"|452|317|986|" +
"-------------" +
"|238|695|741|" +
"|195|274|863|" +
"|674|183|529|" +
"-------------" +
"|546|721|398|" +
"|921|538|674|" +
"|387|946|215|" +
"-------------";
// @formatter:on

    public GridSampleStringFormattedEasy1() {
        super(new StringGridReader(9, false, true), new StringGridWriter(false, true, false), INITIAL, SOLUTION);
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
