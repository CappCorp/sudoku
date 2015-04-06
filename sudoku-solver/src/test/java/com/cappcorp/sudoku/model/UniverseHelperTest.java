package com.cappcorp.sudoku.model;

import org.junit.Assert;
import org.junit.Test;

public class UniverseHelperTest
{

    private static void checkBuildCharacters(int cardinal, char[] expectedCharacters)
    {
        char[] characters = UniverseHelper.buildCharacters(cardinal);
        Assert.assertArrayEquals(expectedCharacters, characters);
    }

    @Test
    public void test1()
    {
        checkBuildCharacters(1, new char[] { '1' });
    }

    @Test
    public void test4()
    {
        checkBuildCharacters(4, new char[] { '1', '2', '3', '4' });
    }

    @Test
    public void test9()
    {
        checkBuildCharacters(9, new char[] { '1', '2', '3', '4', '5', '6', '7', '8', '9' });
    }

    @Test
    public void test16()
    {
        checkBuildCharacters(16, new char[] { '1', '2', '3', '4', '5', '6', '7', '8', '9',

        'A', 'B', 'C', 'D', 'E', 'F', 'G' });
    }

    @Test
    public void test36()
    {
        checkBuildCharacters(36, new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',

        'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' });
    }

    @Test
    public void test49()
    {
        checkBuildCharacters(49, new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',

        'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',

        'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm' });
    }

    @Test
    public void test64()
    {
        checkBuildCharacters(64, new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',

        'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',

        'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',

        '!', '?' });
    }

    @Test(expected = IllegalArgumentException.class)
    public void test81()
    {
        UniverseHelper.buildCharacters(81);
    }
}
