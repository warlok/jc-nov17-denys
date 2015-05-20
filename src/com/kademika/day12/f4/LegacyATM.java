package com.kademika.day12.f4;

/**
 * Created by dean on 5/19/15.
 */
public class LegacyATM {

    public double checkBalance(Card card) {
        return card.getCardBalance();
    }

    public synchronized void withdrawMoney(Card card, int amount) {
        if ((card.getCardBalance() - amount) >= 0 ) {
            card.setCardBalance(card.getCardBalance() - amount);
        } else {
            System.out.println("No match money");
        }
    }

}
