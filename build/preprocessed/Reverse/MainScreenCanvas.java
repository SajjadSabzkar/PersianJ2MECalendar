package Reverse;

import java.io.IOException;
import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

public final class MainScreenCanvas extends Canvas {

    private static int HorizontalRowHeight;

    private static int width;

    private static int height;

    public static int densityX = 0;

    private static String backgroundPath = "";

    private static Image backgroundImageHolder;

    public static boolean is_holidays = false;

    public static Image[] DayImageHolder;

    public static Image[][] EventsBitmap = new Image[4][];

    private static int threshold_y;

    private static Image memoImageHolder;

    public static Image[] DayNameHolder;

    private static int verticalOffset;

    public static Image[] MonthImageHolder;

    private static int todayButtonOffset;

    public static Image[] TodayButton;

    private static int dayNameMargin;

    public static Image[] NoteButton;

    private static int day_height;

    public static Image[] ExitButton;

    private static int day_width;

    public static Image[] islamicDateImage;

    private static int dayNameOffset;

    public static Image[] gregorianDateImage;

    private static int gregorianDateHeightOffset;

    private static int gregorianDateWidthOffset;

    private static int islamicDateHeightOffset;

    private static int islamicDateWidthOffset;

    private static int height_margin;

    private static int SolarEventWidthOffset;

    private static int eventHeight;

    private static int memoStateHeight;

    private static int memoStateWidth;

    private static int exitButtonWidth;

    private static int exitButtonHeight;

    private static int noteButtonWidth;

    private static int noteButtonHeight;

    private static int todayButtonWidth;

    private static int todayButtonHeight;

    private static int countOfEventsLine;

    private static int width_margin;

    private static int HorizontalRowWidth;

    private DateManager dateManager;

    private RecordStoreManager recordStoreManager;

