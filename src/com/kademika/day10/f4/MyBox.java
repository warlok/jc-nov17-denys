package com.kademika.day10.f4;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dean on 3/20/15.
 */
public class MyBox<T> {

    private T type;
    private List list = new ArrayList<T>();

    public MyBox() {

    }

    public T getObject(T object) {
       for (Object elem : list) {
           if (object.equals(elem)) {
               return (T) elem;
           }
       }
        return null;
    }

    public void addObject(T object) {
        list.add(object);
    }

    public void removeObject(T object) {
        list.remove(object);
    }

}
