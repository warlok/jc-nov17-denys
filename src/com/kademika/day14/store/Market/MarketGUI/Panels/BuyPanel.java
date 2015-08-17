package com.kademika.day14.store.Market.MarketGUI.Panels;

import com.kademika.day14.store.Market.Market;
import com.kademika.day14.store.Market.MarketGUI.MarketUI;
import com.kademika.day14.store.Market.Objects.Animal;
import com.kademika.day14.store.Market.Objects.Customer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by dean on 8/16/15.
 */
public class BuyPanel extends AbstractPanel {

//    private JTextField text;
    private Animal[] pets;
    private String[] petStrings;
    private JComboBox petList;
    private JTextField amountAnimals;
    private JTextField price;
    private JButton buttonAdd;
    private JButton buttonRemove;
    private JButton buy;
    private String animalsInBasket= "Basket is empty";
    private HashMap<Animal,HashMap<Integer,Double>> bucket = new HashMap<>();

    public BuyPanel(final Market market, final JFrame frame, final JPanel mainPane) {
        setLayout(new GridBagLayout());

        JLabel lable = new JLabel("Animals:");
//        text = new JTextField(10);

//        pets = new Animal[market.getAnimals().size()];
//        pets = market.getAnimals().values().toArray(pets);
//        petStrings = new String[pets.length];
//        for (int i=0; i< pets.length; i++) {
//            petStrings[i] = pets[i].getName();
//        }
        petList = new JComboBox(market.generatePetlistString());
        petList.setMaximumRowCount(5);

        JLabel amountLable = new JLabel("Amount:");
        amountAnimals = new JTextField(2);
        JLabel priceLable = new JLabel("Price:");
        price = new JTextField(5);

        buttonAdd = new JButton("Add");
        buttonRemove = new JButton("Remove");
        buy = new JButton("BUY");
//        JLabel lablePetsList = new JLabel("Choose an Animal:");
        final JLabel lableBasket = new JLabel(animalsInBasket);
//        final JTextArea lableBasket = new JTextArea(animalsInBasket);
        JScrollPane scrollPane = new JScrollPane(lableBasket);

        add(lable, new GridBagConstraints(0, 0, 1, 1, 0, 0,
                GridBagConstraints.LINE_START, 0, new Insets(0, 0, 0, 0), 0, 0));
        add(petList, new GridBagConstraints(1, 0, 1, 1, 0, 0,
                GridBagConstraints.LINE_START, 0,
                new Insets(0, 0, 0, 0), 0, 0));
        add(amountLable, new GridBagConstraints(2, 0, 1, 1, 0, 0,
                GridBagConstraints.LINE_START, 0, new Insets(0, 0, 0, 0), 0, 0));
        add(amountAnimals, new GridBagConstraints(3, 0, 1, 1, 0, 0,
                GridBagConstraints.LINE_START, 0,
                new Insets(0, 0, 0, 0), 0, 0));
        add(priceLable, new GridBagConstraints(4, 0, 1, 1, 0, 0,
                GridBagConstraints.LINE_START, 0, new Insets(0, 0, 0, 0), 0, 0));
        add(price,
                new GridBagConstraints(5, 0, 1, 1, 0, 0,
                        GridBagConstraints.LINE_START, 0,
                        new Insets(5, 0, 0, 0), 0, 0));
        add(buttonRemove,
                new GridBagConstraints(0, 1, 1, 1, 0, 0,
                        GridBagConstraints.LINE_START, 0, new Insets(10, 10, 0,
                        0), 0, 0));
        add(buttonAdd,
                new GridBagConstraints(5, 1, 1, 1, 0, 0,
                        GridBagConstraints.LINE_START, 0, new Insets(10, 0, 0,
                        10), 0, 0));
        add(scrollPane, new GridBagConstraints(0, 2, 6, 1, 0, 0,
                GridBagConstraints.LINE_START, GridBagConstraints.BOTH, new Insets(10, 0, 0, 0), 0, 50));
        add(buy,
                new GridBagConstraints(2, 3, 1, 1, 0, 0,
                        GridBagConstraints.LINE_START, 0, new Insets(10, 0, 0,
                        0), 0, 0));

        buttonAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (amountAnimals.getText() != null && !amountAnimals.getText().isEmpty() &&
                        Integer.valueOf(amountAnimals.getText()) > 0 && checkString(price.getText())) {
                    Animal anim = findAnimal(pets, petList.getSelectedItem().toString());
                    Integer amount = Integer.valueOf(amountAnimals.getText());
                    Double buyngPrice = Double.valueOf(price.getText());
                    HashMap<Integer, Double> amountAndPrice = new HashMap<>();
                    amountAndPrice.put(amount, buyngPrice);
                    bucket.put(anim, amountAndPrice);
                    animalsInBasket = getAnimalsInBasket(bucket);
                    lableBasket.setText(animalsInBasket);
                } else {
                    JOptionPane.showMessageDialog(frame, "Enter positive amount or price");
                }
            }
        });

        buttonRemove.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (amountAnimals.getText() != null && !amountAnimals.getText().isEmpty() &&
                        Integer.valueOf(amountAnimals.getText()) > 0 && checkString(price.getText())) {
                    Animal anim = findAnimal(pets, petList.getSelectedItem().toString());
                    Integer amount = Integer.valueOf(amountAnimals.getText());

                    if (bucket.containsKey(anim)) {
                        int exist = bucket.get(anim).keySet().iterator().next();
                        if ((exist-amount) <= 0) {
                            bucket.remove(anim);
                        } else {
                            HashMap<Integer, Double> amountAndPrice = new HashMap<>();
                            Double buyngPrice = Double.valueOf(price.getText());
                            amountAndPrice.put(exist-amount, buyngPrice);
                            bucket.put(anim, amountAndPrice);
                        }
                    }
                    animalsInBasket = getAnimalsInBasket(bucket);
                    lableBasket.setText(animalsInBasket);
                } else {
                    JOptionPane.showMessageDialog(frame, "Enter positive amount or price");
                }
            }
        });

        buy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!"Basket is empty".equals(animalsInBasket)) {
                    Animal anim = null;
                    for (Iterator<Animal> iter = bucket.keySet().iterator(); iter.hasNext(); ) {
                        anim = iter.next();
                        int amount = bucket.get(anim).keySet().iterator().next();
                        double buyngPrice = bucket.get(anim).get(amount);
                        market.buyToStore(anim,amount,buyngPrice*amount);
                        MarketUI.refreshMainPanel(mainPane, market);
                    }
                    animalsInBasket = "Basket is empty";
                    lableBasket.setText(animalsInBasket);
                    bucket.clear();
                    frame.setContentPane(new CatalogPanel(market,frame,mainPane));
                    frame.pack();
                } else {
                    JOptionPane.showMessageDialog(frame, "Basket is Empty");
                }
            }
        });
    }
}
