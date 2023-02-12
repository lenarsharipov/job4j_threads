package ru.job4j;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Версия bounded blocking queue - это блокирующая очередь, ограниченная по размеру.
 * Producer помещает данные в очередь, а Consumer извлекает данные из очереди.
 * Если очередь заполнена полностью, то при попытке добавления поток Producer блокируется,
 * до тех пор пока Consumer не извлечет очередные данные, т.е. в очереди появится свободное место.
 * И наоборот если очередь пуста поток Consumer блокируется, до тех пор пока Producer не поместит
 * в очередь данные.
 *
 * @author Lenar Sharipov
 * @version 1.0
 *
 * @param <T> тип добавляемого значения.
 */
@ThreadSafe
public class SimpleBlockingQueue<T> {
    @GuardedBy("this")
    private final Queue<T> queue = new LinkedList<>();

    /**
     * Размер очереди, задается в конструкторе при создании объекта.
     */
    private final int capacity;

    public SimpleBlockingQueue(int capacity) {
        this.capacity = capacity;
    }

    /**
     * Метод offer() добавляет объект во внутреннюю коллекцию.
     * Если коллекция заполнена (queue.size == capacity),
     * то текущая нить переходит в состояние ожидания.
     * @param value добавляемое значение.
     */
    public void offer(T value) throws InterruptedException {
        synchronized (this) {
            while (queue.size() == capacity) {
                wait();
            }
            queue.offer(value);
            notifyAll();
        }
    }

    /**
     * Метод poll() возвращает объект из внутренней коллекции.
     * Если в коллекции объектов нет, то текущая нить переходит в состояние ожидания.
     * @return первый объект в очереди.
     */
    public T poll() throws InterruptedException {
        synchronized (this) {
            while (queue.size() == 0) {
                wait();
            }
            T result = queue.poll();
            notifyAll();
            return  result;
        }
    }

    /**
     * @return значение true, при пустой очереди, либо false,
     * если в очереди есть хотя бы один элемент.
     */
    public boolean isEmpty() {
        synchronized (this) {
            return queue.isEmpty();
        }
    }

}
