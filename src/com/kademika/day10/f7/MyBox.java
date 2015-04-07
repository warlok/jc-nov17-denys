package com.kademika.day10.f7;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by dean on 3/20/15.
 */
public class MyBox<T extends Animal> {

    private T type;
    public List<T> list;

    public MyBox() {

        list = new ArrayList<T>();
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

    public void sort() {
        Collections.sort(list, new Comparator<T>() {
            @Override
            public int compare(T o1, T o2) {
              if (o1 instanceof Animal && o2 instanceof Animal){
                    Animal a1 = (Animal) o1;
                    Animal a2 = (Animal) o2;
                    return (a1.getAge() - a2.getAge());
                }
                return 0;
            }
        });
    }

}
