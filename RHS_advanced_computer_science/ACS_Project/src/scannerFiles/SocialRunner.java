package scannerFiles;


import java.io.IOException;
import java.io.File;
import java.util.Scanner;
import static java.lang.System.*;

public class SocialRunner
{
	public static void main( String args[] ) throws IOException
	{
		Scanner file = new Scanner(new File("src/scannerFiles/social.dat"));
		int size = file.nextInt();
		file.nextLine();
		for(int i=0; i<size; i++)
		{
			Social butterfly = new Social(file.nextLine());
			System.out.println(butterfly);
		}
	}
}