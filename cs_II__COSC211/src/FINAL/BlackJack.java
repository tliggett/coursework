package FINAL;


//TJ Liggett
//CS 211 Final Exam
//BlackJack.java
//Class BlackJack allows player to play BlackJack against a dealer

import javax.swing.*;							
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.util.Vector;

public class BlackJack  extends JFrame	implements ActionListener			
{
	
	private static DeckOfCards deck = new DeckOfCards();
	private static Vector<String> dealer;
	private static Vector<String> player;
	
	private static boolean dealerHidden;
	
	private JButton btnDeal = new JButton("Deal");					
	private JButton btnPlayer = new JButton("Player");
	private JButton btnDealer = new JButton("Dealer");					
	private JButton btnNew = new JButton("New");
	private JButton btnAuthor = new JButton("Author");
	private JPanel pnlButtons = new JPanel(new FlowLayout());	
	
	private JLabel[] lblPCard = new JLabel[8];
	private JLabel[] lblDCard = new JLabel[8];
	private JPanel pnlCards = new JPanel(new GridLayout(2, 8));

	public static void main(String[] args) 
	{
		BlackJack f=new BlackJack();					
		f.setTitle("BlackJack");			
		f.setSize(750, 300);						
		f.setLocationRelativeTo(null);					
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
		f.setVisible(true);		
		

	}
	
	
	public BlackJack()							
	{
		pnlButtonsAddControls();
		pnlCardsAddCards();
		
		player = new Vector<String>();
		dealer = new Vector<String>();
		dealerHidden = true;
		add(pnlButtons, BorderLayout.SOUTH);
		add(pnlCards, BorderLayout.CENTER);
		
	}									
	private void pnlButtonsAddControls()							
	{
		btnDeal.addActionListener(this);
		btnPlayer.addActionListener(this);
		btnDealer.addActionListener(this);
		btnNew.addActionListener(this);
		btnAuthor.addActionListener(this);
		pnlButtons.add(btnDeal);
		pnlButtons.add(btnPlayer);
		pnlButtons.add(btnDealer);
		pnlButtons.add(btnNew);
		pnlButtons.add(btnAuthor);
		btnPlayer.setEnabled(false);
		btnDealer.setEnabled(false);
		btnNew.setEnabled(false);
		
	}
	
	private void pnlCardsAddCards()
	{
		for(int i = 0; i < lblPCard.length; i++)
		{
			lblPCard[i] = new JLabel("Player");
			pnlCards.add(lblPCard[i]);
		}
		
		for(int i = 0; i < lblDCard.length; i++)
		{
			lblDCard[i] = new JLabel("Dealer");
			pnlCards.add(lblDCard[i]);
		}
		
		
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnDeal)
		{
			dealerHidden = true;
			deck.shuffle();
			player.add(deck.deal());
			player.add(deck.deal());
			dealer.add(deck.deal());
			dealer.add(deck.deal());
			btnDeal.setEnabled(false);
			btnPlayer.setEnabled(true);
		}
		
		if(e.getSource() == btnPlayer)
		{
			while(true)
			{
				String s = "";
				while(!(s.equalsIgnoreCase("h") || s.equalsIgnoreCase("s")))
				{
					s = JOptionPane.showInputDialog(null, "You have " + this.total(player) + ". Hit or stay? h/s");
					if(s == null) return;
				}
				if(s.equalsIgnoreCase("h"))
				{
					player.add(deck.deal());
					updateCardDisplay();
					if(this.total(player) > 21)
					{
						JOptionPane.showMessageDialog(null, "You busted.");
						btnPlayer.setEnabled(false);
						btnDealer.setEnabled(false);
						btnNew.setEnabled(true);
						return;
					}
				}
				else break;
			}
			
			btnPlayer.setEnabled(false);
			btnDealer.setEnabled(true);
			
			
		}
		
		if(e.getSource() == btnDealer)
		{
			dealerHidden = false;
			while(this.total(dealer) < 17)
				dealer.add(deck.deal());
			updateCardDisplay();
			
			if(this.total(dealer) > 21) 
				JOptionPane.showMessageDialog(null, "Dealer Busted! You win!!! Congratulations!");
			else if(this.total(dealer) > this.total(player))
				JOptionPane.showMessageDialog(null, "You lose! You are a terrible blackjack player!");
			else if(this.total(dealer) == this.total(player))
				JOptionPane.showMessageDialog(null, "Push! The game is a tie!");
			else JOptionPane.showMessageDialog(null, "You win!!! Congratulations!!!");
			
			btnDealer.setEnabled(false);
			btnNew.setEnabled(true);
		
		}
		
		if(e.getSource() == btnNew)
		{
			
			player = new Vector<String>();
			dealer = new Vector<String>();
			deck = new DeckOfCards();
			for(JLabel label : lblPCard)
			{
				label.setIcon(null);
				label.setText("Player");
			}
			for(JLabel label : lblDCard)
			{
				label.setIcon(null);
				label.setText("Dealer");
			}
			btnNew.setEnabled(false);
			btnDeal.setEnabled(true);
		}
		
		if(e.getSource() == btnAuthor)
		{
			btnAuthor.setText("TJ Liggett");
		}
		
		updateCardDisplay();
		
	}
	
	public void updateCardDisplay()
	{
		for(int i = 0; i < player.size(); i++)
		{
			lblPCard[i].setIcon(new ImageIcon("res/cardImages/" + player.get(i) + ".gif"));
			lblPCard[i].setText("");
		}
		
		for(int i = 0; i < dealer.size(); i++)
		{
			lblDCard[i].setIcon(new ImageIcon("res/cardImages/" + dealer.get(i) + ".gif"));
			lblDCard[i].setText("");
		}
		
		if(dealerHidden && lblDCard[1].getIcon() != null) 
			lblDCard[1].setIcon(new ImageIcon("res/cardImages/card.gif"));
		
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







//A DeckOfCards object represents a deck of ordinary playing cards.  The top card is dealt each 
//time the method deal is called.  A dealt card will not be reused until the DeckOfCards is shuffled.

class DeckOfCards
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
