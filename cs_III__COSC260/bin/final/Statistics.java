//TJ Liggett
//Final Exam
//12_2_2018
//Statistics.java

/*
	Class Statistics takes user input test scores and computes the high, low,
	median, and average test scores
 */

import java.util.Scanner;
import java.util.Vector;

public class Statistics
{
    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter scores, -1 to end: ");
        Vector<Integer> v = new Vector<Integer>();
        while(true)
        {
            int i = input.nextInt();
            if(i == -1) break;
            else v.add(i);
        }
        Integer[] arr = new Integer[v.size()];
        for(int i = 0; i <arr.length; i++)
        {
            arr[i] = v.get(i);
        }

        int highest=arr.length-1;
        int lowest=0;
        int median= arr.length/2;
        QuickSort.findKth (arr, highest);
        System.out.println("High: "+arr[highest]);
        QuickSort.findKth (arr, lowest);
        System.out.println("Low: "+arr[lowest]);
        QuickSort.findKth (arr, median);
        System.out.println("Median: "+arr[median]);

        int avg = 0;
        for(int i : arr) avg += i;
        avg /= arr.length;
        System.out.println("Average: " + avg);

        input.close();
    }

}