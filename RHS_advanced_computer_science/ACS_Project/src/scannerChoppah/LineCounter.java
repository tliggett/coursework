package scannerChoppah;

//� A+ Computer Science  -  www.apluscompsci.com
//Name -
//Date -
//Class - 
//Lab  -

import java.util.Scanner;
import static java.lang.System.*;

public class LineCounter
{
   private String line;

   public LineCounter()
   {
		setLine("");
   }

   public LineCounter(String s)
   {
	   setLine(s);
   }

	public void setLine(String s)
	{
		line = s;
	}

	public int getCount()
	{	
		String[] nums = line.split(" ");
		
		return nums.length;
	}

	public String getLine()
	{
		return line;
	}

	public String toString()
	{
		return getLine() + "\nCount: " + getCount();
		
	}
}