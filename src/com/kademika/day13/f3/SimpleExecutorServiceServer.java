package com.kademika.day13.f3;

import com.kademika.day13.f2.Utils;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by dean on 6/8/15.
 */
public class SimpleExecutorServiceServer {

    public static void main(String[] args) throws Exception {
        ServerSocket ss = new ServerSocket(7070);
        ExecutorService pool = Executors.newCachedThreadPool();

        while (true) {
            final Socket socket = ss.accept();
            pool.submit(new Runnable() {
                @Override
                public void run() {
                    com.kademika.day13.f3.Utils.process(socket);
                }
            });

        }
    }
}
