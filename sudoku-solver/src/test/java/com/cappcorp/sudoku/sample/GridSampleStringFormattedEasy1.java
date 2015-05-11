package com.cappcorp.sudoku.sample;

import com.cappcorp.sudoku.model.Universe;
import com.cappcorp.sudoku.reader.StringGridReader;

public class GridSampleStringFormattedEasy1 extends GridSampleSingleType<String> {

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
        super(Universe.fromCardinal(9), new StringGridReader(Universe.fromCardinal(9), false, true), INITIAL, SOLUTION);
    }

}