package com.cappcorp.sudoku.listener;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.cappcorp.sudoku.model.Grid;

public class ListeningGridTest {

    private Grid grid;
    private GridListener gridListener;
    private ListeningGrid listeningGrid;

    @Before
    public void setUp() {
        grid = Mockito.mock(Grid.class);
        gridListener = Mockito.mock(GridListener.class);
        listeningGrid = new ListeningGrid(grid);
        listeningGrid.addListener(gridListener);
    }

    @After
    public void tearDown() {
        Mockito.verifyNoMoreInteractions(gridListener);
    }

    @Test
    public void getUniverse_noCallback() {
        listeningGrid.getUniverse();
    }

    @Test
    public void getCellValueIfResolved_noCallback() {
        listeningGrid.getCellPossibleValues(5, 3);
    }

    @Test
    public void getCellPossibleValues_noCallback() {
        listeningGrid.getCellPossibleValues(5, 3);
    }

    @Test
    public void setCellPossibleValues_callback() {
        listeningGrid.setCellPossibleValues(5, 3, 1, 2, 7);
        Mockito.verify(gridListener).onSetCellPossibleValues(5, 3, 1, 2, 7);
    }

    @Test
    public void removeCellPossibleValues_callback() {
        listeningGrid.removeCellPossibleValues(5, 3, 1, 2, 7);
        Mockito.verify(gridListener).onRemoveCellPossibleValues(5, 3, 1, 2, 7);
    }

    @Test
    public void getRowUnresolvedValues_noCallback() {
        listeningGrid.getRowUnresolvedValues(5);
    }

    @Test
    public void removeRowPossibleValues_callback() {
        listeningGrid.removeRowPossibleValues(5, 1, 2, 7);
        Mockito.verify(gridListener).onRemoveRowPossibleValues(5, 1, 2, 7);
    }

    @Test
    public void getColumnUnresolvedValues_noCallback() {
        listeningGrid.getColumnUnresolvedValues(3);
    }

    @Test
    public void removeColumnPossibleValues_callback() {
        listeningGrid.removeColumnPossibleValues(3, 1, 2, 7);
        Mockito.verify(gridListener).onRemoveColumnPossibleValues(3, 1, 2, 7);
    }

    @Test
    public void getBoxUnresolvedValues_noCallback() {
        listeningGrid.getBoxUnresolvedValues(5, 3);
    }

    @Test
    public void removeBoxPossibleValues_callback() {
        listeningGrid.removeBoxPossibleValues(5, 3, 1, 2, 7);
        Mockito.verify(gridListener).onRemoveBoxPossibleValues(5, 3, 1, 2, 7);
    }

}
