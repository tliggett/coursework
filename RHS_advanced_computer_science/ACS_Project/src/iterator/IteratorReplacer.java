package iterator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.ListIterator;
import static java.lang.System.*;

public class IteratorReplacer
{
	private ArrayList<String> list;
	private String toRemove, replaceWith;

	public IteratorReplacer(String line, String rem, String rep)
	{
		toRemove = rem;
		replaceWith = rep;
		list = new ArrayList<String>();
		String[]spt = line.split(" ");
		for(String s : spt){
			list.add(s);
		}
	}


	public void replace()
	{
		ListIterator<String> iter = list.listIterator();
		while(iter.hasNext()){
			if(iter.next().equals(toRemove))
				iter.remove();
				iter.add(replaceWith);
		}
	}

	public String toString()
	{
		return list.toString()+"\n\n";
	}
}