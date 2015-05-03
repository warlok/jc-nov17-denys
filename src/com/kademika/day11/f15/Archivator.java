package com.kademika.day11.f15;

import java.io.File;

/**
 * Created by dean on 5/3/15.
 */
public class Archivator {

    public static void main(String[] args) {

        File dir = new File(args[1]);

        switch(args[0]) {
            case "zip":
                sdcs;
                break;
            case "unzip":
                sdfds;
                break;
            default:
                System.out.println("You must choose action \"zip\" or \"unzip\"");
                break;
        }
    }

}
