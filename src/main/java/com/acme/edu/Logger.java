package com.acme.edu;

import static java.lang.System.lineSeparator;

public class Logger {
    private static String primitive = "primitive";
    private static int value;
    private static boolean cleanBuffer;
    private static String buf;


    private static void cleanBuf(){
        if (cleanBuffer) {
            buf += primitive + ": " + String.valueOf(value) + lineSeparator();
            cleanBuffer = false;
            value = 0;
        }
    }

    private static void saveLog (String type, String message) {
        cleanBuf();
        buf += type + ": "+ message + lineSeparator();
    }
    public static void start () {
        cleanBuffer = false;
        value = 0;
    }

    public static void flash() {
        cleanBuf();
        System.out.println(buf);
    }
    public static void log(int message) {
        cleanBuffer = true;
        value += message;
   //     printLog(primitive, String.valueOf(message));
    }

    public static void log(char message) {
        java.lang.String type = "char";
        saveLog(type, String.valueOf(message));
    }

    public static void log(String message) {
        java.lang.String type = "string";
        saveLog(type, String.valueOf(message));
    }

    public static void log(boolean message) {
        saveLog(primitive, String.valueOf(message));
    }

    public static void log(Object message) {
        java.lang.String type = "reference";
        if (message == null) {
            saveLog(type, "@null");
            return;
        }
        saveLog(type, String.valueOf(message));
    }




}
