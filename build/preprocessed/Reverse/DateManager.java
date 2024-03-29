package Reverse;

import java.util.Calendar;

public class DateManager {

    byte[] data;

    public DateManager() {
    }

    public DateManager(int i, byte[] data) {
        this.data = data;
    }

    public static void prepare() {
        calculateAndUpdateDates();
        EventGraphicsManager.prepareEventGraphics();
    }

    public static void updateDates() {
        int totalDays = calculateTotalDays(Main.todayDate);
        Main.dayOFShamsiYear = totalDays - 79;

        Main.dayOFShamsiYear -= 1;
        calculateAndUpdateDates();
        EventGraphicsManager.prepareEventGraphics();
    }

    private static int calculateTotalDays(DateInfo customCalendar) {
        int totalDays = 0;
        for (int i = 0; i < customCalendar.getMonth(); i++) {
            totalDays += Variable.MILADI_MONTHS_DAYS[i];
        }
        return customCalendar.getDay() + totalDays;
    }

    private static void calculateAndUpdateDates() {

        Main.mShamsi.calculateNthDayOfYear(Main.dayOFShamsiYear + 1);

        int mShamsiDay = Main.mShamsi.getDay();
        int mShamsiMonth = Main.mShamsi.getMonth();
        int mShamsiYear = Main.mShamsi.getYear();
        Main.currentShamsiDate.setDay(mShamsiDay);
        Main.currentShamsiDate.setMonth(mShamsiMonth);

        Main.mConvertMiladi.PersianToGregorian(mShamsiYear, mShamsiMonth, mShamsiDay);
        int mMiladiDay = Main.mConvertMiladi.getDay();
        int mMiladiMonth = Main.mConvertMiladi.getMonth();
        int mMiladiYear = Main.mConvertMiladi.getYear();
        Main.currentMiladiDate.setDay(mMiladiDay);
        Main.currentMiladiDate.setMonth(mMiladiMonth);

        System.out.println("Date Manager " + mMiladiDay + " " + mMiladiMonth + " " + mMiladiYear);
        Hijri.calculateDate(mMiladiDay, mMiladiMonth, mMiladiYear, 1);

        System.out.println("Date Manager " + Hijri.myDay + " " + Hijri.myMonth + " " + Hijri.myYear);

        Main.currentGhamariDate.setDay(Hijri.myDay);
        Main.currentGhamariDate.setMonth(Hijri.myMonth+1);

        Main.currentShamsiDate.setDayOfWeek(Main.mShamsi.getDayOfWeek());

    }

    private static void SetTodayDates() {

        int currentYear = Main.todayDate.getYear();
        int currentMonth = Main.todayDate.getMonth();
        int currentDay = Main.todayDate.getDay();

        Main.mShamsi.GregorianToPersian(currentYear, currentMonth + 1, currentDay);
        int mShamsiDay = Main.mShamsi.getDay();
        int mShamsiMonth = Main.mShamsi.getMonth();
        int mShamsiYear = Main.mShamsi.getYear();
        Main.currentShamsiDate.setDay(mShamsiDay);
        Main.currentShamsiDate.setMonth(mShamsiMonth);

        Main.mConvertMiladi.PersianToGregorian(mShamsiYear, mShamsiMonth, mShamsiDay);
        int mMiladiDay = Main.mConvertMiladi.getDay();
        int mMiladiMonth = Main.mConvertMiladi.getMonth();
        int mMiladiYear = Main.mConvertMiladi.getYear();
        Main.currentMiladiDate.setDay(mMiladiDay);
        Main.currentMiladiDate.setMonth(mMiladiMonth);

        Hijri.calculateDate(mMiladiDay - 1, mMiladiMonth + 1, mMiladiYear, 1);
        Main.currentGhamariDate.setDay(Hijri.myDay);
        Main.currentGhamariDate.setMonth(Hijri.myMonth);

        Main.currentShamsiDate.setDayOfWeek(Main.mShamsi.getDayOfWeek());
    }

}
