package com.acme.edu;

import static com.acme.edu.CommandType.*;
import static java.lang.System.lineSeparator;

public class Logger {
    private static String primitive = "primitive";
    private static int value;
    private static boolean cleanBuffer;
    private static String buf;

    private static String lastString;
    private static int numberOfString;
    public static CommandType currentType = NONE;

    static {
        cleanBuffer = false;
        value = 0;
        buf = "";
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

    public static void flash() {
        cleanBuf();
        saveLogStr();
        System.out.println(buf);
    }

    private  static String splitMassive (int [] massive) {
        String newMessage = "{";
        for (int i = 0; i < massive.length - 1; i++ ){
            newMessage += String.valueOf(massive[i]) + ", ";
        }
        newMessage += String.valueOf(massive[massive.length - 1]) + "}";
        return newMessage;

    }

    public void log (Command message) {

    }
    public static void log (int [][] message) {
        String newMessage = "{" + lineSeparator();
        for (int [] current: message) {
            newMessage += splitMassive(current) + lineSeparator();
        }
        newMessage += "}";
        saveLog(primitive + "s matrix",newMessage);
    }

    public static void log (int [] message) {
        switch (currentType){
            case BYTE:
                cleanBuf();
                break;
            case STRING:
                saveLogStr();
                break;
            default:
                break;
        }
        saveLog(primitive + "s array",splitMassive(message));
        currentType = INT;
    }

    public static void log(int message) {
        //saveLogStr("");
        switch (currentType){
            case BYTE:
                cleanBuf();
                break;
            case STRING:
                saveLogStr();
                numberOfString = 0;
                lastString = "";
                break;
            default:
                break;
        }
        long result = (long) message + value;
        if (result >= (long) Integer.MAX_VALUE) {
            if (cleanBuffer) cleanBuf();
            saveLog(primitive, String.valueOf(message));
            return;
            }
        cleanBuffer = true;
        value += message;
        currentType = INT;
    }

    public static void log(byte message) {
        switch (currentType){
            case INT:
                cleanBuf();
                break;
            case STRING:
                saveLogStr();
                numberOfString = 0;
                lastString = "";
                break;
            default:
                break;
        }
        int result = message + value;
        if ( result >= (int) Byte.MAX_VALUE){
            if (cleanBuffer) cleanBuf();
            saveLog(primitive, String.valueOf(message));
            return;
        }
        cleanBuffer = true;
        value += message;
        currentType = BYTE;
    }

    public static void log(char message) {
        saveLogStr();
        String type = "char";
        saveLog(type, String.valueOf(message));
    }

    public static void log (String ... message) {
        for (String current: message){
            log (current);
        }
    }

    public static void log(String message) {
        if (lastString.equals(message)  ) {
            numberOfString++;
        } else {
            saveLogStr();
            numberOfString = 1;
            lastString = message;
        }
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
