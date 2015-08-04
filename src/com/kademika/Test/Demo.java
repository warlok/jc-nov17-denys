package com.kademika.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dean on 7/14/15.
 */
public class Demo {

    public static void main(String[] args) {

        List<Class1> test1 = new ArrayList<>();
        List<Class2> test2 = new ArrayList<>();
        List<Class3> test3 = new ArrayList<>();
        Class1 class1 = new Class1();
        Class2 class2 = new Class2();
        Class3 class3 = new Class3();

//        test(test3,test3);

//        List<String> ls = new ArrayList<String>(); // 1
//        List<Object> lo = ls; // 2

        MyGeneric<Class1> box = new MyGeneric<>();
        box.settValue(class1);
        box.settValue(class2);
        box.settValue(class3);



    }



    }


