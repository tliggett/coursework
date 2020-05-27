package scannerFiles;
//� A+ Computer Science  -  www.apluscompsci.com
//Name -
//Date -
//Class - 
//Lab  -

import java.util.Scanner;
import static java.lang.System.*;
import java.io.IOException;
import java.io.File;

public class AverageRunner
{
	public static void main( String args[] ) throws IOException
	{
		Scanner file = new Scanner(new File("src/scannerFiles/average.dat"));
		int size = file.nextInt();//2
		file.nextLine();
		for(int i=0; i<size; i++)
		{
			Average lin = new Average(file.nextLine());
			System.out.println(lin);
			System.out.println();
		}
	}
}