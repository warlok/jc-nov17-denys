package com.kademika.day10.f24;

import com.kademika.day10.f23.Reflection;

import java.util.*;

/**
 * Created by dean on 4/22/15.
 */
public class Launch {

    public static void main(String[] args) {

        List<Object> listInt = new ArrayList<>();
        List<Object> listString = new ArrayList<>();
        List<Object> listEmpty = new ArrayList<>();
        List<Object> listIntString = new ArrayList<>();
        List<Object> listStringIntString = new ArrayList<>();
        List<Object> listStringInt = new ArrayList<>();

        listInt.add(15);
        listString.add("Vasil");
        listIntString.add(15);
        listIntString.add("Vasil");
        listStringInt.add("Denys");
        listStringInt.add(50);
        listStringIntString.add("Vanya");
        listStringIntString.add(16);
        listStringIntString.add("Kilpgram");

        ClassInitiator<Test> initer = new ClassInitiator();

        Test test = initer.initClass(Test.class,listEmpty);
        System.out.println(test.a + "\n" + test.b + "\n" + test.ch + "\n");


        test = initer.initClass(Test.class,listString);
        System.out.println(test.a + "\n" + test.b + "\n" + test.ch + "\n");

        test = initer.initClass(Test.class,listInt);
        System.out.println(test.a + "\n" + test.b + "\n" + test.ch + "\n");

        test = initer.initClass(Test.class,listIntString);
        System.out.println(test.a + "\n" + test.b + "\n" + test.ch + "\n");

        test = initer.initClass(Test.class,listStringInt);
        System.out.println(test.a + "\n" + test.b + "\n" + test.ch + "\n");

        test = initer.initClass(Test.class,listStringIntString);
        System.out.println(test.a + "\n" + test.b + "\n" + test.ch + "\n");
    }

}
