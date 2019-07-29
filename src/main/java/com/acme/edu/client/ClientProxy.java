package com.acme.edu.client;

import com.acme.edu.command.Command;
import com.acme.edu.command.Impl.IntCommand;

import java.io.*;
import java.net.Socket;

/**
 * Created by Java_2 on 26.07.2019.
 */
public class ClientProxy {

    private static void log(String command, BufferedWriter out) throws IOException {
            out.write(command);
            out.newLine();
            out.flush();
    }

    public static void main(String[] args) {
        try(final Socket server = new Socket("localhost", 666)) {
            try (final BufferedWriter out =
                         new BufferedWriter(
                                 new OutputStreamWriter(
                                         new BufferedOutputStream(
                                                 server.getOutputStream(), 10)))) {
                log("IntCommand:5", out);
                log("IntCommand:7", out);
                log("StringCommand:str2", out);
                log("flush", out);
                //log("IntCommand: 6", out, in);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
    /*final BufferedReader in =
            new BufferedReader(
                    new InputStreamReader(
                            new BufferedInputStream(
                                    server.getInputStream(), 100)));*/