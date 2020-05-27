package homework3;//TJ Liggett
//Homework 3
//2_28_2019
//Time24.java


//A data structure that stores integer values for hour (0..23) and minute (0..59) to represent the
//time of day in a 24-hour clock

import java.util.StringTokenizer;
import java.text.DecimalFormat;
import java.io.Serializable;

public class Time24 implements Comparable<Time24>, Serializable, Cloneable {
    private int hour;
    private int minute;

    //Post: Sets the hour value in the range 0 to 23 and the minute value in the range 0 to 59
    private void normalizeTime() {
        int extraHours = minute / 60;
        minute %= 60;
        hour = (hour + extraHours) % 24;
    }

    //Post:	hour and minute of this Time24 object both initialized to 0
    public Time24() {
        this(0, 0);        //calls the 2-argument constructor of class Time24
    }

    //Pre:	h and m cannot be negative
    //Post:	hour and minute of this Time24 object initialized to h and m respectively.  This operation
    //	will normalize the time if necessary (e.g. 9:75 is stored as 10:15).
    //Exceptn:Throws InvalidArgumentException if h or m is negative
    public Time24(int h, int m) {
        setTime(h, m);
    }

    //Pre:	h and m cannot be negative
    //Post:	hour and minute of this Time24 object set to h and m respectively.  This operation
    //	will normalize the time if necessary (e.g. 9:75 is stored as 10:15).
    //Exceptn:Throws InvalidArgumentException if h or m is negative
    public void setTime(int h, int m) {
        if (h < 0 || m < 0)
            throw new IllegalArgumentException("Time24.setTime: argument"
                    + " must not be negative");
        this.hour = h;
        this.minute = m;
        normalizeTime();
    }

    //Pre:	m cannot be negative
    //Post:	This Time24 object set to m minutes later.  This operation will normalize the time if
    //	necessary (e.g. 9:75 is stored as 10:15).
    //Exceptn:Throws InvalidArgumentException if m is negative
    public void addTime(int m) {
        if (m < 0)
            throw new IllegalArgumentException("Time24.addTime: argument"
                    + " must not be negative");
        minute += m;
        normalizeTime();
    }

    //Return:	The interval from this Time24 object to t as a Time24
    public Time24 interval(Time24 t) {
        int currTime = hour * 60 + minute;
        int tTime = t.hour * 60 + t.minute;
        if (tTime < currTime) tTime += 24 * 60;
        return new Time24(0, tTime - currTime);
    }

    //Return:	The hour value of this Time24 object
    public int getHour() {
        return hour;
    }

    //Return:	The minute value of this Time24 object
    public int getMinute() {
        return minute;
    }

    //Return:	This Time24 object as a String in the form "hh:mm"
    public String toString() {
        DecimalFormat f = new DecimalFormat("00");
        return new String(hour + ":" + f.format(minute));
    }

    //Return:	True if this Time24 has the same time as obj; false otherwise
    public boolean equals(Object obj) {
        if (obj instanceof Time24) {
            Time24 t = (Time24) obj;
            int currTime = hour * 60 + minute;
            int tTime = t.hour * 60 + t.minute;
            return tTime == currTime;
        }
        return false;
    }

    //Return:	A positive int if this Time24 > t; a negative int if this Time24 < t; 0 if this
    //          Time24 == t
    public int compareTo(Time24 t) {
        int currTime = hour * 60 + minute;
        int tTime = t.hour * 60 + t.minute;
        if (currTime > tTime) return 1;
        else if (currTime == tTime) return 0;
        else return -1;
    }

    //Pre:	s must be in the form "hh:mm" where hh and mm are positive integers
    //Return:	A Time24 object that corresponds to s
    public static Time24 parseTime(String s) {
        StringTokenizer t = new StringTokenizer(s, ":");
        int h, m;
        h = Integer.parseInt(t.nextToken());
        m = Integer.parseInt(t.nextToken());
        return new Time24(h, m);
    }

    public Object clone() {
        Time24 copy = null;            //line 26
        try {
            copy = (Time24) super.clone();    //line 29
        } catch (CloneNotSupportedException e) {
        }
        return copy;                //line34
    }
}
