#include <stdlib.h>		// C standard library include
#include <stdio.h>		// I/O library include

void first_processing_job(int *);	// Routine to do the first processing job
void second_processing_job(int *);	// Routine to do the second processing job
void finalize_processing(int, int);	// Routine to finalize processing

main(void) {
   int status;				// Status variable for waitpid() call
   int job1numIterations;		// Number of iterations in Job1
   int job2numIterations;		// Number of iterations in Job2

   job1numIterations = 0;		// Initialize job1's iteration count
   job2numIterations = 0;		// Initialize job2's iteration count

   first_processing_job(&job1numIterations);
   second_processing_job(&job2numIterations);
   printf("   First process iterations:   %d\n", job1numIterations);
   printf("   Second process iterations:  %d\n", job2numIterations);

   // Sumarize information from the two processing jobs
   finalize_processing(job1numIterations, job2numIterations);
   return 0;
}

void first_processing_job(int *numIterations) {
   int i,j,x;

   for (i=0; i<4; ++i) {
      printf("  --> first job doing something...\n");
      // Do some processing here!
      sleep(5);
      (*numIterations)++;
   }
}

void second_processing_job(int *numIterations) {
   int i,j,x;

   for (i=0; i<4; ++i) {
      printf("  --> second job doing something!!!\n");
      // Do some processing here!
      sleep(3);
      (*numIterations)++;
   }
}

void finalize_processing(int one_times, int another_times) {
   int total;

   total = one_times + another_times;
   printf("Processing Summary:\n");
   printf("   Total number of iterations: %d\n", total);
}

