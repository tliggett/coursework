package blackjack;

//A DeckOfCards object represents a deck of ordinary playing cards.  The top card is dealt each 
//time the method deal is called.  A dealt card will not be reused until the DeckOfCards is shuffled.
import java.util.Random;
public class DeckOfCards
{

private int top;
private String[] rank;
private String[] suit;
	//Post:	This DeckOfCards initialized to a new deck; the cards are ordered from AceSpace to 
	//	KingDiamond as in a new deck; top initialized to 0.
	//
	public DeckOfCards()
	{
		top = 0;
		rank = new String[52];
		suit = new String[52];
		for(int j = 0; j < 52; j+= 13)
		{
			rank[j+0] = "Ace";
			rank[j+1] = "Two";
			rank[j+2] = "Three";
			rank[j+3] = "Four";
			rank[j+4] = "Five";
			rank[j+5] = "Six";
			rank[j+6] = "Seven";
			rank[j+7] = "Eight";
			rank[j+8] = "Nine";
			rank[j+9] = "Ten";
			rank[j+10] = "Jack";
			rank[j+11] = "Queen";
			rank[j+12] = "King";
		}
		for(int i = 0; i<13; i++)
			suit[i] = "Spade";
		for(int i = 0; i<13; i++)
			suit[i+13] = "Heart";
		for(int i = 0; i<13; i++)
			suit[i+26] = "Club";
		for(int i = 0; i<13; i++)
			suit[i+39] = "Diamond";
	}
	//Post:	This DeckOfCards thoroughly shuffled; top set to 0.
	public void shuffle()
	{
		for(int j = 0; j<10000; ++j)
		{
			Random r = new Random();
			int one = r.nextInt(52);
			int two = r.nextInt(52);
			String temp = rank[one];
			rank[one] = rank[two];
			rank[two] = temp;
			temp = suit[one];
			suit[one] = suit[two];
			suit[two] = temp;
		}
		top = 0;
	}

	//Post:	top incremented by 1.
	//Return:	The top card of this DeckOfCards as a String such as "AceHeart", "TwoSpade", 
	//	"TenDiamond", "KingClub".  If top >=52, "NoMoreCard" is returned.
	public String deal()
	{
		if(top >= 52)
			return "NoMoreCard";
		String s = rank[top] + suit[top];
		top++;
		return s;
	}
}
