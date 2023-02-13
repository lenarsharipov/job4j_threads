package ru.job4j;

import net.jcip.annotations.ThreadSafe;

import java.util.concurrent.atomic.AtomicReference;

@ThreadSafe
public class CASCount {
    private final AtomicReference<Integer> count = new AtomicReference<>(0);

    public void increment() {
        Integer value;
        Integer next;
        do {
            value = count.get();
            next = value + 1;
        } while (!count.compareAndSet(value, next));
    }

    public int get() {
        Integer value;
        do {
            value = count.get();
        } while (!count.compareAndSet(value, value));
        return value;
    }

}
