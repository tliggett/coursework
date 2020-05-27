/* Factorization Machine */
/* TJ Liggett */

#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <string.h>

int printFactors(long long number, char primes[], int *hasPrimes);
int isPrime(long long number);

int main()
{
	long long number;
	char primes[100];
	int hasPrimes;
	hasPrimes = 0;
	/* Prompt user for integer (long) */
	printf("Enter a positive integer: ");
	scanf("%ld", &number);
	printf("You entered: %lld\n", number);
	if(isPrime(number) == 1)
	{
		
		printf("%lld = 1 * %lld\n%lld is prime!\n", number, number, number);
	}
	else
	{	
		/* Find pairs of integers */
		printFactors(number, primes, &hasPrimes);
		/* Find pair of primes */
		if(hasPrimes == 0)
		{	
			printf("Among these, none is pair of primes.\n");
		}
		else
		{
			printf("Among these, only %s is pair of primes\n", primes);
		}
	}
}


int printFactors(long long number, char primes[], int *hasPrimes)
{
	long double d, sqr;
       	long long root, fact;	
	d = (long double) number;
	sqr = sqrtl(d);
	root = (long long) sqr;
	printf("%lld = 1 * %lld\n", number, number);
	for(fact = 2; fact <= root; ++fact)
	{
		if(number % fact == 0)
		{
			char factors[100];
			sprintf(factors, "%lld = %lld * %lld", number, fact, number/fact);
			printf("%s \n", factors);
			if(*hasPrimes == 0)
			{
				if(isPrime(fact) == 1 && isPrime(number/fact) == 1)
				{ 
					strcpy(primes, factors);
					*hasPrimes = 1;
				}
			}
		}
	}
}

int isPrime(long long number)
{
	long double d, sqr;
       	long long root, fact;	
	d = (long double) number;
	sqr = sqrtl(d);
	root = (long long) sqr;
	for(fact = 2; fact <= root; ++fact)
	{
		if(number % fact == 0)
		{
			return 0;
		}
	}	
	return 1;
}
