package com.kademika.day13.f3;

import java.io.IOException;
import java.net.Socket;

/**
 * Created by dean on 6/8/15.
 */
public class NastyChump {

    public static void main(String[] args) throws InterruptedException {
        for (int i=0; i<3000; i++) {
            try {
                new Socket("localhost", 7070);
                System.out.println(i);
            } catch (IOException e) {
                System.out.println("Could not connect - " + e);
            }
        }
        Thread.sleep(1000000);
    }

}
