package com.acme.edu.Logger;


import com.acme.edu.command.Command;
import com.acme.edu.command.Impl.massive.IntMassivesCommand;
import com.acme.edu.exception.FlushException;
import com.acme.edu.exception.IllegalArgumentException;


public class Logger {

    private Command lastCmd;

    public Command getLastCmd() {
        return lastCmd;
    }


    public boolean isLastCommandMassive() {
        return lastCmd instanceof IntMassivesCommand;
    }

    public Logger() {
        lastCmd = null;
    }

    public void flash() throws FlushException {
        if (lastCmd == null) throw new FlushException();
        if (!isLastCommandMassive()) {
            lastCmd.clean();
        }
    }

    public void log(Command cmd) throws IllegalArgumentException {
        if (cmd == null) throw new IllegalArgumentException();

        cmd.accamulate(lastCmd);
        cmd.handle();
        lastCmd = cmd;
    }
}
