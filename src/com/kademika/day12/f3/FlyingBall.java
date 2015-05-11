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
    private int ovalWidth=10;
    private int ovalHeight=10;
    private int ovalX;
    private int ovalY;
    private int ballSpeed;
    private int ballColour;


    public static void main(String[] args) {
        final FlyingBall ball = new FlyingBall();

        ballFly(ball);
    }

    private static void ballFly(final FlyingBall ball) {
        new Thread() {
            @Override
            public void run() {
                ball.myRepaint(ball);
            }
        }.start();

        new Thread() {
            @Override
            public void run() {
                ball.move();
            }
        }.start();
    }

    public FlyingBall() {
        JFrame frame = new JFrame("Flying Ball v1.1");
        frame.setContentPane(this);
        frame.setBounds(200, 200, frameWidth, frameHeight);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(new Color(255, 0, 0));
        g.fillOval(ovalX, 10, ovalWidth, ovalHeight);
    }

    public void myRepaint(JPanel panel) {
        while (true) {
            panel.repaint();
            mySleep(freq);
        }
    }

    public void move() {
        int direction = 1;
        while (true) {
            if (ovalX == frameWidth-10) {
                direction = -1;
                ovalX--;
            } else if (ovalX == 0) {
                direction = 1;
                ovalX++;
            } else {
                ovalX = ovalX+direction;
            }
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

    public int getOvalX() {
        return ovalX;
    }

    public void setOvalX(int ovalX) {
        this.ovalX = ovalX;
    }

    public int getOvalY() {
        return ovalY;
    }

    public void setOvalY(int ovalY) {
        this.ovalY = ovalY;
    }

    public int getBallSpeed() {
        return ballSpeed;
    }

    public void setBallSpeed(int ballSpeed) {
        this.ballSpeed = ballSpeed;
    }

    public int getBallColour() {
        return ballColour;
    }

    public void setBallColour(int ballColour) {
        this.ballColour = ballColour;
    }
}
