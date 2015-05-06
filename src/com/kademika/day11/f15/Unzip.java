package com.kademika.day11.f15;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * Created by dean on 5/5/15.
 */
public class Unzip {

    public void decompress(File targetFile) {

        try (ZipInputStream zis = new ZipInputStream(
                new BufferedInputStream(
                        new FileInputStream(targetFile)))) {

            BufferedOutputStream bos;
            ZipEntry entry;
            while ((entry = zis.getNextEntry()) != null) {
                File file = new File(entry.getName());
                if (!file.getParentFile().exists()) {
                    file.getParentFile().mkdirs();
                }
                bos = new BufferedOutputStream(new FileOutputStream(file));
                int i;
                while((i=zis.read()) != -1) {
                    bos.write(i);
                }
                bos.flush();
                bos.close();
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}
