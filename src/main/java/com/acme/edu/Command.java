package com.acme.edu;

public interface Command {
    void handle();
    void clean();

    boolean isSimilarType(Command cmd);
    String decor();
    void acc(Command lastCmd);
}
