package sets;

import static java.lang.System.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class DupRunner
{
	public static void main( String args[] ) throws Exception
	{
		Scanner scan = new Scanner(new File("src/sets/dupes.txt")); 
		UniquesDupes dupadupdupe = new UniquesDupes();
		String s = "";
		while(scan.hasNext()){
			s = scan.nextLine();
			System.out.println("Original List : " + s);
			System.out.println("Uniques : " + dupadupdupe.getUniques(s));
			System.out.println("Dupes : " + dupadupdupe.getDupes(s));
			
		}
		
		
		
	}
}