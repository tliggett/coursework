package FINAL;

//TJ Liggett
//CS 211 Final
//MyJFramec
//Class MyJFramec handles window closing through handler object of a local class

import javax.swing.*;								
import java.awt.event.*; 		

public class MyJFramec extends JFrame			
{	
	public MyJFramec()							
	{
		registerListeners();
		setTitle("Event handling");						
		setSize(400,200); 						
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);	
		setVisible(true);							
	}
	
	public void registerListeners()
	{
		class WindowHandler extends WindowAdapter
		{
			public void windowClosing(WindowEvent e)
			{
				String s;
				s=JOptionPane.showInputDialog(null, "Do you really want to exit? y/n");
				if (s==null) return;
				if (s.equals("y")) System.exit(0);	
			}
		}
		addWindowListener(new WindowHandler());			
	}
	
	public static void main(String[] args)
 	{
		MyJFramec f=new MyJFramec();					
 	}
}