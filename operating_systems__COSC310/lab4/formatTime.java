

import java.util.*;
import java.io.*;


public class formatTime
{

    public static void main(String[] args){
        Scanner input = new Scanner(new File("lab4.csv"));
        ArrayList<String> lines = new ArrayList<String>();

        input.nextLine();
        while(input.hasNextLine())
        {
            String[] line = input.nextLine().split(",");
            for(int i = 1; i <= 9; ++i)
            {
                int sec = 0;
                String[] time = line[i].split("[ms]");
                sec += Double.parseDouble(time[0]) + Dd
            }
        }
    }



}