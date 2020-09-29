//TJ Liggett
//9_10_2020
//Homework 2
//Demo.java

/*
 *
 *       Class Demo times insertion and search for HashSet, TreeSet, and array
 *
 */

import java.util.*;
import java.io.*;
public class Demo
{
    public static void main(String[] args) throws FileNotFoundException
    {
        Vector<String> v = new Vector<String>();
        loadVector(v, "dict.txt");
        Collections.shuffle(v);
        System.out.println("Number of words is " + v.size());

        TreeSet<String> tSet = new TreeSet<String>();
        double seconds=loadTreeSet(v, tSet);
        System.out.printf("\tBuilt TreeSet in %.3f seconds\n", seconds);

        HashSet<String> hSet = new HashSet<String>();
        seconds=loadHashSet(v, hSet);
        System.out.printf("\tBuilt HashSet in %.3f seconds\n", seconds);

        String[] arr = new String[v.size()];
        seconds=loadArray(v, arr);
        System.out.printf("\tBuilt array in %.3f seconds\n", seconds);

        seconds=searchTreeSet(v, tSet);
        System.out.printf("\tSearch TreeSet in %.3f seconds\n", seconds);

        seconds=searchHashSet(v, hSet);
        System.out.printf("\tSearch HashSet in %.3f seconds\n", seconds);

        seconds=searchArray(v, arr);
        System.out.printf("\tSearch array in %.3f seconds\n", seconds);
    }
    public static void loadVector(Vector<String> v, String filename) throws FileNotFoundException
    {
        Scanner input = new Scanner(new File(filename));
        while(input.hasNextLine()) v.add(input.nextLine());
    }
    public static double loadTreeSet (Vector<String> v, TreeSet<String> tSet)
    {
        StopWatch timer = new StopWatch();
        timer.start();
        for(String s : v) tSet.add(s);
        return timer.stop();
    }
    public static double loadHashSet (Vector<String> v, HashSet<String> hSet)
    {
        StopWatch timer = new StopWatch();
        timer.start();
        for(String s : v) hSet.add(s);
        return timer.stop();
    }
    public static double loadArray (Vector<String> v, String[] arr)
    {
        StopWatch timer = new StopWatch();
        timer.start();
        for(int i = 0; i < v.size(); ++i) arr[i] = v.get(i);
        return timer.stop();
    }
    public static double searchTreeSet (Vector<String> v, TreeSet<String> tSet)
    {
        StopWatch timer = new StopWatch();
        timer.start();
        boolean b = false;
        for(String s : v) b = tSet.contains(s);
        return timer.stop();
    }
    public static double searchHashSet (Vector<String> v, HashSet<String> hSet)
    {
        StopWatch timer = new StopWatch();
        timer.start();
        boolean b = false;
        for(String s : v) b = hSet.contains(s);
        return timer.stop();
    }
    public static double searchArray(Vector<String> v, String[] arr)
    {
        StopWatch timer = new StopWatch();
        timer.start();
        int index = 0;
        for(String s : v) index = seqSearch(arr, s);
        return timer.stop();

    }
    public static int seqSearch(String[] arr, String key)
    {
        int index=0;
        while (index <arr.length)
            if (key.equals(arr[index])) return index;
            else index ++;
        return -1;
    }
}


/**
 Class StopWatch supports objects representing a stop watch for measuring the time required to
 execute a process. Time is measured in seconds.
 */
class StopWatch
{
    private long startTime, stopTime;
    /**
     Initializes this StopWatch object<p>
     <b>Post:</b><br> startTime and stopTime of this StopWatch object initialized to 0<p>
     */
    public StopWatch()
    {
        startTime = stopTime = 0;
    }
    /**
     Starts this StopWatch <p>
     <b>Post:</b><br> startTime of this StopWatch set to the current system clock time in
     nanosecond <p>
     */
    public void start()
    {
        startTime = System.nanoTime();
    }
    /**
     Stops this StopWatch <p>
     <b>Post:</b><br> stopTime of this StopWatch set to the current system clock time in
     nanosecond <p>
     <b>Return:</b><br>Interval of time from start to stop measured in seconds.
     */
    public double stop()
    {
        stopTime = System.nanoTime();
        return (stopTime - startTime)/1000000000.0;
    }
}
