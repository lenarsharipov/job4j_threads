package ru.job4j.cache;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class CacheTest {
    @Test
    public void whenAdd3BasesThenAllTrue() throws InterruptedException {
        Cache cache = new Cache();
        var thread1 = new Thread(() -> {
            Base base1 = new Base(1, 0);
            base1.setName("Ivan");
            assertThat(cache.add(base1)).isTrue();

            Base base2 = new Base(2, 0);
            base2.setName("Oleg");
            assertThat(cache.add(base2)).isTrue();

            Base base3 = new Base(3, 0);
            base3.setName("Elena");
            assertThat(cache.add(base3)).isTrue();
        });
        thread1.start();
        thread1.join();
    }

    @Test
    public void whenUpdateId1WithVersion0ThenTrue() throws InterruptedException {
        Cache cache = new Cache();
        var thread1 = new Thread(() -> {
            Base base1 = new Base(1, 0);
            base1.setName("Ivan");
            assertThat(cache.add(base1)).isTrue();

            Base base2 = new Base(1, 0);
            base2.setName("Oleg");
            assertThat(cache.update(base2)).isTrue();
        });
        thread1.start();
        thread1.join();
    }

    @Test
    public void whenUpdateVersion0UpdateVersion1ThenTrue() throws InterruptedException {
        Cache cache = new Cache();
        var thread1 = new Thread(() -> {
            Base base1 = new Base(1, 0);
            base1.setName("Ivan");
            assertThat(cache.add(base1)).isTrue();

            Base base2 = new Base(1, 0);
            base2.setName("Oleg");
            assertThat(cache.update(base2)).isTrue();

            Base base3 = new Base(1, 1);
            base3.setName("Elena");
            assertThat(cache.update(base3)).isTrue();
        });
        thread1.start();
        thread1.join();
    }

    @Test
    public void whenUpdateModelWithNotExistingIdThenFalse() throws InterruptedException {
        Cache cache = new Cache();
        var thread1 = new Thread(() -> {
            Base base1 = new Base(1, 0);
            base1.setName("Ivan");
            assertThat(cache.add(base1)).isTrue();

            Base base2 = new Base(2, 0);
            base2.setName("Oleg");
            assertThat(cache.update(base2)).isFalse();
        });
        thread1.start();
        thread1.join();
    }

    @Test
    public void whenUpdateId1WithWrongVersionThenOptimisticException() throws InterruptedException {
        Cache cache = new Cache();
        var thread1 = new Thread(() -> {
            Base base1 = new Base(1, 0);
            base1.setName("Ivan");
            assertThat(cache.add(base1)).isTrue();

            Base base2 = new Base(1, 1);
            base2.setName("Oleg");
            assertThatThrownBy(() -> cache.update(base2)).isInstanceOf(OptimisticException.class)
                    .hasMessage("Versions are not equal");
        });
        thread1.start();
        thread1.join();
    }

    @Test
    public void whenAddById1DeleteById1AddById1ThenAllTrue() throws InterruptedException {
        Cache cache = new Cache();
        var thread1 = new Thread(() -> {
            Base base1 = new Base(1, 0);
            base1.setName("Ivan");
            assertThat(cache.add(base1)).isTrue();

            cache.delete(base1);

            Base base2 = new Base(1, 0);
            base2.setName("Oleg");
            assertThat(cache.add(base2)).isTrue();
        });
        thread1.start();
        thread1.join();
    }
}