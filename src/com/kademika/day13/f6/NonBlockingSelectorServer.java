package com.kademika.day13.f6;


import com.kademika.day13.f2.Utils;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.*;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.ConcurrentLinkedQueue;


/**
 * Created by dean on 6/8/15.
 */
public class NonBlockingSelectorServer {

    private static Map<SocketChannel, Queue<ByteBuffer>> pendingData = new HashMap<>();

    public static void main(String[] args) throws Exception {
        ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.bind(new InetSocketAddress("localhost", 7070));
        ssc.configureBlocking(false);

        Selector selector = Selector.open();
        SelectionKey sk = ssc.register(selector, SelectionKey.OP_ACCEPT);

        while (true) {
            selector.select();
            for (Iterator<SelectionKey> keyIterator = selector.selectedKeys().iterator();
                 keyIterator.hasNext(); ) {
                SelectionKey key = keyIterator.next();
                keyIterator.remove();

                if (key.isValid()) {
                    if (key.isAcceptable()) {
                        accept(key);
                    } else if (key.isReadable()) {
                        read(key);
                    } else if (key.isWritable()) {
                        write(key);
                    }
                }
            }
        }
    }

    private static void accept(SelectionKey key) throws Exception{
        ServerSocketChannel ssc = (ServerSocketChannel) key.channel();
        SocketChannel sc = ssc.accept();
        sc.configureBlocking(false);

        sc.register(key.selector(), SelectionKey.OP_READ);
        pendingData.put(sc, new ConcurrentLinkedQueue<ByteBuffer>());
    }

    private static void read(SelectionKey key) throws Exception{
        SocketChannel sc = (SocketChannel) key.channel();
        ByteBuffer buffer = ByteBuffer.allocateDirect(1024);

        int read = sc.read(buffer);
        if (read ==-1) {
            pendingData.remove(sc);
            return;
        }
        buffer.flip();
        for(int i=0; i< buffer.limit(); i++) {
            buffer.put(i, (byte) com.kademika.day13.f6.Utils.transmogrify(buffer.get(i)));
        }
        pendingData.get(sc).add(buffer);
        sc.register(key.selector(),SelectionKey.OP_WRITE);
    }

    private static void write(SelectionKey key) throws Exception{
        SocketChannel sc = (SocketChannel) key.channel();

        Queue<ByteBuffer> queue = pendingData.get(sc);
        ByteBuffer buffer;

        while ((buffer = queue.peek()) != null ) {
            sc.write(buffer);
            if(!buffer.hasRemaining()) {
                queue.poll();
            } else {
                return;
            }
        }
        sc.register(key.selector(), SelectionKey.OP_READ);
    }

}
