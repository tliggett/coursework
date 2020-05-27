#include <stdlib.h>		// C standard library include
#include <stdio.h>		// I/O library include
#include <math.h>
#include <time.h>
#include <omp.h>

void generic_processing_job(int, int, double *, int, double *);

int main(argc,argv) int argc; char *argv[]; {
   double accum;

   double *theBuffer;
   int bufSize;
   int i;
 
   time_t startTime, endTime;
   int duration;
 
   int np;			// Number of processes

   // Check the number of command-line arguments
   if (argc != 2) {
      printf("  use:  num3omp np\n");
      printf("        where np is the number of threads to generate, up to 20\n");
      exit(-1);
   }
   
   // Start the timer
   startTime = time(NULL);

   // Get the number of processes entered
   sscanf(argv[1], "%d",&np);
   if ((np < 1) || (np > 20)) {
      printf("   Error:  np = %d.  Needs to be between 1 and 20 inclusive...\n", np);
      exit(-1);
   }

   // Allocate data buffer memory
   bufSize = 50000000;
   theBuffer = (double *)malloc(bufSize * sizeof(double));
   if (!theBuffer) {
      printf("   Error allocating memory...\n");
      exit(-1);
   }

   srand(startTime);
   for(i=0; i<bufSize; ++i) {
      theBuffer[i] = 100.0 * (rand() / (RAND_MAX + 1.0));
   }
 
   omp_set_num_threads(np);
   #pragma omp parallel reduction(+:accum)
   {
      generic_processing_job(omp_get_thread_num(), omp_get_num_threads(), theBuffer, bufSize, &accum);
   }

   // Sumarize information from the two processing jobs
   printf("  %d %d ", np, bufSize);
   printf("%lf ", accum/(double)bufSize);

   // Get the end time
   endTime = time(NULL);
   duration = endTime - startTime;
   printf("%d\n", duration);
}

void generic_processing_job(int start, int increment, double *buf, int size, double *accum) {
   int i,j;
   int numVals;
   double localAvg;
   double delta;
   double stdDev;

   // Calculate sum
   numVals = 0;
   for (i=start; i<size; i+=increment) {
      *accum += buf[i];
      numVals++;
   }
   // Calculate local aveage
   localAvg = *accum/(double)numVals;

   // Calculate local stdev -- to spend time, do it 500x
   for (j=0; j<500; ++j) {
      numVals = 0;
      for (i=start; i<size; i+=increment) {
         delta += pow((buf[i] - localAvg),2);
         numVals++;
      }
      stdDev = sqrt(delta/numVals);
   }
}

