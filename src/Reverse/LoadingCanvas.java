package Reverse;

import java.io.IOException;
import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

public final class LoadingCanvas extends Canvas {

    private static int progress = 0;

    private static LoadingCanvas instance;

    private String folder_path = "";

    private Image Author_image;

    private int y;

    private String absolout_author_icon = "";

    private Image P_image;

    private int x;

    private int threshold;

    public LoadingCanvas() {
        instance = this;
        setFullScreenMode(true);
        int height = getHeight();
        int width = getWidth();
        if (width < 170 || height < 202) {
            Main.projectWidth = 128;
            Main.projectHeight = 160;
        } else if (width >= 234 && height >= 308) {
            Main.projectWidth = 240;
            Main.projectHeight = 320;
        } else if (height < 216) {
            Main.projectWidth = 176;
            Main.projectHeight = 208;
        } else {
            Main.projectWidth = 176;
            Main.projectHeight = 220;
        }
        ResourceVerifier.setDensity(Main.projectHeight);
        if (Main.projectHeight == 160) {
            this.folder_path = "160";
            this.absolout_author_icon = "/160/L.png";
            this.x = 14;
            this.y = 134;
            this.threshold = 2;
        } else if (Main.projectHeight < 300) {
            this.folder_path = "220";
            this.absolout_author_icon = "/220/L.png";
            this.x = 38;
            this.y = 188;
            this.threshold = 2;
        } else {
            this.folder_path = "320";
            this.absolout_author_icon = "/320/L.png";
            this.x = 45;
            this.y = 266;
            this.threshold = 3;
        }
        try {
            this.P_image = Image.createImage(new StringBuffer().append("/").append(this.folder_path).append("/P.png").toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            this.Author_image = Image.createImage(this.absolout_author_icon);
        } catch (IOException e2) {
            e2.printStackTrace();
        }
    }

    public static void setProgress(int i) {
        progress = i;
        instance.repaint();
    }

    public final void paint(Graphics graphics) {
        int width = (getWidth() - Main.projectWidth) / 2;
        int height = (getHeight() - Main.projectHeight) / 2;
        graphics.setColor(0, 0, 0);
        graphics.fillRect(0, 0, getWidth(), getHeight());
        graphics.drawImage(this.Author_image, width, height, 20);
        graphics.setColor(255, 245, 0);
        graphics.drawString("Gol4u@ymail.com", width + 4, height + 4, 20);
        for (int i = 0; i < progress; i++) {
            graphics.drawImage(this.P_image, this.x + width + (this.threshold * i), this.y + height, 20);
        }
    }
}
