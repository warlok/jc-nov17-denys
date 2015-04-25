package com.kademika.day10.f24;

/**
 * Created by dean on 4/22/15.
 */
public class Test {

    String a = "a";
    int b = 1;
    String ch = "c";

    public Test() {

    }

    public Test(Integer b) {
        this.b = b;
    }

    public Test(String a) {
        this.a = a;
    }

    public Test(String a, Integer b, String ch) {
        this.a = a;
        this.b = b;
        this.ch = ch;
    }

    public Test(String a, Integer b) {
        this.a = a;
        this.b = b;
    }

    public Test(Integer b, String ch) {
        this.b = b;
        this.ch = ch;
    }
}
