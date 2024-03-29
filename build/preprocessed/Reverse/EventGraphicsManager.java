package Reverse;

import javax.microedition.lcdui.Image;

public final class EventGraphicsManager {

    public static int solarEventIndex = 0;

    public static GraphicHandler fontBhoma13O;

    private static String homa13white;

    public static int[][] sEvents = {new int[0], new int[]{11, 21, 31, 41, 121, 131, 201, 251, 291}, new int[]{11, 21, 31, 51, 91, 101, 122, 171, 241, 251, 271, 281}, new int[]{12, 31, 141, 151, 251, 271, 291, 311}, new int[]{11, 72, 101, 122, 141, 161, 251}, new int[]{91, 141, 171, 281, 301}, new int[]{12, 31, 41, 52, 101, 111, 132, 171, 211, 272, 301, 311}, new int[]{71, 81, 133, 141, 201, 261, 291}, new int[]{11, 81, 131, 141, 181, 241}, new int[]{71, 131, 161, 251, 261, 301}, new int[]{51, 141, 151, 201, 261}, new int[]{122, 191, 221}, new int[]{53, 141, 151, 251, 291}};

    private static Image[][] solarEventImages = new Image[13][];

    public static GraphicHandler fontHoma13White;

    private static String b_homa13;

    public static int[][] gEvents = {new int[0], new int[]{91, 101, 121}, new int[]{71, 201, 282, 291}, new int[]{81, 123, 172}, new int[]{81, 101}, new int[]{52, 132}, new int[]{31, 202}, new int[]{11, 31, 101, 131, 153, 251, 271}, new int[]{32, 42, 51, 112, 152}, new int[]{101, 151, 181, 191, 201, 211, 221}, new int[]{11, 251}, new int[]{12, 111, 291}, new int[]{11, 71, 91, 101, 151, 181}};

    private static Image[][] gregorianEventImages = new Image[7][];

    private static int islamicEventIndex = 0;

    private static GraphicHandler fontBHoma13;

    private static String b_homa23Sign;

    public static int[][] mEvents = {new int[0], new int[0], new int[0], new int[]{221, 231}, new int[]{71}, new int[]{11, 82, 181, 311}, new int[]{51, 101, 171, 261}, new int[0], new int[]{11, 211}, new int[]{271, 302}, new int[]{11, 81, 91, 141, 151, 161}, new int[0], new int[]{11, 31, 71}};

    private static int gregorianEventIndex = 0;

    private static GraphicHandler fontBhoma23Sign;

    private static String tahoma13_e;

    private static GraphicHandler fontTahoma13_e;

    private static String b_homa10;

    private static GraphicHandler fontBHoma10O;

    private static GraphicHandler fontBHoma10;

    public static void m17a() {
        if (Main.projectHeight == 160) {
            homa13white = "fonts/Homa13w_p";
            b_homa13 = "fonts/B Homa13";
            b_homa23Sign = "fonts/B Homa23_o";
            tahoma13_e = "fonts/Tahoma13_e";
            b_homa10 = "fonts/B Homa10";
        } else if (Main.projectHeight == 320) {
            homa13white = "fonts/B Homa24w_p";
            b_homa13 = "fonts/B Homa24";
            b_homa23Sign = "fonts/B Homa45_o";
            tahoma13_e = "fonts/Tahoma20_e";
            b_homa10 = "fonts/B Homa18";
        } else {
            homa13white = "fonts/Homa18w_p";
            b_homa13 = "fonts/B Homa18";
            b_homa23Sign = "fonts/B Homa36_o";
            tahoma13_e = "fonts/Tahoma17_e";
            b_homa10 = "fonts/B Homa13";
        }
        fontBHoma13 = new GraphicHandler(new StringBuffer().append(b_homa13).append("_p").toString());
        fontBHoma13.loadNumbers();
        fontBHoma13.initBitmap(true);
        LoadingCanvas.setProgress(5);

        fontBhoma13O = new GraphicHandler(new StringBuffer().append(b_homa13).append("_o").toString());
        fontBhoma13O.initBitmap(true);
        LoadingCanvas.setProgress(10);

        fontBhoma23Sign = new GraphicHandler(b_homa23Sign);
        fontBhoma23Sign.loadFarsiChars();
        LoadingCanvas.setProgress(15);

        fontTahoma13_e = new GraphicHandler(tahoma13_e);
        fontTahoma13_e.loadSpecialChars();
        fontTahoma13_e.initBitmap(true);
        LoadingCanvas.setProgress(20);

        fontHoma13White = new GraphicHandler(homa13white);
        fontHoma13White.initBitmap(true);
        LoadingCanvas.setProgress(25);

        fontBHoma10 = new GraphicHandler(new StringBuffer().append(b_homa10).append("_p").toString());
        fontBHoma10.loadFarsiChars();
        LoadingCanvas.setProgress(30);

        fontBHoma10O = new GraphicHandler(new StringBuffer().append(b_homa10).append("_o").toString());
        fontBHoma10O.loadFarsiChars();

        MainScreenCanvas.TodayButton = GraphicHandler.createFixedHeightBitmap("امروز", fontBHoma13, (GraphicHandler) null, (GraphicHandler) null, true, true);
        LoadingCanvas.setProgress(40);
        MainScreenCanvas.NoteButton = GraphicHandler.createFixedHeightBitmap("یادداشت", fontBHoma13, (GraphicHandler) null, (GraphicHandler) null, true, true);
        LoadingCanvas.setProgress(45);
        MainScreenCanvas.ExitButton = GraphicHandler.createFixedHeightBitmap("خروج", fontBHoma13, (GraphicHandler) null, (GraphicHandler) null, true, true);
        LoadingCanvas.setProgress(50);
    }

