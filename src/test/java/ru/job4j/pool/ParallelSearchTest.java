package ru.job4j.pool;

import org.junit.jupiter.api.Test;

import java.util.concurrent.ForkJoinPool;

import static org.assertj.core.api.Assertions.*;

class ParallelSearchTest {

    @Test
    void whenTryToFind19InIntegerLongArrayThenGet18() {
        Integer[] array = {
                1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21
        };
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        int index = forkJoinPool.invoke(new ParallelSearch<>(array, 0, array.length - 1, 19));
        assertThat(index).isEqualTo(18);
    }

    @Test
    void whenTryToFind333InIntegerLongArrayThenGetMinus1() {
        Integer[] array = {
                1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21
        };
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        int index = forkJoinPool.invoke(new ParallelSearch<>(array, 0, array.length - 1, 333));
        assertThat(index).isEqualTo(-1);
    }

    @Test
    void whenTryToFind3InShortIntegerArrayThenGet2() {
        Integer[] array = {
                1, 2, 3, 4, 5, 6, 7, 8
        };
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        int index = forkJoinPool.invoke(new ParallelSearch<>(array, 0, array.length - 1, 3));
        assertThat(index).isEqualTo(2);
    }

    @Test
    void whenTryToFind333InShortIntegerArrayThenGetMinus1() {
        Integer[] array = {
                1, 2, 3, 4, 5, 6, 7, 8
        };
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        int index = forkJoinPool.invoke(new ParallelSearch<>(array, 0, array.length - 1, 333));
        assertThat(index).isEqualTo(-1);
    }

    @Test
    void whenTryToFind19InStringLongArrayThenGet18() {
        String[] array = {
                "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11",
                "12", "13", "14", "15", "16", "17", "18", "19", "20", "21"
        };
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        int index = forkJoinPool.invoke(new ParallelSearch<>(array, 0, array.length - 1, "19"));
        assertThat(index).isEqualTo(18);
    }

    @Test
    void whenTryToFind333InStringLongArrayThenGetMinus1() {
        String[] array = {
                "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11",
                "12", "13", "14", "15", "16", "17", "18", "19", "20", "21"
        };
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        int index = forkJoinPool.invoke(new ParallelSearch<>(array, 0, array.length - 1, "333"));
        assertThat(index).isEqualTo(-1);
    }

    @Test
    void whenTryToFind3InShortStringArrayThenGet2() {
        String[] array = {
                "1", "2", "3", "4", "5", "6", "7", "8"
        };
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        int index = forkJoinPool.invoke(new ParallelSearch<>(array, 0, array.length - 1, "3"));
        assertThat(index).isEqualTo(2);
    }

    @Test
    void whenTryToFind333InShortStringArrayThenGetMinus1() {
        String[] array = {
                "1", "2", "3", "4", "5", "6", "7", "8"
        };
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        int index = forkJoinPool.invoke(new ParallelSearch<>(array, 0, array.length - 1, "333"));
        assertThat(index).isEqualTo(-1);
    }
}