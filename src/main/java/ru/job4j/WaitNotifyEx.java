package ru.job4j;

public class WaitNotifyEx {
    public static void main(String[] args) {
        var market = new Market();
        var producer = new Producer(market);
        var consumer = new Consumer(market);
        var thread1 = new Thread(producer);
        var thread2 = new Thread(consumer);
        thread1.start();
        thread2.start();
    }
}

class Market {
    private int breadCount = 0;

    public synchronized void getBread() {
        while (breadCount < 1) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        breadCount--;
        System.out.println("Потребитель купил 1 хлеб");
        System.out.println("Количество хлеба в магазине : " + breadCount);
        System.out.println();
        notify();
    }

    public synchronized void putBread() {
        while (breadCount > 4) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        breadCount++;
        System.out.println("Производитель произвел 1 хлеб");
        System.out.println("Количество хлеба в магазине : " + breadCount);
        System.out.println();
        notify();
    }
}

class Producer implements Runnable {
    Market market;

    public Producer(Market market) {
        this.market = market;
    }
    @Override
    public void run() {
        for (var i = 0; i < 10; i++) {
            market.putBread();
        }
    }
}

class Consumer implements Runnable {
    Market market;

    public Consumer(Market market) {
        this.market = market;
    }
    @Override
    public void run() {
        for (var i = 0; i < 10; i++) {
            market.getBread();
        }
    }
}
