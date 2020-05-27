package scannerFiles;


import java.io.IOException;
import java.io.File;
import java.util.Scanner;
import static java.lang.System.*;

public class BoxRunner
{
	public static void main( String args[] ) throws IOException
	{
	Scanner file = new Scanner(new File("src/scannerFiles/box.dat"));
	int size = file.nextInt();//2
	file.nextLine();
	for(int i=0; i<size; i++)
	{
		//
		Box lin = new Box(file.nextLine());
		System.out.println(lin);
	}
	}
}