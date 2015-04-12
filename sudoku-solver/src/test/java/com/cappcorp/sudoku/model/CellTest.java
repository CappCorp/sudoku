package com.cappcorp.sudoku.model;

import mockit.Deencapsulation;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class CellTest {

    private static final int CARDINAL = 5;
    private Cell cell;
    private PossibleValues possibleValues;

    @Before
    public void setUp() {
        cell = new Cell(CARDINAL);
        possibleValues = Mockito.mock(PossibleValues.class);
        Deencapsulation.setField(cell, possibleValues);
    }

    @Test
    public void getPossibleValues() {
        cell.getPossibleValues();
        Mockito.verify(possibleValues).getPossibleValues();
    }

    @Test
    public void setPossibleValues() {
        cell.setPossibleValues(0, 1, 3);
        Mockito.verify(possibleValues).setValues(0, 1, 3);
    }

    @Test
    public void removePossibleValues() {
        cell.removePossibleValues(0, 1, 3);
        Mockito.verify(possibleValues).removeValues(0, 1, 3);
    }

    @Test
    public void getValueIfResoved() {
        cell.getValueIfResolved();
        Mockito.verify(possibleValues).getValueIfResolved();
    }

    @Test
    public void isResoved() {
        cell.isResolved();
        Mockito.verify(possibleValues).isResolved();
    }
}
