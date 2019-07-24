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
    public boolean isSimilarType(Command cmd) {
        return cmd instanceof ByteCommand;
    }

    @Override
    public void handle() {
        if (checkOverflow()) {
            clean();
        }
        value += message;
    }

    @Override
    public void clean() {
        System.out.print(decor());
        value = 0;
    }

    @Override
    public String decor() {
        return "primitive: "+ String.valueOf(value) + lineSeparator();
    }

    @Override
    public void accamulate(Command lastCmd) {
        if (lastCmd == null || lastCmd instanceof IntMassivesCommand) return;
        if (isSimilarType(lastCmd)) {
            value = ((ByteCommand) lastCmd).getValue();
            return;
        }
        lastCmd.clean();
    }

}
