/*
 * TJ Liggett
 * 3_7_2019
 * COSC 310
 * Lab 5
 * lab5.java
 */

import java.util.*;
import java.io.*;

public class lab5
{

    public static boolean verb;
    public static int[] virtAdd = new int[16];
    public static int[] physAdd = new int[8];

    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in);
        String command = "";
        verb = true;

        for(int i = 0; i<8; ++i) virtAdd[i] = i;
        for(int i = 8; i<16; ++i) virtAdd[i] = 8;
        for(int i = 0; i<8; ++i) physAdd[i] = 0;



        System.out.println("COSC 310 Lab 5 by TJ Liggett. Enter ? for help.");
        while(true)
        {
            System.out.print("lab5> ");
            command = input.nextLine();
            command = command.trim();
            if(command.equals("q")) break;
            if (command.matches("\\d+"))
            {
                int vAdd = Integer.parseInt(command);
                if(vAdd >= 65536) System.out.printf("Address is out of bounds!! \n");
                else System.out.printf("   %d --> %d\n", vAdd, mmu(vAdd));
            }
            else switch(command)
            {
                case "?": help();
                break;
                case "on": verbose(true);
                break;
                case "off": verbose(false);
                break;
                case "dump": dump();
                break;
                case "textbook": textbook();
                break;
                default: System.out.printf("%s not a valid command. Press ? for help.\n", command);
                break;

            }

        }
    }

    public static void help()
    {
        System.out.printf("Commands:\n");
        System.out.printf("   q\t\tQuit the program\n");
        System.out.printf("   ?\t\tPrint this help screen\n");
        System.out.printf("   on\t\tTurns Verbose Mode On\n");
        System.out.printf("   off\t\tTurns Verbose Mode Off\n");
        System.out.printf("   dump\t\tDumps the current Page Table\n");
        System.out.printf("   textbook\tSets up pages as in Fig 3-9 in text\n");
        System.out.printf("   1729\t\t(Or any number -- decode this address)\n");
    }

    public static void verbose(boolean v)
    {
        if(v)
        {
            verb = true;
            System.out.println("   Verbose mode ON");
        }else{
            verb = false;
            System.out.println("   Verbose mode OFF");
        }
    }

    public static void dump()
    {
        System.out.println("     Virtual-Address   Physical-Address");
        System.out.println("\tIndex  MapsTo\tIndex  RefCnt");
        for(int i = 15; i > 7; --i) System.out.printf("\t%4d%4d\n", i, virtAdd[i]);
        for(int i = 7; i >= 0; --i) System.out.printf("\t%4d%4d\t%4d%4d\n", i, virtAdd[i], i, physAdd[i]);
    }

    //Set up virtual mapping as in Fig 3-9 in text and reset ref Counts.
    public static void textbook()
    {
        virtAdd[0] = 2;
        virtAdd[1] = 1;
        virtAdd[2] = 6;
        virtAdd[3] = 0;
        virtAdd[4] = 4;
        virtAdd[5] = 3;
        virtAdd[6] = 8;
        virtAdd[7] = 8;
        virtAdd[8] = 8;
        virtAdd[9] = 5;
        virtAdd[10] = 8;
        virtAdd[11] = 7;
        virtAdd[12] = 8;
        virtAdd[13] = 8;
        virtAdd[14] = 8;
        virtAdd[15] = 8;
        for(int i = 0; i<8; ++i) physAdd[i] = 0;
        if(verb) System.out.println("Set up pages as in Fig 3-9 in text");
    }

    //
    public static int mmu(int vAdd)
    {
        int offset = vAdd%4096;
        int blkNum = vAdd/4096;
        if(verb) System.out.printf("Address:  %d offset:  %d blkNum:  %d PageTableLoc:  %d\n", vAdd, offset, blkNum, virtAdd[blkNum]);
        if(virtAdd[blkNum] == 8)
        {
            pageFault(blkNum);
            if(verb) System.out.printf("After-Trap -- Address:  %d offset:  %d blkNum:  %d PageTableLoc: %d\n", vAdd, offset, blkNum, virtAdd[blkNum]);
        }
        int physPage = virtAdd[blkNum];
        int pAdd = (physPage * 4096) + offset;
        physAdd[physPage]++;
        return pAdd;
    }

    public static void pageFault(int blkNum)
    {
        int low = 0;
        for(int i = 1; i < 8; ++i) if(physAdd[i] < physAdd[low]) low = i;
        if(verb) System.out.printf("PageFrame %d had reference count of %d. Evicted from Page Table\n", low, physAdd[low]);
        int unmap = 0;
        for(int i = 0; i < 15; ++i)
            if(virtAdd[i] == low)
            {
                virtAdd[i] = 8;
                if(verb) System.out.printf("Virtual page table at location %d set to 8 (unmapped)\n", i);
            }
        virtAdd[blkNum] = low;
        physAdd[low] = 0;
    }


}