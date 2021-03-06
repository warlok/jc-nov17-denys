package com.kademika.day12.f3;

import javax.swing.*;
import java.awt.*;

/**
 * Created by dean on 5/11/15.
 */
public class FlyingBall extends JPanel {

    private long freq = 16;
    private int frameWidth=600;
    private int frameHeight=600;
    private Ball ball1;
    private Ball ball2;
    private Ball ball3;
    private Ball ball4;
    private Ball ball5;
    private Ball ball6;
    private Ball ball7;
    private Ball ball8;
    private Ball ball9;
    private Ball ball0;

    public static void main(String[] args) {
        FlyingBall flball = new FlyingBall();
        while (true) {
            flball.myRepaint(flball);
        }

    }


    public FlyingBall() {
        ball1 = new Ball(10, 16, new Color(255,0,0));
        ball2 = new Ball(25, 20, new Color(177, 255, 112));
        ball3 = new Ball(40, 30, new Color(95, 136, 255));
        ball4 = new Ball(55, 40, new Color(255, 90, 121));
        ball5 = new Ball(70, 5, new Color(240, 255, 79));
        ball6 = new Ball(95, 1, new Color(92, 255, 20));
        ball7 = new Ball(110, 50, new Color(249, 255, 37));
        ball8 = new Ball(135, 70, new Color(227, 209, 255));
        ball9 = new Ball(150, 35, new Color(255, 65, 249));
        ball0 = new Ball(175, 7, new Color(45, 255, 243));
        JFrame frame = new JFrame("Flying Ball v1.1");
        frame.setContentPane(this);
        frame.setBounds(200, 200, frameWidth, frameHeight);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public void myRepaint(JPanel panel) {
        while (true) {
            panel.repaint();
            mySleep(freq);
        }
    }

    public void mySleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        ball1.draw(g);
        ball2.draw(g);
        ball3.draw(g);
        ball4.draw(g);
        ball5.draw(g);
        ball6.draw(g);
        ball7.draw(g);
        ball8.draw(g);
        ball9.draw(g);
        ball0.draw(g);

    }
}
