package com.acme.edu;

import static java.lang.System.lineSeparator;

public class Logger {
    private static String primitive = "primitive";
    private static int value;
    private static String lastString;
    private static int numberOfString;

    private static boolean cleanBuffer;
    private static String buf;

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

    private static void saveLogStr (String message){
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
        if (message == "") {
            numberOfString = 0;
        } else {
            numberOfString = 1;
        }
        lastString = message;
    }


    public static void flash() {
        cleanBuf();
        saveLogStr("");
        System.out.println(buf);
    }

    public static void log(int message) {
        saveLogStr("");
        long result = (long) message + value;
        if ( result >= (long) Integer.MAX_VALUE){
            if (cleanBuffer) cleanBuf();
            saveLog(primitive, String.valueOf(message));
            return;
        }
        cleanBuffer = true;
        value += message;
    }

    public static void log(byte message) {
        saveLogStr("");
        int result = message + value;
        if ( result >= (int) Byte.MAX_VALUE){
            if (cleanBuffer) cleanBuf();
            saveLog(primitive, String.valueOf(message));
            return;
        }
        cleanBuffer = true;
        value += message;
    }

    public static void log(char message) {
        saveLogStr("");
        String type = "char";
        saveLog(type, String.valueOf(message));
    }

    public static void log(String message) {
        if (lastString.equals(message)  ) {
            numberOfString++;
        } else {
            saveLogStr(message);
        }
    }

    public static void log(boolean message) {
        saveLogStr("");
        saveLog(primitive, String.valueOf(message));
    }

    public static void log(Object message) {
        saveLogStr("");
        String type = "reference";
        if (message == null) {
            saveLog(type, "@null");
            return;
        }
        saveLog(type, String.valueOf(message));
    }




}
