package com.pro;

public class Time {
    int hour;
    int minute;
    Time(int hour,int minute){
        this.hour=hour;
        this.minute=minute;
    }

    public Time(String time){
        String[] cos = time.split(":");
        this.hour = Integer.parseInt(cos[0]);
        this.minute = Integer.parseInt(cos[1]);

    }
    @Override
    public String toString(){
        return hour +":"+minute;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Time time = (Time) o;

        if (hour != time.hour) return false;
        return minute == time.minute;
    }

    @Override
    public int hashCode() {
        int result = hour;
        result = 31 * result + minute;
        return result;
    }
}
