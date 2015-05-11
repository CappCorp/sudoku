package com.cappcorp.sudoku.sample;

public class GridSampleStringRawMedium1 extends GridSampleStringRaw {

    public static final String INITIAL =
// @formatter:off
"    6 357" +
"  38  6  " +
" 4  35   " +
" 94  1   " +
"         " +
"   2  78 " +
"   74  2 " +
"  1  69  " +
"579 1    ";
            // @formatter:on

    public static final String SOLUTION = 
// @formatter:off
"982164357" +
"153827649" +
"746935812" +
"894671235" +
"237458196" +
"615293784" +
"368749521" +
"421586973" +
"579312468";
// @formatter:on

    public GridSampleStringRawMedium1() {
        super(9, INITIAL, SOLUTION);
    }

}
