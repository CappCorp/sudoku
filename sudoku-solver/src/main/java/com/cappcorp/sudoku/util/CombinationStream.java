package com.cappcorp.sudoku.util;

import java.util.Collections;
import java.util.HashSet;
import java.util.OptionalInt;
import java.util.Set;
import java.util.Spliterators.AbstractSpliterator;
import java.util.function.Consumer;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class CombinationStream {

    private static int countCombinations(int count, int size) {
        OptionalInt numerator = IntStream.rangeClosed(size - count + 1, size).parallel().reduce((a, b) -> a * b);
        OptionalInt denominator = IntStream.rangeClosed(1, count).parallel().reduce((a, b) -> a * b);
        return numerator.getAsInt() / denominator.getAsInt();
    }

    private static class CombinationSpliterator<T> extends AbstractSpliterator<Set<T>> {

        private final Set<T> subSet;
        private final int count;
        private T value;
        private CombinationSpliterator<T> subSpliterator;

        CombinationSpliterator(Set<T> fullSet, int count) {
            super(countCombinations(count, fullSet.size()), ORDERED + DISTINCT + NONNULL + IMMUTABLE);
            this.count = count;
            subSet = new HashSet<T>(fullSet);
        }

        @Override
        public boolean tryAdvance(Consumer<? super Set<T>> action) {
            if (value == null) {
                // first step, no initialization, try to go for a full set
                if (subSet.size() == count) {
                    // the full set is the only combination, iteration other
                    action.accept(Collections.unmodifiableSet(subSet));
                    return false;
                }
                // initializing for first value
                nextIteration();
            }

            if (subSpliterator == null) { // returning singletons one by one
                action.accept(Collections.singleton(value));
            } else {
                // generate the combinations from the sub spliterator with current value fixed as the first value of the combination
                Set<T> set = new HashSet<>();
                set.add(value);
                if (subSpliterator != null && subSpliterator.tryAdvance(subSplitSet -> consumeSubSpliteratorSet(set, subSplitSet, action))) {
                    return true;
                }
            }
            if (!subSet.isEmpty() && count <= subSet.size()) {
                // the subset is not empty and contains enough elements to fill a combination
                nextIteration();
                return true;
            }
            return false;
        }

        private void consumeSubSpliteratorSet(Set<T> set, Set<T> subSplitSet, Consumer<? super Set<T>> action) {
            set.addAll(subSplitSet);
            action.accept(Collections.unmodifiableSet(set));
        }

        private void nextIteration() {
            value = subSet.iterator().next();
            subSet.remove(value);
            // cannot iterate anymore if either the count is 1 (or less) which correspond to returning singletons from the set one by one
            if (count > 1) {
                subSpliterator = new CombinationSpliterator<>(subSet, count - 1);
            }
        }
    }

    public static <T> Stream<Set<T>> streamOf(Set<T> set, int count) {
        if (set.isEmpty()) {
            throw new IllegalArgumentException("Cannot create a combination using an empty set");
        }

        int size = set.size();
        if (count <= 0 || count > size) {
            throw new IllegalArgumentException("Invalid count value [" + count
                    + "], the count must be a strictly positive integer lesser that or equal to the specified set size [" + size + "]");
        }

        if (size == count) {
            return Collections.singleton(set).stream();
        }

        return StreamSupport.stream(new CombinationSpliterator<T>(set, count), false);
    }
}
