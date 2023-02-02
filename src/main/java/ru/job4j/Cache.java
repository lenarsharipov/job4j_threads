package ru.job4j;

public class Cache {
    private static Cache cache;

    public static Cache instOf() {
        if (cache == null) {
            cache = new Cache();
        }
        System.out.println(cache);
        return cache;
    }
}
