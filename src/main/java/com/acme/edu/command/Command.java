package com.acme.edu.command;

public interface Command {
    void handle();
    void clean();

    String decor();
    void accamulate(Command lastCmd);
}
