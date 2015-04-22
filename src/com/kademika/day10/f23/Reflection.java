package com.kademika.day10.f23;

import junit.framework.TestSuite;

import java.util.Map;
import java.util.Set;

/**
 * Created by dean on 4/20/15.
 */
public class Reflection <T> {

    public T initClass(Class c, Map map) {
        Object obj = null;
        Set keys = map.keySet();

        try {
            obj = c.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            System.out.println("Fucking shit");
        }
        T someObject = (T) obj;

        for (Object s : keys) {
            Object value =  map.get(s);
            try {
                someObject.getClass().getDeclaredField((String) s).set(someObject,value);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return someObject;
    }

}