    public MainScreenCanvas() {
        setFullScreenMode(true);
        width = (getWidth() - Main.projectWidth) / 2;
        height = (getHeight() - Main.projectHeight) / 2;
        switch (Main.projectHeight) {
            case 160:
                backgroundPath = "/160/B.png";
                day_height = 62;
                dayNameMargin = 22;
                verticalOffset = 4;
                todayButtonOffset = -1;
                day_width = 122;
                dayNameOffset = -1;
                SolarEventWidthOffset = 116;
                height_margin = 73;
                eventHeight = 16;
                islamicDateWidthOffset = 64;
                islamicDateHeightOffset = 49;
                gregorianDateWidthOffset = 64;
                gregorianDateHeightOffset = 52;
                memoStateWidth = 40;
                memoStateHeight = 133;
                threshold_y = 45;
                exitButtonWidth = 4;
                exitButtonHeight = 143;
                noteButtonWidth = 63;
                noteButtonHeight = 143;
                todayButtonWidth = 121;
                todayButtonHeight = 143;
                countOfEventsLine = 4;
                width_margin = 8;
                HorizontalRowWidth = 118;
                HorizontalRowHeight = 11;
                break;
            case 208:
                backgroundPath = "/208/B.png";
                day_height = 85;
                dayNameMargin = 32;
                verticalOffset = 8;
                todayButtonOffset = -1;
                day_width = 166;
                dayNameOffset = -1;
                SolarEventWidthOffset = 158;
                height_margin = 98;
                eventHeight = 20;
                islamicDateWidthOffset = 88;
                islamicDateHeightOffset = 65;
                gregorianDateWidthOffset = 88;
                gregorianDateHeightOffset = 66;
                memoStateWidth = 64;
                memoStateHeight = 174;
                threshold_y = 66;
                exitButtonWidth = 7;
                exitButtonHeight = 182;
                noteButtonWidth = 86;
                noteButtonHeight = 182;
                todayButtonWidth = 167;
                todayButtonHeight = 182;
                countOfEventsLine = 4;
                width_margin = 15;
                HorizontalRowWidth = 160;
                HorizontalRowHeight = 13;
                break;
            case 220:
                backgroundPath = "/220/B.png";
                day_height = 85;
                dayNameMargin = 32;
                verticalOffset = 8;
                todayButtonOffset = -1;
                day_width = 166;
                dayNameOffset = -1;
                SolarEventWidthOffset = 158;
                height_margin = 98;
                eventHeight = 24;
                islamicDateWidthOffset = 88;
                islamicDateHeightOffset = 66;
                gregorianDateWidthOffset = 88;
                gregorianDateHeightOffset = 66;
                memoStateWidth = 64;
                memoStateHeight = 186;
                threshold_y = 66;
                exitButtonWidth = 7;
                exitButtonHeight = 194;
                noteButtonWidth = 86;
                noteButtonHeight = 194;
                todayButtonWidth = 167;
                todayButtonHeight = 194;
                countOfEventsLine = 4;
                width_margin = 15;
                HorizontalRowWidth = 160;
                HorizontalRowHeight = 13;
                break;
            case 320:
                backgroundPath = "/320/B.png";
                day_height = 118;
                dayNameMargin = 50;
                verticalOffset = 8;
                todayButtonOffset = 1;
                day_width = 229;
                dayNameOffset = 1;
                SolarEventWidthOffset = 218;
                height_margin = 142;
                eventHeight = 28;
                islamicDateWidthOffset = 120;
                islamicDateHeightOffset = 103;
                gregorianDateWidthOffset = 120;
                gregorianDateHeightOffset = 106;
                memoStateWidth = 89;
                memoStateHeight = 282;
                threshold_y = 96;
                countOfEventsLine = 5;
                exitButtonWidth = 6;
                exitButtonHeight = 288;
                noteButtonWidth = 116;
                noteButtonHeight = 288;
                todayButtonWidth = 231;
                todayButtonHeight = 288;
                width_margin = 16;
                HorizontalRowWidth = 223;
                HorizontalRowHeight = 18;
                break;
        }
        try {
            backgroundImageHolder = Image.createImage(backgroundPath);
            memoImageHolder = Image.createImage("/memo.png");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public final void recreate() {
        repaint(width, height + threshold_y, getWidth(), 40);
    }

    public final void keyPressed(int keyCode) {
        boolean z = false;

        int KeyCodeAction = new KeyCodeAdapter().adoptKeyCode(keyCode);

        if ((KeyCodeAction == KeyCodeAdapter.KEY_5) || (KeyCodeAction == KeyCodeAdapter.CENTER_KEY)) { //handle Mem
            if (Main.keyboardThread.isAlive()) {
                Main.keyboardThread.running = false;
            }
            this.recordStoreManager = new RecordStoreManager();
            this.dateManager = this.recordStoreManager.getRecord(Main.dayOFShamsiYear + 1);
            String str = "";

            if (this.dateManager.data != null) {
                byte[] localData = this.dateManager.data;
                for (int i = 0; i < localData.length; i += 2) {
                    byte firstByte  = localData[i];
                    byte secondByte  = localData[i + 1];
                    int value = (firstByte  < 0 ? firstByte  + 256 : firstByte ) + ((secondByte  < 0 ? secondByte  + 256 : secondByte ) << 8);
                    str += (char) value;
                }
                Main.memoTextBox.setString(str);
            } else {
                Main.memoTextBox.setString("");
            }
            Main.display.setCurrent(Main.memoTextBox);
        }
    
        if (KeyCodeAction == KeyCodeAdapter.SOFT_KEY_LEFT) {
            Main.instance.destroyApp(true);
            Main.instance.notifyDestroyed();
        }
        if (keyCode == -7) {
            DateManager.updateDates();
           
            repaint();
        }
        if ((keyCode == 50) || (keyCode == -1)) {
            if (Main.currentShamsiDate.getMonth() != 1) {
                int i4 = 0;
                for (int i5 = 0; i5 < Main.currentShamsiDate.getMonth() - 2; i5++) {
                    i4 += Variable.SHAMSI_MONTHS_DAYS[i5];
                }
                Main.dayOFShamsiYear = (Main.currentShamsiDate.getDay() + i4) - 1;
                DateManager.prepare();
                repaint();
            } else {
                return;
            }
        }
        if ((keyCode == 56) || (keyCode == -2)) {
            if (Main.currentShamsiDate.getMonth() != 12) {
                int i6 = 0;
                for (int i7 = 0; i7 < Main.currentShamsiDate.getMonth(); i7++) {
                    i6 += Variable.SHAMSI_MONTHS_DAYS[i7];
                }
                if (Main.currentShamsiDate.getDay() > Variable.SHAMSI_MONTHS_DAYS[Main.currentShamsiDate.getMonth()]) {
                    Main.dayOFShamsiYear = (Main.currentShamsiDate.getDay() + i6) - 2;
                } else {
                    Main.dayOFShamsiYear = (Main.currentShamsiDate.getDay() + i6) - 1;
                }
                DateManager.prepare();
                repaint();
            } else {
                return;
            }
        }
        if ((keyCode == 52) || (keyCode == -3)) {
            if (Main.dayOFShamsiYear > 0) {
                Main.dayOFShamsiYear--;
            }
            DateManager.prepare();
            repaint();
            if (!Main.keyboardThread.isAlive() && keyCode == -3) {
                Main.keyboardThread = new KeyboradThread(3);
            }
        }
        boolean z2 = keyCode == -4;
        if (keyCode == 54) {
            z = true;
        }
        if (z2 || z) {
            if (Main.dayOFShamsiYear < 365) {
                Main.dayOFShamsiYear++;
            }
            DateManager.prepare();
            repaint();
            if (!Main.keyboardThread.isAlive() && keyCode == -4) {
                Main.keyboardThread = new KeyboradThread(4);
            }
        }
        repaint();
    }

    public final void keyReleased(int i) {
        Main.keyboardThread.running = false;
        System.gc();
    }

    public final void paint(Graphics graphics) {
        setFullScreenMode(true);
        graphics.setColor(255, 91, 109);
        if (!is_holidays) {
            graphics.setColor(100, 180, 0);
        }
        graphics.fillRect(0, 0, getWidth(), getHeight());
        graphics.setColor(0, 0, 0);
        graphics.drawLine(width - 1, height - 1, width + Main.projectWidth, height - 1);
        graphics.drawLine(width - 1, height + Main.projectHeight + 1, width + Main.projectWidth, height + Main.projectHeight + 1);
        graphics.drawLine(width - 1, height - 1, width - 1, height + Main.projectHeight + 1);
        graphics.drawLine(width + Main.projectWidth, height - 1, width + Main.projectWidth, height + Main.projectHeight + 1);
        graphics.setColor(255, 91, 109);
        if (!is_holidays) {
            graphics.setColor(100, 180, 0);
        }
        
        graphics.drawImage(backgroundImageHolder, width, height, 20);
        GraphicHandler.displayImagesHorizontally(graphics, DayImageHolder, day_height + width, dayNameMargin + height, 'c', 0);
        GraphicHandler.displayImagesHorizontally(graphics, MonthImageHolder, verticalOffset + width, todayButtonOffset + height, 'l', 0);
        GraphicHandler.displayImagesHorizontally(graphics, DayNameHolder, day_width + width, dayNameOffset + height, 'r', 0);
        
        for (int i = 0; i < countOfEventsLine; i++) {
            graphics.drawLine(width + width_margin, height + height_margin + (eventHeight * i) + HorizontalRowHeight, width + HorizontalRowWidth, height + height_margin + (eventHeight * i) + HorizontalRowHeight);
        }
        for (int i = 0; i < EventGraphicsManager.solarEventIndex; i++) {
           GraphicHandler.displayImagesHorizontally(graphics, EventsBitmap[i], SolarEventWidthOffset + width, (eventHeight * i) + height + height_margin, 'r', 1);
        }
        GraphicHandler.displayImagesHorizontally(graphics, islamicDateImage, ((width + islamicDateWidthOffset) + densityX) - (Main.projectWidth / 2), islamicDateHeightOffset + height, 'c', 0);
        GraphicHandler.displayImagesHorizontally(graphics, gregorianDateImage, (Main.projectWidth / 2) + width + gregorianDateWidthOffset + densityX, gregorianDateHeightOffset + height, 'c', 0);
        GraphicHandler.displayImagesHorizontally(graphics, TodayButton, todayButtonWidth + width, todayButtonHeight + height, 'r', 0);
        GraphicHandler.displayImagesHorizontally(graphics, NoteButton, noteButtonWidth + width, noteButtonHeight + height, 'c', 0);
        GraphicHandler.displayImagesHorizontally(graphics, ExitButton, exitButtonWidth + width, exitButtonHeight + height, 'l', 0);
        if (Main.memoState[Main.dayOFShamsiYear + 1] == 49) {
            graphics.drawImage(memoImageHolder, width + memoStateWidth, height + memoStateHeight, 20);
        }
        graphics.setColor(255, 91, 109);
        if (!is_holidays) {
            graphics.setColor(100, 180, 0);
        }
        graphics.fillRect(0, 0, width - 1, getHeight());
        graphics.fillRect(width + 1 + Main.projectWidth, 0, getWidth(), getHeight());
    }
}
