package com.kademika.day12.f7;

import java.awt.*;

/**
 * Created by dean on 5/23/15.
 */
public class Gates {

    public boolean isOpen = false;

    private class HalfGate {
        public int x;
        public int y;

        public void draw(Graphics g) {
            g.setColor(new Color(255, 0, 0));
            g.fillRect(x, y, 10, 100);
        }

        public HalfGate(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private HalfGate uperGate = new HalfGate(320,200);
    private HalfGate lowerGate = new HalfGate(320,300);

    public void gatesOpen() {
        for (int i=0; i<15; i++) {
            uperGate.y--;
            lowerGate.y++;
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        isOpen = true;
    }

    public void gatesClose() {
        for (int i=0; i<15; i++) {
            uperGate.y++;
            lowerGate.y--;
        }
        isOpen = false;
    }

    public void draw(Graphics g) {
        uperGate.draw(g);
        lowerGate.draw(g);
    }

    public int getX() {
        return uperGate.x;
    }

    public boolean isOpen() {
        return isOpen;
    }
}
