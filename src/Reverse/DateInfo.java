package Reverse;

public final class DateInfo {

   
    private int day;

   
    private int month;

   
    private int year;

 
    private int day_of_week;

    public DateInfo() {
    }

    public DateInfo(int day , int month, int year, int dayofweek) {
        this.day = day;
        this.month = month;
        this.year = year;
        this.day_of_week = dayofweek;
    }

    
    public final int getDay() {
        return this.day;
    }

   
    public final void setDay(int i) {
        this.day = i;
    }

    public final int getDayOfWeek() {
        return this.day_of_week;
    }

    public final void setDayOfWeek(int i) {
        this.day_of_week = i;
    }

    public final int getMonth() {
        return this.month;
    }

    public final void setMonth(int i) {
        this.month = i;
    }

      public void setYear(int year) {
        this.year = year;
    }
    public final int getYear() {
        return this.year;
    }
}
