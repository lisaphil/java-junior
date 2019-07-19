package com.acme.edu;

public class IntOneDimMassiveCommand extends IntMassivesCommand{

    public IntOneDimMassiveCommand(int[] message) {
        this.message = message;
    }

    @Override
    public boolean isSimilarType(Command cmd) {
        return false;
    }

    @Override
    public String decor() {
        return "primitives array: " + splitMassive();
    }
}
