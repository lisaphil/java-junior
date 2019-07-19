package com.acme.edu.command.Impl.massive;

import com.acme.edu.command.Impl.massive.IntMassivesCommand;

public class IntOneDimMassiveCommand extends IntMassivesCommand {

    public IntOneDimMassiveCommand(int[] message) {
        this.message = message;
    }

    @Override
    public boolean isSimilarType(com.acme.edu.Command cmd) {
        return false;
    }

    @Override
    public String decor() {
        return "primitives array: " + splitMassive();
    }
}
