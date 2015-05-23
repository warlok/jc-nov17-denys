package com.kademika.day12.f7;

import javax.swing.*;
import java.awt.*;

/**
 * Created by dean on 5/23/15.
 */
public class StarGates {

    public static void main(String[] main) {

        final StarShip ship = new StarShip();
        final Gates gates = new Gates();

        JFrame frame = new JFrame("Star Gates");
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ship.draw(g);
                gates.draw(g);
            }
        };
        frame.setContentPane(panel);
        frame.setBounds(200, 200, 600, 600);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);


            new Thread() {
                @Override
                public void run() {
                    synchronized (gates) {
                        System.out.println("Expect for the ship");
                        try {
                            gates.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    gates.gatesOpen();

                    synchronized (ship) {
                        ship.notify();
                    }
                }
            }.start();


        new Thread() {
            @Override
            public void run() {
                while (!gates.isOpen && ship.getX() < gates.getX() - 13) {
                    ship.setX(ship.getX() + 1);
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                if (!gates.isOpen) {
                    synchronized (gates) {
                        gates.notify();
                    }
                    synchronized (ship) {
                        try {
                            ship.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
                ship.shipFly();
            }

        }.start();

        while (true) {
            panel.repaint();
        }
    }

}
