package com.acme.edu.saver;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by Java_2 on 25.07.2019.
 */
public class LogFileSaver extends LogSaver {

    private final BufferedWriter writer;
    private final File file;
    private final FileWriter fileWriter;

    public LogFileSaver(String fileName) throws IOException {
        file = new File(fileName);
        fileWriter = new FileWriter(file);
        writer = new BufferedWriter(fileWriter);
    }

    @Override
    protected void save() throws IOException {
        writer.write(buffer);
        if (writer != null) {
            try {
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (fileWriter != null) {
            try {
                fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
