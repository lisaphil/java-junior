package com.acme.edu;

public class IntOneDimMassiveCommand extends IntMassivesCommand{

    public IntOneDimMassiveCommand(int[] message) {
        this.message = message;
    }

    @Override
    public String decor() {
        return "primitives array: " + splitMassive();
    }

    @Override
    public void acc(Command lastCmd) {
        if (lastCmd == null) return;
        lastCmd.clean();
    }
}
