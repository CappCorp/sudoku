package com.cappcorp.sudoku.model;

import static org.assertj.core.api.Assertions.assertThat;
import mockit.Deencapsulation;

import org.junit.Test;
import org.mockito.Mockito;

public class GroupTest {

    @Test
    public void setCell_expectCell() {
        Group group = new Group(5);
        Cell cell = new Cell(5);

        group.setCell(3, cell);
        Cell[] cells = Deencapsulation.getField(group, "cells");
        assertThat(cells[3]).isEqualTo(cell);
    }

    @Test
    public void removePossibleValues_expectCallOnCellsAndPossibleValues() {
        Group group = new Group(1);

        Cell cell = Mockito.mock(Cell.class);
        Mockito.when(cell.isResolved()).thenReturn(false);
        group.setCell(0, cell);

        group.removePossibleValues(0);
        Mockito.verify(cell).isResolved();
        Mockito.verify(cell).removePossibleValues(0);
    }

}
