package com.acme.edu;

import static java.lang.System.lineSeparator;

public class IntMatrixCommand extends IntMassivesCommand {

    private int[][] messageTwoDim;
    public IntMatrixCommand(int[][] message) {
        this.messageTwoDim = message;
    }

    @Override
    public String decor() {
        String newMessage = "{" + lineSeparator();
        for (int [] current: messageTwoDim) {
            message = current;
            newMessage += splitMassive() + lineSeparator();
        }
        newMessage += "}";
        return "primitives matrix: " + newMessage;
        //saveLog(primitive + "s matrix",newMessage);
       // return null;
    }

    @Override
    public void acc(Command lastCmd) {
        if (lastCmd == null) return;
        lastCmd.clean();
    }
}
