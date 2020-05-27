//TJ Liggett
//Homework 7
//OneCard.java
//Class OneCard reads user inputs for game OneCard 

import java.util.Scanner;
//import myJavaLib.util.DeckOfCards;

//Desc:	Play the game of OneCard.  The game has 2 players, the user and the computer.  
//	The user is dealt 1 card, the computer is dealt 1 card.  The user has the option to 
//	change the card once.  The winner is the player with the higher rank card 
//	(Ace>King>Queen>�>3>2).  The suit is not relevant.
	
public class OneCard
{
	private DeckOfCards deck;
	
	//Post: Initializes DeckOfCards deck and shuffles it.
	public OneCard()
	{
		deck = new DeckOfCards();
		deck.shuffle();
				
	}

	//Input: The user enters a character (Y/N) signaling whether the user wants to change the card.
	//Output:The user's card, followed by a message asking the user if he wants to change the card, 
	//	followed optionally the user's new card, followed by the computer's card, followed by the 
	//	result of the game
	public void play()
	{
		Scanner scan = new Scanner(System.in);	
		String userCard = deck.deal();
		System.out.println("Your card: " + userCard);
		char c = ' ';
		while(c != 'y' && c != 'n')
		{
			System.out.println("Change Card? <y/n>: ");
			c = scan.nextLine().toLowerCase().charAt(0);
		}
		if(c == 'y') 
		{
			userCard = deck.deal();
			System.out.println("Your card: " + userCard);
		}
		String comCard = deck.deal();
		System.out.println("Dealer's card: " + comCard);				
		declareWinner(findRank(userCard), findRank(comCard));
	}
	
	//Return: Numerical value of card described in String s
	//Note: Aces are worth 1, Jacks are worth 11, Queens are worth 12, Kings are worth 13
	public int findRank(String s)
	{
		if(s.contains("Ace")) return 1;
		if(s.contains("Two")) return 2;
		if(s.contains("Three")) return 3;
		if(s.contains("Four")) return 4;
		if(s.contains("Five")) return 5;
		if(s.contains("Six")) return 6;
		if(s.contains("Seven")) return 7;
		if(s.contains("Eight")) return 8;
		if(s.contains("Nine")) return 9;
		if(s.contains("Ten")) return 10;
		if(s.contains("Jack")) return 11;
		if(s.contains("Queen")) return 12;
		if(s.contains("King")) return 13;		
		else return 0;
	}

	//Output: Result of the game; whether the user won, lost, or tied.
	public void declareWinner(int user, int computer)
	{
		if(user > computer) System.out.println("You win!");
		else if(user == computer) System.out.println("It's a tie!");
		else System.out.println("You lose!");						
	}

}