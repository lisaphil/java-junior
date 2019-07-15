package com.acme.edu;

public class Logger {
    private static String primitive = "primitive";

    private static void printLog (String type, String message) {
        System.out.println(type + ": "+ message);
    }

    public static void log(int message) {
        printLog(primitive, String.valueOf(message));
    }

    public static void log(char message) {
        java.lang.String type = "char";
        printLog(type, String.valueOf(message));
    }

    public static void log(String message) {
        java.lang.String type = "string";
        printLog(type, String.valueOf(message));
    }

    public static void log(boolean message) {
        printLog(primitive, String.valueOf(message));
    }

    public static void log(Object message) {
        java.lang.String type = "reference";
        if (message == null) {
            printLog(type, "@null");
            return;
        }
        printLog(type, String.valueOf(message));
    }




}
