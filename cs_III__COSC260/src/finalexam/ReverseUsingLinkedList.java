package finalexam;

import java.util.LinkedList;
import java.util.Scanner;

public class ReverseUsingLinkedList 
{
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
		while(list.size() > 0) System.out.print(list.removeLast() + " ");
		input.close();
	}
}
