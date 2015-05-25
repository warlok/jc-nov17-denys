package com.kademika.day12.f18;

import java.util.Random;

/**
 * Created by dean on 5/24/15.
 */
public class SkatingDemo {

    public static void main(String[] args) {

        final PerfectSkatingRink rink = new PerfectSkatingRink();
        final Random r = new Random();

        for (int i = 0; i < 100; i++) {

            final Skater skater = new Skater("Skater"+i);

            new Thread() {
                @Override
                public void run() {
                    Skates skaters = rink.getSkates(skater);
                    try {
                        sleep(r.nextInt(2000));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    rink.returnSkates(skater,skaters);
                }
            }.start();

            sleep(1000);
        }

    }

    private static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
