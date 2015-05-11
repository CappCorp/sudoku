package com.cappcorp.sudoku.sample;

public class GridSampleStringRawHell1 extends GridSampleStringRaw {

    public static final String INITIAL =
// @formatter:off
"    6   5" +
"   9 2 4 " +
"3 24  6  " +
" 87 4  1 " +
"         " +
" 6  3 57 " +
"  8  42 9" +
" 4 3 1   " +
"5   7    " ;
// @formatter:on

    public static final String SOLUTION = 
// @formatter:off
"491763825" +
"856912347" +
"372485691" +
"287546913" +
"135297486" +
"964138572" +
"718654239" +
"649321758" +
"523879164" ;
// @formatter:on

    public GridSampleStringRawHell1() {
        super(9, INITIAL, SOLUTION);
    }

}
