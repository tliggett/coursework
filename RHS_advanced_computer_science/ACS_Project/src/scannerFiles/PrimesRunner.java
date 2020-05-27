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
import java.lang.Math;

public class PrimesRunner
{
	public static void main( String args[] ) throws IOException
	{
		Scanner file = new Scanner(new File("src/scannerFiles/primes.dat"));
		int size = file.nextInt();//2
		file.nextLine();
		for(int i=0; i<size; i++)
		{
			Prime lin = new Prime(file.nextLine());
			System.out.println(lin);
			System.out.println();
		}
	}
}