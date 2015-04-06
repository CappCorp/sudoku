package com.cappcorp.sudoku.model;

public abstract class Group
{
    protected static interface GroupBuilder<G extends Group>
    {
        G createGroup(int cardinal);
    }

    protected static <G extends Group> G[] createGroups(int cardinal, GroupBuilder<G> groupBuilder)
    {
        @SuppressWarnings("unchecked")
        G[] groups = (G[]) new Object[cardinal];
        for (int i = 0; i < cardinal; i++)
        {
            groups[i] = groupBuilder.createGroup(cardinal);
        }
        return groups;
    }

    private final Cell[] cells;

    protected Group(int cardinal)
    {
        this.cells = new Cell[cardinal];
    }

    public void addCell(int position, Cell cell)
    {
        cells[position] = cell;
    }
}
