package ru.job4j;

/**
 * Данный класс блокирует выполнение по условию счетчика.
 *
 * @author Lenar Sharipov
 * @version 1.0
 */
public class CountBarrier {
    private final Object monitor = this;

    /**
     * Переменная total содержит количество вызовов метода count().
     */
    private final int total;
    private int count = 0;

    public CountBarrier(final int total) {
        this.total = total;
    }

    /**
     * Метод count изменяет состояние программы.
     * Внутри метода count вызывается метод notifyAll.
     */
    public void count() {
        synchronized (monitor) {
            count++;
            monitor.notifyAll();
        }
    }

    /**
     * Нити, которые выполняют метод await, могут начать работу если поле count >= total.
     * Если оно не равно, то нужно перевести нить в состояние wait.
     * Для проверки состояния используется цикл while.
     */
    public void await() {
        synchronized (monitor) {
            while (count < total) {
                try {
                    monitor.wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }
}