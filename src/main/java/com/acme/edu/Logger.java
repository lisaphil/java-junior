package com.acme.edu;

import static com.acme.edu.CommandType.*;
import static java.lang.System.lineSeparator;

public class Logger {

    private Command cmd;
    private Command lastCmd;

    //private StringCommand strLog = new StringCommand();
    //private IntCommand intLog = new IntCommand();
    //private ByteCommand byteLog = new ByteCommand();
    //private IntOneDimMassiveCommand intMassiveLog = new IntOneDimMassiveCommand();

    private static String primitive = "primitive";
    private static int value;
    private static boolean cleanBuffer;
    private static String buf;

    private static String lastString;
    private static int numberOfString;
    public static CommandType currentType = NONE;

    public Logger() {
        cleanBuffer = false;
        value = 0;
        numberOfString = 0;
        lastString = "";
    }



    private static void cleanBuf(){
        if (cleanBuffer) {
            buf += primitive + ": " + String.valueOf(value) + lineSeparator();
            cleanBuffer = false;
            value = 0;
        }
    }

    private static void saveLog (String type, String message) {
        if (cleanBuffer) cleanBuf();
        buf += type + ": "+ message + lineSeparator();
    }

    private static void saveLogStr (){
        String type = "string";
        switch (numberOfString) {
            case 0:
                break;
            case 1:
                saveLog(type, String.valueOf(lastString));
                break;
            default:
                saveLog(type, String.valueOf(lastString) + " (x" + String.valueOf(numberOfString) + ")");
        }
    }

    public void flash() {
        lastCmd.clean();
    }

    private  static String splitMassive (int [] massive) {
        String newMessage = "{";
        for (int i = 0; i < massive.length - 1; i++ ){
            newMessage += String.valueOf(massive[i]) + ", ";
        }
        newMessage += String.valueOf(massive[massive.length - 1]) + "}";
        return newMessage;
    }

    public static void log (int [][] message) {
        String newMessage = "{" + lineSeparator();
        for (int [] current: message) {
            newMessage += splitMassive(current) + lineSeparator();
        }
        newMessage += "}";
        saveLog(primitive + "s matrix",newMessage);
    }

    public void log (int [] message) {
        cmd = new IntOneDimMassiveCommand(message);
        cmd.acc(lastCmd);
        cmd.handle();
        lastCmd = cmd;
        //intMassiveLog.handle(message);
        currentType = MASSIVE_INT;
    }

    public void log(int message) {
        cmd = new IntCommand(message);
        cmd.acc(lastCmd);
        cmd.handle();
        lastCmd = cmd;
        /*if ((currentType != INT) && (currentType != NONE)) {
            strLog.clean();
            byteLog.clean();
        }
        intLog.handle(message);*/
        currentType = INT;
    }

    public void log(byte message) {
        cmd = new ByteCommand(message);
        cmd.acc(lastCmd);
        cmd.handle();
        lastCmd = cmd;
       /* if ((currentType != BYTE) && (currentType != NONE)) {
            intLog.clean();
            strLog.clean();
        }
        byteLog.handle(message);*/
        currentType = BYTE;
    }

    public static void log(char message) {
        saveLogStr();
        String type = "char";
        saveLog(type, String.valueOf(message));
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
    public void log(String message) {
        cmd = new StringCommand(message);
        cmd.acc(lastCmd);
        cmd.handle();
        lastCmd = cmd;
        /*if ((currentType != STRING) && (currentType != NONE))  {
            intLog.clean();
            byteLog.clean();
        }
        strLog.handle(message);*/
        currentType = STRING;
    }

    public static void log(boolean message) {
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
    }


}
