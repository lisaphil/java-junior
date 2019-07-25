package com.acme.edu.command;

public interface Command {
    String handle();
    String clean();

    String decor();
    String accamulate(Command lastCmd);
}
