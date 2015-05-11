package com.cappcorp.sudoku.model;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Map;

import mockit.Deencapsulation;
import mockit.Expectations;
import mockit.Verifications;
import mockit.integration.junit4.JMockit;

import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(JMockit.class)
public class UniverseTest {

    @Test
    public void checkAndComputeSqrt_validValues() {
        checkAndComputeSqrt_validValue(1);
        checkAndComputeSqrt_validValue(2);
        checkAndComputeSqrt_validValue(3);
        checkAndComputeSqrt_validValue(4);
        checkAndComputeSqrt_validValue(5);
    }

    private void checkAndComputeSqrt_validValue(int sqrt) {
        assertThat(Universe.checkAndComputeSqrt(sqrt * sqrt)).isEqualTo(sqrt);
    }

    @Test(expected = IllegalArgumentException.class)
    public void checkAndComputeSqrt_negativeValue() {
        Universe.checkAndComputeSqrt(-1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void checkAndComputeSqrt_zero() {
        Universe.checkAndComputeSqrt(0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void checkAndComputeSqrt_nonSquare() {
        Universe.checkAndComputeSqrt(7);
    }

    @Test(expected = IllegalArgumentException.class)
    public void ctor_illegalCharacter() {
        new Universe(new char[] { Universe.BLANK });
    }

    @Test(expected = IllegalArgumentException.class)
    public void ctor_duplicatedCharacters() {
        new Universe(new char[] { 'a', 'a' });
    }

    @Test
    public void fromCardinal_callsUniverseHelper() {
        Deencapsulation.getField(Universe.class, Map.class).clear();

        new Expectations(UniverseHelper.class) {
            {
            }
        };

        Universe.fromCardinal(1);

        new Verifications() {
            {
                UniverseHelper.buildCharacters(1);
            }
        };
    }

    @Test
    public void getCardinal_expectCardinal() {
        int cardinal = 16;
        Universe universe = Universe.fromCardinal(cardinal);
        assertThat(universe.getCardinal()).isEqualTo(cardinal);
    }

    @Test
    public void getSqrt_expectSqrt() {
        int sqrt = 3;
        int cardinal = sqrt * sqrt;
        Universe universe = Universe.fromCardinal(cardinal);
        assertThat(universe.getSqrt()).isEqualTo(sqrt);
    }

    @Test
    public void map_expectIntForCharAndCharForInt() {
        char[] characters = new char[] { 'a', 'b', 'c', 'd' };
        Universe universe = new Universe(characters);
        for (int i = 0; i < characters.length; i++) {
            assertThat(universe.map(i)).isEqualTo(characters[i]);
            assertThat(universe.map(characters[i])).isEqualTo(i);
        }
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void map_illegalIntValue() {
        Universe universe = Universe.fromCardinal(1);
        universe.map(-1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void map_illegalCharValue() {
        Universe universe = new Universe(new char[] { 'a' });
        universe.map('b');
    }
}
