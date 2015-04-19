package com.kademika.day10.junit.f17;

import com.kademika.day10.f2.Aligator;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.internal.JUnitSystem;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 * Created by dean on 4/19/15.
 */
@RunWith(JUnit4.class)
public class DomainModel {
    private Aligator aligator;

    @Before
    public void init() {
        aligator = new Aligator();
    }

    @Test
    public void checkAnimalDefaultName() {
        assertEquals(null,aligator.getName());
    }

    @Test
    public void checkAligatorDefaultType() {
        assertEquals(null,aligator.getAligatorType());
    }

    @Test
    public void checkAnimalDefaultAge() {
        assertEquals(0,aligator.getAge());
    }

    @Test
    public void checkAnimalDefaultType() {
        assertEquals(null,aligator.getType());
    }
}
