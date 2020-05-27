package scannerChoppah;


import static java.lang.System.*;

import java.io.File;
import java.util.Scanner;

public class LineTotallerRunner
{
	   public static void main( String args[] ) throws Exception
	   {	
		   Scanner scan = new Scanner(new File ("src/scannerfiles/scannerfiles1.txt"));
		   while(scan.hasNextLine()){
		   LineTotaller lin = new LineTotaller(scan.nextLine());
		   System.out.println(lin + "\n");
		   }
		}
}