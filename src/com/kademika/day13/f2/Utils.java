package com.kademika.day13.f2;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * Created by dean on 6/8/15.
 */
public class Utils {

    public static int transmogrify(int data) {
        if (Character.isLetter(data)) {
            return data ^ ' ';
        }
        return data;
    }

    public static void process(Socket socket) {
        System.out.println("Connection from " + socket);
        try (
                InputStream in = socket.getInputStream();
                OutputStream out = socket.getOutputStream();
                ) {
            int data;
            while ((data = in.read()) != -1) {
                data = Utils.transmogrify(data);
                out.write(data);
            }
        } catch (IOException e) {
            System.out.println("Connection problem - " + e);
        }
    }

}
