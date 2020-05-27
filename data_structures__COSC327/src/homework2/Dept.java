package homework2;//TJ Liggett
//Homework 2
//2_21_2019
//Dept.java

/*
	Class Dept reads in Departments and their number of majors from file Dept.txt,
	storing them in a TreeMap and printing them out.
 */


import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Dept
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
        Set<Map.Entry<String, Integer>> entrySet = depts.entrySet();
        Iterator<Map.Entry<String,Integer>> iter= entrySet.iterator();
        System.out.println("Department\tNumber of majors");
        System.out.println("----------\t----------------");
        while (iter.hasNext())
        {
            Map.Entry<String,Integer> entry = iter.next();
            System.out.printf("%s\t\t%d\n", entry.getKey(), entry.getValue());
        }
    }
}