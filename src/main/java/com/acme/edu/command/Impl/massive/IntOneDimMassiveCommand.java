package com.acme.edu.command.Impl.massive;

public class IntOneDimMassiveCommand extends IntMassivesCommand {

    public IntOneDimMassiveCommand(int[] message) {
        this.message = message;
    }

    @Override
    public String decor() {
        return "primitives array: " + splitMassive();
    }
}
