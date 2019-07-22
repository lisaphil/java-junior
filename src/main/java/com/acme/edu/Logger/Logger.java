package com.acme.edu.Logger;


import com.acme.edu.command.Command;
import com.acme.edu.command.Impl.massive.IntMassivesCommand;


public class Logger {

    private Command cmd;
    private Command lastCmd;

    public Logger() {
        lastCmd = null;
        cmd = null;
    }

    public void flash() {
        if (!(lastCmd instanceof IntMassivesCommand)) {
            lastCmd.clean();
        }
    }

    public void log(Command cmd) {
        cmd.acc(lastCmd);
        cmd.handle();
        lastCmd = cmd;
    }
}
