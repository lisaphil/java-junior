package com.acme.edu.saver;

/**
 * Created by Java_2 on 25.07.2019.
 */
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public abstract class  LogSaver {
    protected String buffer = null;

    public void passBufferToSaver(String message) throws IOException {
        this.buffer = message;
        save();
    }

    protected abstract void save() throws IOException;
}
