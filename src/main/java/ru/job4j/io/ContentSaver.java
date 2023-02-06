package ru.job4j.io;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class ContentSaver implements Saver {
    private final File file;

    public ContentSaver(final File file) {
        this.file = file;
    }
    @Override
    public void saveContent(String content) {
        try (var o = new BufferedOutputStream(new FileOutputStream(file))) {
            for (int i = 0; i < content.length(); i++) {
                o.write(content.charAt(i));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
