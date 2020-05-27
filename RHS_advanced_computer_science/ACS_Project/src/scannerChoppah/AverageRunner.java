package scannerChoppah;
//Name -
//Date -
//Class - 
//Lab  -

import java.util.Scanner;
import static java.lang.System.*;

import java.io.File;

public class AverageRunner
{
	  public static void main( String args[] ) throws Exception
	   {	
		   Scanner scan = new Scanner(new File ("src/scannerfiles/scannerfiles1.txt"));
		   while(scan.hasNextLine()){
		   Average lin = new Average(scan.nextLine());
		   System.out.println(lin + "\n");
		   }
		}
}