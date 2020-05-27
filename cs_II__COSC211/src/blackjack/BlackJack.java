package blackjack;

import java.util.Scanner;
import java.util.Vector;

public class BlackJack
{
		private DeckOfCards deck;
		private Vector<String> dealer;
		private Vector<String> player;
		//Post: 	The BlackJack object properly initialized.  In particular, the deck is shuffled, the 
		//	dealer's hand is initialized, the player's hand is initialized, 2 cards were dealt to 
		//	the player, 2 cards were dealt to the dealer.
		public BlackJack ()
		{	
				deck = new DeckOfCards();
				deck.shuffle();
				player = new Vector<String>();
				player.add(deck.deal());
				player.add(deck.deal());
				dealer = new Vector<String>();
				dealer.add(deck.deal());
				dealer.add(deck.deal());
		}
		//Desc: 	Plays 1 game of BlackJack.
		//Input: 	'H' or 'S' from the user to indicate whether the user wants to hit or stay
		//Output: Various messages indicating the progress of the game
		public void play() 
		{
				Scanner scan = new Scanner(System.in);
				this.displayPlayer();
				this.displayDealer(true);
				boolean playing = true;
				do
				{
					System.out.println("You have " + this.total(player) + ". Hit or Stay H/S: ");
					String s = scan.nextLine();
					if(s.equals("H"))
					{
						player.add(deck.deal());
					}
					else playing = false;
					this.displayPlayer();
					if(this.total(player) > 21)
					{
					System.out.println("You busted. Bye bye.");
					return;
					}
				}while(playing);
				while(this.total(dealer) < 17)
					dealer.add(deck.deal());
				this.displayDealer(false);
				if(total(dealer) > 21) System.out.println("Dealer busted. You win!");
				else System.out.print("Dealer has " + total(dealer) + ".");
				if(total(dealer) > total(player)) System.out.println("You lose");
				else if(total(dealer) == total(player)) System.out.println("You tied.");
				else System.out.println("You win!!!");
			}
		//Output: Player's hand displayed on the screen
		private void displayPlayer()
		{
			System.out.println("Your hand: ");
			for(String s : player)
				System.out.println("\t" + s);
		}
		//Output: Dealer's hand displayed on the screen with 1 card hidden if first is true; all 
		//	cards of the dealer's hand displayed on the screen if first is false
		private void displayDealer(boolean first)
		{
			System.out.println("Dealer's hand: ");
			if(first)
			{
				System.out.println("\t" + dealer.firstElement());
				System.out.println("\t*******");
			}
			else
			{
				for(String s : dealer)
					System.out.println("\t" + s);
			}
		}
		//Pre: v contains playing cards
		//Return: The numeric total of all the cards in v
		private int total(Vector<String> v)
		{
			int sum = 0;
			for(String s : v)
			{
				if(s.contains("Ace")) sum += 11;
				else if(s.contains("Two")) sum += 2;
				else if(s.contains("Three")) sum+= 3;
				else if(s.contains("Four")) sum += 4;
				else if(s.contains("Five")) sum+= 5;
				else if(s.contains("Six")) sum += 6;
				else if(s.contains("Seven")) sum+= 7;
				else if(s.contains("Eight")) sum += 8;
				else if(s.contains("Nine")) sum+= 9;
				else sum += 10;
			}
			return sum;
		}
}

