//TJ Liggett
//3_31_2019
//COSC 310 Lab 7


import java.util.*;
//NOTE: Class SystemMethods.java must be included

public class Linked {

    public static final int BLOCKSIZE = 992;           /* Number of bytes allocated to file */
    public static int[] disk;                          /* The array of disk blocks */
    public static int usedBlocks;                       /* Count of blocks used */
    public static TreeSet<TFile> fileInfo;              /* A list of file information (location & bytesize)*/

    public static void main(String[] args) {
        System.out.println("TJ's linked-list filesystem: Input help for command list");

        //Initialize disk storage
        disk = new int[200];
        for (int i = 0; i < disk.length; i++) disk[i] = -1;
        /*
         * NOTE: For this linked list file system, blocks will be marked as follows:
         *   -1: Block is unused (Empty/Unused)
         *   1-199: Block is in use, value is pointer to next block (written)
         *   -2: Block has been used and deleted (deleted)
         *   -3: Block is end of file and points to null
         * */

        usedBlocks = 0;                     /* Easily tell which block to write to next (Santa's little helper)*/
        fileInfo = new TreeSet<TFile>();    /* Stores file directory information (no duplicates) */

        //Main command loop
        while (true) {

            //Receive command from user
            String cmd = SystemMethods.prompt("linkedAlloc");

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
            } else switch (cmd) {
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
        for (TFile file : fileInfo) {
            //Checks for file in directory, if found deletes the file and returns
            if (file.filename.equals(filename)) {
                int blockCount = SystemMethods.getBlockCount(file.size, BLOCKSIZE);
                System.out.printf("Reading file %s from disk:\t %d bytes (%d blocks on disk).\n Reading blocks:\t",
                        filename, file.size, blockCount);
                int curr = file.loc; /* Set pointer to first block of file */
                //Goes through each linked block and reads from memory.
                while (curr != -3) {
                    int next = disk[curr];
                    int read = disk[curr];
                    System.out.printf("%3d ", curr);
                    curr = next;
                }

                System.out.println();
                fileInfo.remove(file);
                return;
            }
        }
        //If method did not return, file was not found.
        System.out.printf("File %s not found.\n", filename);

    }

    //Deletes file from disk
    static void del(String filename) {
        for (TFile file : fileInfo) {
            //Checks for file in directory, if found deletes the file and returns
            if (file.filename.equals(filename)) {
                int blockCount = SystemMethods.getBlockCount(file.size, BLOCKSIZE);
                System.out.printf("Deleting file %s from disk:\t %d bytes (%d blocks on disk).\n Deleting blocks:\t",
                        filename, file.size, blockCount);
                int curr = file.loc; /* Set pointer to first block of file */
                //Goes through each linked block and deletes from memory.
                while (curr != -3) {
                    int next = disk[curr];
                    disk[curr] = -2;
                    System.out.printf("%3d ", curr);
                    curr = next;
                }

                System.out.println();
                fileInfo.remove(file);
                usedBlocks -= blockCount;       /*NOTE: Must decrement used blocks in Linked List implementations */
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
        if ((200 - usedBlocks) < blockSize) {
            System.out.printf("Error: File %s not saved; file system full.\n", filename);
            return;
        }

        //If file doesn't exist, write to disk. Otherwise print error statement
        TFile file = new TFile(filename, numBytes, usedBlocks);
        if (fileInfo.add(file)) {
            System.out.printf("Writing File %s to disk in %d blocks: ", filename, blockSize);
            //Go through disk, find and link blocks to write to disk.
            int blocksLeft = blockSize;     /* Keeps track of how many blocks left to link */
            int curr = -1, prev = -1;       /* Define a pointer to both current and previous open block */
            //Loop through disk and write and link blocks while blocksLeft > 0
            //NOTE: Loop should never go out of bounds due to earlier size check
            while (blocksLeft > 0) {
                curr++;
                //If block is empty write file to disk
                if (disk[curr] == -1 || disk[curr] == -2) {
                    if (prev > -1) {
                        disk[prev] = curr;
                        System.out.printf("%3d ", prev);
                    }else{
                        fileInfo.remove(file);
                        fileInfo.add(new TFile(filename, numBytes, curr));
                    }

                    prev = curr;
                    blocksLeft--;
                }
            }
            disk[curr] = -3; /* Points last block to null */
            System.out.printf("%3d", curr);

            //Increment usedBlocks
            usedBlocks += blockSize;
            System.out.println();
        } else System.out.printf("Error: File %s not saved; file already exists.\n", filename);
    }

    //Output: Dumps most of the relevant information for Linked-List Allocation File System
    static void dumpAll() {
        SystemMethods.dir(fileInfo, BLOCKSIZE);     /* Prints file directory */
        //Dump Block Map with links
        System.out.println("Advanced Block Map Dump: \n");
        String[] map = SystemMethods.getFileMap(disk);
        for (TFile file : fileInfo) map[file.loc] = file.filename;
        for (int i = 0; i < disk.length; i += 10) {
            System.out.printf("%3d: ", i);
            for (int j = i; j < i + 10; ++j) {
                switch(map[j])
                {
                    case "used": System.out.printf("---link=>%3d---\t", disk[j]); break;
                    case "open": System.out.printf("-----open----- \t"); break;
                    case "deleted": System.out.printf("----deleted----\t"); break;
                    default: System.out.printf("%10s=>%3d\t", map[j], disk[j]); break;
                }

            }
            System.out.println();
        }


    }


}




