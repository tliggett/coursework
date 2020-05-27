package homework1;//TJ Liggett
//Homework 1
//2_14_2019
//ReverseUsingLinkedList.java

/*
	Class ReverseUsingLinkedList takes user input list of integers and reverses it
	using a LinkedList
 */


import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Scanner;

public class ReverseUsingLinkedList
{
    //Input: a sequence of integers and -1 to end
    //Output: the user input sequence in reverse
    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in);
        LinkedList<Integer> list = new LinkedList<Integer>();
        System.out.print("Enter a sequence of integers, -1 to end: ");
        while(true)
        {
            int i = input.nextInt();
            if(i == -1) break;
            else list.add(i);
        }
        System.out.print("list in reverse: ");
        ListIterator<Integer> iter = list.listIterator(list.size());
        while(iter.hasPrevious()) System.out.print(iter.previous() + " ");
        input.close();
    }
}