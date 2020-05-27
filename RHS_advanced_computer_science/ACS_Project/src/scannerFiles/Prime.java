package scannerFiles;
//� A+ Computer Science  -  www.apluscompsci.com
//Name -
//Date -
//Lab  -

import static java.lang.System.*;
import java.lang.Math;
import java.util.Scanner;

public class Prime
{
	private int number;

	//constructor methods go here
	public Prime(){
		number = 0;
	}
	public Prime(String num){
		Scanner scan = new Scanner(num);
		setPrime(scan.nextInt());
	}
	public void setPrime(int num)
	{
		number = num;
	}

	//boolean isPrime()   goes here
	public boolean isPrime(){
		
		for(int i = number-1; i>1; i--){
			if(number%i == 0){
				return false;
			}
			
		}
		return true;
		
		
	}






	public String toString()
	{
		
		if(isPrime()){
			return number + " is Prime";
		}
		return number + " is not Prime";
	}
}