package ru.job4j.thread;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

public class Wget implements Runnable {
    private final String url;
    private final int speed;

    public Wget(String url, int speed) {
        this.url = url;
        this.speed = speed;
    }

    @Override
    public void run() {
        try (var in = new BufferedInputStream(new URL(url).openStream());
            var out = new FileOutputStream("src/main/java/ru/job4j/thread/output/test_pom.xml")) {
            var dataBuffer = new byte[1024];
            int bytesRead;
            var bytes = 0;
            var start = System.currentTimeMillis();
            while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
                bytes += bytesRead;
                if (bytes >= speed) {
                    var total = System.currentTimeMillis() - start;
                    if (total < 1000) {
                        Thread.sleep(1000 - total);
                    }
                    bytes = 0;
                    start = System.currentTimeMillis();
                }
                out.write(dataBuffer, 0, bytesRead);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        if (args.length < 2) {
            throw new IllegalArgumentException("Passed arguments incorrect");
        }
        var url = args[0];
        var speed = Integer.parseInt(args[1]);
        var wget = new Thread(new Wget(url, speed));
        wget.start();
        wget.join();
    }
}
