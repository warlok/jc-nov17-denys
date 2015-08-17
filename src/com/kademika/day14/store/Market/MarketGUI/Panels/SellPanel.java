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
import java.util.Date;
import java.util.LinkedList;
import java.util.Observable;

/**
 * Created by dean on 8/16/15.
 */
public class SellPanel extends AbstractPanel {

    private SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss" + ".0");
    private JTextField text;
    private Animal[] pets;
    private String[] petStrings;
    private JComboBox petList;
    private JTextField amountAnimals;
    private JButton buttonAdd;
    private JButton buttonRemove;
    private JButton buy;
    private String animalsInBasket= "Basket is empty";

    public SellPanel(final Market market, final JFrame frame, final JPanel mainPane, final LinkedList<Customer> cust) {
        setLayout(new GridBagLayout());

        JLabel lable = new JLabel("Customer's Name:");
        text = new JTextField(10);

//        pets = new Animal[market.getAnimals().size()];
//        pets = market.getAnimals().values().toArray(pets);
//        petStrings = new String[pets.length];
//        for (int i=0; i< pets.length; i++) {
//            petStrings[i] = pets[i].getName();
//        }

        petList = new JComboBox(market.generatePetlistString());
        petList.setMaximumRowCount(5);

        JLabel amountLable = new JLabel("Input Amount:");
        amountAnimals = new JTextField(2);
        buttonAdd = new JButton("Add");
        buttonRemove = new JButton("Remove");
        buy = new JButton("BUY");
        JLabel lablePetsList = new JLabel("Choose an Animal:");
        final JLabel lableBasket = new JLabel(animalsInBasket);
//        final JTextArea lableBasket = new JTextArea(animalsInBasket);
        JScrollPane scrollPane = new JScrollPane(lableBasket);

        add(lable, new GridBagConstraints(0, 0, 1, 1, 0, 0,
                GridBagConstraints.LINE_START, 0, new Insets(0, 0, 0, 0), 0, 0));
        add(text, new GridBagConstraints(1, 0, 1, 1, 0, 0,
                GridBagConstraints.LINE_START, GridBagConstraints.HORIZONTAL,
                new Insets(0, 0, 0, 0), 0, 0));
        add(lablePetsList, new GridBagConstraints(0, 1, 1, 1, 0, 0,
                GridBagConstraints.LINE_START, 0, new Insets(0, 0, 0, 0), 0, 0));
        add(petList, new GridBagConstraints(1, 1, 1, 1, 0, 0,
                GridBagConstraints.LINE_START, GridBagConstraints.HORIZONTAL,
                new Insets(0, 0, 0, 0), 0, 0));
        add(amountLable, new GridBagConstraints(0, 2, 1, 1, 0, 0,
                GridBagConstraints.LINE_START, 0, new Insets(0, 0, 0, 0), 0, 0));
        add(amountAnimals,
                new GridBagConstraints(1, 2, 1, 1, 0, 0,
                        GridBagConstraints.LINE_START, 0,
                        new Insets(5, 0, 0, 0), 15, 7));
        add(buttonRemove,
                new GridBagConstraints(0, 3, 1, 1, 0, 0,
                        GridBagConstraints.LINE_START, 0, new Insets(5, 47, 0,
                        0), 0, 0));
        add(buttonAdd,
                new GridBagConstraints(1, 3, 1, 1, 0, 0,
                        GridBagConstraints.LINE_START, 0, new Insets(5, 47, 0,
                        0), 0, 0));
        add(scrollPane, new GridBagConstraints(0, 4, 2, 1, 0, 0,
                GridBagConstraints.LINE_START, GridBagConstraints.BOTH, new Insets(10, 0, 0, 0), 0, 50));
        add(buy,
                new GridBagConstraints(1, 5, 1, 1, 0, 0,
                        GridBagConstraints.LINE_START, 0, new Insets(10, 0, 0,
                        0), 0, 0));

        buttonAdd.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                Customer c;
                String name = text.getText();
                if (name.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Bad customer");
                    return;
                }
                Customer temp_cust = market.customerByName(name);
                if (temp_cust != null) {
                    c = temp_cust;
                } else {
                    c = new Customer();
                    c.setName(text.getText());
                    market.addCustomer(c);
                }
                cust.addFirst(c);
                if (amountAnimals.getText() != null && !amountAnimals.getText().isEmpty() &&
                        Integer.valueOf(amountAnimals.getText()) > 0) {
                    int counts = Integer.valueOf(amountAnimals.getText());
                    c.addOnBucket(findAnimal(pets, (String) petList.getSelectedItem()), counts);
                    animalsInBasket = getAnimalsInBasket(c);
                    lableBasket.setText(animalsInBasket);
                } else {
                    JOptionPane.showMessageDialog(frame, "Enter positive amount");
                };
            }
        });

        buttonRemove.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String name = text.getText();
                Customer cust = market.customerByName(name);
                if (cust == null) {
                    JOptionPane.showMessageDialog(frame, "Bad customer");
                    return;
                } else if (cust.getBucket().isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Basket is empty");
                    return;
                }
                if (amountAnimals.getText() != null && !amountAnimals.getText().isEmpty() &&
                        Integer.valueOf(amountAnimals.getText()) > 0) {
                    int counts = Integer.valueOf(amountAnimals.getText());
                    cust.delFromBucket(findAnimal(pets, (String) petList.getSelectedItem()), counts);
                    animalsInBasket = getAnimalsInBasket(cust);
                    lableBasket.setText(animalsInBasket);
                    frame.pack();
                } else {
                    JOptionPane.showMessageDialog(frame, "Enter positive amount");
                }
            }
        });

        buy.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (!text.getText().isEmpty() && !"Basket is empty".equals(animalsInBasket)) {
                    Customer cu = cust.getFirst();
                    market.sell(sdfDate.format(new Date()), cu, cu.getBucket());
                    market.printStore();
                    MarketUI.refreshMainPanel(mainPane,market);
                    animalsInBasket = "Basket is empty";
                    lableBasket.setText(animalsInBasket);
                    cu.clearBucket();
                    frame.setContentPane(mainPane);
                    frame.pack();
                }
            }
        });
    }

}
