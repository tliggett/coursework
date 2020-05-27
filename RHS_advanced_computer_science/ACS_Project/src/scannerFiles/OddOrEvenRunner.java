package scannerFiles;

import java.io.IOException;
import java.io.File;
import java.util.Scanner;
import static java.lang.System.*;

public class OddOrEvenRunner
{
	public static void main( String args[] ) throws IOException
	{
		Scanner file = new Scanner(new File("src/scannerFiles/oddoreven.dat"));
		
		int size = file.nextInt();
		file.nextLine();
		for(int i = 0; i < size; i++)
		{
		   int num = file.nextInt();
			OddOrEven suckyprogram = new OddOrEven(num);
			System.out.println(suckyprogram);
		}
	}
}