package com.cappcorp.sudoku.model;

public class Grid
{
    static int computeBoxNumber(int sqrt, int row, int col)
    {
        return col % sqrt + sqrt * (row % sqrt);
    }

    static int computeBoxPosition(int sqrt, int row, int col)
    {
        return (row % sqrt) * sqrt + col % sqrt;
    }

    private final Universe universe;
    private final Cell[][] cells;
    private final Group[] rows;
    private final Group[] columns;
    private final Group[] boxes;

    public Grid(int cardinal)
    {
        this(new Universe(cardinal));
    }

    public Grid(char[] characters)
    {
        this(new Universe(characters));
    }

    public Grid(Universe universe)
    {
        this.universe = universe;
        int cardinal = universe.getCardinal();
        int sqrt = universe.getSqrt();

        this.cells = new Cell[cardinal][cardinal];
        this.rows = Group.createGroups(cardinal);
        this.columns = Group.createGroups(cardinal);
        this.boxes = Group.createGroups(cardinal);

        for (int row = 0; row < cardinal; row++)
        {
            for (int col = 0; col < cardinal; col++)
            {
                int box = computeBoxNumber(sqrt, row, col);
                int boxPosition = computeBoxPosition(sqrt, row, col);

                Cell cell = new Cell(cardinal);
                cells[row][col] = cell;
                rows[row].setCell(col, cell);
                columns[col].setCell(row, cell);
                boxes[box].setCell(boxPosition, cell);
            }
        }
    }

    public Group getRow(int row)
    {
        return rows[row];
    }

    public Group getColumn(int col)
    {
        return columns[col];
    }

    public Group getBox(int row, int col)
    {
        return boxes[computeBoxNumber(universe.getSqrt(), row, col)];
    }

    public void setCell(int row, int col, int value)
    {
        cells[row][col].setValue(value);
    }

    public void setCell(int row, int col, char value)
    {
        setCell(row, col, universe.map(value));
    }

}
