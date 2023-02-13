package ru.job4j;

import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.*;

class CASCountTest {
    @Test
    public void when3ThreadsIncrementBy5ThenGet15() throws InterruptedException {
        final CASCount counter = new CASCount();
        Thread thread1 = new Thread(
                () -> IntStream.range(0, 5).forEach(
                        el -> counter.increment()
                )
        );
        Thread thread2 = new Thread(
                () -> IntStream.range(0, 5).forEach(
                        el -> counter.increment()
                )
        );
        Thread thread3 = new Thread(
                () -> IntStream.range(0, 5).forEach(
                        el -> counter.increment()
                )
        );
        thread1.start();
        thread2.start();
        thread3.start();
        thread1.join();
        thread2.join();
        thread3.join();

        assertThat(counter.get()).isEqualTo(15);
    }

    @Test
    public void when3ThreadsIncrementBy200ThenGet600() throws InterruptedException {
        final CASCount counter = new CASCount();
        Thread thread1 = new Thread(
                () -> IntStream.range(0, 200).forEach(
                        el -> counter.increment()
                )
        );
        Thread thread2 = new Thread(
                () -> IntStream.range(0, 200).forEach(
                        el -> counter.increment()
                )
        );
        Thread thread3 = new Thread(
                () -> IntStream.range(0, 200).forEach(
                        el -> counter.increment()
                )
        );
        thread1.start();
        thread2.start();
        thread3.start();
        thread1.join();
        thread2.join();
        thread3.join();

        assertThat(counter.get()).isEqualTo(600);
    }

}