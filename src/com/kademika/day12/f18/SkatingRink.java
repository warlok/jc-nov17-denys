package com.kademika.day12.f18;

/**
 * Created by dean on 5/24/15.
 */
public interface SkatingRink {

    public Skates getSkates(Skater skater);

    public void returnSkates(Skater skater, Skates skates);
}
