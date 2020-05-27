//TJ Liggett
//3_31_2019

import java.util.Scanner;
import java.util.TreeSet;

public class SystemMethods {

    //Desc: Retrieves user's command for File System
    //Input: The user's command request
    public static String prompt(String systemName){
        Scanner input = new Scanner(System.in);
        System.out.printf("%s> ", systemName);
        return input.nextLine();
    }

    public static void help()
    {
        System.out.println( "dir:                       Lists filesystem contents & attributes\n" +
                            "store filename numBytes:   Stores file filename of size numBytes\n" +
                            "access filename:           Reads file filename from filesystem\n" +
                            "del filename:              Deletes file filename from filesystem\n" +
                            "dump:                      Writes a disk-block map\n" +
                            "dump-all:                  Dumps all relevant info\n" +
                            "help:                      Prints this command list\n" +
                            "exit:                      Exits the program");
    }

    //Output: dumps block table onto console in groups of ten
    public static void dump(int[] disk){
        System.out.println("Block-Map Dump: \n");
        //Goes through every block and prints out status
        for(int i  = 0; i< disk.length; i+= 10)
        {
            System.out.printf("%3d: ", i);
            for(int j = i; j<i+10; ++j)
            {

                char c = 'O';
                //NOTE: disk blocks will be either:
                //          unused/open (O) -1
                //          deleted     (D) -2
                //          in use      (U) any other int
                switch(disk[j]){
                    case -1: c = 'O'; break;
                    case -2: c = 'D'; break;
                    default: c = 'U'; break;
                }
                System.out.printf("%2s", c);
            }
            System.out.println();
        }
    }

    //Return: A String[] that contains the file stored at each block
    public static String[] getFileMap(int[] disk)
    {
        String[] map = new String[disk.length];
        //Go through map and mark all Strings as either used, deleted, or open
        for(int i = 0; i < map.length; ++i)
        {
            switch(disk[i]){
                case -1: map[i] = "open";       break;
                case -2: map[i] = "deleted";    break;
                default: map[i] = "used";       break;
            }
        }
        return map;
    }



    //Return: number of blocks required to store number of bytes desired
    public static int getBlockCount(int bytes, int blockSize)
    {
        double b = (double) bytes;
        double s = (double) blockSize;
        return (int) Math.ceil(b/s);

    }

    //Output: A list of files in the directory
    public static void dir(TreeSet<TFile> fileInfo, int blockSize)
    {
        if(fileInfo.isEmpty()) System.out.println("Directory is empty");
        else{
            System.out.println("Directory Listing: ");
            for(TFile file : fileInfo)
            {
                System.out.printf("\t%s:\t%d bytes in %d blocks, starting at block# %d\n",
                        file.filename, file.size, SystemMethods.getBlockCount(file.size, blockSize), file.loc);
            }
        }

    }



}
