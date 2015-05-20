package com.kademika.day12.f4;

import java.util.Random;

/**
 * Created by dean on 5/19/15.
 */
public class Starter {

    public static void main(String[] args) {

        LegacyATM atm = new LegacyATM();

        final Card card = new Card();
        card.setCardBalance(1300);
        card.setCardNumber(553784233337456l);

        final Person husbend = new Person();
        husbend.setName("Vasyl");
        husbend.setCard(card);
        husbend.setAtm(atm);

        final Person wife = new Person();
        wife.setName("Olga");
        wife.setCard(card);
        wife.setAtm(atm);

        final Random r = new Random();

        new Thread() {
            @Override
            public void run() {
             while (card.getCardBalance() > 0) {
                 wife.checkBalance();
                 wife.getMoney(r.nextInt(500));
             }
            }
        }.start();

        new Thread() {
            @Override
            public void run() {
                while (card.getCardBalance() > 0) {
                    husbend.checkBalance();
                    husbend.getMoney(r.nextInt(500));
                }
            }
        }.start();

    }

}
