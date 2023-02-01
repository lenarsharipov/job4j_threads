package ru.job4j.concurrent;

import java.util.Arrays;

public class ParallelStreamExample {
    public static void main(String[] args) {
        var list = Arrays.asList(1, 2, 3, 4, 5);
        var stream = list.parallelStream();
        System.out.println(stream.isParallel());
        var multiplication = stream.reduce((a, b) -> a * b);
        System.out.println(multiplication.get());

        var list2 = Arrays.asList(1, 2, 3, 4, 5, 6);
        list2.stream().parallel().peek(System.out::print).toList();
        System.out.println();
        list2.stream().parallel().forEach(System.out::print);
        System.out.println();
        list2.stream().parallel().forEachOrdered(System.out::print);
        System.out.println();

    }
}
