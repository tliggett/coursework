#include <stdlib.h>		// C standard library include
#include <stdio.h>		// I/O library include
#include <math.h>
#include <unistd.h>		// Needed for fork() call
#include <time.h>
#include <sys/types.h>		// Needed for waitpid routine (and other things...)
#include <sys/ipc.h>		// Needed for shared memory routines
#include <sys/shm.h>		// Needed for shared memory routines
#include <sys/wait.h>		// Needed for waitpid() routine

void generic_processing_job(int, int, double *, int, double *);
void finalize_processing(double *var[], int size, int np);

int main(argc,argv) int argc; char *argv[]; {
   pid_t child_pid[20];		// Child process ID's
   double *(jobAccum[20]);
   int sharedMemoryID;		// The shared memory identifier
   double *sharedMemoryPtr;	// A pointer to a block of shared memory
   int status;			// Status variable for waitpid() call

   double *theBuffer;
   int bufSize;
   int i;
   int accum;
 
   time_t startTime, endTime;
   int duration;
 
   int np;			// Number of processes

   // Check the number of command-line arguments
   if (argc != 2) {
      printf("  use:  num3 np\n");
      printf("        where np is the number of processes to fork, up to 20\n");
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

   //printf("   np = %d", np);
   //printf("   bufSize = %d\n", bufSize);
   // Initialize the buffer with pseudo-random numbers between 1 and 100
   srand(startTime);
   for(i=0; i<bufSize; ++i) {
      theBuffer[i] = 100.0 * (rand() / (RAND_MAX + 1.0));
   }
 
   // Initialize the shared memory segment
   sharedMemoryID = shmget(IPC_PRIVATE, np*sizeof(double), 0660);
   sharedMemoryPtr = (double *)shmat(sharedMemoryID, (void *)0, 0);

   // Initiate np processes
   for (i=0; i<np; ++i) {
      jobAccum[i] = sharedMemoryPtr + i;
      *jobAccum[i] = 0;

      if ((child_pid[i] = fork()) == 0) {
         generic_processing_job(i, np, theBuffer, bufSize, jobAccum[i]);
         exit(0);
      }
      //printf("   Process ID of process %d  = %d\n", i+1, child_pid[i]);
   }

   // Wait for all processes to compelte
   for (i=0; i<np; ++i) {
      waitpid(child_pid[i], &status, 0);
   }

   // Sumarize information from the two processing jobs
   printf("  %d %d ", np, bufSize);
   finalize_processing(jobAccum, bufSize, np);

   // Get the end time
   endTime = time(NULL);
   duration = endTime - startTime;
   printf("%d\n", duration);
   //printf("   Time: %d seconds\n", duration);
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

void finalize_processing(double *accum[], int n, int np) {
   double total;
   int i;

   total = 0;
   for (i=0; i<np; ++i) total += *accum[i];

   // Print results
   //printf("   Summary:");
   //printf(" Total sum: %lf", total);
   //printf(" Average: %lf\n", total/(double)n);
   printf("%lf ", total/(double)n);
}



