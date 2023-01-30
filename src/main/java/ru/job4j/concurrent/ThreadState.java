package ru.job4j.concurrent;

public class ThreadState {
    public static void main(String[] args) {
        var first = new Thread(
                () -> System.out.println("first : " + Thread.currentThread().getName())
        );
        var second = new Thread(
                () -> System.out.println("second : " + Thread.currentThread().getName())
        );
        System.out.println("first : " + first.getName() + " : " + first.getState());
        System.out.println("second : " + second.getName() + " : " + second.getState());
        first.start();
        second.start();
        while (first.getState() != Thread.State.TERMINATED
                && second.getState() != Thread.State.TERMINATED) {
            System.out.println("first : " + first.getName() + " : " + first.getState());
            System.out.println("second : " + second.getName() + " : " + second.getState());
        }

        System.out.println("first : " + first.getName() + " : " + first.getState());
        System.out.println("first : " + first.getName() + " : " + second.getState());

        System.out.println(Thread.currentThread().getName() + " : " + "работа завершена!");
    }
}