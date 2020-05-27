/*
 * TJ Liggett
 * 2_17_2019
 * COSC 310
 * Lab 2
 * add.c
 */

#include <stdlib.h>
#include <stdio.h>

void add(int *A1,      /* first array to be added*/
        int *A2,        /* second array to be added*/
        int *A3,        /* array to return sum to*/
        int s)          /* size of arrays */
{
    int i;              /* loop pointer */

    /*add A1 and A2 together*/
    for(i = 0; i < s; i++)
    {
        A3[i] = A1[i] + A2[i];
    }
}
