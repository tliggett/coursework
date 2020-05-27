//TJ Liggett
//4_5_2019
//COSC 310 Lab 7


import java.util.*;
///NOTE: Class SystemMethods.java must be included

public class Contig {

    public static final int BLOCKSIZE = 1024;           /* Number of bytes allocated to file */
    public static int[] disk;                          /* The array of disk blocks */
    public static int usedBlocks;                       /* Count of blocks used */
    public static TreeSet<TFile> fileInfo;              /* A list of file information (location & bytesize)*/

    public static void main(String[] args) {
        System.out.println("TJ's contiguous filesystem: Input help for command list");

        //Initialize disk storage
        disk = new int[200];
        for (int i = 0; i < disk.length; i++) disk[i] = -1;
        /*
        * NOTE: For this contiguous file system, blocks will be marked as follows:
        *   -1: Block is unused (Empty/Unused)
        *   1: Block is in use (written)
        *   -2: Block has been used and deleted (deleted)
        * */

        usedBlocks = 0;                     /* Easily tell which block to write to next (Santa's little helper)*/
        fileInfo = new TreeSet<TFile>();    /* Stores file directory information (no duplicates) */

        //Main command loop
        while (true) {

            //Receive command from user
            String cmd = SystemMethods.prompt("contiguousAlloc");

            //Execute command if valid
            if (cmd.contains("store")) {
                //If command has proper format, will execute store command. Else print error message.
                try {
                    String[] store = cmd.split(" ");
                    store(store[1], Integer.parseInt(store[2]));
                } catch (Exception e) {
                    System.out.println("Command not recognized.");
                }
            } else if (cmd.contains("del")) {
                //If command has proper format, will execute delete command. Else print error message.
                try {
                    String[] del = cmd.split(" ");
                    del(del[1]);
                } catch (Exception e) {
                    System.out.println("Command not recognized.");
                }
            } else if (cmd.contains("access")) {
                //If command has proper format, will execute access command. Else print error message.
                try {
                    String[] access = cmd.split(" ");
                    access(access[1]);
                } catch (Exception e) {
                    System.out.println("Command not recognized.");
                }
            }else switch (cmd) {
                case "dir":
                    SystemMethods.dir(fileInfo, BLOCKSIZE);
                    break;
                case "dump":
                    SystemMethods.dump(disk);
                    break;
                case "dump-all":
                    dumpAll();
                    break;
                case "help":
                    SystemMethods.help();
                    break;
                case "exit":
                    System.exit(0);
                    break;
                default:
                    System.out.println("Command not recognized.");
                    break;
            }
        }
    }

    //Reads file if found
    static void access(String filename) {
        for(TFile file : fileInfo)
        {
            //Checks for file in directory, if found reads the file and returns
            if(file.filename.equals(filename))
            {
               int blockCount = SystemMethods.getBlockCount(file.size, BLOCKSIZE);
               System.out.printf("Reading file %s from disk:\t %d bytes (%d blocks on disk).\n Reading blocks:\t",
                                    filename, file.size, blockCount);
               for(int i = file.loc; i < file.loc + blockCount; ++i)
               {
                   int read = disk[i]; /* Simulates reading the disk, doesn't have use in program */
                   System.out.printf("%4d", i);
               }
               System.out.println();
               return;
            }
        }
        //If method did not return, file was not found.
        System.out.printf("File %s not found.\n", filename);

    }

    //Deletes file from disk
    static void del(String filename) {
        for(TFile file : fileInfo)
        {
            //Checks for file in directory, if found deletes the file and returns
            if(file.filename.equals(filename))
            {
                int blockCount = SystemMethods.getBlockCount(file.size, BLOCKSIZE);
                System.out.printf("Deleting file %s from disk:\t %d bytes (%d blocks on disk).\n Deleting blocks:\t",
                        filename, file.size, blockCount);
                for(int i = file.loc; i < file.loc + blockCount; ++i)
                {
                    disk[i] = -2; /* Deletes file from block */
                    System.out.printf("%4d", i);
                }
                System.out.println();
                fileInfo.remove(file);
                return;
            }
        }
        //If method did not return, file was not found.
        System.out.printf("File %s not found.\n", filename);
    }

    //Store file on disk
    static void store(String filename, int numBytes) {
        int blockSize = SystemMethods.getBlockCount(numBytes, BLOCKSIZE);  /* Get block size from number of bytes */

        //Check if file is too big to add into system
        if ((200 - usedBlocks) < blockSize)
        {
            System.out.printf("Error: File %s not saved; file system full.\n", filename);
            return;
        }

        //If file doesn't exist, write to disk. Otherwise print error statement
        TFile file = new TFile(filename, numBytes, usedBlocks);
        if(fileInfo.add(file))
        {
            System.out.printf("Writing File %s to disk in %d blocks: ", filename, blockSize);
            //Go through each block sequentially to write to disk.
            for(int i = file.loc; i < file.loc + blockSize; ++i) {
                disk[i] = 1;
                System.out.printf("%4d", i);
            }
            //Increment usedBlocks
            usedBlocks += blockSize;
            System.out.println();
            //If 80% of disk is used, compact the disk
            if(usedBlocks >= disk.length*4/5) compact();
        }else System.out.printf("Error: File %s not saved; file already exists.\n", filename);
    }


    static void compact(){
        int i = 0;
        while(i < disk.length) {
            if (disk[i] == -2) {
                for (TFile file : fileInfo) if (file.loc > i) file.loc--;
                for (int j = i + 1; j < disk.length; ++j) disk[j - 1] = disk[j];
                disk[199] = -1;

            }else ++i;
        }
        System.out.println("Compaction occurred");
    }


    //Output: An extended block map with file names
    static void dumpAll(){
        SystemMethods.dir(fileInfo, BLOCKSIZE);
        System.out.println("Block-Map Dump: \n");
        String[] map = SystemMethods.getFileMap(disk);
        for(TFile file : fileInfo)
            for(int i = file.loc; i < file.loc + SystemMethods.getBlockCount(file.size, BLOCKSIZE); ++i) map[i] = file.filename;

        for(int i  = 0; i< disk.length; i+= 10)
        {
            System.out.printf("%3d: ", i);
            for(int j = i; j<i+10; ++j)
            {
                System.out.printf("%10s", map[j]);
            }
            System.out.println();
        }

    }




}



