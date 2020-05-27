/* Factorization Machine */
/* TJ Liggett */

#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <string.h>
#include "factors.h"

		

int primeFactors(unsigned long long number, unsigned long long primes[])
{
	long double d, sqr;
       	unsigned long long root, fact;	
	d = (long double) number;
	sqr = sqrtl(d);
	root = (unsigned long long) sqr;
	for(fact = 2; fact <= root; ++fact)
	{
		if(number % fact == 0)
		{
			if(isPrime(fact) == 1 && isPrime(number/fact) == 1)
			{
				primes[0] = fact;
				primes[1] = number/fact;
				return 1;
			}
		}
	}
	return 0;
}

int isPrime(unsigned long long number)
{
	long double d, sqr;
       	unsigned long long root, fact;	
	d = (long double) number;
	sqr = sqrtl(d);
	root = (unsigned long long) sqr;
	for(fact = 2; fact <= root; ++fact)
	{
		if(number % fact == 0)
		{
			return 0;
		}
	}	
	return 1;
}
