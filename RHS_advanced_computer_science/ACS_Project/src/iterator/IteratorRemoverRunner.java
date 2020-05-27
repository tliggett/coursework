package iterator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;

import static java.lang.System.*;

public class IteratorRemoverRunner
{
	public static void main ( String[] args )
	{
		LinkedList<IteratorRemover> removers = new LinkedList<IteratorRemover>();
		
		IteratorRemover one = new IteratorRemover("a b c d c a b c d e","c");
		removers.add(one);
		IteratorRemover two = new IteratorRemover("cow chicken pig horse cow pig iguana chicken","cow");
		removers.add(two);
		IteratorRemover three = new IteratorRemover("Clinton Bernie Trump Clinton Bernie Cruz Rubio Trump Clinton","Clinton");
		removers.add(three);
		
		Iterator<IteratorRemover> runner = removers.iterator();
		while(runner.hasNext()){
		IteratorRemover run = runner.next();
		run.remove();
		System.out.println(run);
			
		}
		}
		
		
	}