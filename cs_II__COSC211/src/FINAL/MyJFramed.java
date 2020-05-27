package FINAL;

//TJ Liggett
//CS 211 Final
//MyJFramed
//Class MyJFramed handles window closing through handler object of an anonymous class

import javax.swing.*;								
import java.awt.event.*; 								

public class MyJFramed extends JFrame			
{	
	public MyJFramed()							
	{
		registerListeners();
		setTitle("Event handling");						
		setSize(400,200); 						
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setVisible(true);							
	}
	
	public void registerListeners()
	{
		addWindowListener(
				new WindowAdapter()
				{
					public void windowClosing(WindowEvent e)
					{
						String s;
						s=JOptionPane.showInputDialog(null, "Do you really want to exit? y/n");
						if (s==null) return;
						if (s.equals("y")) System.exit(0);	
					}
				});			
	}
	
	public static void main(String[] args)
 	{
		MyJFramed f=new MyJFramed();					
 	}
}