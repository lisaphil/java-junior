package com.acme.edu;

public class Logger {
    private static String primitive = "primitive: ";
    private static void printLog (String type, String message) {
        System.out.println(type + message);
    }
    public static void log(int message) {
        printLog(primitive, String.valueOf(message));
    }

    /*public static void log(byte message) {
        System.out.println("primitive: " + message);
    }*/

    public static void log(char message) {
        printLog("char: ", String.valueOf(message));
    }

    public static void log(String message) {
        printLog("string: ", String.valueOf(message));
    }

    public static void log(boolean message) {
        printLog(primitive, String.valueOf(message));
    }

    public static void log(Object message) {
        if (message == null) {
            printLog("reference: ", "@null");
            return;
        }
        printLog("reference: ", String.valueOf(message));
    }




}
