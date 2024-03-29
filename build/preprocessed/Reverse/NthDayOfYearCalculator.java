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
public class NthDayOfYearCalculator {

    public static int[] calculate(int n, int year) {
        int januaryFirstJulianDay = calculateJulianDay(1, 1, year);
        int nthDayJulianDay = januaryFirstJulianDay + n - 1;
        int[] date = calculateDate(nthDayJulianDay);
        return date;
    }

    private static int calculateJulianDay(int day, int month, int year) {
        int a = (14 - month) / 12;
        int y = year + 4800 - a;
        int m = month + 12 * a - 3;
        return day + (153 * m + 2) / 5 + 365 * y + y / 4 - 32083;
    }

    private static int[] calculateDate(int julianDay) {
        int a = julianDay + 32044;
        int b = (4 * a + 3) / 146097;
        int c = a - (b * 146097) / 4;
        int d = (4 * c + 3) / 1461;
        int e = c - (1461 * d) / 4;
        int m = (5 * e + 2) / 153;
        int day = e - (153 * m + 2) / 5 + 1;
        int month = m + 3 - 12 * (m / 10);
        int year = b * 100 + d - 4800 + (m / 10);
        return new int[]{day, month, year};
    }

}
