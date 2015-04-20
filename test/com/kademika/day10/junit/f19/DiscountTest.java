package com.kademika.day10.junit.f19;

import com.kademika.day10.f19.Animal;
import com.kademika.day10.f19.Customer;
import com.kademika.day10.f19.Market;
import com.kademika.day10.f19.Type;
import com.kademika.day10.f19.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 * Created by dean on 4/20/15.
 */
@RunWith(JUnit4.class)
public class DiscountTest {

    Market m;
    Animal tiger;
    Animal rabbit;
    Animal penguin;
    Customer dean;


    @Before
    public void init() {
        m = new Market();

        tiger = new Animal();
        tiger.setName("tiger");
        tiger.setOrder("Carnivora"); // Hishnik
        tiger.setType(Type.Mammal); // Mlekopitauhee
        tiger.setPrice(500);
        m.addAnimal(tiger);
        m.addOnStore(tiger, 5);

        rabbit = new Animal();
        rabbit.setName("rabbit");
        rabbit.setOrder("Lagomorpha"); // Zytseobraznie
        rabbit.setType(Type.Mammal); // Mlekopitauhee
        rabbit.setPrice(10);
        m.addAnimal(rabbit);
        m.addOnStore(rabbit, 45);

        penguin = new Animal();
        penguin.setName("penguin");
        penguin.setOrder("Sphenisciformes");
        penguin.setType(Type.Bird);
        penguin.setPrice(80);
        m.addAnimal(penguin);
        m.addOnStore(penguin, 15);

        dean = new Customer();
        dean.setName("Denys");
    }

    @Test
    public void testDiscount() {

        dean.addOnBucket(tiger, 3);
        m.sell("today",dean,dean.getBucket());
        int price=0;
        for (Animal animal : m.getPurchases().get(0).getGoods()) {
            price+=animal.getPrice();
        }

        if (price > 1000) {
            Assert.assertTrue(m.getPurchases().get(0).getPrice() == (price - (price * 0.1)));
        } else if (price > 50) {
            Assert.assertTrue(m.getPurchases().get(0).getPrice() == (price-(price*0.05)));
        }

    }

}
