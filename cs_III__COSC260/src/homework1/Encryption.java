package homework1;
//TJ Liggett
//Homework 1
//Class Encryption can encrypt and decrypt user files. The encrypted and decrypted outputs
//are written to "encrypted.txt" and "decrypted.txt", respectively
//9/6/2018


import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Encryption {
	
	
	public static void main(String[] args) throws FileNotFoundException 
   	{	
		
		Scanner keyboard=new Scanner(System.in);
		char choice='1';
		System.out.println("1. Encrypt a file");
		System.out.println("2. Decrypt a file");
		choice=keyboard.nextLine().charAt(0);
		switch (choice)
		{
			case '1': encrypt(); 
			break;
			case '2': decrypt(); 
			break;
		}	
   	}					
	
	//Desc : 	Encrypts a file.
	//Input: 	The user supplies the name of a disk file via the keyboard and the file must exist.
	//Output: 	The text in the specified file encrypted and written to "encrypted.txt".  
	public static void encrypt() throws FileNotFoundException
	{
		Scanner keyboard=new Scanner(System.in);
		System.out.print("Enter file name: ");
		String s=keyboard.nextLine();
		Scanner input = new Scanner(new File(s));
		input.useDelimiter("");						//line 14
		PrintWriter f = new PrintWriter("C:\\Users\\trevo\\workspace\\CS 260\\res\\encrypted.txt");
		while (input.hasNext())
		{
			char ch = input.next().charAt(0);
			ch=convert(ch);
			f.print(ch);
		}
	   	input.close();
	   	f.close();
	}
	
	
	//Desc : 	Convert a character to another secret character.
	//Return:	The secret character. Letters are converted to their
	//		    successor except z and Z, which are converted to 'a' 
	//			and 'A' respectively. Digits are converted to their 
	//			predecessor, except 0 which is converted to 9. All 
	//			other characters remain the same.
	public static char convert (char ch)
	{
		char enc = ch;
		if(Character.isLetter(ch))
		{
			if(ch == 'z' || ch == 'Z')
			{
				enc = (char)(ch-25);
			}
			else
			{
				enc = (char)(ch+1);
			}
			
		}
		if(Character.isDigit(ch))
		{
			if(ch == '0')
			{
				enc = '9';
			}
			else
			{
				enc = (char)(ch-1);
			}
		}
		return enc;
	}

	
	

	//Desc : 	Decrypts a file.
	//Input: 	The user supplies the name of a disk file via the keyboard and the file must exist.
	//Output: 	The text in the specified file decrypted and written to "decrypted.txt".  
	//Throw: 	FileNotFoundException if the input source file does not exist, or decrypted.txt
	//			cannot be created
	public static void decrypt() throws FileNotFoundException
	{
		Scanner keyboard=new Scanner(System.in);
		char ch='a';
		System.out.print("Enter file name: ");
		String s=keyboard.nextLine();
		Scanner input = new Scanner(new File(s));
		input.useDelimiter("");		
		PrintWriter f = new PrintWriter("decrypted.txt");
		while (input.hasNext())
		{
			ch = input.next().charAt(0);
			ch=inverseConvert(ch);
			f.print(ch);
		}
	   	input.close();
	   	f.close();
	}
	
	//Desc : 	Convert a character to another character.
	//Return:	The original character. Letters are converted to their
	//		    predecessor except a and A, which are converted to 'z' 
	//			and 'Z' respectively. Digits are converted to their 
	//			successor, except 9 which is converted to 0. All 
	//			other characters remain the same.
	public static char inverseConvert(char ch)
	{
		char enc = ch;
		if(Character.isLetter(ch))
		{
			if(ch == 'a' || ch == 'A')
			{
				enc = (char)(ch+25);
			}
			else
			{
				enc = (char)(ch-1);
			}
			
		}
		if(Character.isDigit(ch))
		{
			if(ch == '9')
			{
				enc = '0';
			}
			else
			{
				enc = (char)(ch+1);
			}
		}
		return enc;
	}


}
