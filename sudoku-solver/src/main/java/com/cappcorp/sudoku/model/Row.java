package com.cappcorp.sudoku.model;

public class Row extends Group
{
    public static Row[] createRows(int cardinal)
    {
        return createGroups(cardinal, new GroupBuilder<Row>()
        {
            @Override
            public Row createGroup(int cardinal)
            {
                return new Row(cardinal);
            }

            @Override
            public Class<Row> getClazz()
            {
                return Row.class;
            }
        });
    }

    private Row(int cardinal)
    {
        super(cardinal);
    }

}
