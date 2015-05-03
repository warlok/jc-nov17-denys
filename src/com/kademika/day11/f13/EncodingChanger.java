package com.kademika.day11.f13;

import java.io.*;

/**
 * Created by dean on 5/3/15.
 */
public class EncodingChanger {

    public static void main(String[] args) {

        File file = new File("testFile");
        changeEncoding(file, "Cp1251UTF-8", "UTF-8");
    }

    public static void changeEncoding(File file, String currentEncoding, String neededEncoding) {
        StringBuilder builder = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file),currentEncoding));

        ) {
            String string="";
            while ( (string = br.readLine()) != null) {
                builder.append(string + "\n");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try ( BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file),neededEncoding))) {
            bw.write(builder.toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
