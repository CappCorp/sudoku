package com.cappcorp.sudoku.model;

public class Column extends Group
{
    public static Column[] createColumns(int cardinal)
    {
        return createGroups(cardinal, new GroupBuilder<Column>()
        {
            @Override
            public Column createGroup(int cardinal)
            {
                return new Column(cardinal);
            }
        });
    }

    private Column(int cardinal)
    {
        super(cardinal);
    }

}