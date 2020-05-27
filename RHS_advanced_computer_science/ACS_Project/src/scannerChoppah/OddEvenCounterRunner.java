package scannerChoppah;
//� A+ Computer Science  -  www.apluscompsci.com
//Name -
//Date -
//Class - 
//Lab  -

import java.util.Scanner;
import static java.lang.System.*;

import java.io.File;
import java.io.FileNotFoundException;

public class OddEvenCounterRunner
{
   public static void main(String args[]) throws Exception
   {
	   Scanner scan = new Scanner(new File ("src/scannerfiles/scannerfiles1.txt"));
	   while(scan.hasNextLine()){
	   OddEvenCounter lin = new OddEvenCounter(scan.nextLine());
	   System.out.println(lin + "\n");
	}
   }
}