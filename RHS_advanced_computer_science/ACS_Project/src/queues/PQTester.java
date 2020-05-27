package queues;

import java.util.Queue;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class PQTester
{
	private Queue<String> pQueue;

	public PQTester()
	{
		pQueue =  new PriorityQueue<String>();
		setPQ("");
	}

	public PQTester(String list)
	{
		pQueue =  new PriorityQueue<String>();
		setPQ(list);
	}

	public void setPQ(String list)
	{
		String[] spt = list.split(" ");
		for(String s : spt)
			pQueue.add(s);
		
	}
	
	public Object getMin()
	{
		return pQueue.peek();
	}
	
	public String getNaturalOrder()
	{
		String output="";
		while(!pQueue.isEmpty())
			output += pQueue.poll() + " ";
		return output;		
	}

	public String toString(){
		
		return pQueue + ""; 	
	
	}
}