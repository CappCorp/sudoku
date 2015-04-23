package com.cappcorp.sudoku.sample;

import org.junit.Test;

import com.cappcorp.sudoku.resolver.GridResolverImpl;

public class GridSampleStringRawSuper1 extends GridSampleStringRaw {

    public static final String INITIAL =
// @formatter:off
"   E AB     479 " +
"     9  23DB1 F " +
"5 C7 1 8  F   6 " +
"3 2  C  5 E1    " +
"7CF2    0E   B  " +
"       B  8 A  D" +
"       6 9    07" +
"46 30 1F D 5    " +
"    E C 85 2F 70" +
"24    0 B       " +
"1  F 2  6       " +
"  6   D1    C8B2" +
"    50 A  7  1 C" +
" B   3  D C 04 A" +
" 3 86B49  5     " +
" AE5     03 6   ";
// @formatter:on

    public static final String SOLUTION = 
// @formatter:off
"DF1E3AB2C8604795" +
"A846795023DB1CFE" +
"50C7D1E89AF4B263" +
"392BFC6457E1D0A8" +
"7CF295AD0E438B16" +
"0E59C72BF186A34D" +
"81BD4E36A92C5F07" +
"46A3081F7DB52EC9" +
"BD9AE4C38512F670" +
"243C8607BFAE95D1" +
"178FB2956C0DEA34" +
"E560AFD13491C8B2" +
"62D450FAEB79318C" +
"9B71238ED6CF045A" +
"C3086B49125A7DEF" +
"FAE51D7C4038692B";
// @formatter:on

    public GridSampleStringRawSuper1() {
        super(16, INITIAL, SOLUTION);
    }

    @Test
    public void withGridResolverImpl() {
        resolveAndAssert(new GridResolverImpl());
    }
}
