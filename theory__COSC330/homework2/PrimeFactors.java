// PrimeFactors.java
// This program calculates all the pairs of factors of a long
// TJ Liggett
// Izzy Sommers
// COSC 330

import java.util.Scanner;
import java.lang.Math;
import java.math.BigInteger;
import java.math.BigDecimal;


public class PrimeFactors
{
	public static void main(String [] args)
	{
		long n = 0;
		Scanner input = new Scanner(System.in);
		System.out.println("Enter a positive integer: ");
		n = input.nextLong();
		/* Checks if n is prime */
		if(isPrime(n))
		{
			System.out.printf("%d is a prime number, with only %d = %d * %d as prime factors.\n", n, n, 1, n);
		}
		/* Computes factors of n */
		factors(n);
	}
		
		
	public static void factors(long n)
	{
		// One cannot convert a long to a double with risking loss of digits / precision
		BigInteger i = BigInteger.valueOf(n);
		long root = i.sqrt().longValue();
		long[] primePair = {-1, -1};
		boolean hasPrimes = false;
		long x = 0;

		/* 1 and n are a guaranteed pair of factors */
		System.out.printf("%d = %d * %d\n", n, 1, n);

		/* Checks each number less than or equal to square root to see if it divides n */
		for(long pass= 2; pass<= root; ++pass)
		{
			if((n%pass) ==0)
			{
				System.out.printf("%d = %d * %d\n", n, pass, n/pass);
				/* If we have not found prime factors, we will check for them */
				if(!hasPrimes)
				{
					/* Check if pass, n/pass are prime */
					if(isPrime(pass) && isPrime(n/pass))
					{
						/* Store them if they are prime pair */
						primePair[0] = pass;
						primePair[1] = n/pass;
						hasPrimes = true;
					}
				}
				
			}
		}

		if(hasPrimes)
		{
			System.out.printf("Among these, only %d = %d * %d is pair of primes.\n", n, primePair[0], primePair[1]);
		}
		else{
			System.out.println("Among these, none is pair of primes.");
		}
	}			
	
	
	public static boolean isPrime(long n)
	{
		// One cannot convert a long to a double with risking loss of digits / precision
		BigInteger bigInteger = BigInteger.valueOf(n);
		long root = bigInteger.sqrt().longValue();

		/* Goes through each number from 2 to the square root to check for a factor of n */
		for(long i = 2; i <= root; ++i)
		{
			if(n%i == 0)
			{
				return false;	/* Returns false if i is a factor of n */
			}
		}
		return true;			/* Returns true if n is prime */
		
	}				
		
}