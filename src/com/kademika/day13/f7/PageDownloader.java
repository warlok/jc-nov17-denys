package com.kademika.day13.f7;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by dean on 6/4/15.
 */
public class PageDownloader {

    public static void main(String[] args) throws Exception{
        URL url = new URL("https://www.google.com.ua");
        URLConnection connection = url.openConnection();
        InputStream in = connection.getInputStream();
        BufferedInputStream bif = new BufferedInputStream(in);
        int i;
        while ( (i = bif.read()) != -1 ) {
            System.out.print((char) i);
        }
    }

}
