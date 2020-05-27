/*
 * TJ Liggett
 * 2_17_2019
 * COSC 310
 * Lab 2
 * main.c
 */

#include <stdlib.h>
#include <stdio.h>
#include "array.h"

main()
{
    int i;          /* loop counter*/
    int *x;         /* address in memory for array x*/
    int *y;         /* address in memory for array y*/
    int *z;         /* address in memory for array z*/
    int length;     /* Length of the array */

    /* Prompt user for length of arrays */
    printf("Enter length of arrays (arrays same length): \n");
    scanf("%d", &length);
    /*Allocate space for the arrays*/
    x = malloc(sizeof(int)*length);
    y = malloc(sizeof(int)*length);
    z = malloc(sizeof(int)*length);
    /*Prompt user to input the first two arrays*/
    printf("Enter %d numbers separated by whitespace for array x: \n", length);
    for(i = 0; i<length; ++i) scanf("%d", &x[i]);
    printf("Enter %d numbers separated by whitespace for array y: \n", length);
    for(i = 0; i<length; ++i) scanf("%d", &y[i]);

    /*Add the arrays together*/
    add(x, y, z, length);

    /*Print out the two arrays and their sum*/
    printf("Array x: ");
    printArray(x, length);
    printf("Array y: ");
    printArray(y,length);
    printf("Array x + y: ");
    printArray(z, length);
}