package com.kademika.day10.junit.hw5;

import com.kademika.day10.f25.PrivateSetter;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by dean on 4/28/15.
 */

@RunWith(JUnit4.class)
public class PrivateSetterTest {

    PrivateSetter<TestObject> setter;
    Map fieldsAndValues;

    @Before
    public void init() {
        setter = new PrivateSetter<>();
        fieldsAndValues = new HashMap();
        fieldsAndValues.put("name","Egor");
        fieldsAndValues.put("age",30);
        fieldsAndValues.put("secondName", "Portnov");
    }

    @Test
    public void privateSetterTest() {
        TestObject testObject = setter.initClass(TestObject.class, fieldsAndValues);
        assertEquals(fieldsAndValues.get("name"), testObject.getName());
        assertEquals(fieldsAndValues.get("age"), testObject.getAge());
        assertEquals(fieldsAndValues.get("secondName"), testObject.getSecondName());
    }
}
