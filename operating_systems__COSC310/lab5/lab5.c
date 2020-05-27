/*
 * TJ Liggett
 * 3_10_2019
 * COSC 310
 * Lab 5
 * main.c
 */


#include <stdlib.h>
#include <stdio.h>
#include <string.h>

#define MAX_STRING_LEN 80

void help();
void dump(int fltCnt, int v[], int p[]);
void textbook(int verb, int *fltCnt, int v[], int p[]);
int mmu(int *fltCnt, int loc, int v[], int p[], int verb);
void pageFault(int pageNum, int v[], int p[], int verb);


void main()
{

    char command[MAX_STRING_LEN];   /* User command string */
    int loc;                         /* User command int space */
    int virtAdd[16];                /* Virtual address page table */
    int physAdd[8];                 /* Physical address page table */
    int verb = 0;           /* boolean value set to status of verbose mode (0 is false) */
    int i;                          /* Loop counter */
    int fltCnt = 0;                      /* Count of faults in program */

    // Assign mapping assignments to virtual pages 0-7
    // set physical page reference counts to 0
    for(i = 0; i < 8; ++i)
    {
        physAdd[i] = 0;
        virtAdd[i] = i;
    }
    //Declare virtual pages 8-15 as unmapped.
    for(i = 8; i < 16; ++i) virtAdd[i] = 8;

    printf("COSC 310 Lab 5 by TJ Liggett. For help input ?\n");

    //Handle user commands
    while(free)
    {
        //Prompt user for command
        printf("tjlab5> ");
        scanf("%s", command);

        //Determine operation based on command
        if(strcmp(command, "q") == 0) break;
        else if(strcmp(command, "?") == 0) help();

        //Set verbose mode on/off
        else if(strcmp(command, "on") == 0)
        {
            verb = 1;
            printf("   Verbose Mode ON\n");
        }
        else if(strcmp(command, "off") == 0)
        {
            verb = 0;
            printf("   Verbose Mode OFF\n");
        }
        //dump current page tables
        else if(strcmp(command, "dump") == 0) dump(fltCnt, virtAdd, physAdd);
        //set up tables like Figure 3-9 in the textbook
        else if(strcmp(command, "textbook") == 0) textbook(verb, &fltCnt, virtAdd, physAdd);
        //Interpret address requests/invalid commands
        else
        {

            if(strcmp(command, "0") == 0) /* Ensures address is not zero */
            {
                loc = 0;
                printf("   %d --> %d\n", loc, mmu(&fltCnt, loc, virtAdd, physAdd, verb));
            }
            else
            {
                loc = atoi(command); /* This will return integer if command is integer, 0 else */
                if(loc < 1 || loc > 65535) printf("Invalid Command. Enter ? for help.\n");
                else printf("   %d --> %d\n", loc, mmu(&fltCnt, loc, virtAdd, physAdd, verb));
            }

        }


    }

}


//Outputs a help menu on console
void help()
{
    printf("Commands:\n");
    printf("   q\t\tQuit the program\n");
    printf("   ?\t\tPrint this help screen\n");
    printf("   on\t\tTurns Verbose Mode On\n");
    printf("   off\t\tTurns Verbose Mode Off\n");
    printf("   dump\t\tDumps the current Page Table\n");
    printf("   textbook\tSets up pages as in Fig 3-9 in text\n");
    printf("   1729\t\t(Or any number -- decode this address)\n");
}

//Outputs page tables on console
void dump(int fltCnt,   /* Count of page faults */
        int v[],        /* virtual address map array*/
        int p[])        /* physical address reference array*/
{
    int i = 0;          /* Loop counter */


    printf("     Virtual-Address   Physical-Address\n");
    printf("\tIndex  MapsTo\tIndex  RefCnt\n");
    for(i = 15; i > 7; --i) printf("\t%4d%4d\n", i, v[i]);
    for(i = 7; i >= 0; --i) printf("\t%4d%4d\t%4d%4d\n", i, v[i], i, p[i]);
    printf("     Number of Page-Faults: %d\n", fltCnt);
}

//Sets up pages as in Fig 3-9 in text
//NOTE: textbook resets the page-fault count, as it is assumed there are 0 faults
//      at the beginning of the textbook example
void textbook(int verb,    /* Verbose mode boolean */
              int *fltCnt, /* Fault count */
              int v[],     /* virtual address map array*/
              int p[])     /* physical address reference array*/
{
    *fltCnt = 0;
    v[0] = 2;
    v[1] = 1;
    v[2] = 6;
    v[3] = 0;
    v[4] = 4;
    v[5] = 3;
    v[6] = 8;
    v[7] = 8;
    v[8] = 8;
    v[9] = 5;
    v[10] = 8;
    v[11] = 7;
    v[12] = 8;
    v[13] = 8;
    v[14] = 8;
    v[15] = 8;
    for(int i = 0; i<8; ++i) p[i] = 0;
    if(verb == 1) printf("Set up pages as in Fig 3-9 in text\nNOTE: Page-Fault count was reset\n");
}

//Fetch physical memory location from virtual location
int mmu(int *fltCnt,     /* Page-Fault count */
        int loc,        /* Virtual address */
        int v[],        /* Virtual address page table */
        int p[],        /* Physical address page table */
        int verb)       /* Verbose mode boolean (0 is false)*/
{
    int offset;         /* Page offset for address */
    int pageTemp;       /* Temporary page number */
    int vPage, pPage;   /* Virtual and Physical page number */
    int pAdd;           /* Physical address to return */


    //Virtual page and offset of address are found
    offset = loc & 0x0FFF;
    pageTemp = loc & 0xF000;
    vPage = pageTemp >> 12;
    if(verb == 1) printf("Address:  %d offset:  %d blkNum:  %d PageTableLoc:  %d\n", loc, offset, vPage, v[vPage]);

    //Checks for page-fault. If yes, then execute page-fault operation and increment fault counter
    if(v[vPage] == 8)
    {
        pageFault(vPage, v, p, verb);
        *fltCnt = (*fltCnt + 1);
        if(verb == 1) printf("After-Trap -- Address:  %d offset:  %d blkNum:  %d PageTableLoc: %d\n", loc, offset, vPage, v[vPage]);
    }
    //Finds physical address and returns
    pPage = v[vPage];
    pAdd = (pPage << 12) | offset;
    p[pPage]++;
    return pAdd;
}

//Handles page faults in memory
void pageFault( int pageNum,/* Virtual page number*/
                int v[],    /* Virtual address page table */
                int p[],    /* Physical address page table */
                int verb)   /* Verbose mode boolean (0 is false) */
{
    int low = 0;            /* The page number of page with lowest reference count */


    //Finds page with lowest reference count and evicts from the table
    for(int i = 1; i < 8; ++i) if(p[i] < p[low]) low = i;
    if(verb == 1) printf("PageFrame %d had reference count of %d. Evicted from Page Table\n", low, p[low]);

    //Unmaps virtual page with lowest reference count (least used)
    for(int i = 0; i < 15; ++i)
        if(v[i] == low)
        {
            v[i] = 8;
            if(verb == 1) printf("Virtual page table at location %d set to 8 (unmapped)\n", i);
        }

    //Map virtual page needed to least used physical page
    v[pageNum] = low;
    p[low] = 0;
}