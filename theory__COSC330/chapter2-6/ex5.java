// Example 2-6-5

import java.util.regex.Pattern;
import java.util.Scanner;
class ex5
{
	public static void main(String[] args)
	{
		Scanner keyboard = new Scanner(System.in);
		while(true)
		{
			System.out.print("Type a lastname or enter done to quit:");
			String lastname = keyboard.nextLine();
			if(lastname.equals("done")) break;
			boolean b = Pattern.matches("[Jj][a-zA-z]*", lastname);
			if(b) System.out.println("accept");
			else System.out.println("reject");
		}
	}

}
