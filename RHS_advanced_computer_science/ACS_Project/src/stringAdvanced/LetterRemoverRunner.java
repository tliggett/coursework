package stringAdvanced;
//(c) A+ Computer Science
//www.apluscompsci.com
//Name -

import static java.lang.System.*;

import java.util.Scanner;

public class LetterRemoverRunner
{
	public static void main( String args[] )
	{
Scanner scan = new Scanner(System.in);
		
		while(true){
			System.out.println("Enter your sentence :: ");
			String sent = scan.nextLine();
			System.out.println("Enter your character :: ");
			char rem = scan.nextLine().charAt(0);
			LetterRemover remover = new LetterRemover(sent, rem);
			System.out.println(remover + "\n");
		}			
	}
}