package com.kademika.day10.f25;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by dean on 4/22/15.
 */
public class Launch {

    public static void main(String[] args) {
        PrivateSetter<Test> ref = new PrivateSetter();
        Map map = new HashMap();
        map.put("a","vasil");
        map.put("b",5);
        map.put("ch", "denys");
        Test test = ref.initClass(Test.class,map);
        System.out.println(test.getA() + "\n" + test.getB() + "\n" + test.getCh() + "\n");
    }

}
