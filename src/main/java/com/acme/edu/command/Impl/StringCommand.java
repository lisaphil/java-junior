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
    public boolean isSimilarType(Command command) {
        return command instanceof StringCommand;
    }

    @Override
    public String handle() {
        String cleanResult = "";
        if (isSimilarValue()) {
            numberOfString++;
        } else {
            cleanResult = clean();
            numberOfString = 1;
            lastMessage = message;
        }
        return cleanResult;
    }

    @Override
    public String clean() {
        String decor = "";
        if (numberOfString != 0) {
            decor = decor();
            numberOfString = 0;
            lastMessage = "";
        }
        return decor;
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
    public String accamulate(Command lastCommand) {
        if (lastCommand == null || lastCommand instanceof IntMassivesCommand) return "";
        if (isSimilarType(lastCommand)) {
            lastMessage = ((StringCommand) lastCommand).getLastMessage();
            numberOfString = ((StringCommand) lastCommand).getNumberOfString();
            return "";
        }
        return lastCommand.clean();
    }
}
