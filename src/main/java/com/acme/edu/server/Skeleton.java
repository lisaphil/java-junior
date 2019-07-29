package com.acme.edu.server;

import com.acme.edu.Logger.Logger;
import com.acme.edu.command.Command;
import com.acme.edu.command.Impl.ByteCommand;
import com.acme.edu.command.Impl.IntCommand;
import com.acme.edu.command.Impl.StringCommand;
import com.acme.edu.exception.FlushException;
import com.acme.edu.exception.IllegalArgumentException;
import com.acme.edu.saver.LogFileSaver;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.regex.Matcher;

/**
 * Created by Java_2 on 25.07.2019.
 */
public class Skeleton {
    public static void main(String[] args) throws IOException {
         ServerSocket serverSocket = new ServerSocket(666);
            new Thread ( () -> {
                while (true) {
                    try {
                        final Socket client = serverSocket.accept();
                        new Session(client, "").start();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
    }
}

class Session extends Thread {
    private Socket client;
    private BufferedReader in;
    private BufferedWriter out;
    private String messageFromClient;

    public Session(Socket client, String messageFromClient) throws IOException {
        this.messageFromClient = messageFromClient;
        this.client = client;

        in = new BufferedReader(
                new InputStreamReader(
                        new BufferedInputStream(
                                this.client.getInputStream())));
        out = new BufferedWriter(
                new OutputStreamWriter(
                        new BufferedOutputStream(
                                this.client.getOutputStream())));
    }

    @Override
    public void run() {
        try {
            Logger logger = new Logger(new LogFileSaver("test.txt"));
            messageFromClient = in.readLine();
            while (!(messageFromClient.equals("flush"))) {
                String[] parsedString = new String[2];
                int i = 0;
                for (String retval : messageFromClient.split(":")) {
                    parsedString[i] = retval;
                    i++;
                }
                String typeOfCommand = parsedString[0];
                String valueOfCommand = parsedString[1];
                switch (typeOfCommand) {
                    case "IntCommand":
                        logger.log(new IntCommand(Integer.parseInt(valueOfCommand)));
                        break;
                    case "StringCommand":
                        logger.log(new StringCommand(valueOfCommand));
                        break;
                    case "ByteCommand":
                        logger.log(new ByteCommand(Byte.parseByte(valueOfCommand)));
                        break;
                    default:
                        break;
                }
                messageFromClient = in.readLine();
            }
            logger.flush();
        } catch (IOException | FlushException | IllegalArgumentException e) {
            e.printStackTrace();
        }
    }
}
