package ru.job4j.net;

import java.io.*;
import java.net.URL;

public class FileDownload {
    public static void main(String[] args) throws Exception {
        var file = "https://raw.githubusercontent.com/peterarsentev/course_test/master/pom.xml";
        try (var in = new BufferedInputStream(new URL(file).openStream());
            var fileOutputStream = new FileOutputStream("pom_tmp.xml")) {
            var dataBuffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
                fileOutputStream.write(dataBuffer, 0, bytesRead);
                Thread.sleep(1_000);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
