package stringAdvanced;


import static java.lang.System.*;

import java.util.Scanner;

public class StringRemoverRunner
{
	public static void main( String args[] )
	{
		Scanner scan = new Scanner(System.in);
		
		while(true){
			System.out.println("Enter your sentence :: ");
			String sent = scan.nextLine();
			System.out.println("Enter your expression :: ");
			String rem = scan.nextLine();
			StringRemover remover = new StringRemover(sent, rem);
			System.out.println(remover + "\n");
		}
		
		
											
	}
}