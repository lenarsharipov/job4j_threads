package ru.job4j.io;

import java.io.*;
import java.util.function.Predicate;

public class ParseFile implements Parser {
    private final File file;
    private final Saver saver;

    public ParseFile(File file, Saver saver) {
        this.file = file;
        this.saver = saver;
    }

    public String getContent(Predicate<Character> filter) {
        var output = new StringBuilder();
        int data;
        try (var i = new BufferedInputStream(new FileInputStream(file))) {
            while ((data = i.read()) != -1) {
                if (filter.test((char) data)) {
                    output.append((char) data);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return output.toString();
    }

    public String getContentWithoutUnicode(Predicate<Character> filter) {
        return getContent(filter);
    }

    public void saveContent(final String content) {
        saver.saveContent(content);
    }

}
