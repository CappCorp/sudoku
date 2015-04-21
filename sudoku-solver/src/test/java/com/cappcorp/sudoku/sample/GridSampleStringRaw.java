package com.cappcorp.sudoku.sample;

import org.junit.Assert;

import com.cappcorp.sudoku.model.Universe;
import com.cappcorp.sudoku.reader.StringGridReader;
import com.cappcorp.sudoku.writter.StringGridWriter;

public class GridSampleStringRaw extends GridSample<String, String> {

    public GridSampleStringRaw(int cardinal, String initial, String solution) {
        super(new StringGridReader(new Universe(cardinal), false, false), new StringGridWriter(false, false, false), initial, solution);
    }

    public GridSampleStringRaw(char[] characters, String initial, String solution) {
        super(new StringGridReader(new Universe(characters), false, false), new StringGridWriter(false, false, false), initial, solution);
    }

    public GridSampleStringRaw(Universe universe, String initial, String solution) {
        super(new StringGridReader(universe, false, false), new StringGridWriter(false, false, false), initial, solution);
    }

    @Override
    protected void assertEqual(String solution, String output) {
        Assert.assertEquals(solution, output);

    }

}
