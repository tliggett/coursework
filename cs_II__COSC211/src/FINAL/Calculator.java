package FINAL;

//TJ Liggett
//CS 211 Final Exam
//Calculator.java
//Class Calculator provides user a calculator to do basic arithmetic and square root 

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Calculator extends JFrame implements ActionListener
{
	private JButton btnAdd = new JButton("+");
	private JButton btnSubtract = new JButton("-");
	private JButton btnMultiply = new JButton("*");
	private JButton btnDivide = new JButton("/");
	private JButton btnSqrt = new JButton("sqrt");
	
	public Calculator()
	{
		addControls();
		registerListeners();
		setTitle("Calculator");
		setSize(600,150);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE );
		setVisible(true);
	}
	public void addControls()
	{
		setLayout(new GridLayout(1,5));
		add(btnAdd);
		add(btnSubtract);
		add(btnMultiply);
		add(btnDivide);
		add(btnSqrt);
	}
	public void registerListeners()
	{
		btnAdd.addActionListener(this);
		btnSubtract.addActionListener(this);
		btnMultiply.addActionListener(this);
		btnDivide.addActionListener(this);
		btnSqrt.addActionListener(this);
	}

	public static void main(String[] args)
	{
		Calculator f=new Calculator();
	}

	//Desc: Event handler to handle the button clicks.
	//Input: User enters 1 to 2 numbers via input dialogs, depending on operation.
	//Output:The result of the operation indicated by the button clicked applied to the 
	//input numbers displayed in a message box.
	public void actionPerformed(ActionEvent e)
	{
		String oper1 = JOptionPane.showInputDialog(null, "Operand1" , "0.0");
		double num1=Double.parseDouble(oper1);
		double result=0.0;
		if(e.getSource() == btnSqrt)
		{
			if(num1 < 0.0)
			{
				JOptionPane.showMessageDialog(null, "Cannot take sqrt of negative number!", 
						"ERROR", JOptionPane.ERROR_MESSAGE);
			}
			else
			{
				result = Math.sqrt(num1);
				JOptionPane.showMessageDialog(null, "sqrt(" + oper1 + ")=" + result);
			}
		}
		else
		{	
			
			String oper2 = JOptionPane.showInputDialog(null, "Operand2" , "0.0");
			double num2=Double.parseDouble(oper2);
			if(e.getSource() == btnAdd)
			{
				result=num1+num2;
				JOptionPane.showMessageDialog(null, oper1+"+"+oper2+"="+result);
			}
			else if(e.getSource() == btnSubtract)
			{
				result=num1-num2;
				JOptionPane.showMessageDialog(null, oper1+"-"+oper2+"="+result);
			}
			else if(e.getSource() == btnMultiply)
			{
				result=num1*num2;
				JOptionPane.showMessageDialog(null, oper1+"*"+oper2+"="+result);
			}
			else if (e.getSource() == btnDivide)
			{
				if(num2 == 0.0)
				{
					JOptionPane.showMessageDialog(null, "Cannot divide by Zero", 
							"ERROR", JOptionPane.ERROR_MESSAGE);
				}
				else
				{	
					result=num1/num2;
					JOptionPane.showMessageDialog(null, oper1+"/"+oper2+"="+result);
				}
			}
		}
	}
}
