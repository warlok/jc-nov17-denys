package com.kademika.day12.f3;

import javax.swing.*;
import java.awt.*;

/**
 * Created by dean on 5/11/15.
 */
public class Ball {

    private int ballX;
    private int ballY;
    private int ballSpeed;
    private Color ballColour;

    public Ball(int ballY, int ballSpeed, Color ballColour) {
        this.ballY = ballY;
        this.ballSpeed = ballSpeed;
        this.ballColour = ballColour;
        new Thread() {
            @Override
            public void run() {
                move();
            }
        }.start();
    }

    public void draw(Graphics g) {
        g.setColor(ballColour);
        g.fillOval(ballX, ballY, 10, 10);
    }

    public void move() {
        int direction = 1;
        while (true) {
            if (ballX == 600-10) {
                direction = -1;
                ballX--;
            } else if (ballX == 0) {
                direction = 1;
                ballX++;
            } else {
                ballX = ballX+direction;
            }
            try {
                Thread.sleep(ballSpeed);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public int getOvalX() {
        return ballX;
    }

    public void setOvalX(int ovalX) {
        this.ballX = ovalX;
    }

    public int getOvalY() {
        return ballY;
    }

    public void setOvalY(int ovalY) {
        this.ballY = ovalY;
    }

    public int getBallSpeed() {
        return ballSpeed;
    }

    public void setBallSpeed(int ballSpeed) {
        this.ballSpeed = ballSpeed;
    }

    public Color getBallColour() {
        return ballColour;
    }

    public void setBallColour(Color ballColour) {
        this.ballColour = ballColour;
    }

}
