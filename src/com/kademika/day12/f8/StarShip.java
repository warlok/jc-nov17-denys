package com.kademika.day12.f8;

import java.awt.*;

/**
 * Created by dean on 5/23/15.
 */
public class StarShip {

    private int x;
    private int y = 295;

    StarShip() {

    }

    public void draw(Graphics g) {

        g.setColor(new Color(255, 189, 28));
        g.fillRect(x,y,10,10);

    }

    public void shipFly() {
                while (x < 590) {
                    x++;
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }
}
