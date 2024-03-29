package Reverse;

import java.io.IOException;
import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;


public final class HelpCanvas extends Canvas {
    private String folder_path ;
    private Image H_image ;

    HelpCanvas(Main main) {
    }

    public final void keyPressed(int i) {
        Main.display.setCurrent(Main.mainScreen);
    }

    public final void paint(Graphics graphics) {
        int width = (getWidth() - Main.projectWidth) / 2;
        int height = (getHeight() - Main.projectHeight) / 2;
        graphics.setColor(0, 0, 0);
        graphics.fillRect(0, 0, getWidth(), getHeight());
        if (Main.projectHeight == 160) {
            this.folder_path = "/160/H.png";
        } else if (Main.projectHeight < 300) {
            this.folder_path = "/220/H.png";
        } else {
            this.folder_path = "/320/H.png";
        }
        try {
            this.H_image  = Image.createImage(this.folder_path);
        } catch (IOException e) {
        }
        graphics.drawImage(this.H_image , width, height, 20);
    }
}
