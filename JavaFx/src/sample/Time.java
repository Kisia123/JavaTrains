package sample;

import java.io.Serializable;

public class Time implements Comparable<Time>, Serializable {
    int hour;
    int minute;

    Time(int hour, int minute) {
        this.hour = hour;
        this.minute = minute;
    }



    public Time(String time) {
        String[] cos = time.split(":");
        this.hour = Integer.parseInt(cos[0]);
        this.minute = Integer.parseInt(cos[1]);
    }

    @Override
    public String toString() {
        StringBuilder bob = new StringBuilder(hour + ":");
        if (minute < 10) {
            bob.append(0);
        }
        bob.append(minute);
        return bob.toString();
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

    @Override
    public int compareTo(Time other) {
        if (this.equals(other)) {
            return 0;
        }
        if (this.hour > other.hour) {
            return 1;
        }
        if (this.hour < other.hour) {
            return -1;
        }
        if (this.minute > other.minute) {
            return 1;
        }
        return -1;
    }
}
