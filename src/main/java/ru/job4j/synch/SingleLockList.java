package ru.job4j.synch;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@ThreadSafe
public final class SingleLockList<T> implements Iterable<T> {
    @GuardedBy("list")
    private final List<T> list;

    public SingleLockList(List<T> list) {
        this.list = copy(list);
    }

    @GuardedBy("list")
    public synchronized void add(T value) {
        list.add(value);
    }

    @GuardedBy("list")
    public synchronized T get(int index) {
        return list.get(index);
    }

    @GuardedBy("list")
    @Override
    public synchronized Iterator<T> iterator() {
        return copy(list).iterator();
    }

    @GuardedBy("list")
    private synchronized List<T> copy(List<T> origin) {
        return new ArrayList<>(origin);
    }

}