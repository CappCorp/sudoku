package com.cappcorp.sudoku.model;

public class Grid
{
    static int computeBoxPosition(int sqrt, int row, int col)
    {
        return (row % sqrt) * sqrt + col % sqrt;
    }

    static int computeBoxNumber(int sqrt, int row, int col)
    {
        return col % sqrt + sqrt * (row % sqrt);
    }

    private final Universe universe;
    private final Cell[][] cells;
    private final Row[] rows;
    private final Column[] colulmns;
    private final Box[] boxes;

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
        this.rows = Row.createRows(cardinal);
        this.colulmns = Column.createColumns(cardinal);
        this.boxes = Box.createBoxes(cardinal);

        for (int row = 0; row < cardinal; row++)
        {
            for (int col = 0; col < cardinal; col++)
            {
                int box = computeBoxNumber(sqrt, row, col);
                int boxPosition = computeBoxPosition(sqrt, row, col);

                Cell cell = new Cell(cardinal);
                cells[row][col] = cell;
                rows[row].addCell(col, cell);
                colulmns[col].addCell(row, cell);
                boxes[box].addCell(boxPosition, cell);
            }
        }
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
