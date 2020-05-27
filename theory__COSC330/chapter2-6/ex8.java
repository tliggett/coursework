//Example 2-6-8
//

import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.Scanner;

class ex8
{
	public static void main(String[] args)
	{
		Scanner keyboard = new Scanner(System.in);
		String line, name;
		Pattern p = Pattern.compile("[Jj][a-zA-Z]*");
		while(true)
		{
			System.out.println("Enter a line or enter done to quit.");
			line = keyboard.nextLine();
			if(line.equals("done")) break;
			Matcher m = p.matcher(line);
			System.out.println("Words start with J or j:");
			while(m.find())
				System.out.println("\t" + line.substring(m.start(), m.end()));
		}
	}
}

