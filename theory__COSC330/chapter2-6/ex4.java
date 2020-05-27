//Example 2-6-4

import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.Scanner;

class ex4
{
	public static void main(String[] args)
	{
		Scanner keyboard = new Scanner(System.in);
		Pattern p = Pattern.compile("a*b");
		while(true)
		{
			System.out.print("Enter a string that matches a*b, or enter done to quit:");
			String word = keyboard.nextLine();
			if(word.equals("done")) break;
			Matcher m = p.matcher(word);
			if(m.matches()) System.out.println("matched");
			else System.out.println("unmatched");
		}
	}
}
