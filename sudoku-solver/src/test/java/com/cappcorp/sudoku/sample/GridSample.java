package com.cappcorp.sudoku.sample;

import org.junit.Assert;
import org.junit.Test;

import com.cappcorp.sudoku.model.Grid;
import com.cappcorp.sudoku.model.Universe;
import com.cappcorp.sudoku.reader.GridReader;
import com.cappcorp.sudoku.resolver.GridResolver;
import com.cappcorp.sudoku.resolver.GridResolverImpl;
import com.cappcorp.sudoku.stat.StatGrid;
import com.cappcorp.sudoku.util.CellKey.CellKeys;
import com.cappcorp.sudoku.util.GridChecker;

public abstract class GridSample<I, O> {

    private final Universe universe;
    private final I initial;
    private final O solution;

    public GridSample(Universe universe, GridReader<I> initialGridReader, GridReader<O> solutionGridReader, I initial, O solution) {
        this.universe = universe;
        this.initial = initial;
        this.solution = solution;
    }

    protected abstract GridReader<I> getInitialGridReader();

    protected abstract GridReader<O> getSolutionGridReader();

    public I getInitial() {
        return initial;
    }

    public Grid getInitialGrid() {
        return getInitialGridReader().readGrid(initial);
    }

    public O getSolution() {
        return solution;
    }

    public Grid getSolutionGrid() {
        return getSolutionGridReader().readGrid(solution);
    }

    public StatGrid resolveAndAssert(GridResolver resolver) {
        GridChecker gridChecker = new GridChecker(universe, new CellKeys(universe));

        Grid solutionGrid = getSolutionGrid();
        Assert.assertNull(gridChecker.checkIsValid(solutionGrid));

        Grid testGrid = getInitialGrid();
        Assert.assertNull(gridChecker.checkIsValid(testGrid));
        Assert.assertNull(gridChecker.checkContains(solutionGrid, testGrid));

        StatGrid statGrid = new StatGrid(testGrid);
        resolver.resolveGrid(statGrid);
        System.out.println("Grid after resolution:");
        System.out.println(testGrid);
        System.out.println("Reads / Writes: " + statGrid.getReads() + "/" + statGrid.getWrites());

        Assert.assertNull(gridChecker.checkContains(solutionGrid, testGrid));
        Assert.assertNull(gridChecker.checkAreEqual(solutionGrid, testGrid));

        return statGrid;
    }

    @Test
    public void withGridResolverImpl() {
        resolveAndAssert(new GridResolverImpl());
    }
}
