package com.acme.edu.command.Impl;

import com.acme.edu.command.Command;
import com.acme.edu.command.Impl.massive.IntMassivesCommand;
import com.acme.edu.command.comparable.Comparable;

import static java.lang.System.lineSeparator;

public class ByteCommand implements Command, Comparable {
    private byte value;
    private byte message;
    //boolean cleanValue;

    public ByteCommand(byte message) {
        this.message = message;
        value = 0;
    }

    boolean checkOverflow() {
        int result = message + value;
        return result > (int) Byte.MAX_VALUE;
    }

    public byte getValue() {
        return value;
    }

    @Override
    public boolean isSimilarType(Command command) {
        return command instanceof ByteCommand;
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
            value = ((ByteCommand) lastCommand).getValue();
            return "";
        }
        return lastCommand.clean();
    }

}
