package com.cappcorp.sudoku.model;

public class Box extends Group
{
    public static Box[] createBoxes(int cardinal)
    {
        return createGroups(cardinal, new GroupBuilder<Box>()
        {
            @Override
            public Box createGroup(int cardinal)
            {
                return new Box(cardinal);
            }
        });
    }

    private Box(int cardinal)
    {
        super(cardinal);
    }

}
