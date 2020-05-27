package homework4;

import java.text.DecimalFormat;
import java.util.Scanner;

public class ParkingGarage {
	//Desc:  	The program computes the cost of parking a car in a public garage at the rate $5.00/hour.  The client will always be charged for whole hours.  For example, if a car parked for 2 hours and 1 minute, the client will be charged for 3 hours.
	//Input: 	User inputs the entry time and exit time in 24-hr clock format (hh:mm) 
	//Output:	The enter and exit times, the length of time the car is parked and the total charges.
	public static void main(String[] args)
	{
		Scanner input = new Scanner(System.in);
		Time24 enterTime = new Time24();
		Time24 exitTime = new Time24();
		DecimalFormat d = new DecimalFormat("0.00");
		System.out.println("Time enters the garage in 24-hr clock format <hh:mm>");
		enterTime.readTime(input);
		System.out.println("Time exits the garage in 24-hr clock format <hh:mm>");
		exitTime.readTime(input);
		System.out.println("\tCar enters at: " + enterTime);
		System.out.println("\tCar exits at: " + exitTime);
		Time24 parkingTime = enterTime.interval(exitTime);
		System.out.println("\tParking time: " + parkingTime);
		System.out.println("\tCharges: $" + d.format(charge(parkingTime)));
	}
	
	
	//Desc:	Computes the cost of parking car
	//Return: Cost of parking the car
	public static double charge(Time24 t)
	{
		double d = 5.0 * t.getHour();
		if(t.getMinute() > 0) d += 5.0;
		return d;
	}
}
