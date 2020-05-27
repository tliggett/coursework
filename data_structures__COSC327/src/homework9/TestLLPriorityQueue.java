package homework9;//TJ Liggett
//4_25_2019
//Homework 9
//TestLLPriorityQueue.java

/*
 *
 *       Class TestLLPriorityQueue tests both constructors and methods in LLPriorityQueue
 *
 * */

import java.util.Comparator;

public class TestLLPriorityQueue {

    public static void main(String[] args)
    {
        //Testing methods using comparator constructor
        LLPriorityQueue<String> q = new LLPriorityQueue<String>(new maxStringComparator());
        while (!q.isEmpty())
        {
            String name= q.poll();
            System.out.println(name);
        }

        //Testing methods using non-comparator constructor
        LLPriorityQueue<String> q2 = new LLPriorityQueue<String>();
        LLPriorityQueue<String> clone2 = (LLPriorityQueue<String>) q2.clone();
        while (!q2.isEmpty())
        {
            String name= q2.poll();
            System.out.println(name);
        }
        System.out.println(q2.size()+" elements in original,  " + clone2.size() + " in clone");

    }

    //Class maxStringComparator compares strings in descending order
    static class maxStringComparator implements Comparator<String>
    {
        @Override
        public int compare(String o1, String o2) {
            return -1 * o1.compareTo(o2);
        }
    }
}
