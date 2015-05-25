package com.kademika.day12.f18;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by dean on 5/24/15.
 */
public class SkatingDemo implements Runnable {

    final static PerfectSkatingRink rink = new PerfectSkatingRink();
    final static Random r = new Random();
    Skater skater;

    public SkatingDemo(Skater skater) {
        this.skater = skater;
    }

    public static void main(String[] args) {

        ExecutorService executor = Executors.newFixedThreadPool(rink.getSkatesStore().size());
        List<Skater> skatersList = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            skatersList.add(new Skater("Skater"+i));
        }

        for (Skater skater : skatersList) {
            executor.submit(new SkatingDemo(skater));
        }

    }

    private static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        Skates skaters = rink.getSkates(skater);
        sleep(r.nextInt(2000));
        rink.returnSkates(skater,skaters);
    }
}
