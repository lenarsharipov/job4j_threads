package ru.job4j.pool;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

class ParallelSearchTest {

    @Test
    void whenTryToFind19InIntegerLongArrayThenGet18() {
        Integer[] array = {
                1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21
        };
        int index = ParallelSearch.findIndex(array, 19);
        assertThat(index).isEqualTo(18);
    }

    @Test
    void whenTryToFind333InIntegerLongArrayThenGetMinus1() {
        Integer[] array = {
                1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21
        };
        int index = ParallelSearch.findIndex(array, 333);
        assertThat(index).isEqualTo(-1);
    }

    @Test
    void whenTryToFind3InShortIntegerArrayThenGet2() {
        Integer[] array = {
                1, 2, 3, 4, 5, 6, 7, 8
        };
        int index = ParallelSearch.findIndex(array, 3);
        assertThat(index).isEqualTo(2);
    }

    @Test
    void whenTryToFind333InShortIntegerArrayThenGetMinus1() {
        Integer[] array = {
                1, 2, 3, 4, 5, 6, 7, 8
        };
        int index = ParallelSearch.findIndex(array, 333);
        assertThat(index).isEqualTo(-1);
    }

    @Test
    void whenTryToFind19InStringLongArrayThenGet18() {
        String[] array = {
                "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11",
                "12", "13", "14", "15", "16", "17", "18", "19", "20", "21"
        };
        int index = ParallelSearch.findIndex(array, "19");
        assertThat(index).isEqualTo(18);
    }

    @Test
    void whenTryToFind333InStringLongArrayThenGetMinus1() {
        String[] array = {
                "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11",
                "12", "13", "14", "15", "16", "17", "18", "19", "20", "21"
        };
        int index = ParallelSearch.findIndex(array, "333");
        assertThat(index).isEqualTo(-1);
    }

    @Test
    void whenTryToFind3InShortStringArrayThenGet2() {
        String[] array = {
                "1", "2", "3", "4", "5", "6", "7", "8"
        };
        int index = ParallelSearch.findIndex(array, "3");
        assertThat(index).isEqualTo(2);
    }

    @Test
    void whenTryToFind333InShortStringArrayThenGetMinus1() {
        String[] array = {
                "1", "2", "3", "4", "5", "6", "7", "8"
        };
        int index = ParallelSearch.findIndex(array, "333");
        assertThat(index).isEqualTo(-1);
    }

    @Test
    void whenTryToFindUserInLongUserArrayThenGet10() {
        User[] array = {
            new User("name1", "email1"),
            new User("name2", "email2"),
            new User("name3", "email3"),
            new User("name4", "email4"),
            new User("name5", "email5"),
            new User("name6", "email6"),
            new User("name7", "email7"),
            new User("name8", "email8"),
            new User("name9", "email9"),
            new User("name10", "email10"),
            new User("name11", "email11"),
            new User("name12", "email12")
        };
        int index = ParallelSearch.findIndex(array, new User("name11", "email11"));
        assertThat(index).isEqualTo(10);
    }

    @Test
    void whenTryToFindUserInLongUserArrayThenGetMinus1() {
        User[] array = {
                new User("name1", "email1"),
                new User("name2", "email2"),
                new User("name3", "email3"),
                new User("name4", "email4"),
                new User("name5", "email5"),
                new User("name6", "email6"),
                new User("name7", "email7"),
                new User("name8", "email8"),
                new User("name9", "email9"),
                new User("name10", "email10"),
                new User("name11", "email11"),
                new User("name12", "email12")
        };
        int index = ParallelSearch.findIndex(array, new User("name15", "email15"));
        assertThat(index).isEqualTo(-1);
    }

    @Test
    void whenTryToFindUserInShortUserArrayThenGet10() {
        User[] array = {
                new User("name1", "email1"),
                new User("name2", "email2"),
                new User("name3", "email3"),
                new User("name4", "email4"),
                new User("name5", "email5"),
                new User("name6", "email6"),
                new User("name7", "email7"),
                new User("name8", "email8"),
                new User("name9", "email9"),
                new User("name10", "email10")
        };
        int index = ParallelSearch.findIndex(array, new User("name4", "email4"));
        assertThat(index).isEqualTo(3);
    }

    @Test
    void whenTryToFindUserInShortUserArrayThenGetMinus1() {
        User[] array = {
                new User("name1", "email1"),
                new User("name2", "email2"),
                new User("name3", "email3"),
                new User("name4", "email4"),
                new User("name5", "email5"),
                new User("name6", "email6"),
                new User("name7", "email7"),
                new User("name8", "email8")
        };
        int index = ParallelSearch.findIndex(array, new User("name15", "email15"));
        assertThat(index).isEqualTo(-1);
    }
}