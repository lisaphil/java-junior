package com.acme.edu.command.Impl;

import com.acme.edu.command.Command;
import com.acme.edu.command.Impl.massive.IntMassivesCommand;
import com.acme.edu.command.comparable.Comparable;

import static java.lang.System.lineSeparator;

public class IntCommand implements Command, Comparable {
    private int value;
    private int message;

    public IntCommand(int message) {
        this.message = message;
        value = 0;
    }

    private boolean checkOverflow() {
        long result = (long) message + value;
        return result > (long) Integer.MAX_VALUE;
    }

    public int getValue() {
        return value;
    }

    @Override
    public boolean isSimilarType(Command command) {
        return command instanceof IntCommand;
    }

    @Override
    public String handle() {
        String cleanResult = "";
        if (checkOverflow()) {
            cleanResult = clean();
        }
        value += message;
        return cleanResult;
    }

    @Override
    public String clean() {
        String decor = decor();
        value = 0;
        return decor;
    }

    @Override
    public String decor() {
        return "primitive: "+ String.valueOf(value) + lineSeparator();
    }

    @Override
    public String accamulate(Command lastCommand) {
        if (lastCommand == null || lastCommand instanceof IntMassivesCommand) return "";
        if (isSimilarType(lastCommand)) {
            value = ((IntCommand) lastCommand).getValue();
            return "";
        }
        return lastCommand.clean();
    }
}
