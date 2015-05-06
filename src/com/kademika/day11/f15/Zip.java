package com.kademika.day11.f15;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Created by dean on 5/5/15.
 */
public class Zip {

    public void compress(File targetFile) {

        try (BufferedOutputStream bos = new BufferedOutputStream(
                new FileOutputStream(targetFile.getName() + ".zip"));
             ZipOutputStream zos = new ZipOutputStream(bos)
        ) {

            if (targetFile.isDirectory()) {
                addDirectoryToArchive(targetFile, zos);
            } else {
                addFileToArchive(targetFile, zos);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void addDirectoryToArchive(File targetFile, ZipOutputStream zos) throws IOException {
        File[] files = targetFile.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                addDirectoryToArchive(file,zos);
            } else {
                addFileToArchive(file, zos);
            }
        }
    }

    private void addFileToArchive(File targetFile, ZipOutputStream zos) throws IOException {
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(targetFile));
        ZipEntry entry = new ZipEntry(targetFile.getPath());
        int i;
        zos.setMethod(ZipOutputStream.DEFLATED);
        zos.putNextEntry(entry);

        while ((i = bis.read()) != -1) {
            zos.write((byte) i);
        }
        zos.closeEntry();
        bis.close();
    }


}
