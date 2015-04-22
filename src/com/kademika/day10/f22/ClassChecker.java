package com.kademika.day10.f22;

import com.kademika.day10.f19.Animal;

import java.awt.*;
import java.lang.reflect.*;
import java.util.Random;

/**
 * Created by dean on 4/22/15.
 */
public class ClassChecker {

    public static void printClassInfo(Class c) {
        String simpleName = c.getSimpleName();
        String name = c.getName();
        String packageName = c.getPackage().getName();
        String canonicalName = c.getCanonicalName();
        System.out.println("Name: " + name + "\n" + "Package: " + packageName + "\n" +
                "Canonical name: " + canonicalName + "\n" + "Short name: " + simpleName);
    }

    public static void printClassMethod(Class c) {

        Constructor[] constructors = c.getConstructors();
        Method[] methods = c.getMethods();
        for (Constructor cons : constructors) {

            String parameterTypes = "";
            for (Class<?> cla : cons.getParameterTypes()) {
                parameterTypes += cla.getSimpleName() + ", ";
            }

            System.out.println(cons.getName() +
                    "(" + parameterTypes + ")");
        }



        for (Method m : methods) {

            String parameterTypes = "";
            String modifier1="";
            String modifier2 = "";
            for (Class<?> cla : m.getParameterTypes()) {
                parameterTypes += cla.getSimpleName() + ", ";
            }
            int mods = m.getModifiers();
            if (Modifier.isPublic(mods)) {
                modifier1="public";
            }
            if (Modifier.isAbstract(mods)) {
                modifier2="abstract";
            }
            if (Modifier.isFinal(mods)) {
                modifier2="final";
            }
            if (Modifier.isPrivate(mods)) {
                modifier1="private";
            }
            if (Modifier.isProtected(mods)) {
                modifier1="protected";
            }
            if (Modifier.isStatic(mods)) {
                modifier2="static";
            }

            System.out.println(modifier1 + " " + modifier2 + " " + m.getReturnType().getSimpleName() +
                    " " + m.getName() + "(" + parameterTypes + ")");
        }

    }

    public static void printClassFields(Class c) {
           Field[] fields =  c.getFields();

        for (Field f : fields) {
            System.out.println(f.getType().getSimpleName() + " " + f.getName());
        }

    }

    public static void main(String[] args) {
        printClassInfo(Random.class);
        System.out.println();
        printClassMethod(Graphics2D.class);
        printClassFields(Animal.class);
    }

}
