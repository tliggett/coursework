#include <stdlib.h>		// C standard library include
#include <stdio.h>		// I/O library include

void sumArray(int*, int*, int, int);//Routine to sum array
void finalize_processing(int, int);	// Routine to finalize processing

int main(void) {
    int status;				    // Status variable for waitpid() call
    int job1sum;		        // Sum of iterations in Job1
    int job2sum;		        // Sum of iterations in Job2
    int A[50000];               // Array for summing
    int i;                      // loop counter

    for(i = 0; i<50000; ++i)
    {
        A[i] = i%100;
    }
    job1sum = 0;		// Initialize job1's iteration count
    job2sum = 0;		// Initialize job2's iteration count

    sumArray(A, &job1sum, 0, 25000);
    sumArray(A, &job2sum, 25000, 50000);

    // Summarize information from the two processing jobs
    finalize_processing(job1sum, job2sum);
    return 0;
}

//Sums count numbers in array starting at index sIndex
void sumArray(int *array, int *sum, int sIndex, int count) {
    int i;

    for (i=sIndex; i<count; ++i) {
        *sum += array[i];
    }
}

//Output: The processing summary including sum and average
void finalize_processing(int one_sum, int another_sum) {
    int total, average;

    total = one_sum + another_sum;
    average = total/50000;
    printf("Processing Summary:\n");
    printf("   Total sum: %d\n", total);
    printf("   Total average: %d\n", average);
}

