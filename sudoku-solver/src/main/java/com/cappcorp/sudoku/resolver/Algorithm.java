package com.cappcorp.sudoku.resolver;

public interface Algorithm {

    /**
     * Performs and iteration of the algorithm.
     * 
     * @return <code>true</code> if the iteration changed the grid, <code>false</code> otherwise
     */
    boolean performIteration();
    
    String getName();
}
