package sets;

import java.util.Set;
import java.util.TreeSet;
import java.util.Arrays;
import java.util.Scanner;

import static java.lang.System.*;

public class MathSet
{
	private Set<Integer> one;
	private Set<Integer> two;

	public MathSet()
	{ 
		one = null;
		two = null;
	}

	public MathSet(String o, String t)
	{
		one = new TreeSet<Integer>();
		two = new TreeSet<Integer>();
		
		Scanner scan = new Scanner(o);
		while(scan.hasNextInt()){
			one.add(scan.nextInt());
		}
		scan = new Scanner(t);
		while(scan.hasNextInt()){
			two.add(scan.nextInt());
		}
		
	}

	public Set<Integer> union()
	{
		Set<Integer> union = new TreeSet<Integer>();
		union.addAll(one);
		union.addAll(two);
		return union;
	}

	public Set<Integer> intersection()
	{
		Set<Integer> inter = new TreeSet<Integer>();
		for(Integer i : two){
			if(one.contains(i)){
				inter.add(i);
			}
		}
		
		
		
		return inter;
	}

	public Set<Integer> differenceAMinusB()
	{
		Set<Integer> dif = new TreeSet<Integer>();
		for(Integer i : one){
			if(!two.contains(i))
				dif.add(i);
		}
		return dif;
	}

	public Set<Integer> differenceBMinusA()
	{
		Set<Integer> dif = new TreeSet<Integer>();
		for(Integer i : two){
			if(!one.contains(i))
				dif.add(i);
		}
		return dif;
	}
	
	public Set<Integer> symmetricDifference()
	{		
		Set<Integer> dif = new TreeSet<Integer>();
		for(Integer i : two){
			if(!one.contains(i))
				dif.add(i);
		}
		for(Integer i : one){
			if(!two.contains(i))
				dif.add(i);
		}
		
		return dif;
	}	
	
	public String toString()
	{
		return "Set one " + one + "\n" +	"Set two " + two +  "\n";
	}
}