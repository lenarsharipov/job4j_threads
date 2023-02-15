package ru.job4j.pool;

import ru.job4j.SimpleBlockingQueue;

import java.util.LinkedList;
import java.util.List;

/**
 * Пул - это хранилище для ресурсов, которые можно переиспользовать.
 * Клиент берет ресурс из пула, выполняет свою работу и возвращает обратно в пул.
 *
 * @author Lenar Sharipov
 * @version 1.0
 */
public class ThreadPool {
    /**
     * Поле tasks - это блокирующая очередь.
     * Если в очереди нет элементов, то нить переводится в состояние Thread.State.WAITING.
     * Когда приходит новая задача, всем нитям в состоянии Thread.State.WAITING посылается
     * сигнал проснуться и начать работу.
     */
    private final SimpleBlockingQueue<Runnable> tasks = new SimpleBlockingQueue<>(10);

    /**
     * Количество нитей всегда одинаковое и равно size (size - количество ядер в системе).
     * В каждую нить передается блокирующая очередь tasks.
     * В метод run передается задача из очереди tasks.
     */
    private final List<Thread> threads = new LinkedList<>();

    /**
     * Приватный вспомогательный метод addAndStartThread(), который создает,
     * добавляет вновь созданные потоки в очередь и запускает их.
     */
    private void addAndStartThread() {
        var task = new PoolThread(tasks);
        threads.add(task);
        task.start();
    }

    /**
     * Конструктор класса ThreadPool. В теле конструктора инициализируется переменная
     * size - количество ядер в системе. Соответственно, в программе будет задействовано
     * size нитей постоянно.
     * Далее вызывается вспомогательный метод addAndStartThread(), который создает, 
     * добавляет вновь созданные потоки в очередь и запускает их.
     */
    public ThreadPool() {
        int size = Runtime.getRuntime().availableProcessors();
        for (int i = 0; i < size; i++) {
            addAndStartThread();
        }
    }

    /**
     * Метод work(Runnable job) добавляет задачи в блокирующую очередь tasks.
     * @param job - задача, которая будет храниться в блокирующей очереди.
     */
    public void work(Runnable job) throws InterruptedException {
        tasks.offer(job);
        addAndStartThread();
    }

    /**
     * Метод shutdown() завершает все запущенные задачи.
     */
    public void shutdown() {
        threads.forEach(Thread::interrupt);
    }

    /**
     * Статический приватный внутренний класс - модель нити, в метод run(),
     * которого передается задача из блокирующей очереди.
     * Переданная задача запускается в ните.
     */
    private static class PoolThread extends Thread {
        SimpleBlockingQueue<Runnable> tasks;

        public PoolThread(SimpleBlockingQueue<Runnable> tasks) {
            this.tasks = tasks;
        }
        @Override
        public void run() {
            try {
                tasks.poll().run();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}