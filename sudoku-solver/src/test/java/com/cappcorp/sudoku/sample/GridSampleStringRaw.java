package com.cappcorp.sudoku.sample;

import com.cappcorp.sudoku.model.Universe;
import com.cappcorp.sudoku.reader.StringGridReader;

public abstract class GridSampleStringRaw extends GridSampleSingleType<String> {

    public GridSampleStringRaw(int cardinal, String initial, String solution) {
        this(Universe.fromCardinal(cardinal), initial, solution);
    }

    public GridSampleStringRaw(Universe universe, String initial, String solution) {
        super(universe, new StringGridReader(universe, false, false), initial, solution);
    }

}
