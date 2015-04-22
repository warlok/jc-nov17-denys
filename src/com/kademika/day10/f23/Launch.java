package com.kademika.day10.f23;

import com.kademika.day10.f22.ClassChecker;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by dean on 4/22/15.
 */
public class Launch {

    public static void main(String[] args) {
        Reflection<Test> ref = new Reflection();
        Map map = new HashMap();
        map.put("a","vasil");
        map.put("b",5);
        map.put("ch", "denys");
        Test test = ref.initClass(Test.class,map);
        System.out.println(test.a + "\n" + test.b + "\n" + test.ch + "\n");
    }

}
