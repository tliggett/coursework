package FINAL;

//TJ Liggett
//CS 211 Final
//MyJFramea
//Class MyJFramea handles window closing through handler object of a static member class

import javax.swing.*;								
import java.awt.event.*; 								

public class MyJFramea extends JFrame			
{
	
	private static class WindowHandler extends WindowAdapter
	{
		public void windowClosing(WindowEvent e)
		{
			String s;
			s=JOptionPane.showInputDialog(null, "Do you really want to exit? y/n");
			if (s==null) return;
			if (s.equals("y")) System.exit(0);	
		}
	}
	
	public MyJFramea()							
	{
		registerListeners();
		setTitle("Event handling");						
		setSize(400,200); 						
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);	
		setVisible(true);							
	}
	
	public void registerListeners()
	{
		addWindowListener(new WindowHandler());			
	}
	
	public static void main(String[] args)
 	{
		MyJFramea f=new MyJFramea();					
 	}
}