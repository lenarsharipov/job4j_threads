package ru.job4j.pool;

import java.util.concurrent.RecursiveTask;

public class ParallelSearch<T> extends RecursiveTask<Integer> {
    private static final int MIN_LENGTH = 10;
    private final T[] array;
    private final int from;
    private final int to;
    private final T value;

    public ParallelSearch(T[] array, int from, int to, T value) {
        this.array = array;
        this.from = from;
        this.to = to;
        this.value = value;
    }

    @Override
    protected Integer compute() {
        if (to - from <= MIN_LENGTH) {
            int index = -1;
            for (int i = from; i <= to; i++) {
                if (array[i] == value) {
                    index = i;
                    break;
                }
            }
            return index;
        }

        int mid = (from + to) / 2;
        ParallelSearch<T> leftSearch = new ParallelSearch<>(array, from, mid, value);
        ParallelSearch<T> rightSearch = new ParallelSearch<>(array, mid + 1, to, value);
        leftSearch.fork();
        rightSearch.fork();
        int left = leftSearch.join();
        int right = rightSearch.join();
        return Search.merge(left, right);
    }
}