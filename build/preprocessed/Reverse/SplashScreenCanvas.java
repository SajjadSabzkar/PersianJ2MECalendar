package Reverse;

import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Graphics;

final class SplashScreenCanvas extends Canvas {
    SplashScreenCanvas(Main main) {
    }

    public final void paint(Graphics graphics) {
        graphics.fillRect(0, 0, getWidth(), getHeight());
        try {
            Thread.sleep(20);
        } catch (InterruptedException e) {
        }
        Main.display.setCurrent(Main.mainScreen);
    }
}
