package com.acme.edu.command.Impl;

import com.acme.edu.command.Command;
import com.acme.edu.command.Impl.massive.IntMassivesCommand;
import com.acme.edu.command.comparable.Comparable;

import static java.lang.System.lineSeparator;

public class StringCommand implements Command, Comparable {
    private int numberOfString;
    private String message;
    private  String lastMessage;

    public StringCommand(String message) {
        this.message = message;
        numberOfString = 0;
        lastMessage = "";
    }

    private boolean isSimilarValue() {
        return message.equals(lastMessage);
    }

    public String getLastMessage() {
        return lastMessage;
    }

    public int getNumberOfString() {
        return numberOfString;
    }

    @Override
    public boolean isSimilarType(Command cmd) {
        return cmd instanceof StringCommand;
    }

    @Override
    public void handle() {
        if (isSimilarValue()) {
            numberOfString++;
        } else {
            clean();
            numberOfString = 1;
            lastMessage = message;
        }
    }

    @Override
    public void clean() {
        if (numberOfString != 0) {
            System.out.print(decor());
            numberOfString = 0;
            lastMessage = "";
        }
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
        if (lastCmd == null || lastCmd instanceof IntMassivesCommand) return;
        if (isSimilarType(lastCmd)) {
            lastMessage = ((StringCommand) lastCmd).getLastMessage();
            numberOfString = ((StringCommand) lastCmd).getNumberOfString();
            return;
        }
        lastCmd.clean();
    }
}
