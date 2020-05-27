#include <stdlib.h>		// C standard library include
#include <stdio.h>		// I/O library include
#include <unistd.h>		// Needed for fork() call
#include <sys/types.h>		// Needed for waitpid routine (and other things...)
#include <sys/ipc.h>		// Needed for shared memory routines
#include <sys/shm.h>		// Needed for shared memory routines
#include <sys/wait.h>		// Needed for waitpid() routine

void first_processing_job(int *);	// Routine to do the first processing job
void second_processing_job(int *);	// Routine to do the second processing job
void finalize_processing(int, int);	// Routine to finalize processing

main(void) {
   pid_t child1_pid, child2_pid;	// Child process ID's
   int status;				// Status variable for waitpid() call

   int sharedMemoryID;			// The shared memory identifier
   int *sharedMemoryPtr;		// A pointer to a block of shared memory
   int *job1numIterations;		// Number of iterations in Job1
   int *job2numIterations;		// Number of iterations in Job2

   // Initialize the shared memory segment
   sharedMemoryID = shmget(IPC_PRIVATE, 2*sizeof(int), 0660);
   sharedMemoryPtr = (int *)shmat(sharedMemoryID, (void *)0, 0);
   job1numIterations = sharedMemoryPtr;
   job2numIterations = (sharedMemoryPtr + 1);
   *job1numIterations = 0;		// Initialize job1's iteration count
   *job2numIterations = 0;		// Initialize job2's iteration count

   if ((child1_pid = fork()) == 0) {	// first child process
      printf("   Process ID = %d (inside first forked() process)\n", child1_pid);
      first_processing_job(job1numIterations);
      exit(0);
   }

   if ((child2_pid = fork()) == 0) {	// second child process
      printf("   Process ID = %d (inside second forked() process)\n", child2_pid);
      second_processing_job(job2numIterations);
      exit(0);
   }
   printf("Process ID of first process  = %d\n", child1_pid);
   printf("Process ID of second process = %d\n", child2_pid);

   // Wait for both processes to complete
   waitpid(child1_pid, &status, 0);
   waitpid(child2_pid, &status, 0);

   // Sumarize information from the two processing jobs
   finalize_processing(*job1numIterations, *job2numIterations);
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
   printf("   First process iterations:   %d\n", one_times);
   printf("   Second process iterations:  %d\n", another_times);
   printf("   Total number of iterations: %d\n", total);
}

