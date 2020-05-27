package scannerFiles;
//� A+ Computer Science  -  www.apluscompsci.com
//Name -
//Date -
//Class - 
//Lab  -

import java.io.IOException;
import java.io.File;
import java.util.Scanner;
import static java.lang.System.*; 

public class TheLineRunner
{
	public static void main( String args[] ) throws IOException
	{
		Scanner file = new Scanner(new File("src/scannerFiles/line.dat"));
		int size = file.nextInt();//2
		file.nextLine();
		for(int i=0; i<size; i++)
		{
			TheLine lin = new TheLine(file.nextLine());
			System.out.println(lin);
		}
	}
}