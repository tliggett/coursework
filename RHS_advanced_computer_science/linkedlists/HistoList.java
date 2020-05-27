package linkedlists;
//© A+ Computer Science  -  www.apluscompsci.com
//Name -
//Date -
//Class - 
//Lab  -

import java.util.*;
import static java.lang.System.*;

public class HistoList
{
   private HistoNode front;

	public HistoList( )
	{
		front = null;
	}

	//addLetter will add a new node to the front for let if let does not exist
	//addLetter will bump up the count if let already exists
	public void addLetter(char let)
	{
		HistoNode hotstepper = front;
		boolean nue = true;
		while(hotstepper != null){
			if(hotstepper.getLetter() == let){
				hotstepper.setLetterCount(hotstepper.getLetterCount() + 1);
				nue = false;
			}
			hotstepper = hotstepper.getNext();
		}
		if(nue)
			front = new HistoNode(let,1, front);






	}

	//returns the index pos of let in the list if let exists
	public int indexOf(char let)
	{
		HistoNode hotstepper = front;
		int count = 0;
		while(hotstepper != null){
			if(hotstepper.getLetter() == let){
				return count;
			}
			hotstepper = hotstepper.getNext();
			count++;
		}








		return -1;
	}

	//returns a reference to the node at spot
	private HistoNode nodeAt(int spot)
	{
		HistoNode current=front;
		for(int i = 0; i < spot; i++)
		current = current.getNext();
		
		







		return current;
	}

	//returns a string will all values from list
	public String toString()
	{
		String output = "";
		HistoNode hotstepper = front;
		while(hotstepper != null){
			output += hotstepper.getLetter() + "-" + hotstepper.getLetterCount() + "\t"; 
			hotstepper = hotstepper.getNext();
		}






		return output;
	}
}