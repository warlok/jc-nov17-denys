package com.kademika.day12.f4;

/**
 * Created by dean on 5/19/15.
 */
public class Person {

    private String name;
    LegacyATM atm;
    private Card card;

    public void getMoney(int amount) {
        atm.withdrawMoney(card, amount);
        System.out.println(name + " takes " + amount + " dollars from bank");
    }

    public void checkBalance() {
        double balance = atm.checkBalance(card);
        System.out.println(name + ": Your balance - " + balance);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    public void setAtm(LegacyATM atm) {
        this.atm = atm;
    }
}
