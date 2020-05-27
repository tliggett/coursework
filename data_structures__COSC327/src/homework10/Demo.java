package homework10;//5_2_2019
//TJ Liggett
//Homework 10
//DemoH.java

import java.util.Iterator;

/*
 *  Class DemoH tests the iterator capability of MyLinkedList as well as the iterable
 *  capability of MyLinkedList
 */


public class Demo {
    public static void main(String[] args)
    {
        MyLinkedList<String> list = new MyLinkedList<>();
        Iterator<String> iter = list.iterator();            /* Testing function with empty list */
        while(iter.hasNext()) System.out.println(iter.next());
        list.add("John"); list.add("Paul"); list.add("George"); list.add("Ringo");
        iter = list.iterator();            /* Testing function with filled list */
        while(iter.hasNext()) System.out.print(iter.next() + " ");
        System.out.println();
        iter = list.iterator();            /* Testing function remove */
        while(iter.hasNext()) {
            if (iter.next().equals("Paul")) iter.remove();
        }
        for(String s : list) System.out.print(s + " ");
        System.out.println();

    }
}
