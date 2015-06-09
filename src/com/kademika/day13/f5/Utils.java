package com.kademika.day13.f5;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

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

    public static void process(SocketChannel socketChannel) {
        System.out.println("Connection from " + socketChannel);
        try {
            ByteBuffer buffer = ByteBuffer.allocateDirect(1024);
            while(socketChannel.read(buffer) != -1) {
                buffer.flip();
                for (int i = 0; i < buffer.limit(); i++) {
                    buffer.put(i, (byte) transmogrify(buffer.get(i)));
                }
                socketChannel.write(buffer);
                buffer.clear();
            }
        } catch (IOException e) {
            System.out.println("Connection problem - " + e);
        }
    }

}
