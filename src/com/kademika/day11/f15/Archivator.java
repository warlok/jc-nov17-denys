package com.kademika.day11.f15;

import java.io.File;

/**
 * Created by dean on 5/3/15.
 */
public class Archivator {

    public static void main(String[] args) {

        if (args.length != 2) {
            System.err.print("Usage: archivator command (zip|unzip) file");
            System.exit(-1);
        }

        File dir = new File(args[1]);

        switch(args[0]) {
            case "zip":
                Zip zip = new Zip();
                zip.compress(dir);
                break;
            case "unzip":
                break;
            default:
                System.out.println("You must choose action \"zip\" or \"unzip\"");
                break;
        }
    }

}
