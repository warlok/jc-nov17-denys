package com.kademika.day13.f4;

import com.kademika.day13.f2.Utils;

import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by dean on 6/8/15.
 */
public class NewIOServer {

    public static void main(String[] args) throws Exception {
        ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.bind(new InetSocketAddress("localhost", 7070));
        ExecutorService pool = Executors.newCachedThreadPool();

        while (true) {
            final SocketChannel socketChannel = ssc.accept();
            pool.submit(new Runnable() {
                @Override
                public void run() {
                    com.kademika.day13.f4.Utils.process(socketChannel);
                }
            });

        }
    }
}
