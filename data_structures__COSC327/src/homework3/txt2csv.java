package homework3;

import java.util.*;
import java.io.*;

public class txt2csv
{
    public static void main(String [] args) throws FileNotFoundException
    {
        Scanner input = new Scanner(new File(args[0]) + ".csv");
        PrintWriter output = new PrintWriter(new File(args[0] + "1.csv"));

        while(input.hasNextLine())
        {
            input.nextLine();
            for(int i = 0; i < 3; ++i)
            {
                String[] line = input.nextLine().split(",");
                String[] time = line[1].split("[ms]");
                output.print(   ((Double.parseDouble(time[0])*60) + Double.parseDouble(time[1])) +  ", ");
            }


        }
        input.close();
        output.close();
    }

}
