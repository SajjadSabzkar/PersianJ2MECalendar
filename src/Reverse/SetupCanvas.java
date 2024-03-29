package Reverse;

import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Graphics;

public final class SetupCanvas extends Canvas {
    SetupCanvas(Main main) {
    }

    public final void paint(Graphics graphics) {
        graphics.setColor(0, 0, 0);
        graphics.fillRect(0, 0, getWidth(), getHeight());
        graphics.setColor(255, 180, 0);
        graphics.drawString("Please wait...", 4, 4, 20);
        graphics.drawString("Installing...", 4, 20, 20);
        graphics.drawString(new StringBuffer().append(Integer.toString(Main.memoInstallState)).append("/366").toString(), 4, 36, 20);
    }
}
