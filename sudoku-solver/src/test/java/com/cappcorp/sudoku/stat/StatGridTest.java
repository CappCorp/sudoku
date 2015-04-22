package com.cappcorp.sudoku.stat;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.cappcorp.sudoku.model.Grid;

public class StatGridTest {

    private Grid grid;
    private StatGrid statGrid;
    private long reads;
    private long writes;

    @Before
    public void setUp() {
        grid = Mockito.mock(Grid.class);
        statGrid = new StatGrid(grid);
        reads = statGrid.getReads();
        writes = statGrid.getWrites();
    }

    @After
    public void tearDown() {
        assertThat(statGrid.getReads()).isEqualTo(reads);
        assertThat(statGrid.getWrites()).isEqualTo(writes);
    }

    private void assertRead() {
        assertThat(statGrid.getReads()).isEqualTo(++reads);
    }

    private void assertWrite() {
        assertThat(statGrid.getWrites()).isEqualTo(++writes);
    }

    @Test
    public void getUniverse_noCallback() {
        statGrid.getUniverse();
    }

    @Test
    public void getCellValueIfResolved_noCallback() {
        statGrid.getCellPossibleValues(5, 3);
        assertRead();
    }

    @Test
    public void getCellPossibleValues_noCallback() {
        statGrid.getCellPossibleValues(5, 3);
        assertRead();
    }

    @Test
    public void setCellPossibleValues_callback() {
        statGrid.setCellPossibleValues(5, 3, 1, 2, 7);
        assertWrite();
    }

    @Test
    public void removeCellPossibleValues_callback() {
        statGrid.removeCellPossibleValues(5, 3, 1, 2, 7);
        assertWrite();
    }

    @Test
    public void removeRowPossibleValues_callback() {
        statGrid.removeRowPossibleValues(5, 1, 2, 7);
        assertWrite();
    }

    @Test
    public void removeColumnPossibleValues_callback() {
        statGrid.removeColumnPossibleValues(3, 1, 2, 7);
        assertWrite();
    }

    @Test
    public void removeBoxPossibleValues_callback() {
        statGrid.removeBoxPossibleValues(5, 3, 1, 2, 7);
        assertWrite();
    }

}
