package com.kademika.day11.f4;

import java.io.ByteArrayInputStream;

/**
 * Created by dean on 5/2/15.
 */
public class PrintInputStream {

    public static void main(String[] args) {
        byte[] bytes = {16,20,32,7,5,12,30};
        ByteArrayInputStream in = new ByteArrayInputStream(bytes);

        printStreamData(in);
    }

    public static void printStreamData(ByteArrayInputStream inStreamByte) {
        int i;
        while ((i = inStreamByte.read()) != -1) {
            System.out.print(i + " ");
        }
    }

}
