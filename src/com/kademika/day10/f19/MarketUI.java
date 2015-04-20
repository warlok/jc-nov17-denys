package com.kademika.day10.f19;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

//import javax.swing.JFrame;
//import javax.swing.JLabel;
//import javax.swing.JList;
//import javax.swing.JPanel;
//import javax.swing.JRadioButton;
//import javax.swing.JRadioButtonMenuItem;
//import javax.swing.JTextArea;

public class MarketUI {

	public MarketUI() {
        SplashScreen splashScreen = SplashScreen.getSplashScreen();

		final LinkedList<Customer> cust = new LinkedList<>();
		final Market m = new Market();
		Animal tiger = new Animal();
		tiger.setName("tiger");
		tiger.setOrder("Carnivora"); // Hishnik
		tiger.setType(Type.Mammal); // Mlekopitauhee
		tiger.setPrice(500);
		m.addAnimal(tiger);
		m.addOnStore(tiger, 5);

		Animal rabbit = new Animal();
		rabbit.setName("rabbit");
		rabbit.setOrder("Lagomorpha"); // Zytseobraznie
		rabbit.setType(Type.Mammal); // Mlekopitauhee
		rabbit.setPrice(10);
		m.addAnimal(rabbit);
		m.addOnStore(rabbit, 45);

		Animal raccoon = new Animal();
		raccoon.setName("raccoon");
		raccoon.setOrder("Carnivora");
		raccoon.setType(Type.Mammal);
		raccoon.setPrice(100);
		m.addAnimal(raccoon);
		m.addOnStore(raccoon, 10);

		Animal penguin = new Animal();
		penguin.setName("penguin");
		penguin.setOrder("Sphenisciformes");
		penguin.setType(Type.Bird);
		penguin.setPrice(80);
		m.addAnimal(penguin);
		m.addOnStore(penguin, 15);

		Animal chameleon = new Animal();
		chameleon.setName("chameleon");
		chameleon.setOrder("Squamata");
		chameleon.setType(Type.Reptile);
		chameleon.setPrice(20);
		m.addAnimal(chameleon);
		m.addOnStore(chameleon, 20);

		Animal iguana = new Animal();
		iguana.setName("iguana");
		iguana.setOrder("Squamata");
		iguana.setType(Type.Reptile);
		iguana.setPrice(30);
		m.addAnimal(iguana);
		m.addOnStore(iguana, 25);

		Animal owl = new Animal();
		owl.setName("owl");
		owl.setOrder("Strigiformes"); // Zaytseobraznie
		owl.setType(Type.Bird);
		owl.setPrice(30);
		m.addAnimal(owl);
		m.addOnStore(owl, 40);

		Animal parrot = new Animal();
		parrot.setName("parrot");
		parrot.setOrder("Psittaciformes");
		parrot.setType(Type.Bird);
		parrot.setPrice(10);
		m.addAnimal(parrot);
		m.addOnStore(parrot, 15);

		Animal sunfish = new Animal();
		sunfish.setName("sunfish");
		sunfish.setOrder("Tetraodontiformes"); // Kostnie
		sunfish.setType(Type.Fish);
		sunfish.setPrice(2000);
		m.addAnimal(sunfish);
		m.addOnStore(sunfish, 5);

		Animal boa = new Animal();
		boa.setName("boa");
		boa.setOrder("Squamata");
		boa.setType(Type.Reptile);
		boa.setPrice(100);
		m.addAnimal(boa);
		m.addOnStore(boa, 10);

		Animal frog = new Animal();
		frog.setName("frog");
		frog.setOrder("Anura");
		frog.setType(Type.Amphibian);
		frog.setPrice(15);
		m.addAnimal(frog);
		m.addOnStore(frog, 20);

		Animal aligator = new Animal();
		aligator.setName("aligator");
		aligator.setOrder("Crocodilia");
		aligator.setType(Type.Reptile);
		aligator.setPrice(500);
		m.addAnimal(aligator);
		m.addOnStore(aligator, 4);

		Animal newt = new Animal();
		newt.setName("newt");
		newt.setOrder("Caudata");
		newt.setType(Type.Amphibian);
		newt.setPrice(5);
		m.addAnimal(newt);
		m.addOnStore(newt, 50);

        final JPanel panel1 = new JPanel();
        final JPanel panel2 = new JPanel();
        panel2.add(m.printTransactions());
        panel1.setLayout(new GridBagLayout());
//        panel2.setLayout(new GridBagLayout());
		final JFrame frame = new JFrame("Petshop");
		frame.setBounds(350, 50, 300, 500);
		frame.setContentPane(panel2);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("File");
        JMenuItem menuItem = new JMenuItem("By birds");
        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setContentPane(panel1);
                frame.pack();
            }
        });
        menu.add(menuItem);
        menuBar.add(menu);
        frame.setJMenuBar(menuBar);
		JLabel lable = new JLabel("Customer's Name:");
		final JTextField text = new JTextField(10);
		final Animal[] pets = { newt, aligator, frog, boa, sunfish,
				parrot, owl, tiger, rabbit, raccoon, penguin,
				chameleon, iguana };
		String[] petStrings = { newt.getName(), aligator.getName(), frog.getName(), boa.getName(), sunfish.getName(),
				parrot.getName(), owl.getName(), tiger.getName(), rabbit.getName(), raccoon.getName(), penguin.getName(),
				chameleon.getName(), iguana.getName() };
		final JComboBox petList = new JComboBox(petStrings);
		petList.setMaximumRowCount(5);
		JLabel amountLable = new JLabel("Input Amount:");
		final JTextField amountAnimals = new JTextField(2);
		final JButton button = new JButton("Add");
		final JButton buy = new JButton("BUY");
		JLabel lablePetsList = new JLabel("Choose an Animal:");
		// this.add(lable);
		panel1.add(lable, new GridBagConstraints(0, 0, 1, 1, 0, 0,
                GridBagConstraints.LINE_START, 0, new Insets(0, 0, 0, 0), 0, 0));
        panel1.add(text, new GridBagConstraints(1, 0, 1, 1, 0, 0,
                GridBagConstraints.LINE_START, GridBagConstraints.HORIZONTAL,
                new Insets(0, 0, 0, 0), 0, 0));
        panel1.add(lablePetsList, new GridBagConstraints(0, 1, 1, 1, 0, 0,
                GridBagConstraints.LINE_START, 0, new Insets(0, 0, 0, 0), 0, 0));
        panel1.add(petList, new GridBagConstraints(1, 1, 1, 1, 0, 0,
                GridBagConstraints.LINE_START, GridBagConstraints.HORIZONTAL,
                new Insets(0, 0, 0, 0), 0, 0));
        panel1.add(amountLable, new GridBagConstraints(0, 2, 1, 1, 0, 0,
                GridBagConstraints.LINE_START, 0, new Insets(0, 0, 0, 0), 0, 0));
        panel1.add(amountAnimals,
                new GridBagConstraints(1, 2, 1, 1, 0, 0,
                        GridBagConstraints.LINE_START, 0,
                        new Insets(5, 0, 0, 0), 15, 7));
        panel1.add(button,
                new GridBagConstraints(1, 2, 1, 1, 0, 0,
                        GridBagConstraints.LINE_START, 0, new Insets(5, 47, 0,
                        0), 0, 0));
        panel1.add(buy,
                new GridBagConstraints(1, 3, 1, 1, 0, 0,
                        GridBagConstraints.LINE_START, 0, new Insets(50, 0, 0,
                        0), 0, 0));
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Customer c;
				if (!cust.isEmpty() && cust.getFirst().getName().equals(text.getText())) {
					c = cust.getFirst();
				} else {
					c = new Customer();
					c.setName(text.getText());
					cust.addFirst(c);
				}
				int counts = Integer.valueOf(amountAnimals.getText());
//				c.addOnBucket( (Animal) petList.getSelectedItem(),counts);
				c.addOnBucket( findAnimal(pets, (String) petList.getSelectedItem()),counts);
			}
		});

		buy.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Customer cu = cust.getFirst();
				for (int i=0; i<100; i++) {
					System.out.println("\n\n\n\n");
				}
				m.sell("Today", cu, cu.getBucket());
                panel2.remove(0);
                panel2.add(m.printTransactions());
				frame.setContentPane(panel2);
                frame.pack();

			}
		});
		frame.pack();
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            System.err.println("Caught exeption");
        }
        splashScreen.close();
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		new MarketUI();
	}

	private Animal findAnimal(Animal[] array,String name) {
		for (Animal a : array) {
			if (a.getName().equals(name)) {
				return a;
			}
		}
		return null;
	}
}
