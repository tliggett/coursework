package linkedlists;
//© A+ Computer Science  -  www.apluscompsci.com
//Name -
//Date -
//Class - 
//Lab  -

import java.util.LinkedList;
import static java.lang.System.*;

public class JavaLinkedList
{
	private LinkedList<Integer> list;

	public JavaLinkedList()
	{
		list = new LinkedList<Integer>();
	}

	public JavaLinkedList(int[] nums)
	{
		list = new LinkedList<Integer>();
		for(int num : nums)
		{
			list.add(num);
		}
	}

	public double getSum(  )
	{
		double total=0;
		for(int i : list){
			total += i;
			}
		return total;
	}

	public double getAvg(  )
	{
		double avg = 0;
		for(int i : list){
			avg += i;
			}
		
		return avg/list.size();
	}

	public int getLargest()
	{
		int largest=Integer.MIN_VALUE;
		for(int i : list){
			if(i > largest){
				largest = i;
			}
		}
		return largest;
	}

	public int getSmallest()
	{
		int smallest = smallest=Integer.MAX_VALUE;
		for(int i : list){
			if(i < smallest){
				smallest = i;
			}
		}
		return smallest;
	}

	public String toString()
	{
		String output= "" + list;
		output += "\nSum :: " + getSum();
		output += "\nAverage :: " + getAvg();
		output += "\nLargest :: " + getLargest();
		output += "\nSmallest :: " + getSmallest();
		
		return output;
	}
}