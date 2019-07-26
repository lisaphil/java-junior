package com.acme.edu.client;

import com.acme.edu.command.Command;
import com.acme.edu.command.Impl.IntCommand;

import java.io.*;
import java.net.Socket;

/**
 * Created by Java_2 on 26.07.2019.
 */
public class ClientProxy {

    private static void log(String command, BufferedWriter out, BufferedReader in) throws IOException {

            out.write(command);
            out.newLine();
            out.flush();
           // System.out.println(in.readLine());


    }
    public static void main(String[] args) {
        try(final Socket server = new Socket("localhost", 666)) {

            try (final BufferedReader in =
                         new BufferedReader(
                                 new InputStreamReader(
                                         new BufferedInputStream(
                                                 server.getInputStream(), 100)));
                 final BufferedWriter out =
                         new BufferedWriter(
                                 new OutputStreamWriter(
                                         new BufferedOutputStream(
                                                 server.getOutputStream(), 10)))) {


                log("IntCommand:5", out, in);
                log("IntCommand:7", out, in);
                log("StringCommand:str2", out, in);
                log("flush", out, in);
                //log("IntCommand: 6", out, in);

            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
