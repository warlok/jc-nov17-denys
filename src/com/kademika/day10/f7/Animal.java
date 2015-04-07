package com.kademika.day10.f7;

import java.util.Iterator;

/**
 * Created by dean on 3/20/15.
 */
public abstract class Animal {

    private String name;
    private String type;
    private int age;

    public Animal() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

}
