package Reverse;

import java.io.DataInputStream;
import java.io.IOException;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

public class GraphicHandler {

    private Image bitmapFont;

    private boolean hasFarsiChars;

    private Image[] farsiBitmapChars;

    private int[][] fontData;

    private boolean hasNumbers;

    private Image[] numberBitmapChars;

    private boolean hasSpecialChars;

    private Image[] specialBitmapChars;

    private boolean decodeBitmaps;

    public GraphicHandler() {
    }

    public GraphicHandler(String fontName) {
        this.hasFarsiChars = false;
        this.hasNumbers = false;
        this.hasSpecialChars = false;
        this.decodeBitmaps = false;
        if (!fontName.equals("") || fontName != null) {
            load(fontName);
        }
    }

    private int getFontDataIndex(int i) {
        for (int idx = 0; idx < this.fontData.length; idx++) {
            if (this.fontData[idx][0] == i) {
                return idx;
            }
        }
        return 0;
    }

    private void load(String fontName) {
        String token = "";
        DataInputStream dataFileStream = new DataInputStream(getClass().getResourceAsStream(new StringBuffer().append("/").append(fontName).append(".txt").toString()));
        while (true) {
            try {
                char inputChar = (char) dataFileStream.readByte();
                if (inputChar == ' ') {
                    break;
                }
                 token = String.valueOf(token) + inputChar;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        int numberOfChars = Integer.parseInt(token);
        this.fontData = new int[numberOfChars][3];
        for (int i = 0; i < numberOfChars; i++) {
            for (int i2 = 0; i2 < 3; i2++) {
                String token2 = "";
                while (true) {
                    try {
                        char inputChar2 = (char) dataFileStream.readByte();
                        if (inputChar2 == ' ') {
                            break;
                        }
                        token2 = String.valueOf(token2) + inputChar2;
                    } catch (IOException e2) {
                        e2.printStackTrace();
                    }
                }
                this.fontData[i][i2] = Integer.parseInt(token2);
            }
        }
        try {
            dataFileStream.close();
        } catch (IOException e3) {
            e3.printStackTrace();
        }
        try {
            this.bitmapFont = Image.createImage(new StringBuffer().append("/").append(fontName).append(".png").toString());
        } catch (IOException e4) {
            e4.printStackTrace();
        }
    }

    public static void displayImagesHorizontally(Graphics graphics, Image[] imageArr, int x   , int y, char alignment, int space) {
        int totalwidth = 0;
        for (int i6 = 0; i6 < imageArr.length; i6++) {
            totalwidth += (imageArr[i6].getWidth() - 2) + space;
        }
        if (alignment == 'c') {
            int offsetX = 0;
            for (int i = imageArr.length - 1; i >= 0; i--) {
                graphics.drawImage(imageArr[i], ((x + offsetX) - (totalwidth / 2)) + space, y, 20);
                offsetX += (imageArr[i].getWidth() - 2) + space;
            }
        } else if (alignment == 'r') {
            int offsetX = 0;
            for (int i = imageArr.length - 1; i >= 0; i--) {
                graphics.drawImage(imageArr[i], ((x + offsetX) - totalwidth) + space, y, 20);
                offsetX += (imageArr[i].getWidth() - 2) + space;
            }
        } else if (alignment == 'l') {
            int offsetX = 0;
            for (int i = imageArr.length - 1; i >= 0; i--) {
                graphics.drawImage(imageArr[i], x + offsetX + space, y, 20);
                offsetX += (imageArr[i].getWidth() - 2) + space;
            }
        }
    }

    private static boolean isFarsi(char c) {
        if ((c <= 1631 || c >= 1642) && c > 1569 && c < 1741) {
            return true;
        }
        return false;
    }

    public static Image[] createFixedHeightBitmap(String input, GraphicHandler farsiFont, GraphicHandler englishFont, GraphicHandler signFont, boolean farsi, boolean forceFarsi) {

        String input2;
        
        Image[] bitmapString = new Image[input.length()];
        if (farsi) {
            input2 = preprocess(input);
        } else {
            input2 = reverse(input);
        }

        String input3 = new StringBuffer().append(" ").append(input2).append(" ").toString();
        for (int i = 1; i < input3.length() - 1; i++) {
            if (isFarsi(input3.charAt(i))) {
                int state = 0;
                if (isSticki(input3.charAt(i - 1))) {
                    state = 0 + 2;
                }
                if (isFarsi(input3.charAt(i + 1))) {
                    state++;
                }
                bitmapString[i - 1] = farsiFont.getBitmapByCode(state + (input3.charAt(i) * '\n'));
            } else if (isFarsiNumber(input3.charAt(i))) {
                bitmapString[i - 1] = farsiFont.getBitmapByCode((int) input3.charAt(i));
            } else if (isEnglish(input3.charAt(i))) {
                bitmapString[i - 1] = englishFont.getBitmapByCode((int) input3.charAt(i));
            } else if (isEnglishNumber(input3.charAt(i))) {
                int code = input3.charAt(i);
                if (forceFarsi) {
                    bitmapString[i - 1] = farsiFont.getBitmapByCode(code + 1584);
                } else {
                    bitmapString[i - 1] = englishFont.getBitmapByCode( code);
                }
            } else {
                int code2 = input3.charAt(i);
                if (forceFarsi) {
                    if (code2 == 40) {
                        code2 = 41;
                    } else if (code2 == 41) {
                        code2 = 40;
                    }
                }
                bitmapString[i - 1] = signFont.getBitmapByCode((int) code2);
            }
        }
        return bitmapString;
    }

    private static boolean isSticki(char c) {
        if (!isFarsi(c)) {
            return false;
        }
      return !(c == 1570 || c == 1575 || c == 1583 || c == 1584 || c == 1585 || c == 1586 || c == 1608 || c == 1688);
    }

    private static boolean isFarsiNumber(char c) {
        if (c < 1632 || c > 1641) {
            return false;
        }
        return true;
    }

    private static boolean isEnglish(char c) {
        if (c >= 'A' && c <= 'Z') {
            return true;
        }
        if (c < 'a' || c > 'z') {
            return false;
        }
        return true;
    }

    private static boolean isEnglishNumber(char c) {
        if (c < '0' || c > '9') {
            return false;
        }
        return true;
    }

    private static String reverse(String s) {
        char[] tmpArray = s.toCharArray();
        for (int i = 0; i < s.length() / 2; i++) {
            char tmp = tmpArray[i];
            tmpArray[i] = tmpArray[(s.length() - i) - 1];
            tmpArray[(s.length() - i) - 1] = tmp;
        }
        return String.valueOf(tmpArray);
    }

    private static String preprocess(String input) {
        int begin = 0;
        char[] chars = input.toCharArray();
        boolean started = false;
        for (int i = 0; i < chars.length; i++) {
            if (isFarsiNumber(chars[i]) || isEnglish(chars[i]) || isEnglishNumber(chars[i])) {
                if (!started) {
                    begin = i;
                    started = true;
                }
            } else if (started) {
                started = false;
                int end = i - 1;
                for (int j = 0; j < ((end - begin) + 1) / 2; j++) {
                    char tmpChar = chars[begin + j];
                    chars[begin + j] = chars[end - j];
                    chars[end - j] = tmpChar;
                }
            }
        }
        if (started) {
            int end2 = chars.length - 1;
            for (int j2 = 0; j2 < ((end2 - begin) + 1) / 2; j2++) {
                char tmpChar2 = chars[begin + j2];
                chars[begin + j2] = chars[end2 - j2];
                chars[end2 - j2] = tmpChar2;
            }
        }
        return String.valueOf(chars);
    }

    public final Image getBitmapByCode(int i) {
        int a = getFontDataIndex(i);
        if (this.hasNumbers && i > 1631 && i < 1642) {
            return this.numberBitmapChars[i - 1632];
        }
        if (this.hasSpecialChars && i > 47 && i < 58) {
            return this.specialBitmapChars[i - 48];
        }
        if (this.hasFarsiChars) {
            return this.farsiBitmapChars[a];
        }
        if (this.decodeBitmaps) {
            if (this.farsiBitmapChars[a] == null) {
                int[] data = new int[(this.bitmapFont.getHeight() * this.fontData[a][2])];
                this.bitmapFont.getRGB(data, 0, this.fontData[a][2], this.fontData[a][1] + 1, 0, this.fontData[a][2], this.bitmapFont.getHeight());
                this.farsiBitmapChars[a] = Image.createRGBImage(data, this.fontData[a][2], this.bitmapFont.getHeight(), true);
            }
            return this.farsiBitmapChars[a];
        }
        int[] iArr2 = new int[(this.bitmapFont.getHeight() * this.fontData[a][2])];
        this.bitmapFont.getRGB(iArr2, 0, this.fontData[a][2], this.fontData[a][1] + 1, 0, this.fontData[a][2], this.bitmapFont.getHeight());
        return Image.createRGBImage(iArr2, this.fontData[a][2], this.bitmapFont.getHeight(), true);
    }


    public final void loadFarsiChars() {
        int length = this.fontData.length;
        this.hasFarsiChars = true;
        this.farsiBitmapChars = new Image[length];
        for (int i = 0; i < length; i++) {
            int[] iArr = new int[(this.bitmapFont.getHeight() * this.fontData[i][2])];
            this.bitmapFont.getRGB(iArr, 0, this.fontData[i][2], this.fontData[i][1], 0, this.fontData[i][2], this.bitmapFont.getHeight());
            this.farsiBitmapChars[i] = Image.createRGBImage(iArr, this.fontData[i][2], this.bitmapFont.getHeight(), true);
        }
    }

    public final void initBitmap(boolean z) {
        this.decodeBitmaps = true;
        this.farsiBitmapChars = new Image[this.fontData.length];
    }

    public final void loadNumbers() {
        this.hasNumbers = true;
        this.numberBitmapChars = new Image[10];
        for (int i = 1632; i < 1642; i++) {
            int a = getFontDataIndex(i);
            int[] iArr = new int[(this.bitmapFont.getHeight() * this.fontData[a][2])];
            this.bitmapFont.getRGB(iArr, 0, this.fontData[a][2], this.fontData[a][1], 0, this.fontData[a][2], this.bitmapFont.getHeight());
            this.numberBitmapChars[i - 1632] = Image.createRGBImage(iArr, this.fontData[a][2], this.bitmapFont.getHeight(), true);
        }
    }

    public final void loadSpecialChars() {
        this.hasSpecialChars = true;
        this.specialBitmapChars = new Image[10];
        for (int i = 48; i < 58; i++) {
            int a = getFontDataIndex(i);
            int[] iArr = new int[(this.bitmapFont.getHeight() * this.fontData[a][2])];
            this.bitmapFont.getRGB(iArr, 0, this.fontData[a][2], this.fontData[a][1], 0, this.fontData[a][2], this.bitmapFont.getHeight());
            this.specialBitmapChars[i - 48] = Image.createRGBImage(iArr, this.fontData[a][2], this.bitmapFont.getHeight(), true);
        }
    }
}
