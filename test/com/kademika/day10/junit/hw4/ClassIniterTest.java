package com.kademika.day10.junit.hw4;

import com.kademika.day10.f24.ClassInitiator;
import com.kademika.day10.f24.Test;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.List;

/**
 * Created by dean on 4/28/15.
 */

@RunWith(JUnit4.class)
public class ClassIniterTest {

    ClassInitiator<Test> initer;
    List<Object> construktorArgs;

    @Before
    public void init() {
        initer = new ClassInitiator();
    }

    @org.junit.Test
    public void testInitiator() {
        Test object = initer.initClass(Test.class, construktorArgs);
    }

}
