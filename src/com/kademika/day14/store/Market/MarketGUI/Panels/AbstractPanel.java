package com.kademika.day14.store.Market.MarketGUI.Panels;

import com.kademika.day14.store.Market.Objects.Animal;
import com.kademika.day14.store.Market.Objects.Customer;

import javax.swing.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by dean on 8/16/15.
 */
public abstract class AbstractPanel extends JPanel {


    protected String getAnimalsInBasket(Customer cust) {
        String result = "";
        if (cust.getBucket().isEmpty()) {
            return "Basket is empty";
        }
        for (Iterator<Animal> iter = cust.getBucket().keySet().iterator();iter.hasNext();) {
            Animal animal = iter.next();
            if (iter.hasNext()) {
                result += animal.getName() + "(x" + cust.getBucket().get(animal) + "),";
            } else {
                result += animal.getName() + "(x" + cust.getBucket().get(animal) + ");";
            }
        }
        return result;
    }

    protected String getAnimalsInBasket(HashMap<Animal,HashMap<Integer,Double>> bucket) {
        String result = "";
        if (bucket.isEmpty()) {
            return "Basket is empty";
        }
        for (Iterator<Animal> iter = bucket.keySet().iterator();iter.hasNext();) {
            Animal animal = iter.next();
            Set<Integer> amount = bucket.get(animal).keySet();
            int amount_anim = amount.iterator().next();
            if (iter.hasNext()) {
                result += animal.getName() + "(x" + amount_anim + "),";
            } else {
                result += animal.getName() + "(x" + amount_anim + ");";
            }
        }
        return result;
    }

    protected Animal findAnimal(Animal[] array,String name) {
        for (Animal a : array) {
            if (a.getName().equals(name)) {
                return a;
            }
        }
        return null;
    }

    protected boolean checkString(String string) {
        try {
            Double.parseDouble(string);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

}
