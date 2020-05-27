/* Using the public keys n = 91 and e = 5, encrypt the binary bit string 101.
 * Give your answer in the form of a binary bit string */

#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <string.h>

#include "factors.h"

/* ed = k(p-1)(q-1) + 1 */

// int generateEncryption(long ekeys[]) /* Generates an encryption system 
unsigned long long decrypt(unsigned long long encrypted, unsigned long long n, unsigned long long d);
unsigned long long encrypt(unsigned long long decrypted, unsigned long long n, unsigned long long e);
unsigned long long power(unsigned long long base, unsigned long long exponent);
unsigned long long powermod(unsigned long long base, unsigned long long exponent, unsigned long long n);
unsigned long long decryptHard(unsigned long long encrypted, unsigned long long n, unsigned long long e);
int encryptString(char str[], int length, unsigned long long n, unsigned long long e);
unsigned long long longBits(unsigned long long n);


int main(int argc, char *argv[])
{
	unsigned long long ekeys[3], number;
	char s[100];
	int length;
	ekeys[0] = 91;	/* n */
	ekeys[1] = 5;   /* e */
	ekeys[2] = 29;  /* d */
	
	/* TODO:  If the input message m > n, we must encrypt in parts*/
	/* TODO: Generate encrpytion */
	if(argc < 2) return 1;
		
	strcpy(s, argv[1]);
	length = strlen(s);
	printf("%s of length %d\n", s, length);
	
	encryptString(s, length, ekeys[0], ekeys[1]);

	printf("ENCRYPTED STRING: %s\n", s);

	/*
	number = atoi(s);
	printf("Encrypting: %llu...\n", number);
	number = encrypt(number, ekeys[0], ekeys[1]);
	printf("Encrypted: %llu\n", number);

	printf("Decrypting HARD: %llu ...\n", number);
	number = decryptHard(number, ekeys[0], ekeys[1]);
	printf("Decrypted: %llu \n", number);
	*/

}

unsigned long long decryptHard(unsigned long long encrypted, unsigned long long n, unsigned long long e)
{
	unsigned long long decrypted, primefactors[2], prod, k, dkey;
	int isPrime;
	
	decrypted = 0;
	isPrime = primeFactors(n, primefactors);
	
	if(isPrime > 0)
	{
		prod = (primefactors[0] - 1) * (primefactors[1] - 1);
		for(k = 1; k < n; ++k)
		{
			if((k*prod+1) % e == 0)
			{
				dkey = ((k*prod+1)/e);
				printf("Decrypt Key: %llu\n", dkey);
				break;
			}
		}
	}
	else
	{
		printf("Decryption failed! No primes found...\n");
		return -1;
	}
	//printf("+++%llu\n", encrypted);
	decrypted = powermod(encrypted, dkey, n);
	//printf("---%llu\n", decrypted);
	return decrypted;
}

unsigned long long decryptLong(unsigned long long encrypted, unsigned long long n, unsigned long long d)
{
	unsigned long long decrypted;
	decrypted = 0;
	decrypted = powermod(encrypted, d, n);
	//printf("%d\n", decrypted);
	return decrypted;
}

int encryptString(char str[], int length, unsigned long long n, unsigned long long e)
{
	int bitlength, nbits, offset;
	unsigned long long *loc, zero, one, mod, save;
	int test, test1, test2;
	char *word, word2, *word3;	
	zero = 0;
	one = 18446744073709551615;	
	/* Pointing to start of string to be encrypted */
	loc = str;
	test = loc;
	test1 = *loc;
	word = loc;
	word2 = str[0]; 
	word3 = str;
	printf("Length of str: %d\n", strlen(str));
	bitlength = strlen(str) * 8;
	nbits = longBits(n);
	printf("Bit size of str: %d\n", bitlength);
	printf("Bit size of n: %lld\n", nbits);
		
	printf("%llu	%llu	%c	%c	%c\n", zero, one, *word, word2, *word3);
	
	mod = *loc;	
	save = *loc;
	mod = mod >> (40);
	save = save << 8;
	save = save >> 8;
	printf("%llu\n", mod);	
	/*	
	for(offset = 0; offset<bitlength; offset += nbits)
	{*/
		/* Save ull from pointer */
		/* Save non-modified contents */
		/* extract nbits to ull */
		/* encrypt nbits */
		/* move nbits back over */
		/* or with saved contents */
		/* put ull back into pointer */
	//}
	
	/* Set long long pointer */
	
	/*
	for(int i = 0; i < length; ++i)
	{
		printf("%d\n", str[i]);
	}
	*/
}


unsigned long long longBits(unsigned long long n)
{
	unsigned long long bits;
	bits = 0;
	while(n > 0)
	{
		n /= 2;
		++bits;
	}
	return bits;
}


unsigned long long encryptLong(unsigned long long decrypted, unsigned long long n, unsigned long long e)
{
	unsigned long long encrypted;
	encrypted = 0;
	encrypted = powermod(decrypted, e, n);
	//printf("%d\n", encrypted);
	return encrypted;
}	


unsigned long long powermod(unsigned long long base, unsigned long long exponent, unsigned long long n)
{
	unsigned long long result, expo;
	result = 1;
	//printf("EXPO: %llu\n", exponent);
	expo = 1;
	for(expo = exponent; expo>0; expo--)
	{
		result = (result * base) % n;
		printf("RESULT: %llu\n", result);
		//printf("EXPO: %llu\n", exponent);
	}
	return result;
}

unsigned long long power(unsigned long long base, unsigned long long exponent)
{
	unsigned long long result, expo;
	result = 1;
	printf("EXPO: %llu\n", exponent);

	for(expo = exponent; expo>0; expo--)
	{
		result = result * base;
		//printf("RESULT: %llu\n", result);
		//printf("EXPO: %llu\n", exponent);
	}
	return result;
}


/*
void bin(unsigned n) 
{ 
    unsigned i; 
    for (i = 1 << 31; i > 0; i = i / 2) 
        (n & i)? printf("1"): printf("0"); 
} 
*/
