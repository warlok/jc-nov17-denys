package com.kademika.day10.junit.f18;

import com.kademika.day10.f11.SomeServise;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import javax.xml.namespace.QName;

import static org.junit.Assert.*;

/**
 * Created by dean on 4/19/15.
 */
@RunWith(JUnit4.class)
public class ServiceRepository {

    com.kademika.day10.f11.ServiceRepository repo;
    SomeServise service1;
    SomeServise service2;

    @Before
    public void init() {
        repo = new com.kademika.day10.f11.ServiceRepository();
        service1 = new SomeServise();
        service1 = new SomeServise();;
    }

    @Test
    public void testGetObject() {
        assertEquals(null,repo.getObject());
    }

    @Test
    public void testAddObject() {
       repo.addObject(service1);
        assertNotNull(repo.getObject());
    }

    @Test
    public void testRemoveObject() {
        repo.addObject(service1);
        assertNotNull(repo.getObject());
        repo.removeObject(service1);
        assertNull(repo.getObject());
    }

}
