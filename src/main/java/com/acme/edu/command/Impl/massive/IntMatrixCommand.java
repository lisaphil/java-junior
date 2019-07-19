package com.acme.edu.command.Impl.massive;

import com.acme.edu.Command;
import com.acme.edu.command.Impl.massive.IntMassivesCommand;

import static java.lang.System.lineSeparator;

public class IntMatrixCommand extends IntMassivesCommand {

    private int[][] messageTwoDim;
    public IntMatrixCommand(int[][] message) {
        this.messageTwoDim = message;
    }

    @Override
    public boolean isSimilarType(Command cmd) {
        return false;
    }

    @Override
    public String decor() {
        String newMessage = "{" + lineSeparator();
        for (int [] current: messageTwoDim) {
            message = current;
            newMessage += splitMassive() + lineSeparator();
        }
        newMessage += "}" + lineSeparator();
        return "primitives matrix: " + newMessage;
    }
}
