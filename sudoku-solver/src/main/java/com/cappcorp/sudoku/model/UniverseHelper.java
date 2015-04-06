package com.cappcorp.sudoku.model;

enum UniverseHelper
{
    ;

    static char[] buildCharacters(int cardinal)
    {
        char[] characters = new char[cardinal];
        if (cardinal < 10)
        {
            fillWithInts(characters, 0, 1, cardinal);
            return characters;
        }
        if (cardinal <= 25)
        {
            fillWithInts(characters, 0, 1, 9);
            fillWithChars(characters, 9, 'A', cardinal - 9);
            return characters;
        }
        if (cardinal <= 10 + 26 + 26 + 2)
        {
            fillWithInts(characters, 0, 0, 10);
            if (cardinal <= 10 + 26)
            {
                fillWithChars(characters, 10, 'A', cardinal - 10);
            }
            else
            {
                fillWithChars(characters, 10, 'A', 26);
                if (cardinal <= 10 + 26 + 26)
                {
                    fillWithChars(characters, 10 + 26, 'a', cardinal - 10 - 26);
                }
                else
                {
                    fillWithChars(characters, 10 + 26, 'a', 26);
                    characters[62] = '!';
                    if (cardinal == 64)
                    {
                        characters[63] = '?';
                    }
                }
            }
            return characters;
        }
        throw new IllegalArgumentException("Cardinal too big, cannot generate characters automatically for cardinal: " + cardinal);
    }

    private static void fillWithInts(char[] characters, int start, int first, int count)
    {
        for (int i = 0; i < count; i++)
        {
            characters[start + i] = toChar(first + i);
        }
    }

    private static char toChar(int i)
    {
        if (i >= 10)
        {
            throw new IllegalArgumentException(i + " cannot be represented as a character.");
        }
        return Integer.toString(i).charAt(0);
    }

    private static void fillWithChars(char[] characters, int start, char first, int count)
    {
        for (int i = 0; i < count; i++)
        {
            characters[start + i] = (char) (first + i);
        }
    }
}
