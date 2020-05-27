package queues;
//© A+ Computer Science  -  www.apluscompsci.com
//Name -
//Date -
//Class -  
//Lab  -

import java.util.Queue;
import java.util.Stack;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.LinkedList;

public class PalinList
{
	private Queue<String> queue;
	private Stack<String> stack;

	public PalinList()
	{
		queue = new LinkedList<String>();
		stack =  new Stack<String>();
		setList("");
	}

	public PalinList(String list)
	{
		queue = new LinkedList<String>();
		stack = new Stack<String>();
		setList(list);
	}

	public void setList(String list)
	{
		String[] spt = list.split(" ");
		for(String s : spt){
		stack.add(s);
		queue.add(s);
		}
		
		
	}

	public boolean isPalin()
	{
		while(!stack.empty()){
			if(!stack.pop().equals(queue.remove())){
			return false;
			}
		}
		return true;
	}

	public String toString(){
		//return queue + "" + isPalin();
		String s = "";
		s += queue + " is ";
		
		if(!this.isPalin()){
			s += "not ";
		}
		
		return s + "a palindrome!";
	}
	//write a toString method
}