package com.kademika.day10.f28;

import com.kademika.day10.f26.Service;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by dean on 4/26/15.
 */
public class ApplicationManager {

    private List<Class> serviceList;

    public ApplicationManager() {
        serviceList = new LinkedList<>();
    }

    public Class getObject() {
        if (!serviceList.isEmpty()) {
            return serviceList.get(0);
        }
        return null;
    }

    public void addObject(Class c) {
        if (c.isAnnotationPresent(Service.class)) {
            serviceList.add(c);
        }
    }

    public void removeObject(Class c) {
        serviceList.remove(c);
    }

    public void initClass(Class c) {
        // some code to create object
        // for (Methods m : o.getMethods()) {
        //  if (m.isAnnotationPresent(initService.class)) {
        //  m.invoke(null);
        //  }
        // }
    }

}
