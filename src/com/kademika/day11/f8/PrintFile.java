package com.kademika.day11.f8;

import java.io.*;

/**
 * Created by dean on 5/2/15.
 */
public class PrintFile {

    public static void main(String[] args) {

        File file = new File("testFile");
        FileInputStream inStream = null;
        try {
            inStream = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        printStreamData(inStream);

        if (inStream != null) {
            try {
                inStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public static void printStreamData(InputStream stream) {

        StringBuilder builder = new StringBuilder();
        int i = 0;

        while ( i != -1) {
            try {
                i = stream.read();
                builder.append((char) i);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println(builder.toString());
    }
}
