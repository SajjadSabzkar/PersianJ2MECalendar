package Reverse;

import java.io.IOException;
import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

public final class TableScreenCanvas extends Canvas {

    private int f98a;

    private GraphicHandler f99a;

    private String f100a;

    private Image f101a;

    private byte[] f102a = {3, 6, 2, 5, 1, 4, 0, 2, 4, 6, 1, 3};

    private final int[] f103a = {0, 31, 62, 93, 124, 155, 186, 216, 246, 276, 306, 336};

    private Image[] f104a;


    private byte[][] f105a = new byte[6][7];

    private int f106b;

    private String f107b;

    private Image f108b;

    private Image[] f109b;

    private int f110c;

    private Image f111c;

    private Image[] f112c;

    private int f113d;

    private Image f114d;

    private Image[] f115d;

    private int f116e;

    private Image f117e;

    private Image[] f118e;

    private int f119f;

    private Image f120f;

    private int f121g;

    private Image f122g;

    private int f123h;

    private Image f124h;

    private int f125i;

    /* renamed from: i */
    private Image f126i;

    /* renamed from: j */
    private int f127j;

    /* renamed from: k */
    private int f128k;

    /* renamed from: l */
    private int f129l;

    /* renamed from: m */
    private int f130m;

    /* renamed from: n */
    private int f131n;

    public TableScreenCanvas() {
        setFullScreenMode(true);
        this.f114d = m20a("/empty.png");
        switch (Main.projectHeight) {
            case 160:
                this.f110c = 8;
                this.f113d = 38;
                this.f116e = 18;
                this.f119f = 16;
                this.f121g = 0;
                this.f123h = 38;
                this.f125i = 64;
                this.f127j = 0;
                this.f100a = "/160/S.png";
                this.f107b = "/160";
                this.f99a = new GraphicHandler("fonts/B Homa13_p");
                this.f99a.loadNumbers();
                break;
            case 208:
                this.f110c = 14;
                this.f113d = 58;
                this.f116e = 24;
                this.f119f = 20;
                this.f121g = 4;
                this.f123h = 55;
                this.f125i = 88;
                this.f127j = 0;
                this.f128k = 7;
                this.f130m = 86;
                this.f131n = 167;
                this.f129l = 182;
                this.f100a = "/208/S.png";
                this.f107b = "/220";
                this.f99a = new GraphicHandler("fonts/B Homa13_p");
                this.f99a.loadNumbers();
                break;
            case 220:
                this.f110c = 14;
                this.f113d = 53;
                this.f116e = 24;
                this.f119f = 24;
                this.f121g = 4;
                this.f123h = 50;
                this.f125i = 88;
                this.f127j = 0;
                this.f128k = 7;
                this.f130m = 86;
                this.f131n = 167;
                this.f129l = 195;
                this.f100a = "/220/S.png";
                this.f107b = "/220";
                this.f99a = new GraphicHandler("fonts/B Homa13_p");
                this.f99a.loadNumbers();
                break;
            case 320:
                this.f110c = 19;
                this.f113d = 76;
                this.f116e = 33;
                this.f119f = 33;
                this.f121g = 5;
                this.f123h = 72;
                this.f125i = 120;
                this.f127j = 0;
                this.f128k = 8;
                this.f130m = 118;
                this.f131n = 228;
                this.f129l = 288;
                this.f100a = "/320/S.png";
                this.f107b = "/320";
                this.f99a = new GraphicHandler("fonts/B Homa18_p");
                this.f99a.loadNumbers();
                break;
        }
        this.f117e = m20a(this.f100a);
        this.f101a = m20a(new StringBuffer().append(this.f107b).append("/SGN.png").toString());
        this.f108b = m20a(new StringBuffer().append(this.f107b).append("/SRN.png").toString());
        this.f122g = m20a(new StringBuffer().append(this.f107b).append("/SGS.png").toString());
        this.f120f = m20a(new StringBuffer().append(this.f107b).append("/SRS.png").toString());
        this.f124h = m20a(new StringBuffer().append(this.f107b).append("/SRM.png").toString());
        this.f126i = m20a(new StringBuffer().append(this.f107b).append("/SGM.png").toString());
    }


    private static Image m20a(String str) {
        try {
            return Image.createImage(str);
        } catch (IOException e) {
            return null;
        }
    }


    private byte[][] m21a(int i) {
        byte[] bArr = new byte[42];
        byte[][] bArr2 = new byte[6][7];
        for (int i2 = 0; i2 < 42; i2++) {
            bArr[i2] = 0;
        }
        int i3 = this.f102a[i - 1];
        while (i3 < this.f102a[i - 1] + Variable.SHAMSI_MONTHS_DAYS[i - 1]) {
            int i4 = ((i3 == 1 ? 1 : 0) - this.f102a[i - 1]) + 1;
            int i5 = 0;
            boolean z = false;
            while (i5 < Variable.Vacations[i].length) {
                if (i4 == Variable.Vacations[i][i5]) {
                    i5 = 100;
                    z = true;
                }
                i5++;
                z = z;
            }
            boolean z2 = z;
            if ((((this.f103a[i - 1] + i4) + 3) - 1) % 7 == 6) {
                z2 = true;
            }
            if (z2) {
                bArr[i3] = 2;
            } else {
                bArr[i3 == 1 ? 1 : 0] = 1;
            }
            i3++;
        }
        int i6 = (this.f106b + this.f102a[i - 1]) - 1;
        bArr[i6] = (byte) (bArr[i6] + 2);
        for (int i7 = 0; i7 < 6; i7++) {
            for (int i8 = 0; i8 < 7; i8++) {
                bArr2[i7][i8] = bArr[(i7 * 7) + i8];
            }
        }
        return bArr2;
    }

