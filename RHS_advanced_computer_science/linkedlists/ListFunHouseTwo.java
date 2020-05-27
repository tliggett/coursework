package linkedlists;
//© A+ Computer Science  -  www.apluscompsci.com
//Name -
//Date -
//Class - 
//Lab  -  

import static java.lang.System.*;

public class ListFunHouseTwo
{
	private ListNode theList;
	public ListFunHouseTwo()
	{
		theList = null;
	}
	
	public void add(Comparable data)
	{
		theList = new ListNode(data, theList);
	}
	
	//this method will return the number of nodes present in list
	public int nodeCount()
	{
   	int count=0;
   	ListNode hotstepper = theList;
   	while(hotstepper != null){
   		count++;
   		hotstepper = hotstepper.getNext();
   		
   	}

	
   	return count;
	}
		
	//this method will create a new node with the same value as the first node and add this
	//new node at the front of the list.  Once finished, the first node will occur twice.
	public void doubleFirst()
	{
		theList = new ListNode(theList.getValue(), theList);

				
	}

	//this method will create a new node with the same value as the last node and add this
	//new node at the end.  Once finished, the last node will occur twice.
	public void doubleLast()
	{
		ListNode list = theList;
		while(list != null && list.getNext() != null){
			list = list.getNext();
		}
		ListNode add = new ListNode(list.getValue(), null);
		list.setNext(add);
		
	}
	
	//method skipEveryOther will remove every other node
	public void skipEveryOther()
	{
		while(theList != null && theList.getNext() != null){
			theList.setNext(theList.getNext().getNext());
			theList = theList.getNext();
		}


	}

	//this method will set the value of every xth node in the list
	public void setXthNode(int x, Comparable value)
	{
		ListNode current = theList;
		int count = 0;
		while(current != null){
			if(x == count){
				current.setValue(value);
				return;
			}
			else{
				count ++;
				current = current.getNext();
			}
			
		}


	
	}	

	//this method will remove every xth node in the list
	public void removeXthNode(int x)
	{
		ListNode current = theList;
		int count = 0;
		while(current != null && current.getNext() != null){
			if(x-1 == count){
				current.setNext(current.getNext().getNext());
				current = current.getNext();
				count++;
			}
			else{
				count ++;
				current = current.getNext();
			}
			
		}
	}		
	//this method will return a String containing the entire list
   public String toString()
   {
   	String output="";
   	ListNode current = theList;
   	output += "[";
   	while(current.getNext() != null){
   		output += current.getValue() + ",";
   		current = current.getNext();
   		
   	}
   	output += current.getValue() + "]";
   	

   	return output;
   }			
	
}