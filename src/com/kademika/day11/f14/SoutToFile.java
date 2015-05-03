package com.kademika.day11.f14;

import java.io.*;

/**
 * Created by dean on 5/3/15.
 */
public class SoutToFile {

    public static void main(String[] args) {
        OutputStream outStream = null;
        try {
            outStream = new FileOutputStream(new File("testFile"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        PrintStream newStream = new PrintStream(outStream);
        System.setOut(newStream);

        System.out.println("Test1");
        System.out.println();
        System.out.print("Ya-ba-da-bady");
        System.out.print("-badu");

        try {
            outStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
