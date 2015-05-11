package com.cappcorp.sudoku.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Set of possible values.
 */
public class Universe {

    public static final char BLANK = ' ';

    private static final Map<Integer, Universe> universeMap = new HashMap<>();

    static int checkAndComputeSqrt(int cardinal) {
        if (cardinal <= 0) {
            throw universeCardinalException();
        }
        double sqrtd = Math.sqrt(cardinal);
        if (sqrtd != (int) sqrtd) {
            throw universeCardinalException();
        }
        return (int) sqrtd;
    }

    private static RuntimeException universeCardinalException() {
        return new IllegalArgumentException("Invalid universe; universe cardinal must by the square of a strictly positive integer value");
    }

    public synchronized static Universe fromCardinal(int cardinal) {
        Integer cardinalInt = Integer.valueOf(cardinal);
        Universe universe = universeMap.get(cardinalInt);
        if (universe == null) {
            universe = new Universe(cardinal);
            universeMap.put(cardinalInt, universe);
        }
        return universe;
    }

    private final List<Character> sortedValues;
    private final Set<Character> uniqueValues;
    private final int cardinal;
    private final int sqrt;

    private Universe(int cardinal) {
        this(UniverseHelper.buildCharacters(cardinal));
    }

    public Universe(char[] characters) {
        cardinal = characters.length;
        sqrt = checkAndComputeSqrt(cardinal);
        sortedValues = new ArrayList<>(characters.length);
        uniqueValues = new HashSet<>(characters.length);
        for (char c : characters) {
            if (c == BLANK) {
                throw new IllegalArgumentException("Space character is not allowed in universe");
            }
            Character character = Character.valueOf(c);
            if (!uniqueValues.add(character)) {
                throw new IllegalArgumentException("Duplicated character found in universe: " + c);
            }
            sortedValues.add(character);
        }
    }

    public int getCardinal() {
        return cardinal;
    }

    public int getSqrt() {
        return sqrt;
    }

    public char map(int value) {
        return sortedValues.get(value).charValue();
    }

    public int map(char value) {
        int position = sortedValues.indexOf(Character.valueOf(value));
        if (position == -1) {
            throw new IllegalArgumentException("Character [" + value + "] is not part of the universe.");
        }
        return position;
    }
}
