package com.kademika.day13.f2;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.*;

/**
 * Created by dean on 6/3/15.
 */
public class SimpleThreededServer implements Runnable {

    private static ServerSocket ss;

    public static void main(String[] args) throws IOException {

        ss = new ServerSocket(7070);

        ExecutorService exec = Executors.newFixedThreadPool(3);
        for (int i=0; i<3;i++) {
            exec.submit(new SimpleThreededServer());
        }

    }

    private static void runSocket(ServerSocket ss) throws IOException {
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

    @Override
    public void run() {
        try {
            runSocket(ss);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
