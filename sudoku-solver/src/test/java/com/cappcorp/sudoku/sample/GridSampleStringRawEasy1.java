package com.cappcorp.sudoku.sample;

public class GridSampleStringRawEasy1 extends GridSampleStringRaw {

    public static final String INITIAL =
// @formatter:off
"   4    7" +
"7138     " +
"  2 1  86" +
" 3869    " +
"  5   8  " +
"    8352 " +
"54  2 3  " +
"     8674" +
"3    6   ";
// @formatter:on

    public static final String SOLUTION = 
// @formatter:off
"869452137" +
"713869452" +
"452317986" +
"238695741" +
"195274863" +
"674183529" +
"546721398" +
"921538674" +
"387946215" ;
// @formatter:on

    public GridSampleStringRawEasy1() {
        super(9, INITIAL, SOLUTION);
    }

}
