package com.kademika.day14.store.Market.MarketGUI.Panels;

import com.kademika.day14.store.Market.Market;
import com.kademika.day14.store.Market.Objects.Animal;
import com.kademika.day14.store.Market.Objects.Type;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by dean on 8/16/15.
 */
public class ManagePanel extends AbstractPanel  {

    private Animal[] pets;
    private String[] petStrings;
    private JComboBox petList;
    private JButton buttonAdd;
    private JButton buttonRemove;
    private JButton exitButton;
    private JTextField name;
    private JTextField type;
    private JTextField price;

    public ManagePanel(final Market market, final JFrame frame, final JPanel mainPane) {

        setLayout(new GridBagLayout());
//        pets = new Animal[market.getAnimals().size()];
//        pets = market.getAnimals().values().toArray(pets);
//        petStrings = new String[pets.length];
//        for (int i=0; i< pets.length; i++) {
//            petStrings[i] = pets[i].getName();
//        }
        petList = new JComboBox(market.generatePetlistString());
        petList.setMaximumRowCount(5);
//        petList.setMinimumSize(new Dimension(150,10));
        buttonAdd = new JButton("Add");
        buttonRemove = new JButton("Remove");
        exitButton = new JButton("Exit");
        name = new JTextField(10);
        type = new JTextField(10);
        price = new JTextField(5);


        add(petList, new GridBagConstraints(0, 0, 3, 1, 0, 0,
                GridBagConstraints.LINE_START, 0, new Insets(0, 0, 0, 0), 150, 0));
        add(buttonRemove, new GridBagConstraints(3, 0, 1, 1, 0, 0,
                GridBagConstraints.LINE_START, 0,
                new Insets(0, 0, 0, 0), 0, 0));
        add(name, new GridBagConstraints(0, 1, 1, 1, 0, 0,
                GridBagConstraints.LINE_START, 0, new Insets(0, 0, 0, 0), 0, 0));
        add(type, new GridBagConstraints(1, 1, 1, 1, 0, 0,
                GridBagConstraints.LINE_START, 0, new Insets(0, 0, 0, 0), 0, 0));
        add(price,
                new GridBagConstraints(2, 1, 1, 1, 0, 0,
                        GridBagConstraints.LINE_START, 0,
                        new Insets(0, 0, 0, 0), 0, 0));
        add(buttonAdd,
                new GridBagConstraints(3, 1, 1, 1, 0, 0,
                        GridBagConstraints.LINE_START, 0, new Insets(0, 0, 0,
                        0), 0, 0));
        add(exitButton,
                new GridBagConstraints(1, 2, 1, 1, 0, 0,
                        GridBagConstraints.LINE_END, 0, new Insets(0, 0, 0,
                        0), 0, 0));

        buttonRemove.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            Animal anim = findAnimal(pets,petList.getSelectedItem().toString());
                market.removeAnimal(anim);
            }
        });

        buttonAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!name.getText().isEmpty() && !type.getText().isEmpty() &&
                        checkString(price.getText())) {
                    Animal a = new Animal();
                    a.setType(Type.valueOf(type.getText()));
                    a.setName("name.getText()");
                    a.setPrice(Double.parseDouble(price.getText()));
                    market.addAnimal(a);
                } else {
                    JOptionPane.showMessageDialog(frame, "Bad parameter(s)");
                }
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setContentPane(mainPane);
                frame.pack();
            }
        });

    }

}
