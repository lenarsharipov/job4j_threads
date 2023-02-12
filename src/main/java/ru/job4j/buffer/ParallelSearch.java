package ru.job4j.buffer;

import ru.job4j.SimpleBlockingQueue;

/**
 * Класс ParallelSearch - утилита по поиску текста в файловой системе.
 * Утилита предназначена для работы в многопоточной среде.
 * Одна нить ищет файлы с подходящим именем.
 * Вторая нить берет эти файлы и читает их.
 *
 * @author Lenar Sharipov
 * @version 1.0
 */
public class ParallelSearch {

    public static void main(String[] args) throws InterruptedException {
        var queue = new SimpleBlockingQueue<>(10);
        final Thread consumer = new Thread(
                () -> {
                    while (!queue.isEmpty() || !Thread.currentThread().isInterrupted()) {
                        try {
                            System.out.println(queue.poll());
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    }
                }
        );
        consumer.start();

        final Thread producer = new Thread(
                () -> {
                    for (var index = 0; index != 3; index++) {
                        try {
                            queue.offer(index);
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
        );
        producer.start();
        producer.join();
        consumer.interrupt();
        consumer.join();
    }

}
