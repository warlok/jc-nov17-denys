package com.kademika.day11.f5;

import java.io.File;
import java.io.IOException;

/**
 * Created by dean on 5/2/15.
 */
public class Frame5 {

    static int counter = 0;

    public static void main(String[] args) {
        // Home Folder listing
        printListInHomeDir();

        // Create new direcroty and file
        createDirAndFile("Test15", "FileTest1");

        // Print Home Folder dirs tree (recursion)
        File file = new File(System.getProperty("user.dir"));
        printHomeDirTree(file, "");

    }

    public static void createDirAndFile(String dirName, String fileName) {
        String workingDir = "/home/dean";
        File newDir = new File(workingDir + File.separator + dirName);
        File newFile = new File(newDir + File.separator + fileName);
        newDir.mkdir();
        try {
            newFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void printHomeDirTree(File f,String separ) {
        if (f.isDirectory()) {
            counter++;
            File[] filesAndDirs = f.listFiles();
            for (File file : filesAndDirs) {
                if (file.isDirectory()) {
                    System.err.println(separ + file.getName());
                    String depth = "";
                    for (int i = 0;i<counter;i++) {
                        depth += " ";
                    }
                    printHomeDirTree(file, depth + "-");

                }
            }
            counter--;
        }

    }

    public static void printListInHomeDir() {
        File file1 = new File(System.getProperty("user.dir"));
        if (file1.isDirectory()) {
            String[] listFiles = file1.list();
            for (String s : listFiles) {
                System.out.println(s);
            }
        }
    }

}
