#include <stdlib.h>		// C standard library include
#include <stdio.h>		// I/O library include
#include <unistd.h>		// Needed for fork() call
#include <sys/types.h>		// Needed for waitpid routine (and other things...)
#include <sys/wait.h>		// Needed for waitpid() routine
#include <sys/ipc.h>		// Needed for shared memory routines
#include <sys/shm.h>		// Needed for shared memory routines

void sumArray(int*, int*, int, int);//Routine to sum array
void finalize_processing(int, int, int, int);	// Routine to finalize processing

int main(void) {
    pid_t child1_pid, child2_pid, child3_pid, child4_pid;	// Child process ID's
    int status;				    // Status variable for waitpid() call
    int sharedMemoryID;			// The shared memory identifier
    int *sharedMemoryPtr;		// A pointer to a block of shared memory
    int *job1sum;		        // Sum of iterations in Job1
    int *job2sum;		        // Sum of iterations in Job2
    int *job3sum;		        // Sum of iterations in Job3
    int *job4sum;		        // Sum of iterations in Job4
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
    job3sum = (sharedMemoryPtr + 2);
    job4sum = (sharedMemoryPtr + 3);
    *job1sum = 0;		// Initialize job1's iteration count
    *job2sum = 0;		// Initialize job2's iteration count
    *job3sum = 0;		// Initialize job3's iteration count
    *job4sum = 0;		// Initialize job4's iteration count

    if ((child1_pid = fork()) == 0) {	// first child process
        printf("   Process ID = %d (inside first forked() process)\n", child1_pid);
        sumArray(A, job1sum, 0, 12500);
        printf("   First process sum:   %d\n", *job1sum);
        exit(0);
    }

    if ((child2_pid = fork()) == 0) {	// second child process
        printf("   Process ID = %d (inside second forked() process)\n", child2_pid);
        sumArray(A, job2sum, 12500, 25000);
        printf("   Second process sum:  %d\n", *job2sum);
        exit(0);
    }

    if ((child3_pid = fork()) == 0) {	// second child process
        printf("   Process ID = %d (inside third forked() process)\n", child3_pid);
        sumArray(A, job3sum, 25000, 37500);
        printf("   Third process sum:  %d\n", *job3sum);
        exit(0);
    }

    if ((child4_pid = fork()) == 0) {	// second child process
        printf("   Process ID = %d (inside fourth forked() process)\n", child4_pid);
        sumArray(A, job4sum, 37500, 50000);
        printf("   Fourth process sum:  %d\n", *job4sum);
        exit(0);
    }


    printf("Process ID of first process  = %d\n", child1_pid);
    printf("Process ID of second process = %d\n", child2_pid);
    printf("Process ID of third process  = %d\n", child3_pid);
    printf("Process ID of fourth process = %d\n", child4_pid);

    // Wait for all processes to complete
    waitpid(child1_pid, &status, 0);
    waitpid(child2_pid, &status, 0);
    waitpid(child3_pid, &status, 0);
    waitpid(child4_pid, &status, 0);

    // Summarize information from the fourth processing jobs
    finalize_processing(*job1sum, *job2sum, *job3sum, *job4sum);
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
void finalize_processing(int one_sum, int two_sum, int red_sum, int blue_sum)
{
    int total, average;

    total = one_sum + two_sum + red_sum + blue_sum;
    average = total/50000;
    printf("Processing Summary:\n");
    printf("   Total sum: %d\n", total);
    printf("   Total average: %d\n", average);
}

