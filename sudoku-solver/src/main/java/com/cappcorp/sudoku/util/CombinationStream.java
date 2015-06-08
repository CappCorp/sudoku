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

        private boolean tryAdvanceSub(Consumer<? super Set<T>> action) {
            return subSpliterator != null && subSpliterator.tryAdvance(subSplitSet -> consumeSubSpliteratorSet(subSplitSet, action));
        }

        @Override
        public boolean tryAdvance(Consumer<? super Set<T>> action) {
            if (subSet.size() < count && subSpliterator == null) {
                // not enough elements anymore to generate a combination and no sub iteration ongoing
                return false;
            }

            if (tryAdvanceSub(action)) {
                // still iterations to be done on sub spliterator
                return true;
            }

            if (subSet.size() == count) {
                // the full set is the only combination, iteration over
                action.accept(Collections.unmodifiableSet(new HashSet<T>(subSet)));
                // clearing to force the end of the iteration
                subSet.clear();
                subSpliterator = null;
                return true;
            }

            nextIteration();

            if (subSpliterator == null) { // count==1, returning singletons one by one
                action.accept(Collections.singleton(value));
                return true;
            }
            // should always yield true if we were able to create the sub spliterator anyway
            return subSpliterator.tryAdvance(subSplitSet -> consumeSubSpliteratorSet(subSplitSet, action));
        }

        private void consumeSubSpliteratorSet(Set<T> subSplitSet, Consumer<? super Set<T>> action) {
            Set<T> set = new HashSet<>();
            if (value != null) {
                set.add(value);
            }
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
