package com.cappcorp.sudoku.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.IntStream;

import org.junit.Assert;
import org.junit.Test;

public class CombinationStreamTest {

    private static int[][] SIZE_2_COUNT_1 = new int[][] { { 1 }, { 2 } };

    private static int[][] SIZE_3_COUNT_1 = new int[][] { { 1 }, { 2 }, { 3 } };

    private static int[][] SIZE_3_COUNT_2 = new int[][] { { 1, 2 }, { 1, 3 }, { 2, 3 } };

    private static int[][] SIZE_4_COUNT_1 = new int[][] { { 1 }, { 2 }, { 3 }, { 4 } };

    private static int[][] SIZE_4_COUNT_2 = new int[][] { { 1, 2 }, { 1, 3 }, { 1, 4 }, { 2, 3 }, { 2, 4 }, { 3, 4 } };

    private static int[][] SIZE_4_COUNT_3 = new int[][] { { 1, 2, 3 }, { 1, 2, 4 }, { 1, 3, 4 }, { 2, 3, 4 } };

    @Test
    public void size2() {
        sizeNcountN(2);
        sizeNcountK(2, 1, SIZE_2_COUNT_1);
    }

    @Test
    public void size3() {
        sizeNcountN(3);
        sizeNcountK(3, 2, SIZE_3_COUNT_2);
        sizeNcountK(3, 1, SIZE_3_COUNT_1);
    }

    @Test
    public void size4() {
        sizeNcountN(4);
        sizeNcountK(4, 3, SIZE_4_COUNT_3);
        sizeNcountK(4, 2, SIZE_4_COUNT_2);
        sizeNcountK(4, 1, SIZE_4_COUNT_1);
    }

    private void sizeNcountN(int size) {
        Set<Integer> set = initialSet(size);
        List<Set<Integer>> combinations = combinations(set, size);

        Assert.assertEquals(1, combinations.size());
        Assert.assertEquals(set, combinations.get(0));
    }

    private void sizeNcountK(int size, int count, int[][] expected) {
        Set<Integer> set = initialSet(size);
        List<Set<Integer>> combinations = combinations(set, count);

        Assert.assertEquals(expected.length, combinations.size());
        for (int index = 0; index < expected.length; index++) {
            validate(combinations.get(index), expected[index]);
        }
    }

    private List<Set<Integer>> combinations(Set<Integer> set, int count) {
        List<Set<Integer>> combinations = new ArrayList<>();
        CombinationStream.streamOf(set, count).forEach(combination -> combinations.add(combination));
        return combinations;
    }

    private void validate(Set<Integer> actual, int[] expected) {
        Integer[] actualArray = actual.toArray(new Integer[actual.size()]);
        for (int index = 0; index < expected.length; index++) {
            Assert.assertEquals(expected[index], actualArray[index].intValue());
        }
    }

    private Set<Integer> initialSet(int size) {
        Set<Integer> set = new TreeSet<>();
        IntStream.rangeClosed(1, size).forEach(value -> set.add(Integer.valueOf(value)));
        return set;
    }
}
