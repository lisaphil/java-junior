package com.acme.edu.server;

import com.acme.edu.command.Command;
import com.acme.edu.command.Impl.IntCommand;

/**
 * Created by Java_2 on 25.07.2019.
 */
public class Skeleton {
    public static void main(String[] args) {
        Command command = new IntCommand(6);
        System.out.println(command.decor());
    }
}
