

#include <stdlib.h>
#include <stdio.h>

//Desc: Retrieves user's command for File System
//Input: The user's command request
void prompt(String systemName){
    Scanner input = new Scanner(System.in);
    System.out.printf("%s> ");
    return input.next();
}

//Output: dumps block table onto console in groups of ten
void dump(char[] blocks){
    for(int i  = 0; i< blocks.length; i+= 10)
    {
        System.out.printf("%3d: ", i);
        for(int j = i; j<i+10; ++j) System.out.printf("%2s", blocks[j]);
        System.out.println();
    }
}