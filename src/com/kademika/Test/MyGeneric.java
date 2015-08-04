package com.kademika.Test;

/**
 * Created by dean on 7/14/15.
 */
public class MyGeneric <A> {

    A value;

    public void settValue(A newv) {
        value = newv;
    }

    public void printValue() {
        System.out.println(value.toString());
    }

}
