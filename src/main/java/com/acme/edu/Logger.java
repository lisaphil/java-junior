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

    public void log (String ... message) {
        for (String current: message){
            log (current);
        }
    }

    public void log(Command cmd) {
        cmd.acc(lastCmd);
        cmd.handle();
        lastCmd = cmd;
    }


  /*  public static void log(boolean message) {
        saveLogStr();
        saveLog(primitive, String.valueOf(message));
    }

    public static void log(Object message) {
        saveLogStr();
        String type = "reference";
        if (message == null) {
            saveLog(type, "@null");
            return;
        }
        saveLog(type, String.valueOf(message));
    }*/


 /*   public static void log(char message) {
        saveLogStr();
        String type = "char";
        saveLog(type, String.valueOf(message));
    }*/

}
