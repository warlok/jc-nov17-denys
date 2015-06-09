package com.kademika.day13.f5;


import com.kademika.day13.f2.Utils;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;


/**
 * Created by dean on 6/8/15.
 */
public class NonBlockingServer {

    private static Collection<SocketChannel> sockets = new HashSet<>();

    public static void main(String[] args) throws Exception {
        ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.bind(new InetSocketAddress("localhost", 7070));
        ssc.configureBlocking(false);


        while (true) {
            final SocketChannel sc = ssc.accept();
            if (sc != null) {
                System.out.println("Connection from " + sc);
                sc.configureBlocking(false);
                sockets.add(sc);
            }

            for (Iterator<SocketChannel> it = sockets.iterator(); it.hasNext(); ) {
                SocketChannel socket = it.next();
                try {
                    ByteBuffer buffer = ByteBuffer.allocateDirect(1024);

                    int read = socket.read(buffer);
                    if (read == -1) {
                        it.remove();
                    } else if (read != 0) {
                        buffer.flip();
                        for (int i = 0; i < buffer.limit(); i++) {
                            buffer.put(i, (byte) Utils.transmogrify(buffer.get(i)));
                        }
                        socket.write(buffer);
                    }
                } catch (IOException e) {
                    System.out.println("Connection problem - " + e);
                    it.remove();
                }
            }
        }
    }
}
