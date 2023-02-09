package ru.job4j;

public class Barrier {
    /**
     * Поле flag - общий ресурс. Работаем только в критической секции.
     */
    private boolean flag = false;
    private final Object monitor = this;

    /**
     * Метод on() меняет значение поля flag с false на true.
     * После каждого изменения программа будит нити, которые ждут изменений.
     */
    public void on() {
        synchronized (monitor) {
            flag = true;
            monitor.notifyAll();
        }
    }

    /**
     * Метод off() меняет значение поля flag с true на false.
     * После каждого изменения программа будит нити, которые ждут изменений.
     */
    public void off() {
        synchronized (monitor) {
            flag = false;
            monitor.notifyAll();
        }
    }

    /**
     * Когда нить заходит в метод check(), то она проверяет flag.
     * Если flag == false, нить засыпает.
     * Когда другая нить выполнит метод on или off, то у монитора выполняется метод notifyAll.
     * Метод notifyAll переводит все нити из состояния wait в runnable.
     */
    public void check() {
        synchronized (monitor) {
            while (!flag) {
                try {
                    monitor.wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }
}
