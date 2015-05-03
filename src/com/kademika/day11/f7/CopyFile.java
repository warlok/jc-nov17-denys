package com.kademika.day11.f7;

import java.io.*;

/**
 * Created by dean on 5/2/15.
 */
public class CopyFile {

    public static void main(String[] args) {
        File file = new File("testFile");
        copyFile(file);

    }

    public static void copyFile(File oldFile) {

        int i;
        File newFile = new File(oldFile.getName() + "Copy");
        StringBuilder builder = new StringBuilder();

        try (FileInputStream in = new FileInputStream(oldFile)) {
            while ((i=in.read()) != -1) {
                builder.append((char) i);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (FileOutputStream out = new FileOutputStream(newFile)) {
            out.write(builder.toString().getBytes());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
