package scannerFiles;
//� A+ Computer Science  -  www.apluscompsci.com
//Name -
//Date -
//Class - 
//Lab  -

import static java.lang.System.*;

import java.util.Scanner;

public class Social
{
   private String socialNum;


	public Social(String soc)
	{
		setSocial(soc);
	}

	public void setSocial(String soc)
	{
		socialNum = soc;
	}

	public int getSum()
	{
		int sum = 0;
		Scanner gage = new Scanner(socialNum);
		gage.useDelimiter("-");
		while(gage.hasNextInt()){
			sum += gage.nextInt();
			
		}





		return sum;
	}

	public String toString()
	{
		return "SS# " + socialNum +  " has a sum of " + getSum();
	}
}