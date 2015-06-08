package com.kademika.day13.f2;

import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by dean on 6/8/15.
 */
public class SimpleThreedServer_Oleh {

    public static void main(String[] args) throws Exception {
        ServerSocket ss = new ServerSocket(7070);

        while (true) {
            final Socket socket = ss.accept();

            new Thread(new Runnable() {
                @Override
                public void run() {
                    Utils.process(socket);
                }
            }).start();
        }
    }
}
