package scannerChoppah;
//Name -
//Date -
//Class - 
//Lab  -

import java.util.Scanner;
import static java.lang.System.*;

public class LineBreaker
{
   private String line;
   private int breaker;

   public LineBreaker()
   {
   	this("",0);
   }

   public LineBreaker(String s, int b)
   {
	   setLineBreaker(s,b);
   }

	public void setLineBreaker(String s, int b)
	{
		line = s;
		breaker = b;
	}

	public String getLine()
	{
		return line;
	}

	public String getLineBreaker()
	{
		String box ="";
		Scanner scan = new Scanner(line);
		while(scan.hasNext()){
			box+="\n";
			for(int i = 0; i < breaker; i++){
				if(scan.hasNext()){
					box+= scan.next();
				}
				
			}
			
		}
		







		return box;
	}

	public String toString()
	{
		return getLine() + getLineBreaker();
	}
}