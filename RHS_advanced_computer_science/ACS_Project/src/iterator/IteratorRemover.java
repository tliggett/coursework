package iterator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import static java.lang.System.*;

public class IteratorRemover
{
	private ArrayList<String> list;
	private String toRemove;

	public IteratorRemover(String line, String rem)
	{
		list = new ArrayList<String>();
		toRemove = rem;
		String[]spt = line.split(" ");
		for(String s : spt){
			list.add(s);
		}
		
	}


	public void remove()
	{
		Iterator<String> iter = list.iterator();
		while(iter.hasNext()){
			if(iter.next().equals(toRemove))
				iter.remove();
		}
	}

	public String toString()
	{
		return "" + list;
	}
}