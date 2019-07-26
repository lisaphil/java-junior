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
    public static void main(String[] args) throws IllegalArgumentException {
        try (final ServerSocket serverSocket = new ServerSocket(666)) {
            Logger logger = new Logger(new LogFileSaver("test.txt"));
            String messageFromClient = "";
            while (!(messageFromClient.equals("flush"))) {

                Socket client = serverSocket.accept();
                try (BufferedReader in =
                             new BufferedReader(
                                     new InputStreamReader(
                                             new BufferedInputStream(
                                                     client.getInputStream())));
                     final BufferedWriter out =
                             new BufferedWriter(
                                     new OutputStreamWriter(
                                             new BufferedOutputStream(
                                                     client.getOutputStream())))) {
                    messageFromClient = in.readLine();
                    while (!(messageFromClient.equals("flush"))) {
                        String[] parsedString = new String[2];
                        int i = 0;
                        for (String retval : messageFromClient.split(":")) {
                          //  System.out.println(retval);
                            parsedString[i] = retval;
                            i++;
                        }
                        String typeOfCommand = parsedString[0];
                        String valueOfCommand = parsedString[1];
                        switch (typeOfCommand) {
                            case "IntCommand":
                                logger.log(new IntCommand(Integer.parseInt(valueOfCommand)));
                               // System.out.println("i'm here2");
                                break;
                            case "StringCommand":
                                logger.log(new StringCommand(valueOfCommand));
                                break;
                            case "ByteCommand":
                                logger.log(new ByteCommand(Byte.parseByte(valueOfCommand)));
                                break;
                            default:
                                //System.out.println("i'm here3");
                                break;

                        }
                        messageFromClient = in.readLine();
                    }
                    logger.flush();

                } catch (IOException e) {
                    e.printStackTrace();
                } catch (FlushException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
