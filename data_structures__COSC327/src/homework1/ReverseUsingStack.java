package homework1;//TJ Liggett
//Homework 1
//2_14_2019
//ReverseUsingStack.java

/*
	Class ReverseUsingStack takes user input list of integers and reverses it
	using a Stack
 */


import java.util.Stack;
import java.util.Scanner;

class ReverseUsingStack
{
    //Input: a sequence of integers and -1 to end
    //Output: the user input sequence in reverse
    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in);
        Stack<Integer> stack = new Stack<Integer>();
        System.out.print("Enter a sequence of integers, -1 to end: ");
        while(true)
        {
            int i = input.nextInt();
            if(i == -1) break;
            else stack.push(i);
        }
        System.out.print("list in reverse: ");
        while(stack.size() > 0) System.out.print(stack.pop() + " ");
        input.close();
    }
}