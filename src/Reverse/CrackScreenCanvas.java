package Reverse;

import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Graphics;


final class CrackScreenCanvas extends Canvas {
    CrackScreenCanvas(Main main) {
    }

    public final void paint(Graphics graphics) {
        graphics.setColor(0, 0, 0);
        graphics.fillRect(0, 0, getWidth(), getHeight());
        graphics.setColor(255, 180, 0);
        graphics.drawString("Gol4u@ymail.com", 4, 4, 20);
    }
}
