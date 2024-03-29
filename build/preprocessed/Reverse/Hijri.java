/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Reverse;

/**
 *
 * @author Rz
 */
public class Hijri {
    public static final double MINAGE = 13.5d;
    public static final int NMONTHS = 16861;
    public static final double RPD = 0.017453292519943295d;
    public static final double SUNSET = 19.5d;
    public static final double TIMDIF = 6.0d;
    public static final double TIMZ = 3.0d;
    public static String[] dow = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
    public static String[] hmname = {"MuHarram", "Safar", "Raby` al-awal", "Raby` al-THaany", "Jumaada al-awal", "Jumaada al-THaany", "Rajab", "SHa`baan", "RamaDHaan", "SHawwal", "Thw al-Qi`dah", "Thw al-Hijjah"};
    public static String[] mname = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
    static int myDay;
    static int myDayName;
    static int myMonth;
    static int myYear;
    public static String progname;
    static double rjd;

    public static double julianday(int year, int month, int day, double time) {
        long y;
        long m;
        if (year < 0) {
            year++;
        }
        if (month > 2) {
            y = (long) year;
            m = (long) month;
        } else {
            y = (long) (year - 1);
            m = (long) (month + 12);
        }
        double jul = ((double) y) * 365.25d;
        if (y < 1) {
            jul -= 0.75d;
        }
        double jul2 = ((double) (((long) jul) + ((long) (30.6001d * ((double) (1 + m)))) + ((long) day))) + time + 1720994.5d;
        if (((double) year) + (((double) month) * 0.01d) + ((((double) day) + time) * 1.0E-4d) < 1582.1015d) {
            return jul2;
        }
        double ja = 0.01d * ((double) y);
        return ((2.0d + jul2) - ja) + ((double) ((long) (0.25d * ja)));
    }

    public static void calculateDate(int d1, int m1, int y1, int GH) {
        new Sdate();
        progname = "hdate";
        int argc = 4 - 1;
        if (GH == 2) {
            Sdate sd = gdate(y1, m1, d1);
            myDayName = sd.dw;
            myDay = sd.day;
            myMonth = sd.mon - 1;
            myYear = sd.year;
            return;
        }
        Sdate sd2 = hdate(y1, m1, d1);
        myDayName = sd2.dw;
        myDay = sd2.day;
        myMonth = sd2.mon - 1;
        if (sd2.year > 0) {
            myYear = sd2.year;
        } else {
            myYear = -sd2.year;
        }
    }

    public static Sdate caldate(double julday) {
        long a;
        Sdate sd = new Sdate();
        double julday2 = julday + 0.5d;
        long z = (long) julday2;
        double f = julday2 - ((double) z);
        if (z < 2299161) {
            a = z;
        } else {
            long alpha = (long) ((((double) z) - 1867216.25d) / 36524.25d);
            a = ((1 + z) + alpha) - (alpha / 4);
        }
        long b = a + 1524;
        long c = (long) ((((double) b) - 122.1d) / 365.25d);
        long d = (long) (365.25d * ((double) c));
        long e = (long) (((double) (b - d)) / 30.6001d);
        double f2 = f + ((double) ((b - d) - ((long) (30.6001d * ((double) e)))));
        sd.day = (int) f2;
        sd.time = f2 - ((double) sd.day);
        sd.mon = e > 13 ? ((int) e) - 13 : ((int) e) - 1;
        sd.year = sd.mon > 2 ? ((int) c) - 4716 : ((int) c) - 4715;
        sd.dw = ((int) ((julday2 - sd.time) + 1.1d)) % 7;
        if (sd.year <= 0) {
            sd.year--;
        }
        return sd;
    }

    public static Sdate gdate(int y, int m, int d) {
        new Sdate();
        if (y < 0) {
            y++;
        }
        Sdate sd = caldate(visible(1048 + ((long) (((y * 12) + m) - 16861))) + ((double) d));
        sd.nmtime = rjd;
        return sd;
    }

    public static Sdate hdate(int y, int m, int d) {
        double k;
        double mjd;
        Sdate h = new Sdate();
        double jd = julianday(y, m, d, 0.0d);
        double k2 = 0.6d + ((((((double) y) + (((double) ((int) (((double) m) - 0.5d))) / 12.0d)) + (((double) d) / 365.0d)) - 1900.0d) * 12.3685d);
        while (true) {
            k = k2 - 1.0d;
            mjd = visible((long) k2);
            if (mjd <= jd) {
                break;
            }
            k2 = k;
        }
        long hm = ((long) (k + 1.0d)) - 1048;
        h.year = (int) (1405 + (hm / 12));
        h.mon = (int) ((hm % 12) + 1);
        if (hm != 0 && h.mon <= 0) {
            h.mon += 12;
            h.year--;
        }
        if (h.year <= 0) {
            h.year--;
        }
        h.day = (int) ((jd - mjd) + 1.0d);
        h.time = 0.5d;
        h.dw = ((int) ((long) (1.5d + jd))) % 7;
        return h;
    }

