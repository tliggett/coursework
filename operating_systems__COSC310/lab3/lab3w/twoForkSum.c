#include <stdlib.h>		// C standard library include
#include <stdio.h>		// I/O library include
#include <unistd.h>		// Needed for fork() call
#include <sys/types.h>		// Needed for waitpid routine (and other things...)
#include <sys/wait.h>		// Needed for waitpid() routine
#include <sys/ipc.h>		// Needed for shared memory routines
#include <sys/shm.h>		// Needed for shared memory routines

void sumArray(int*, int*, int, int);//Routine to sum array
void finalize_processing(int, int);	// Routine to finalize processing

int main(void) {
    pid_t child1_pid, child2_pid;	// Child process ID's
    int status;				    // Status variable for waitpid() call
    int sharedMemoryID;			// The shared memory identifier
    int *sharedMemoryPtr;		// A pointer to a block of shared memory
    int *job1sum;		        // Sum of iterations in Job1
    int *job2sum;		        // Sum of iterations in Job2
    int A[50000];               // Array for summing
    int i;                      // loop counter


    //Initialize the values of the array
    for(i = 0; i<50000; ++i)
    {
        A[i] = i%100;
    }


    // Initialize the shared memory segment
    sharedMemoryID = shmget(IPC_PRIVATE, 2*sizeof(int), 0660);
    sharedMemoryPtr = (int *)shmat(sharedMemoryID, (void *)0, 0);
    job1sum = sharedMemoryPtr;
    job2sum = (sharedMemoryPtr + 1);
    *job1sum = 0;		// Initialize job1's iteration count
    *job2sum = 0;		// Initialize job2's iteration count


    if ((child1_pid = fork()) == 0) {	// first child process
        printf("   Process ID = %d (inside first forked() process)\n", child1_pid);
        sumArray(A, job1sum, 0, 25000);
        printf("   First process sum:   %d\n", *job1sum);
        exit(0);
    }

    if ((child2_pid = fork()) == 0) {	// second child process
        printf("   Process ID = %d (inside second forked() process)\n", child2_pid);
        sumArray(A, job2sum, 25000, 50000);
        printf("   Second process sum:  %d\n", *job2sum);
        exit(0);
    }
    printf("Process ID of first process  = %d\n", child1_pid);
    printf("Process ID of second process = %d\n", child2_pid);

    // Wait for both processes to complete
    waitpid(child1_pid, &status, 0);
    waitpid(child2_pid, &status, 0);

    // Summarize information from the two processing jobs
    finalize_processing(*job1sum, *job2sum);
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

