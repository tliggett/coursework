//TJ Liggett
//Homework 10
//Class Time24 stores a time in the 24-hr format
//11_13_2018
//Time24.java

import java.util.Scanner;
import java.util.StringTokenizer;
import java.text.DecimalFormat;

/**
A data structure that stores integer values for hour (0..23) and minute (0..59) 
	to represent the time of day in a 24-hour clock 
*/
public class Time24 implements Comparable<Time24>
{
	private int hour;
	private int minute;
	//Post: Sets the hour value in the range 0 to 23 and the minute value in the range 0 to 59
 	private void normalizeTime()
 	{
    		int extraHours = minute / 60;
    		minute %= 60;
    		hour = (hour + extraHours) % 24;
 	}
 	
 	/**
	Initializes this Time24 object<p>
	<b>Post:</b><br>hour and minute of this Time24 object both initialized to 0<p>
 	 */
 	public Time24()
  	{
      		this(0,0);		//calls the 2-argument constructor of class Time24
  	}
 	
 	/**
	Initializes this Time24 object<p>
	<b>Pre:</b><br>h and m cannot be negative<p>
	<b>Post:</b><br>hour and minute of this Time24 object initialized to h and m 
	respectively.  This operation will normalize the time if necessary (e.g. 
	9:75 is stored as 10:15).<p>
	<b>Throw:</b><br>IllegalArgumentException if h or m is negative<p>
 	 */
 	public Time24(int h, int m)
 	{
 		setTime(h, m);
 		
	}
 	
 	/**
	Sets the hour and minute of this Time24 object to a particular time<p>
	<b>Pre:</b><br>h and m cannot be negative<p>
	<b>Post:</b><br>hour and minute of this Time24 object set to h and m 
	respectively.  This operation will normalize the time if necessary (e.g. 
	9:75 is stored as 10:15).<p>
	<b>Throw:</b><br>IllegalArgumentException if h or m is negative<p>
 	 */
 	public void setTime(int h, int m)
 	{
 		if (h < 0 || m < 0)
       			throw new IllegalArgumentException("Time24.setTime: argument"
       													+ " must not be negative");
    	this.hour = h;
   		this.minute = m;
   		normalizeTime();
 	}

 	/**
	Adds minutes to this Time24 object <p>
	<b>Pre:</b><br>m cannot be negative<p>
	<b>Post:</b><br>This Time24 object set to m minutes later.  This operation will 
	normalize the time if necessary (e.g. 9:75 is stored as 10:15).<p>
	<b>Throw:</b><br>IllegalArgumentException if m is negative<p>
 	 */
 	public void addTime(int m)
 	{
 		
 		if(m < 0)
 			throw new IllegalArgumentException("Time24.addTime: argument"
 													+ " must not be negative");
 		this.minute += m;
 		this.normalizeTime();
 	}

 	/**
	Measures the interval from this Time24 object to another time<p>
	<b>Return:</b><br>The interval from this Time24 object to t as a Time24 
 	 */
 	public Time24 interval(Time24 t)
 	{
 		int curTime = this.hour * 60 + this.minute;
 		int tTime = t.hour * 60 + t.minute;
 		if(tTime<curTime) tTime+=24*60;
 		return new Time24(0, tTime-curTime);
 	}
 	
 	/**
	Gets the hour value of this Time24 object<p>
	<b>Return:</b><br>The hour value of this Time24 object<p>
 	 */
 	public int getHour()
 	{ 
 		return hour; 
 	}

 	/**
	Gets the minute value of this Time24 object<p>
	<b>Return:</b><br>The minute value of this Time24 object<p>
 	 */

 	public int getMinute()
 	{ 
 		return minute; 
 	}

 	/**
	Compares this Time24 object to another object<p>
	<b>Return:</b><br>True if obj is an instanceof Time24 and this and obj have 
		equal hour and minute values, false otherwise<p>
 	 */
 	public boolean equals(Object obj)
 	{
 		if (obj instanceof Time24)
 		{
 					Time24 t =(Time24)obj;
 					return ((this.hour==t.hour) && (this.minute == t.minute));
 		}
 		return false;
 	}
 	
 	
 	
 	
 	/**
	Converts this Time24 object to a string<p>
	<b>Return:</b><br>This Time24 object as a String in the form "hh:mm"<p>
 	 */
 	public String toString()
 	{
 		DecimalFormat d = new DecimalFormat("00");
 		return this.hour + ":" + d.format(this.minute);
 	}
 	
 	/**
	Convert a String to a Time24<p>
	<b>Pre:</b><br>s must be in the form "hh:mm" where hh and mm are positive
									 integers <p>
	<b>Return:</b><br>A Time24 object that corresponds to s<p>
 	 */
 	public static Time24 parseTime(String s)				
 	{
 		StringTokenizer tokenizer = new StringTokenizer(s, ":");
 		int h = Integer.parseInt(tokenizer.nextToken());
 		int m = Integer.parseInt(tokenizer.nextToken());
 		return new Time24(h,m);
	}
 	
 	/**
 	Read from f the hour and minute for this Time24 object<p>
	<b>Pre:</b><br>f is ready to be read, and the next line  contains hh:mm  
			where hh:mm gives a valid 24-hour time<p>
	<b>Post:</b><br>The entire line hh:mm read in from f. The hour and minute
			 of this Time24 object set to hh and mm respectively.  The operation 
			will normalize the time if necessary (e.g. 9:75 is stored as 10:15).<p>
 	 */
 	public void readTime(Scanner f)
 	{
 		Time24 t = Time24.parseTime(f.nextLine());
 		this.setTime(t.hour, t.minute);
 	}
 	
 	/**
 	Compares the time of this Time24 to another Time24 <p>
 	<b>Return:</b><br>A positive int if this Time24 > t; a negative int if this Time24 < t; 0 
 	if this Time24 == t 
 	*/	
 	public int compareTo(Time24 t)
 	   	{
 			int currTime = hour * 60 + minute;
 	      		int tTime = t.hour * 60 + t.minute;
 			if (currTime > tTime) return 1;
 			else if (currTime == tTime) return 0;
 			else return -1;
 	   }
}