    public void usage(String msg) {
        if (msg != null) {
            System.out.println(new StringBuffer().append(progname).append(":").append(msg).toString());
        }
        System.out.println(new StringBuffer().append("Usage: ").append(progname).append(" [ [day month year] | [-h hday hmonth hyear] ]").toString());
        System.exit(1);
    }

    public static double tmoonphase(long n, int nph) {
        double xtra = 0.0d;
        double k = ((double) n) + (((double) nph) / 4.0d);
        double t = k / 1236.85d;
        double t2 = t * t;
        double t3 = t2 * t;
        double jd = (((2415020.75933d + (29.53058868d * k)) - (1.178E-4d * t2)) - (1.55E-7d * t3)) + (3.3E-4d * Math.sin(0.017453292519943295d * ((166.56d + (132.87d * t)) - (0.009173d * t2))));
        double sa = 0.017453292519943295d * (((359.2242d + (29.10535608d * k)) - (3.33E-5d * t2)) - (3.47E-6d * t3));
        double ma = 0.017453292519943295d * (306.0253d + (385.81691806d * k) + (0.0107306d * t2) + (1.236E-5d * t3));
        double tf = 0.03490658503988659d * (((21.2964d + (390.67050646d * k)) - (0.0016528d * t2)) - (2.39E-6d * t3));
        if (nph == 0 || nph == 2) {
            xtra = ((((((((((((0.1734d - (3.93E-4d * t)) * Math.sin(sa)) + (0.0021d * Math.sin(2.0d * sa))) - (0.4068d * Math.sin(ma))) + (0.0161d * Math.sin(2.0d * ma))) - (4.0E-4d * Math.sin(3.0d * ma))) + (0.0104d * Math.sin(tf))) - (0.0051d * Math.sin(sa + ma))) - (0.0074d * Math.sin(sa - ma))) + (4.0E-4d * Math.sin(tf + sa))) - (4.0E-4d * Math.sin(tf - sa))) - (6.0E-4d * Math.sin(tf + ma))) + (0.001d * Math.sin(tf - ma)) + (5.0E-4d * Math.sin((2.0d * ma) + sa));
        } else if (nph == 1 || nph == 3) {
            double xtra2 = (((((((((((((((0.1721d - (4.0E-4d * t)) * Math.sin(sa)) + (0.0021d * Math.sin(2.0d * sa))) - (0.628d * Math.sin(ma))) + (0.0089d * Math.sin(2.0d * ma))) - (4.0E-4d * Math.sin(3.0d * ma))) + (0.0079d * Math.sin(tf))) - (0.0119d * Math.sin(sa + ma))) - (0.0047d * Math.sin(sa - ma))) + (3.0E-4d * Math.sin(tf + sa))) - (4.0E-4d * Math.sin(tf - sa))) - (6.0E-4d * Math.sin(tf + ma))) + (0.0021d * Math.sin(tf - ma))) + (3.0E-4d * Math.sin((2.0d * ma) + sa))) + (4.0E-4d * Math.sin(sa - (2.0d * ma)))) - (3.0E-4d * Math.sin((2.0d * sa) + ma));
            if (nph == 1) {
                xtra = ((0.0028d + xtra2) - (4.0E-4d * Math.cos(sa))) + (3.0E-4d * Math.cos(ma));
            } else {
                xtra = ((xtra2 - 0.0028d) + (4.0E-4d * Math.cos(sa))) - (3.0E-4d * Math.cos(ma));
            }
        } else {
            System.err.println("tmoonphase: illegal phase number\n");
            System.exit(1);
        }
        return jd + (xtra - (((0.41d + (1.2053d * t)) + (0.4992d * t2)) / 1440.0d));
    }

    public static double visible(long n) {
        double jd = tmoonphase(n, 0);
        rjd = jd;
        float tf = (float) (jd - ((double) ((long) jd)));
        if (((double) tf) <= 0.5d) {
            return jd + 1.0d;
        }
        if (((double) ((float) (((((double) tf) - 0.5d) * 24.0d) + 3.0d))) > 6.0d) {
            return jd + 1.0d;
        }
        return jd;
    }

    public static int ndays(int m, int y) {
        short s = new short[]{0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31}[m];
        if (m != 2 || y % 4 != 0) {
            return s;
        }
        if (y < 1582 || y % 100 != 0 || y % 400 == 0) {
            return s + 1;
        }
        return s;
    }
}