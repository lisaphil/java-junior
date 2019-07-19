package com.acme.edu;

import static com.acme.edu.CommandType.*;
import static java.lang.System.lineSeparator;

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
