//TJ Liggett
//9_22_2020
//Homework 4
//BottomUpFact.java

/*
 *
 *       Class BottomUpFact calculates the factorial of an integer with both 
 *       a bottom-up method and recursive 
 *
 */

import java.util.*;
import java.io.*;
import java.math.BigInteger;

public class BottomUpFact
{
	public static void main(String[] args)
    	{
		
		StopWatch timer = new StopWatch();		// for timing the calculations
		Scanner input = new Scanner(System.in);		
		System.out.printf("Enter an integer: ");
		BigInteger n = new BigInteger(input.nextLine());	
		
		if(n.longValue() > 20)
		{	
			/* Calculate the factorial using bottom-up*/
			timer.start();
			BigInteger buf = bottomUpFact(n);
			System.out.printf("%d! bottomUpFact: %d\n", n, buf);
			System.out.printf("bottomUpFact finished in %.4f seconds.\n", timer.stop());
			timer.reset();

			/* Calculate the factorial using recursion */
			timer.start();
			BigInteger fact = recursiveFact(n);
			System.out.printf("%d! recursiveFact: %d\n", n, fact);
			System.out.printf("recursiveFact finished in %.4f seconds.\n\n", timer.stop());
		}
		else
		{
			/* Calculate the factorial using bottom-up*/
			timer.start();
			long buf = bottomUpFact(n.longValue());
			System.out.printf("%d! bottomUpFact: %d\n", n, buf);
			System.out.printf("bottomUpFact finished in %.4f seconds.\n", timer.stop());
			timer.reset();

			/* Calculate the factorial using recursion */
			timer.start();
			long fact = recursiveFact(n.longValue());
			System.out.printf("%d! recursiveFact: %d\n", n, fact);
			System.out.printf("recursiveFact finished in %.4f seconds.\n\n", timer.stop());
		}
		
	}

	/**
	 * calculates a factorial of a long integer bottom-up
	 *
	 * @param n a long integer
	 * @return the factorial of the long
	 *
	 * note: factorial of a number greater than 20 does not work!
	 *
	 */
	public static long bottomUpFact(long n)
	{
		long fact = 1;
		for(long i = 1; i <= n; ++i) fact *= i;
		return fact;
	}

	/**
	 * calculates a factorial of a long integer recursively
	 *
	 * @param n a long integer
	 * @return the factorial of the long
	 *
	 * note: factorial of a number greater than 20 does not work!
	 *
	 */
	public static long recursiveFact(long n)
	{
		if(n == 1) return n;
		else return n * recursiveFact(n - 1);
	}

	/**
	 * calculates a factorial of a BigInteger bottom-up
	 *
	 * @param n a BigInteger
	 * @return the factorial of the BigInteger
	 *
	 * note: factorial of a number greater than 20 does not work!
	 *
	 */
	public static BigInteger bottomUpFact(BigInteger n)
	{
		BigInteger fact = new BigInteger("1");
		for(BigInteger i = new BigInteger("1"); i.compareTo(n) <= 0; i = i.add(BigInteger.ONE))
		{
			fact = fact.multiply(i);
		}
		return fact;
	}

	/**
	 * calculates a factorial of a long integer recursively
	 *
	 * @param n a BigInteger
	 * @return the factorial of the BigInteger
	 *
	 * note: factorial of a number greater than 20 does not work!
	 *
	 */
    	public static BigInteger recursiveFact(BigInteger n)
    	{
		if(n.equals(BigInteger.ONE)) return n;
		else return n.multiply(recursiveFact(n.subtract(BigInteger.ONE)));

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
