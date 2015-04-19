package com.cappcorp.sudoku.test;

import com.cappcorp.sudoku.resolver.GridResolver;
import com.cappcorp.sudoku.resolver.GridResolverImpl;
import com.cappcorp.sudoku.sample.GridSample;
import com.cappcorp.sudoku.sample.GridSampleEasy1;
import com.cappcorp.sudoku.stat.StatGrid;
import com.cappcorp.sudoku.util.GridHelper;

public class Main {

    public static void main(String[] args) {
        GridSample<int[][], int[][]> sampleEasy1 = new GridSampleEasy1();

        System.out.println(GridHelper.displayGrid(sampleEasy1.getInitialGrid()));

        GridResolver resolver = new GridResolverImpl();
        StatGrid statGrid = sampleEasy1.resolveAndAssert(resolver);
        long reads = statGrid.getReads();
        long writes = statGrid.getWrites();

        System.out.println(GridHelper.displayGrid(statGrid));

        System.out.println("Reads: " + reads);
        System.out.println("Writes: " + writes);
    }
}
