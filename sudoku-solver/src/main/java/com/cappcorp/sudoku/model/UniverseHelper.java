package com.cappcorp.sudoku.model;

enum UniverseHelper {
    ;

    static final char[] DIGITS_UNIVERSE = new char[9];

    static final char[] DIGITS_AND_CHARS_UNIVERSE = new char[10 + 26 + 26 + 2];

    static {
        fillWithInts(DIGITS_UNIVERSE, 0, 1, 9);

        fillWithInts(DIGITS_AND_CHARS_UNIVERSE, 0, 0, 10);
        fillWithChars(DIGITS_AND_CHARS_UNIVERSE, 10, 'A', 26);
        fillWithChars(DIGITS_AND_CHARS_UNIVERSE, 10 + 26, 'a', 26);
        DIGITS_AND_CHARS_UNIVERSE[10 + 26 + 26] = '#';
        DIGITS_AND_CHARS_UNIVERSE[10 + 26 + 26 + 1] = '@';
    }

    static char[] buildCharacters(int cardinal) {
        char[] characters = new char[cardinal];
        if (cardinal < 10) {
            System.arraycopy(DIGITS_UNIVERSE, 0, characters, 0, characters.length);
            return characters;
        }
        if (cardinal <= DIGITS_AND_CHARS_UNIVERSE.length) {
            System.arraycopy(DIGITS_AND_CHARS_UNIVERSE, 0, characters, 0, characters.length);
            return characters;
        }
        throw new IllegalArgumentException("Cardinal too big, cannot generate characters automatically for cardinal: " + cardinal);
    }

    private static void fillWithInts(char[] characters, int start, int first, int count) {
        for (int i = 0; i < count; i++) {
            characters[start + i] = toChar(first + i);
        }
    }

    private static char toChar(int i) {
        if (i >= 10) {
            throw new IllegalArgumentException(i + " cannot be represented as a character.");
        }
        return Integer.toString(i).charAt(0);
    }

    private static void fillWithChars(char[] characters, int start, char first, int count) {
        for (int i = 0; i < count; i++) {
            characters[start + i] = (char) (first + i);
        }
    }
}
