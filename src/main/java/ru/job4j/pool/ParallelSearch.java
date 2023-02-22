package ru.job4j.pool;

import java.util.concurrent.ForkJoinPool;
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

    private int search() {
        int index = -1;
        for (int i = from; i <= to; i++) {
            if (array[i] == value) {
                index = i;
                break;
            }
        }
        return index;
    }

    @Override
    protected Integer compute() {
        if (to - from <= MIN_LENGTH) {
            return search();
        }
        int mid = (from + to) / 2;
        ParallelSearch<T> leftSearch = new ParallelSearch<>(array, from, mid, value);
        ParallelSearch<T> rightSearch = new ParallelSearch<>(array, mid + 1, to, value);
        leftSearch.fork();
        rightSearch.fork();
        int left = leftSearch.join();
        int right = rightSearch.join();
        return Math.max(left, right);
    }

    public static <T> int findIndex(T[] array, T value) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        return forkJoinPool.invoke(new ParallelSearch<>(array, 0, array.length - 1, value));
    }
}