    /* renamed from: a */
    public final void mo26a(int i, int i2) {
        this.f106b = i2;
        this.f105a = m21a(i);
        this.f98a = i;
    }

    public final void keyPressed(int i) {
        boolean z = true;
        if (i == -7 && this.f98a < 12) {
            this.f98a++;
            this.f106b = 1;
            this.f105a = m21a(this.f98a);
            repaint();
        }
        if (i == -6 && this.f98a > 1) {
            this.f98a--;
            this.f106b = 1;
            this.f105a = m21a(this.f98a);
            repaint();
        }
        if ((i == 52) || (i == -3)) {
            if (this.f106b > 1) {
                this.f106b--;
            } else if (this.f98a > 1) {
                this.f98a--;
                this.f106b = Variable.SHAMSI_MONTHS_DAYS[this.f98a];
            }
            this.f105a = m21a(this.f98a);
            repaint();
        }
        if ((i == 54) || (i == -4)) {
            if (this.f106b < Variable.SHAMSI_MONTHS_DAYS[this.f98a - 1]) {
                this.f106b++;
            } else if (this.f98a < 12) {
                this.f98a++;
                this.f106b = 1;
            }
            this.f105a = m21a(this.f98a);
            repaint();
        }
        if ((i == 50) || (i == -1)) {
            if (this.f106b > 7) {
                this.f106b -= 7;
            }
            this.f105a = m21a(this.f98a);
            repaint();
        }
        if ((i == 56) || (i == -2)) {
            if (this.f106b < Variable.SHAMSI_MONTHS_DAYS[this.f98a - 1] - 6) {
                this.f106b += 7;
            }
            this.f105a = m21a(this.f98a);
            repaint();
        }
        boolean z2 = i == -5;
        if (i != 53) {
            z = false;
        }
        if (z || z2) {
            Main.dayOFShamsiYear = (this.f103a[this.f98a - 1] + this.f106b) - 1;
            DateManager.prepare();
            Main.display.setCurrent(Main.mainScreen);
        }
        if (i == 35) {
            Main.display.setCurrent(Main.mainScreen);
        }
    }

    public final void paint(Graphics graphics) {
        int i;
        int width = (getWidth() - Main.projectWidth) / 2;
        int height = (getHeight() - Main.projectHeight) / 2;
        int i2 = 0;
        graphics.setColor(0, 0, 0);
        graphics.fillRect(0, 0, getWidth(), getHeight());
        graphics.drawImage(this.f117e, width, height, 20);
        for (int i3 = 0; i3 < 6; i3++) {
            int i4 = 0;
            while (i4 < 7) {
                if (this.f105a[i3][i4] == 0) {
                    this.f111c = this.f114d;
                    this.f104a = null;
                    i = i2;
                } else {
                    i = i2 + 1;
                    this.f104a = GraphicHandler.createFixedHeightBitmap(String.valueOf(i), this.f99a, (GraphicHandler) null, (GraphicHandler) null, true, true);
                }
                if (this.f105a[i3][i4] == 2) {
                    this.f111c = this.f108b;
                    if (Main.memoState[(((this.f103a[this.f98a - 1] + (i3 * 7)) + i4) - this.f102a[this.f98a - 1]) + 1] == 49) {
                        this.f111c = this.f124h;
                    }
                }
                if (this.f105a[i3][i4] == 4) {
                    this.f111c = this.f120f;
                }
                if (this.f105a[i3][i4] == 1) {
                    this.f111c = this.f101a;
                    if (Main.memoState[(((this.f103a[this.f98a - 1] + (i3 * 7)) + i4) - this.f102a[this.f98a - 1]) + 1] == 49) {
                        this.f111c = this.f126i;
                    }
                }
                if (this.f105a[i3][i4] == 3) {
                    this.f111c = this.f122g;
                }
                graphics.drawImage(this.f111c, (this.f116e * i4) + width + this.f121g, (this.f119f * i3) + this.f123h + height, 20);
                if (this.f104a != null) {
                    GraphicHandler.displayImagesHorizontally(graphics, this.f104a, (this.f116e * i4) + this.f110c + width, (this.f119f * i3) + this.f113d + height, 'c', 0);
                }
                i4++;
                i2 = i;
            }
        }
        this.f109b = GraphicHandler.createFixedHeightBitmap(stringStorage.monthsName[this.f98a - 1], EventGraphicsManager.fontHoma13White, (GraphicHandler) null, (GraphicHandler) null, true, true);
        GraphicHandler.displayImagesHorizontally(graphics, this.f109b, width + this.f125i, height, 'c', 0);
        if (Main.projectHeight != 160) {
            this.f112c = GraphicHandler.createFixedHeightBitmap("ماه قبل", EventGraphicsManager.fontHoma13White, (GraphicHandler) null, EventGraphicsManager.fontBhoma13O, true, true);
            GraphicHandler.displayImagesHorizontally(graphics, this.f112c, width + this.f128k, height + this.f129l, 'l', 0);
            this.f115d = GraphicHandler.createFixedHeightBitmap("انتخاب", EventGraphicsManager.fontHoma13White, (GraphicHandler) null, EventGraphicsManager.fontBhoma13O, true, true);
            GraphicHandler.displayImagesHorizontally(graphics, this.f115d, width + this.f130m, height + this.f129l, 'c', 0);
            this.f118e = GraphicHandler.createFixedHeightBitmap("ماه بعد", EventGraphicsManager.fontHoma13White, (GraphicHandler) null, EventGraphicsManager.fontBhoma13O, true, true);
            GraphicHandler.displayImagesHorizontally(graphics, this.f118e, width + this.f131n, height + this.f129l, 'r', 0);
        }
    }
}
