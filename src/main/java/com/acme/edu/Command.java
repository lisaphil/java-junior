package com.acme.edu;

public interface Command {
    void handle();
    void clean();

    String decor();
    void acc(Command lastCmd);
}
