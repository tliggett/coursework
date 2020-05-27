package hashbrown;
//© A+ Computer Science  -  www.apluscompsci.com
//Name -  
//Date -
//Class - 
//Lab  -

import java.util.LinkedList;
import java.util.Scanner;
import java.io.File;
import static java.lang.System.*;

public class HashTableRunner
{
  public static void main ( String[] args )
  {
		try{
			HashTable hashbrown = new HashTable();		
			Scanner scan = new Scanner(new File("src/hashbrown/numbers.dat"));
			//read from the file			
			while(scan.hasNextLine())
				hashbrown.add(new Number(scan.nextInt()));
			//load stuff into the table			
			out.println(hashbrown);
			//print out the table
		}
		catch(Exception e)
		{
			System.out.println("Houston, we have a problem!");
		}
  }
}