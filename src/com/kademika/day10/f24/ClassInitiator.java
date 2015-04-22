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

    public T initClass(Class c, List<Object> list) {
        Constructor[] constructors = c.getDeclaredConstructors();
        T someObject = null;

        String listParameters = "";
        String construktorParameters = "";

        for (Object o : list) {
            listParameters += o.getClass().getSimpleName() + " ";
        }

        for (Constructor constr : constructors) {

            for (Class clas : constr.getParameterTypes()) {
                construktorParameters += clas.getSimpleName() + " ";
            }

            if (construktorParameters.equals(listParameters)) {

                try {
                    someObject = (T) constr.newInstance();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }

            }

        }

        return someObject;
    }

}
