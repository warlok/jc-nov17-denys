package com.kademika.day12.f18;

import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by dean on 5/24/15.
 */
public class PerfectSkatingRink implements SkatingRink {

    Queue<Skates> skatesStore = new LinkedBlockingQueue<>();

    public PerfectSkatingRink() {
        for (int i = 0; i < 5; i++) {
            skatesStore.add(new Skates());
        }
    }

    @Override
    public Skates getSkates(Skater skater) {

            System.out.println("Give skates to" + skater.getName());
            if (!skatesStore.isEmpty()) {
                Skates result = skatesStore.poll();
                return result;
            } else {
                synchronized (skatesStore) {
                    try {
                        skatesStore.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Skates result = skatesStore.poll();
                    return result;
                }
            }
    }

    public Queue<Skates> getSkatesStore() {
        return skatesStore;
    }

    @Override
    public void returnSkates(Skater skater, Skates skates) {
        System.out.println("take skates from " + skater.getName());
        skatesStore.add(skates);
        synchronized (skatesStore) {
            skatesStore.notify();
        }
    }

}
