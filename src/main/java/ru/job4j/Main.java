package ru.job4j;

import java.util.Iterator;

public class Main implements Iterator<Integer> {
    private final int[] data;
    private int point = 0;

    public Main(int[] data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        return point < data.length;
    }

    @Override
    public Integer next() {
        return data[point++];
    }
}