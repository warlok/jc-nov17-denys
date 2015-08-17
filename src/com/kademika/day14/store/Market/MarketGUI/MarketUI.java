package com.kademika.day14.store.Market.MarketGUI;

import com.kademika.day14.store.Market.Market;
import com.kademika.day14.store.Market.MarketGUI.Panels.BuyPanel;
import com.kademika.day14.store.Market.MarketGUI.Panels.CatalogPanel;
import com.kademika.day14.store.Market.MarketGUI.Panels.ManagePanel;
import com.kademika.day14.store.Market.MarketGUI.Panels.SellPanel;
import com.kademika.day14.store.Market.Objects.Animal;
import com.kademika.day14.store.Market.Objects.Customer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.*;

public class MarketUI {

//    private SimpleDateFormat sdfDate = new SimpleDateFormat("MM.dd.yyyy hh:mm");
//    private SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss" + ".0");
	private LinkedList<Customer> cust;
	private JPanel sellPane;
	private JPanel mainPane;
    private JPanel catalogPane;
    private JPanel managePane;
    private JPanel buyPane;
	private JFrame frame;
    private JLabel balanceLable;

	public MarketUI(final Market market) {
		SplashScreen splashScreen = SplashScreen.getSplashScreen();
		cust = new LinkedList<>();
        mainPane = new JPanel(new BorderLayout());
        mainPane.add(market.printTransactions(),BorderLayout.CENTER);
        balanceLable = new JLabel("Balance: " + market.getBudget() + "$");
        mainPane.add(balanceLable,BorderLayout.SOUTH);

		frame = new JFrame("Petshop");
		frame.setBounds(350, 50, 500, 800);
		frame.setContentPane(mainPane);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        sellPane = new SellPanel(market,frame,mainPane,cust);
        buyPane = new BuyPanel(market, frame, mainPane);
        managePane = new ManagePanel(market, frame,mainPane);

//        catalogPane = new CatalogPanel(market,frame,mainPane);

		JMenuBar menuBar = new JMenuBar();
        JMenu menuMarket = new JMenu("Operations");
        JMenu menuGood = new JMenu("Manage goods");
        JMenu menuTransactions = new JMenu("Transactions");
        JMenuItem transactions = new JMenuItem("Sell Operations");
        JMenuItem budgetOperations = new JMenuItem("All Operations");
        JMenuItem sellGoods = new JMenuItem("Sell Animals");
        JMenuItem buyGoods = new JMenuItem("Buy Animals");
        final JMenuItem catalog = new JMenuItem("Print Catalog");
        JMenuItem addGood = new JMenuItem("Add/Del Good");

        addGood.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setContentPane(managePane);
                frame.pack();
            }
        });

        sellGoods.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.setContentPane(sellPane);
				frame.pack();
			}
		});
        catalog.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                catalogPane = new CatalogPanel(market,frame,mainPane);
                frame.setContentPane(catalogPane);
                frame.pack();
            }
        });

        buyGoods.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setContentPane(buyPane);
                frame.pack();
            }
        });

        transactions.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setContentPane(mainPane);
                frame.pack();
            }
        });

        menuMarket.add(sellGoods);
        menuMarket.add(buyGoods);
        menuGood.add(catalog);
        menuGood.add(addGood);
        menuTransactions.add(transactions);
        menuTransactions.add(budgetOperations);
        menuBar.add(menuMarket);
        menuBar.add(menuGood);
        menuBar.add(menuTransactions);
        frame.setJMenuBar(menuBar);
		frame.pack();

		try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            System.err.println("Caught exeption");
        }

		try {
			splashScreen.close();
		} catch (NullPointerException e) {
			System.err.println("Add in your JVM config VM options: -splash:loading.gif");
			System.exit(-1);
		}

		frame.setVisible(true);
	}

//    private String getAnimalsInBasket(Customer cust) {
//        String result = "";
//        if (cust.getBucket().isEmpty()) {
//            return "Basket is empty";
//        }
//        for (Iterator<Animal> iter = cust.getBucket().keySet().iterator();iter.hasNext();) {
//                Animal animal = iter.next();
//            if (iter.hasNext()) {
//                result += animal.getName() + "(x" + cust.getBucket().get(animal) + "),";
//            } else {
//                result += animal.getName() + "(x" + cust.getBucket().get(animal) + ");";
//            }
//        }
//        return result;
//    }
//
//	private Animal findAnimal(Animal[] array,String name) {
//		for (Animal a : array) {
//			if (a.getName().equals(name)) {
//				return a;
//			}
//		}
//		return null;
//	}
    public static void refreshMainPanel(JPanel panel,Market market) {
        panel.removeAll();
        panel.add(market.printTransactions(),BorderLayout.CENTER);
        panel.add(new JLabel("Balance: " + market.getBudget() + "$"),BorderLayout.SOUTH);
    }

}
