package scannerFiles;
//Name -
//Date -
//Class - 
//Lab  -

import java.util.Scanner;
import static java.lang.System.*;

public class Average
{
   private String line;

   //write two constructors

	//write a setLine method

   public Average(){
	   setLine("");
	   
   }
   public Average(String l){
	   setLine(l);
   }
   public void setLine(String l){
	   line = l;
   }

	private int getLowest()
	{
		int lowest=Integer.MAX_VALUE;
		Scanner gage = new Scanner(line);
		while(gage.hasNextInt()){
			int i = gage.nextInt();
			if(i<lowest){
				lowest = i;
			}
		}


		return lowest;
	}

	public double getAverage()
	{
		double average=0.0;
		double i = 0.0;
		
		Scanner gage = new Scanner(line);
		while(gage.hasNextInt()){
			average += gage.nextInt();
			i++;
			}
		average -= getLowest();
		i-=1;
		average = average/i;
		
		average *= 100;
		average = Math.round(average);
		average/= 100;
		return average;
	}

	public String getLine(){
		return line;
	}

	public String toString(){
		return line + "\n" + "average = " + getAverage();
	}


	//write a toString method




}