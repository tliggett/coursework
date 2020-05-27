package iterator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;
import static java.lang.System.*;

public class IteratorReplacerRunner
{
	public static void main ( String[] args )
	{
		LinkedList<IteratorReplacer> removers = new LinkedList<IteratorReplacer>();
		
		IteratorReplacer one = new IteratorReplacer("a b c d c a b c d e","c", "@");
		removers.add(one);
		IteratorReplacer two = new IteratorReplacer("cow chicken pig horse cow pig iguana chicken","cow", "cake");
		removers.add(two);
		IteratorReplacer three = new IteratorReplacer("Clinton Bernie Trump Clinton Bernie Cruz Rubio Trump Clinton","Bernie","Clinton");
		removers.add(three);
		
		Iterator<IteratorReplacer> runner = removers.iterator();
		while(runner.hasNext()){
		IteratorReplacer run = runner.next();
		run.replace();
		System.out.println(run);
			
		}		
	}
}