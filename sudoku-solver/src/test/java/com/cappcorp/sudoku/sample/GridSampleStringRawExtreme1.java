package com.cappcorp.sudoku.sample;

import org.junit.Test;

import com.cappcorp.sudoku.resolver.GridResolverImpl;

public class GridSampleStringRawExtreme1 extends GridSampleStringRaw {

    public static final String INITIAL =
// @formatter:off
"7       1" +
"       5 " +
" 8  52 4 " +
"  89 31  " +
"1       6" +
"  46 78  " +
" 2 54  3 " +
" 5       " +
"9       7";
// @formatter:on

    public static final String SOLUTION = 
// @formatter:off
"795364281" +
"412879653" +
"386152749" +
"568923174" +
"179485326" +
"234617895" +
"627541938" +
"853796412" +
"941238567";
// @formatter:on

    public GridSampleStringRawExtreme1() {
        super(9, INITIAL, SOLUTION);
    }

    @Test
    public void withGridResolverImpl() {
        resolveAndAssert(new GridResolverImpl());
    }
}
