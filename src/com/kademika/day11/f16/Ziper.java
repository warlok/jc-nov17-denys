package com.kademika.day11.f16;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Created by dean on 5/5/15.
 */
public class Ziper {

    public static void main(String[] args) {

        try (BufferedOutputStream bos = new BufferedOutputStream(
                new FileOutputStream("test.zip"));
             ZipOutputStream zos = new ZipOutputStream(bos)
        ) {
            BufferedInputStream bis = new BufferedInputStream(new FileInputStream("test/newfile"));
            ZipEntry entry = new ZipEntry("test/newfile");
            int i;
            zos.setMethod(ZipOutputStream.DEFLATED);
            zos.putNextEntry(entry);

            while ((i = bis.read()) != -1) {
                zos.write((byte) i);
            }
            zos.closeEntry();
            bis.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /*    try (BufferedOutputStream bos = new BufferedOutputStream(
                new FileOutputStream("test.zip"));
             ZipOutputStream zos = new ZipOutputStream(bos)
        ) {*/



          /*      File[] files = targetFile.listFiles();
                for (File file : files) {
                    BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
                    int i;
                    ZipEntry entry = new ZipEntry(file.getName());
                    zos.setMethod(ZipOutputStream.DEFLATED);
                    zos.putNextEntry(entry);
                    while ((i = bis.read()) != -1) {
                        zos.write((byte) i);
                    }
                    zos.closeEntry();
                    bis.close();

                }*/
         /*       BufferedInputStream bis = new BufferedInputStream(new FileInputStream(targetFile));
                ZipEntry entry = new ZipEntry(targetFile.getName());
                int i;
                zos.setMethod(ZipOutputStream.DEFLATED);
                zos.putNextEntry(entry);

                while ((i = bis.read()) != -1) {
                    zos.write((byte) i);
                }
                zos.closeEntry();
                bis.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/

}
