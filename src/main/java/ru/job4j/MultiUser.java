package ru.job4j;

public class MultiUser {
    public static void main(String[] args) {
        var barrier = new Barrier();
        var master = new Thread(
                () -> {
                    System.out.println(Thread.currentThread().getName() + " started");
                    barrier.on();
                },
                "Master"
        );
        var slave = new Thread(
                () -> {
                    barrier.check();
                    System.out.println(Thread.currentThread().getName() + " started");
                },
                "slave"
        );
        master.start();
        slave.start();
    }

}