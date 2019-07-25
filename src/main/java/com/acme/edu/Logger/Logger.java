package com.acme.edu.Logger;


import com.acme.edu.command.Command;
import com.acme.edu.command.Impl.massive.IntMassivesCommand;
import com.acme.edu.exception.FlushException;
import com.acme.edu.exception.IllegalArgumentException;
import com.acme.edu.saver.LogSaver;

import java.io.IOException;


public class Logger {

    private Command lastCommand;

    private String buffer;
    //private BufferedWriter writer;
    private LogSaver saver;


    public Logger(LogSaver logSaver) {
        lastCommand = null;
        //writer = null;
        saver = logSaver;
        buffer = "";
    }
    public Command getLastCommand() {
        return lastCommand;
    }


    public boolean isLastCommandMassive() {
        return lastCommand instanceof IntMassivesCommand;
    }



    public void flush() throws FlushException, IOException {
        if (lastCommand == null) throw new FlushException();
        if (!isLastCommandMassive()) {
            buffer += lastCommand.clean();
        }
        //saver = logSaver;
        saver.passBufferToSaver(buffer);
    }

    public void log(Command command) throws IllegalArgumentException {
        if (command == null) throw new IllegalArgumentException();
        String message = command.accamulate(lastCommand) + command.handle();
        buffer += message;
        lastCommand = command;
    }
}
