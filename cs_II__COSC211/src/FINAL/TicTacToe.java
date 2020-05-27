package FINAL;

//TJ Liggett
//CS 211 Final Exam
//TicTacToe.java
//Class TicTacToe allows 2 user players to play the game of TicTacToe 

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TicTacToe extends JFrame implements ActionListener
{
	
	private JButton[] btnSquares = new JButton[9];
	private JPanel pnlSquares = new JPanel();
	private JLabel lblStatus = new JLabel("Welcome to TJ's TicTacToe");
	private JPanel pnlNorth = new JPanel();
	private boolean xTurn, playing;
	private int turns;
	
	public TicTacToe()
	{
		xTurn = true;
		playing = true;
		turns = 0;
		setLayout(new BorderLayout());
		addPanelNorth();
		addSquares();
		registerListeners();
		add(pnlSquares, BorderLayout.CENTER);
		setTitle("TicTacToe");
		setSize(450,450);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE );
		setVisible(true);
	}
	
	public void addSquares()
	{
		pnlSquares.setLayout(new GridLayout(3,3));
		for(int i = 0; i< btnSquares.length; i++)
		{
			btnSquares[i] = new JButton();
			btnSquares[i].setFont(new Font("Serif", Font.BOLD, 20));
			pnlSquares.add(btnSquares[i]);
		}
	}

	public void registerListeners()
	{
		for(JButton button : btnSquares)
			button.addActionListener(this);
	}
	
	public void addPanelNorth()
	{
		pnlNorth.setLayout(new FlowLayout());
		lblStatus.setFont(new Font("Serif", Font.ITALIC, 24));
		pnlNorth.add(lblStatus);
		add(pnlNorth, BorderLayout.NORTH);
	}
	
	public boolean threeInARow(String player, int un, int deux, int trois)
	{
		if(btnSquares[un].getText().equals(player))
		if(btnSquares[un].getText().equals(btnSquares[deux].getText()) 
			&& btnSquares[deux].getText().equals(btnSquares[trois].getText())) return true;
		
		return false;
	}
	
	public boolean hasWon(String player)
	{
		if(threeInARow(player, 0,1,2)) return true;
		if(threeInARow(player, 3,4,5)) return true;
		if(threeInARow(player, 6,7,8)) return true;
		if(threeInARow(player, 0,3,6)) return true;
		if(threeInARow(player, 1,4,7)) return true;
		if(threeInARow(player, 2,5,8)) return true;
		if(threeInARow(player, 0,4,8)) return true;
		if(threeInARow(player, 2,4,6)) return true;
		return false;
	}
	
	public static void main(String[] args)
	{
		TicTacToe f=new TicTacToe();
	}

	public void actionPerformed(ActionEvent e)
	{
		if(playing)
		{
			boolean change = false;
			for(JButton button : btnSquares)
				if(e.getSource() == button && button.getText().equals(""))
				{
					if(xTurn) 
					{	
						button.setForeground(Color.BLUE);
						button.setText("X"); 
					}
					else
					{
						button.setForeground(Color.RED);
						button.setText("O");
					}
					xTurn = !xTurn;
					turns++;
					change = true;
				}
		
			if(change)
			{
				if(hasWon("X"))
				{
					lblStatus.setText("Game Over! X wins!");
					lblStatus.setForeground(Color.BLUE);
					playing = false;
				}
				else if(hasWon("O"))
				{
					lblStatus.setText("Game Over! O wins!");
					lblStatus.setForeground(Color.RED);
					playing = false;
				}
				else if(turns >= 9)
				{
					lblStatus.setText("Cat's game! It is a draw!");
					playing = false;
				}
					
			}
		
		}
	}	
}
