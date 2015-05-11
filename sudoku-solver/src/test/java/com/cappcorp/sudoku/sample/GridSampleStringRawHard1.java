package com.cappcorp.sudoku.sample;

public class GridSampleStringRawHard1 extends GridSampleStringRaw {

    public static final String INITIAL =
// @formatter:off
" 1   9   " +
"  54     " +
"4   5 7 2" +
"548  3   " +
"  1   5  " +
"   5  298" +
"6 4 2   5" +
"     16  " +
"   7   2 ";
// @formatter:on

    public static final String SOLUTION = 
// @formatter:off
"217839456" +
"365472981" +
"489156732" +
"548293167" +
"921687543" +
"736514298" +
"674928315" +
"852341679" +
"193765824";
// @formatter:on

    public GridSampleStringRawHard1() {
        super(9, INITIAL, SOLUTION);
    }

}
