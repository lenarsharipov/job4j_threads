package ru.job4j.concurrent;

public class ConcurrentOutput {
    public static void main(String[] args) {
        Thread another = new Thread(
                () -> System.out.println(Thread.currentThread().getName())
        );
        another.start();
        System.out.println(Thread.currentThread().getName());

        Thread second = new Thread(
                () -> System.out.println(Thread.currentThread().getName())
        );
        second.start();

        Thread third = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());
            }
        });
        third.start();

        System.out.println("*".repeat(100));
        for (int i = 0; i < 10; i++) {
            Test test = new Test();
            test.start();
        }

    }

    private static class Test extends Thread {
        @Override
        public void run() {
            System.out.println(getName());
        }
    }
}