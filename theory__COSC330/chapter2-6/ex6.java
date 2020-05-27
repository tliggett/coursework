//Example 2-6-6
//
//

import java.util.regex.Pattern;
import java.util.Scanner;

class ex6
{
	public static void main(String[] args)
	{
		Scanner keyboard = new Scanner(System.in);
		while(true)
		{
			String word = keyboard.nextLine();
			if(word.equals("done")) break;
			boolean b = Pattern.matches("a.a[a-z]*", word);
			if(b) System.out.println("accept");
			else System.out.println("reject");
		}
	}
}
