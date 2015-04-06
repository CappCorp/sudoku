package com.cappcorp.sudoku.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Set of possible values.
 */
public class Universe
{
    static int checkAndComputeSqrt(int cardinal)
    {
        if (cardinal <= 0)
        {
            throw universeCardinalException();
        }
        double sqrtd = Math.sqrt(cardinal);
        if (sqrtd != (int) sqrtd)
        {
            throw universeCardinalException();
        }
        return (int) sqrtd;
    }

    private static RuntimeException universeCardinalException()
    {
        return new IllegalArgumentException("Invalid universe; universe cardinal must by the square of a strictly positive integer value");
    }

    private final List<Character> sortedValues;
    private final Set<Character> uniqueValues;
    private final int cardinal;
    private final int sqrt;

    public Universe(int cardinal)
    {
        this(UniverseHelper.buildCharacters(cardinal));
    }

    public Universe(char[] characters)
    {
        cardinal = characters.length;
        sqrt = checkAndComputeSqrt(cardinal);
        sortedValues = new ArrayList<>(characters.length);
        uniqueValues = new HashSet<>(characters.length);
        for (char c : characters)
        {
            Character character = Character.valueOf(c);
            if (!uniqueValues.add(character))
            {
                throw new IllegalArgumentException("Duplicated character found in universe: " + c);
            }
            sortedValues.add(character);
        }
    }

    public int getCardinal()
    {
        return cardinal;
    }

    public int getSqrt()
    {
        return sqrt;
    }

    public char map(int value)
    {
        return sortedValues.get(value).charValue();
    }

    public int map(char value)
    {
        return sortedValues.indexOf(Character.valueOf(value));
    }
}
