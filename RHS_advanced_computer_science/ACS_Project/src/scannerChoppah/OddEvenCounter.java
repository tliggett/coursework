package scannerChoppah;
//� A+ Computer Science  -  www.apluscompsci.com
//Name -
//Date -
//Class - 
//Lab  -

import java.util.ArrayList;
import java.util.Scanner;
import static java.lang.System.*;

import java.io.File;
import java.io.FileNotFoundException;

public class OddEvenCounter
{
	private String line;
	private Scanner scan;
	private ArrayList<ArrayList<Integer>> list = new ArrayList<ArrayList<Integer>>();
	

   public OddEvenCounter(String filename) throws Exception
   {
	   line = filename;
	  
	 
			
		}
   



	public int getEvenCount()
	{
		
		int count= 0;
		scan = new Scanner(line);
		while(scan.hasNextInt()){
			int i = scan.nextInt();
			if(i%2 == 0){
				count ++;
			}
		}
		return count;
	}

	public int getOddCount()
	{
		int count= 0;
		scan = new Scanner(line);
		while(scan.hasNextInt()){
			int i = scan.nextInt();
			if(i%2 != 0){
				count ++;
			}
		}
		return count;
	}

	public String toString( )
	{
		return "" + line + "\nOdd Count :: " + getOddCount() + "\nEven Count :: " + getEvenCount();
	}
}