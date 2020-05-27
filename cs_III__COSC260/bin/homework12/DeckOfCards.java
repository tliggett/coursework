//TJ Liggett
//Homework 12
//12_2_2018
//DeckOfCards.java

/*
	A DeckOfCards object represents a deck of ordinary playing cards.  The top card
	is dealt each time the method deal is called. A dealt card will not be reused
	until the DeckOfCards is shuffled.
 */

import java.util.Random;
import java.util.Scanner;

public class DeckOfCards
{
	enum Suit {DIAMOND, CLUB, HEART, SPADE};
	enum Rank {ACE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK,
				QUEEN, KING}
	private int top;
	private Rank[] rank;
	private Suit[] suit;
	//Post:	This DeckOfCards initialized to a new deck; the cards are ordered from
	//	AceSpade to KingDiamond as in a new deck; top initialized to 0.
	public DeckOfCards()
	{
		rank = new Rank[52];
		suit = new Suit[52];
		for(int j = 0; j < 52; j+= 13)
		{
			rank[j+0] = Rank.ACE;
			rank[j+1] = Rank.TWO;
			rank[j+2] = Rank.THREE;
			rank[j+3] = Rank.FOUR;
			rank[j+4] = Rank.FIVE;
			rank[j+5] = Rank.SIX;
			rank[j+6] = Rank.SEVEN;
			rank[j+7] = Rank.EIGHT;
			rank[j+8] = Rank.NINE;
			rank[j+9] = Rank.TEN;
			rank[j+10] = Rank.JACK;
			rank[j+11] = Rank.QUEEN;
			rank[j+12] = Rank.KING;
		}
		for(int i = 0; i<13; i++) suit[i] = Suit.SPADE;
		for(int i = 0; i<13; i++) suit[i+13] = Suit.HEART;
		for(int i = 0; i<13; i++) suit[i+26] = Suit.CLUB;
		for(int i = 0; i<13; i++) suit[i+39] = Suit.DIAMOND;
		top = 0;
	}
	//Post:	This DeckOfCards thoroughly shuffled; top set to 0.
	public void shuffle()
	{
		for(int j = 0; j<10000; ++j)
		{
			Random r = new Random();
			int one = r.nextInt(52);
			int two = r.nextInt(52);
			Rank tempR = rank[one];
			rank[one] = rank[two];
			rank[two] = tempR;
			Suit tempS = suit[one];
			suit[one] = suit[two];
			suit[two] = tempS;
		}
		top = 0;
	}

	//Post:	top incremented by 1.
	//Return: The top card of this DeckOfCards as a String such as "AceHeart",
	// 		  "TwoSpade", "TenDiamond", "KingClub".  If top >=52, "NoMoreCard"
	// 		  is returned.
	public String deal()
	{
		if(top >= 52) return "NoMoreCard";
		String s = "";
		switch(rank[top])
		{
			case ACE: s += "Ace"; break;
			case TWO: s += "Two"; break;
			case THREE: s += "Three"; break;
			case FOUR: s += "Four";	break;
			case FIVE: s += "Five";	break;
			case SIX: s += "Six"; break;
			case SEVEN: s += "Seven"; break;
			case EIGHT: s += "Eight"; break;
			case NINE: s += "Nine";	break;
			case TEN: s += "Ten"; break;
			case JACK: s += "Jack";	break;
			case QUEEN: s += "Queen"; break;
			case KING: s += "King";	break;
		}
		switch(suit[top])
		{
			case SPADE: s += "Spade"; break;
			case HEART: s += "Heart"; break;
			case CLUB: s += "Club"; break;
			case DIAMOND: s += "Diamond"; break;
		}
		top++;
		return s;
	}
}