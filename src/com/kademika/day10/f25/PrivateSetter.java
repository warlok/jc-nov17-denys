package com.kademika.day10.f25;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.Set;

/**
 * Created by dean on 4/20/15.
 */
public class PrivateSetter<T> {

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
                Field f = someObject.getClass().getDeclaredField((String) s);
                f.setAccessible(true);
                f.set(someObject,value);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return someObject;
    }

}
