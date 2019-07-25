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
    public String handle() {
        return clean();
    }

    @Override
    public String clean() {
        return decor();
    }

    public String accamulate(Command lastCommand){
        if (lastCommand == null || lastCommand instanceof IntMassivesCommand) return "";
        return lastCommand.clean();
    }

    public abstract String decor();
}
