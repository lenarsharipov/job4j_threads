package ru.job4j.concurrent;

public class ConsoleProgress implements Runnable {
    @Override
    public void run() {
        var count = 0;
        var process = new char[] {'-', '\\', '|', '/'};
        char symbol;
        while (!Thread.currentThread().isInterrupted()) {
            try {
                symbol = process[count % process.length];
                Thread.sleep(500);
                System.out.print("\r load: " + symbol);
                count++;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        var progress = new Thread(new ConsoleProgress());
        progress.start();
        Thread.sleep(5000);
        progress.interrupt();
    }
}
