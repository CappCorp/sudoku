package com.cappcorp.sudoku.test;

import com.cappcorp.sudoku.resolver.GridResolver;
import com.cappcorp.sudoku.resolver.GridResolverG2;
import com.cappcorp.sudoku.sample.GridSample;
import com.cappcorp.sudoku.sample.GridSampleIntArrayEasy1;
import com.cappcorp.sudoku.stat.StatGrid;
import com.cappcorp.sudoku.util.GridHelper;

public class Main {

    public static void main(String[] args) {
        GridSample<int[][], int[][]> sampleEasy1 = new GridSampleIntArrayEasy1();

        System.out.println(GridHelper.displayGrid(sampleEasy1.getInitialGrid()));

        GridResolver resolver = new GridResolverG2();
        StatGrid statGrid = sampleEasy1.resolveAndAssert(resolver);
        long reads = statGrid.getReads();
        long writes = statGrid.getWrites();

        System.out.println(GridHelper.displayGrid(statGrid));

        System.out.println("Reads: " + reads);
        System.out.println("Writes: " + writes);
    }
}
