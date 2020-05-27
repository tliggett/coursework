#include <stdlib.h>		// C standard library include
#include <stdio.h>		// I/O library include
#include <math.h>		// Math library
#include <pthread.h>		// Pthreads library
#include <time.h>		// timers...

typedef struct {	// Structure of parameters to pass to generic_processing_job()
   int start;
   int increment;
   int size;
   double *accum;
   double *buf;
} work_order_t;

// Internal processing functions
void generic_processing_job_startup(work_order_t *);
void generic_processing_job(int start, int increment, double *buf, int size, double *accum);
void finalize_processing(double *, int, int);

int main(argc,argv) int argc; char *argv[]; {

   int i;			// Loop counter

   time_t startTime, endTime;	// Timer variables
   int duration;		// Runtime in seconds

   pthread_t thread_id[20];	// Thread ID's
   double jobAccum[20];		// Array of accumulators (sums)
   int np;			// Number of processes
   double *theBuffer;		// Ptr to data buffer (dynamically allocated)
   int bufSize;			// Number of elements in data buffer

   work_order_t *funcParms;	// function parameters
 
   // Check the number of command-line arguments
   if (argc != 2) {
      printf("  use:  num3pthread np\n");
      printf("        where np is the number of threads, up to 20\n");
      exit(-1);
   }
   
   // Start the timer
   startTime = time(NULL);

   // Get the number of threads entered
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

   // Initialize the buffer with psuedo-random numbers between 1 and 100
   srand(startTime);
   for(i=0; i<bufSize; ++i) {
      theBuffer[i] = 100.0 * (rand() / (RAND_MAX + 1.0));
   }
 
   // Initiate np threads
   for (i=0; i<np; ++i) {
      jobAccum[i] = 0;		// Initialize the counter
      
      funcParms = (work_order_t *)malloc(sizeof(work_order_t));
      funcParms->start = i;
      funcParms->increment = np;
      funcParms->size = bufSize;
      funcParms->accum = &(jobAccum[i]);
      funcParms->buf = theBuffer;

      pthread_create(&(thread_id[i]), NULL, (void *)generic_processing_job_startup, (void *)funcParms);
   }

   // Wait for all threads to compelte
   for (i=0; i<np; ++i) {
      pthread_join(thread_id[i], NULL);
   }

   // Sumarize information from the two processing jobs
   printf("  %d %d ", np, bufSize);
   finalize_processing(jobAccum, bufSize, np);

   // Get the end time
   endTime = time(NULL);
   duration = endTime - startTime;
   printf("%d\n", duration);

   // Cleanup
   free(theBuffer);
}

// Thread startup routine -- passes parameters via parms structure to the processing routine
void generic_processing_job_startup(work_order_t *parms) {
   generic_processing_job(parms->start, parms->increment, parms->buf, parms->size, parms->accum);
   free (parms);
}

// Generic processing routine -- sums an array and then wastes time...
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
   //printf("start:  %d  inc:  %d  size:  %d  accum:  %lf  ID:  %d\n", start, increment, size, *accum, pthread_self());
}


void finalize_processing(double *accum, int n, int np) {
   double total;
   int i;

   total = 0;
   for (i=0; i<np; ++i) total += accum[i];
   printf("%lf ", total/(double)n);
}

