package com.acme.edu.command.Impl.massive;

import com.acme.edu.command.Command;

import static java.lang.System.lineSeparator;

public abstract class  IntMassivesCommand implements Command {

    int[] message;

    protected String splitMassive () {
        String newMessage = "{";
        for (int i = 0; i < message.length - 1; i++ ){
            newMessage += String.valueOf(message[i]) + ", ";
        }
        newMessage += String.valueOf(message[message.length - 1]) + "}";
        return newMessage + lineSeparator();
    }

    @Override
    public void handle() {
        clean();
    }

    @Override
    public void clean() {
        System.out.print(decor());
    }

    public void acc(Command lastCmd){
        if (lastCmd == null || lastCmd instanceof IntMassivesCommand) return;
        lastCmd.clean();
    }

    public abstract String decor();
}
