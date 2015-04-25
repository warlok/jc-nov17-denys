package com.kademika.day10.f24;

import org.omg.Dynamic.Parameter;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by dean on 4/22/15.
 */
public class ClassInitiator <T> {

    private Constructor constr;

    public T initClass(Class c, List<Object> list) {

        T someObject = null;

        Object[] arguments = list.toArray(new Object[list.size()]);
        Class[] args = new Class[list.size()];

        for (int i=0; i < args.length; i++ ) {
            args[i] = list.get(i).getClass();
        }

        try {
            constr = c.getDeclaredConstructor(args);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        try {
            someObject = (T) constr.newInstance(arguments);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        return someObject;
    }

}
