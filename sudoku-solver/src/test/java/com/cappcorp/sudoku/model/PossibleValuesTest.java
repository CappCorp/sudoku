package com.cappcorp.sudoku.model;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

public class PossibleValuesTest {

    @SuppressWarnings("serial")
    private static Set<Integer> expectedSet(int... values) {
        return new HashSet<Integer>() {
            {
                for (int value : values) {
                    add(Integer.valueOf(value));
                }
            }
        };
    }

    @Test
    public void testCount() {
        assertThat(new PossibleValues(5).count()).isEqualTo(5);
        assertThat(new PossibleValues(1).count()).isEqualTo(1);
        assertThat(new PossibleValues(0).count()).isEqualTo(0);
    }

    @Test
    public void testIsResolved() {
        assertThat(new PossibleValues(1).isResolved()).isTrue();
        assertThat(new PossibleValues(5).isResolved()).isFalse();
        assertThat(new PossibleValues(0).isResolved()).isFalse();
    }

    @Test(expected = IllegalArgumentException.class)
    public void ctor_negativeCardinal() {
        new PossibleValues(-1);
    }

    @Test
    public void testGetValueIfResolved() {
        assertThat(new PossibleValues(5).getValueIfResolved()).isNull();
        assertThat(new PossibleValues(0).getValueIfResolved()).isNull();
        assertThat(new PossibleValues(1).getValueIfResolved()).isEqualTo(Integer.valueOf(0));
    }

    @Test
    public void testGetPossibleValues() {
        assertThat(new PossibleValues(5).getPossibleValues()).isEqualTo(expectedSet(0, 1, 2, 3, 4));
        assertThat(new PossibleValues(1).getPossibleValues()).isEqualTo(expectedSet(0));
    }

    @Test
    public void removeValues_correctValues() {
        PossibleValues possibleValues = new PossibleValues(5);

        possibleValues.removeValues(3);
        assertThat(possibleValues.getPossibleValues()).isEqualTo(expectedSet(0, 1, 2, 4));

        possibleValues.removeValues(0, 2);
        assertThat(possibleValues.getPossibleValues()).isEqualTo(expectedSet(1, 4));

        possibleValues.removeValues(3);
        assertThat(possibleValues.getPossibleValues()).isEqualTo(expectedSet(1, 4));

        possibleValues.removeValues(1);
        assertThat(possibleValues.getPossibleValues()).isEqualTo(expectedSet(4));

        assertThat(possibleValues.isResolved()).isTrue();
    }

    @Test(expected = IllegalArgumentException.class)
    public void removeValues_negativeValue() {
        new PossibleValues(5).removeValues(-1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void removeValues_tooBigValue() {
        new PossibleValues(5).removeValues(10);
    }

    @Test
    public void setValues_correctValues() {
        PossibleValues possibleValues = new PossibleValues(5);

        possibleValues.setValues(0, 1, 4);
        assertThat(possibleValues.getPossibleValues()).isEqualTo(expectedSet(0, 1, 4));

        possibleValues.setValues(3);
        assertThat(possibleValues.getPossibleValues()).isEqualTo(expectedSet(3));

        assertThat(possibleValues.isResolved()).isTrue();
    }

    @Test(expected = IllegalArgumentException.class)
    public void setValues_negativeValue() {
        new PossibleValues(5).setValues(-1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void setValues_tooBigValue() {
        new PossibleValues(5).removeValues(10);
    }

}
