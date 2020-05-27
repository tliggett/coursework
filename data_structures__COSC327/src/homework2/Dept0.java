package homework2;//TJ Liggett
//Final Exam
//2_21_2019
//Dept0.java

/*
	Class Dept0 reads in Departments and their number of majors from file Dept0.txt,
	storing them in a TreeMap and printing them out.
 */


import java.util.*;
import java.io.*;

public class Dept0
{
    public static void main(String[] args) throws FileNotFoundException
    {
        TreeMap<String, Integer> depts = new TreeMap<String, Integer>();
        Scanner file = new Scanner(new File("homework2/Dept.txt"));
        while(file.hasNextLine())
        {
            String s = file.nextLine();
            Integer i = Integer.parseInt(file.nextLine());
            depts.put(s, i);
        }
        Set<String> keySet = depts.keySet();
        Iterator<String> iter= keySet.iterator();
        System.out.println("Department\tNumber of majors");
        System.out.println("----------\t----------------");
        while (iter.hasNext())
        {
            String key = iter.next();
            System.out.printf("%s\t\t%d\n", key, depts.get(key));
        }
    }
}