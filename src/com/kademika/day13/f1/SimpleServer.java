package com.kademika.day13.f1;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by dean on 6/3/15.
 */
public class SimpleServer {

    public static void main(String[] args) throws IOException {

        ServerSocket ss = new ServerSocket(7070);
        while (true) {
            Socket socket = ss.accept();
            try (   InputStream in = socket.getInputStream();
                    OutputStream out = socket.getOutputStream()
                    ) {
                int data;
                while ((data = in.read()) != -1) {
                    data = transmogrify(data);
                    out.write(data);
                }
            }
        }
    }

    public static int transmogrify(int data) {
        if (Character.isLetter(data)) {
            return data ^ ' ';
        }
        return data;
    }

}
