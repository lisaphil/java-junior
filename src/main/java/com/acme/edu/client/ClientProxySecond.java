package com.acme.edu.client;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;

/**
 * Created by Java_2 on 29.07.2019.
 */
public class ClientProxySecond {
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
                log("StringCommand:str", out);
                log("StringCommand:str", out);
                log("IntCommand:20", out);
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

