package com.acme.edu;

import static java.lang.System.lineSeparator;

public class StringCommand implements Command {
    private int numberOfString;
    private String message;
    private  String lastMessage;

    public StringCommand(String message) {
        this.message = message;
        numberOfString = 0;
        lastMessage = "";
    }

    private boolean isSimilar() {
        return message.equals(lastMessage);
    }

    public String getLastMessage() {
        return lastMessage;
    }

    public int getNumberOfString() {
        return numberOfString;
    }

    @Override
    public void handle() {
        if (isSimilar()) {
            numberOfString++;
        } else {
            clean();
            numberOfString = 1;
            lastMessage = message;
        }
    }

    @Override
    public void clean() {
        System.out.print(decor());
        numberOfString = 0;
        lastMessage = "";

    }

    @Override
    public String decor() {
        if (numberOfString == 1){
            return "string: " + String.valueOf(lastMessage) + lineSeparator();
        } else {
            return "string: " + String.valueOf(lastMessage) + " (x" + String.valueOf(numberOfString) + ")" + lineSeparator();
        }
    }

    @Override
    public void acc(Command lastCmd) {
        if (lastCmd == null) return;
        if (lastCmd instanceof StringCommand) {
            lastMessage = ((StringCommand) lastCmd).getLastMessage();
            numberOfString = ((StringCommand) lastCmd).getNumberOfString();
            return;
        }
        lastCmd.clean();
    }
}
