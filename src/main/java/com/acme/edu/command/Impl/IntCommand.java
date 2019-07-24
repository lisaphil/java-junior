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
    public boolean isSimilarType(Command cmd) {
        return cmd instanceof IntCommand;
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
            value = ((IntCommand) lastCmd).getValue();
            return;
        }
        lastCmd.clean();
    }
}
