package com.kademika.day12.f18;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by dean on 5/24/15.
 */
public class PerfectSkatingRink implements SkatingRink {

    List<Skates> skatesStore = new ArrayList<>(
            Arrays.asList(new Skates[]{new Skates(),
                    new Skates(), new Skates()}));

    Lock myLock = new ReentrantLock();

    @Override
    public Skates getSkates(Skater skater) {
        myLock.lock();
        try {
            System.out.println("Give skates to" + skater.getName());
            if (!skatesStore.isEmpty()) {
                Skates result = skatesStore.get(0);
                skatesStore.remove(0);
                return result;
            } else {
                synchronized (skatesStore) {
                    skatesStore.wait();
                    Skates result = skatesStore.get(0);
                    skatesStore.remove(0);
                    return result;
                }
            }
        } catch (Exception e) {
            System.err.println("Fuck");
        } finally {
            myLock.unlock();
        }
        return null;
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
