package com.kademika.day10.f11;

import javax.xml.ws.Service;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by dean on 3/23/15.
 */
public class ServiceRepository<T extends Service> {

    private T type;
    private List<T> serviceList;

    public ServiceRepository() {
        serviceList = new ArrayList<T>();
    }

    public T getObject() {
        if (!serviceList.isEmpty()) {
            return serviceList.get(0);
        }
        return null;
    }

    public void addObject(T object) {
        serviceList.add(object);
    }

    public void removeObject(T object) {
        serviceList.remove(object);
    }


}