    public static void prepareEventGraphics() {
        if (gregorianEventImages[Main.currentShamsiDate.getDayOfWeek()] == null) {
            gregorianEventImages[Main.currentShamsiDate.getDayOfWeek()] = GraphicHandler.createFixedHeightBitmap(stringStorage.dayName[Main.currentShamsiDate.getDayOfWeek()], fontHoma13White, (GraphicHandler) null, fontBhoma13O, true, true);
        }
        MainScreenCanvas.DayNameHolder = gregorianEventImages[Main.currentShamsiDate.getDayOfWeek()];
        MainScreenCanvas.DayImageHolder = GraphicHandler.createFixedHeightBitmap(String.valueOf(Main.currentShamsiDate.getDay()), fontBhoma23Sign, (GraphicHandler) null, (GraphicHandler) null, true, true);
        if (solarEventImages[Main.currentShamsiDate.getMonth()] == null) {
            solarEventImages[Main.currentShamsiDate.getMonth()] = GraphicHandler.createFixedHeightBitmap(stringStorage.monthsName[Main.currentShamsiDate.getMonth() - 1], fontHoma13White, (GraphicHandler) null, fontBhoma13O, true, true);
        }
        MainScreenCanvas.MonthImageHolder = solarEventImages[Main.currentShamsiDate.getMonth()];
        if (Main.currentShamsiDate.getDayOfWeek() == 6) {
            MainScreenCanvas.is_holidays = true;
        } else {
            MainScreenCanvas.is_holidays = false;
        }
        int currentShamsiDay = Main.currentShamsiDate.getDay();
        int currentShamsiMonth = Main.currentShamsiDate.getMonth();
        if (!MainScreenCanvas.is_holidays) {
            int idx = 0;
            while (idx < Variable.Vacations[currentShamsiMonth].length) {
                if (currentShamsiDay == Variable.Vacations[currentShamsiMonth][idx]) {
                    idx = 100;
                    MainScreenCanvas.is_holidays = true;
                }
                idx++;
            }

        }
        solarEventIndex = 0;
        for (int i = 0; i < sEvents[currentShamsiMonth].length; i++) {
            if (sEvents[currentShamsiMonth][i] / 10 == currentShamsiDay) {
                solarEventIndex = sEvents[currentShamsiMonth][i] % 10;
                break;
            }
        }

        String[] shamsi_Events = stringStorage.getShamsiEvents(currentShamsiMonth, currentShamsiDay, solarEventIndex);
        for (int i3 = 0; i3 < solarEventIndex; i3++) {
            MainScreenCanvas.EventsBitmap[i3] = GraphicHandler.createFixedHeightBitmap(shamsi_Events[i3], fontBHoma10, (GraphicHandler) null, fontBHoma10O, true, true);
        }

        int currentGhamariMonth = Main.currentGhamariDate.getMonth();
        int CurrentGhamariDay = Main.currentGhamariDate.getDay();
        islamicEventIndex = 0;
        for (int i = 0; i < gEvents[currentGhamariMonth].length; i++) {
            if (gEvents[currentGhamariMonth][i] / 10 == CurrentGhamariDay) {
                islamicEventIndex = gEvents[currentGhamariMonth][i] % 10;
                break;
            }
        }
        String[] islamicEvents = stringStorage.getGhamariEvents(currentGhamariMonth, CurrentGhamariDay, islamicEventIndex);
        for (int i = 0; i < islamicEventIndex; i++) {
            if (solarEventIndex < 4) {
                MainScreenCanvas.EventsBitmap[solarEventIndex] = GraphicHandler.createFixedHeightBitmap(islamicEvents[i], fontBHoma10, (GraphicHandler) null, fontBHoma10O, true, true);
                solarEventIndex++;
            }
        }
        int currentMiladiMonth = Main.currentMiladiDate.getMonth();
        int currentMiladiDay = Main.currentMiladiDate.getDay();
        gregorianEventIndex = 0;
        for (int i = 0; i < mEvents[currentMiladiMonth].length; i++) {
            if (mEvents[currentMiladiMonth][i] / 10 == currentMiladiDay) {
                gregorianEventIndex = mEvents[currentMiladiMonth][i] % 10;
                break;
            }
        }
        String[] greogrianEvents = stringStorage.getMiladiEvents(currentMiladiMonth, currentMiladiDay, gregorianEventIndex);
        for (int i = 0; i < gregorianEventIndex; i++) {
            if (solarEventIndex < 4) {
                MainScreenCanvas.EventsBitmap[solarEventIndex] = GraphicHandler.createFixedHeightBitmap(greogrianEvents[i], fontBHoma10, (GraphicHandler) null, fontBHoma10O, true, true);
                solarEventIndex++;
            }
        }
        MainScreenCanvas.islamicDateImage = GraphicHandler.createFixedHeightBitmap(new StringBuffer().append(String.valueOf(Main.currentGhamariDate.getDay())).append(" ").append(stringStorage.gmonthsName[Main.currentGhamariDate.getMonth() - 1]).toString(), fontBHoma13, (GraphicHandler) null, fontBhoma13O, true, true);
        MainScreenCanvas.gregorianDateImage = GraphicHandler.createFixedHeightBitmap(new StringBuffer().append(stringStorage.mmonthsName[Main.currentMiladiDate.getMonth() - 1]).append(" ").append(String.valueOf(Main.currentMiladiDate.getDay())).toString(), (GraphicHandler) null, fontTahoma13_e, fontBhoma13O, false, false);
    }
}
