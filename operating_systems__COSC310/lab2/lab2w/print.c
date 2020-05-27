/*
 * TJ Liggett
 * 2_17_2019
 * COSC 310
 * Lab 2
 * print.c
 */

#include <stdlib.h>
#include <stdio.h>


void printArray(int *A,       /* array to be printed */
            int s)          /* length of array */
{
    int i;                  /* loop counter */

    /*Print the array in the format: [1, 2, ..., s]*/
    printf("[");
    for(i = 0; i< s-1; i++)
    {
        printf("%d, ", A[i]);
    }

    printf("%d]\n", A[s-1]);
}