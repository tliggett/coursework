//TJ Liggett
//9_22_2020
//Homework 4
//BottomUpDynFib.java

/*
 *
 *       Class BottomUpDynFib calculates the fibonacci number of an integer with 
 *       bottom-up, top-down, and recursive methods and times them
 *
 */

import java.util.*;
import java.io.*;
import java.math.BigInteger;

public class BottomUpDynFib
{
	public static void main(String[] args)
    	{
		
		StopWatch timer = new StopWatch();		// for timing the calculations
		Scanner input = new Scanner(System.in);		
		System.out.printf("Enter an integer: ");
		int n = input.nextInt();
		input.nextLine();

		/* Fibonacci Bottom-up Dynamic */
		System.out.printf("Calculating %d fibonacci term bottom-up...\n", n);
		timer.start();
		long bFib = bottomUpFib(n);
		double bTime = timer.stop();
		System.out.printf("%dth Fibonacci term Bottom Up Dynamic Programming: %d\n", n, bFib);
		System.out.printf("Bottom-Up Dynamic Programming found in %.3f seconds.\n\n", bTime);
		timer.reset();

		/* Fibonacci Top-down Dynamic */
		System.out.printf("Calculating %d fibonacci term top-down...\n", n);
		timer.start();
		long tFib = topDownFib(n);
		double tTime = timer.stop();
		System.out.printf("%dth Fibonacci Term Top Down Dynamic: %d\n", n, tFib);
		System.out.printf("Top-down Dynamic Programming found in %.3f seconds.\n\n", tTime);
		timer.reset();

		/* Fibonacci Recursive */	
		System.out.printf("Calculating %d fibonacci term recursive...\n", n);
		timer.start();
		long rFib = rFib(n);
		double rTime = timer.stop();
		System.out.printf("%dth Fibonacci Term Recursive: %d\n Recursive found in %.3f seconds.\n\n", n, rFib, rTime);
		timer.reset();
		

		

	}

	/**
	 * Finds the nth fibonacci number using bottom-up dynamic programming
	 *
	 * @param n the fibonacci number to find
	 * @return  the nth fibonacci number
	 *
	 */
	public static long bottomUpFib(int n)
	{
		long[] fibs = new long[n];
		for(int i = 0; i < n; ++i)
		{
			if(i <= 1) fibs[i] = i;
			else fibs[i] = fibs[i-1] + fibs[i-2];
		}
		return fibs[n-1] + fibs[n-2];

	}

	/**
	 * Finds the nth fibonacci number using top-down dynamic programming
	 *
	 * @param n the fibonacci number to find
	 * @return   the nth fibonacci number
	 *
	 */
	public static long topDownFib(int n)
	{
		long[] fibs = new long[n+1];
		for(int i = 0; i <= n; ++i) fibs[i] = -1L;
		return dynFib(n, fibs);

	}

	/**
	 * Finds the nth fibonacci number using top-down dynamic programming
	 *
	 * @param n the fibonacci number to find
	 * @param fibs an array of previously found fibs with length greater than n
	 * @return   the nth fibonacci number
	 *
	 */
	public static long dynFib(int n, long[] fibs)
	{
		long answer;
		if(fibs[n]>=0) return fibs[n];
		
		if (n<=1) answer = n;
		else answer = dynFib(n-1, fibs) + dynFib(n-2, fibs);
		
		fibs[n] = answer;
		return answer; 

	}

	/**
	 * Finds the nth fibonacci number recursively
	 *
	 * @param n the fibonacci number to find
	 * @return   the nth fibonacci number
	 *
	 */
	public static long rFib(int n)
	{
		if(n <= 2) return 1;
		else return rFib(n-1) + rFib(n-2);
	}


}

/**
 Class StopWatch supports objects representing a stop watch for measuring the time required to
 execute a process. Time is measured in seconds.
 */
class StopWatch
{
    private long startTime, stopTime;
    /**
     Initializes this StopWatch object<p>
     <b>Post:</b><br> startTime and stopTime of this StopWatch object initialized to 0<p>
     */
    public StopWatch()
    {
        startTime = stopTime = 0;
    }
    /**
     Starts this StopWatch <p>
     <b>Post:</b><br> startTime of this StopWatch set to the current system clock time in
     nanosecond <p>
     */
    public void start()
    {
        startTime = System.nanoTime();
    }
    /**
     Stops this StopWatch <p>
     <b>Post:</b><br> stopTime of this StopWatch set to the current system clock time in
     nanosecond <p>
     <b>Return:</b><br>Interval of time from start to stop measured in seconds.
     */
    public double stop()
    {
        stopTime = System.nanoTime();
        return (stopTime - startTime)/1000000000.0;
    }
    public void reset()
    {
	startTime = stopTime = 0;
    }
}
