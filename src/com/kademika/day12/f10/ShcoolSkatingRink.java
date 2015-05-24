package com.kademika.day12.f10;

/**
 * Created by dean on 5/24/15.
 */
public class ShcoolSkatingRink implements SkatingRink {

    @Override
    public Skates getSkates(Skater skater) {
        System.out.println("Give skates to" + skater.getName());
        return new Skates();
    }

    @Override
    public void returnSkates(Skater skater, Skates skates) {
        System.out.println("take skates from " + skater.getName());
    }
}
