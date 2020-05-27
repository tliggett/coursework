package sets;
import java.util.Set;
import java.util.TreeSet;
import java.util.Arrays;
import java.util.Scanner;
import static java.lang.System.*;

public class OddEvenSets
{
	private Set<Integer> odds;
	private Set<Integer> evens;

	public OddEvenSets()
	{
		odds = null;
		evens = null;
	}

	public OddEvenSets(String line)
	{
		Scanner scan = new Scanner(line);
		odds = new TreeSet<Integer>();
		evens = new TreeSet<Integer>();
		while(scan.hasNextInt()){
			int i = scan.nextInt();
			if(i %2 == 0){
				evens.add(i);
			}else{
				odds.add(i);
			}
			
		}
	}

	public String toString()
	{
		return "ODDS : " + odds + "\nEVENS : " + evens + "\n\n";
	}
}