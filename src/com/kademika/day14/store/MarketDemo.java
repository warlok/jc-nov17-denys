package com.kademika.day14.store;

import com.kademika.day14.store.Market.Market;
import com.kademika.day14.store.Market.MarketGUI.MarketUI;

public class MarketDemo {

    public static void main(String[] args) {

        Market m = new Market();
        m.init();
        new MarketUI(m);

    }

}
