package sets;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import static java.lang.System.*;

public class OddRunner
{
	public static void main( String args[] ) throws IOException
	{
		Scanner scan = new Scanner(new File("src/sets/oddevent2.dat"));
		while(scan.hasNextLine()){
			OddEvenSets o = new OddEvenSets(scan.nextLine());
			System.out.println(o);
		}
		
		
				
	}
}