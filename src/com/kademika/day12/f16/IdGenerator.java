package com.kademika.day12.f16;

import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by dean on 5/25/15.
 */
public class IdGenerator {

    AtomicLong id = new AtomicLong(0);

    public long getNextId() {
        return id.getAndIncrement();
    }

/*    long id = 0l;

    public Long getNextId() {
        long oldId = id;
        id++;
        return oldId;
    }*/

}
