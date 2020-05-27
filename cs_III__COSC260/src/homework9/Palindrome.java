package homework9;

//TJ Liggett
//Homework 9
//Palindrome.java
//Class palindrome takes user inputed string and determines whether it is a palindrome

import java.util.Scanner;

public class Palindrome {
	
	
	public static void main(String[] args)
	{
		Scanner input = new Scanner(System.in);
		System.out.print("Enter a String: ");
		String s = input.nextLine();
		System.out.println(isPalindrome(s, 0, s.length()));
		input.close();
	}
	
	
	//Desc:	Determines if a string is a palindrome.  A palindrome is a string (for this assignment,
	//		 consists of lowercase letters only) that reads the same forward and backward
	//Pre:	s consists of lowercase letters only.  
	//Return:	true if the string s in index [start, last) is a palindrome.
	public static boolean isPalindrome(String s, int start, int last)
	{
		if(s.charAt(start) != s.charAt(last-1)) return false;
		if(start >= last) return true;
		return isPalindrome(s, start+1, last-1);
	}
	
}
