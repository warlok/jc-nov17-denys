package com.kademika.day12.f16;

/**
 * Created by dean on 5/25/15.
 */
public class Demo {

    public static void main(String[] args) {
        final IdGenerator generator = new IdGenerator();
        for (int i = 0; i < 50; i++) {
            new Thread() {
                @Override
                public void run() {
                    Long id = generator.getNextId();
                    System.out.println(id);
                }
            }.start();
        }

    }

}
