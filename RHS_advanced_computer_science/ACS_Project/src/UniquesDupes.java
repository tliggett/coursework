package sets;

import java.util.Set;
import java.util.TreeSet;
import java.util.Arrays;
import java.util.Scanner;
import java.util.ArrayList;
import static java.lang.System.*;

public class UniquesDupes
{
	public static Set<String> getUniques(String input)
	{
		String[] spit = input.split(" ");
		Set<String> uniques = new TreeSet<String>();
		for(String $ : spit){
			uniques.add($);
		}
		

		return uniques;
	}

	public static Set<String> getDupes(String input)
	{
		String[] spit = input.split(" ");
		Set<String> uniques = new TreeSet<String>();
		Set<String> dupes = new TreeSet<String>();
		for(String $ : spit){
			if(!uniques.add($))
				dupes.add($);
		}
		

		return dupes;
	}
}