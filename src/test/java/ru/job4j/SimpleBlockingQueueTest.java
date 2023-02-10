package ru.job4j;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class SimpleBlockingQueueTest {

    @Test
    public void whenExecute2ThreadsThenPoll2() throws InterruptedException {
        var queue = new SimpleBlockingQueue<Integer>(4);
        var producer = new Thread(() -> {
            queue.offer(1);
            queue.offer(2);
            queue.offer(3);
        });
        var consumer = new Thread(queue::poll);
        producer.start();
        consumer.start();
        producer.join();
        consumer.join();
        assertThat(queue.poll()).isEqualTo(2);
    }

    @Test
    public void whenExecute2ThreadsThenPoll4() throws InterruptedException {
        var queue = new SimpleBlockingQueue<Integer>(4);
        var producer = new Thread(() -> {
            queue.offer(1);
            queue.offer(2);
            queue.offer(3);
            queue.offer(4);
            queue.offer(5);
        });
        var consumer = new Thread(() -> {
            queue.poll();
            queue.poll();
            queue.poll();
        });
        producer.start();
        consumer.start();
        producer.join();
        consumer.join();
        assertThat(queue.poll()).isEqualTo(4);
    }

    @Test
    public void whenExecute2ThreadsThenPoll5() throws InterruptedException {
        var queue = new SimpleBlockingQueue<Integer>(4);
        var producer = new Thread(() -> {
            queue.offer(1);
            queue.offer(2);
            queue.offer(3);
            queue.offer(4);
            queue.offer(5);

        });
        var consumer = new Thread(() -> {
            queue.poll();
            queue.poll();
            queue.poll();
            queue.poll();

        });
        producer.start();
        consumer.start();
        producer.join();
        consumer.join();
        assertThat(queue.poll()).isEqualTo(5);
    }

}