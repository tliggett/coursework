package homework12;

//TJ Liggett
//Homework 12
//Class Demo provides user a simple calculator

import javax.swing.JOptionPane;
public class Demo 
{
	//Desc: 	A simple calculator for doing addition, subtraction, multiplication, and division.  
	//Input:	The user selects +, –, *, / via an input box, then enters 2 numbers via an input box.  
	//Output:The result of the entered expression displayed in a message box. Various Error messages
	public static void main(String[] args)
	{ 
		while (true)
		{
			String op=JOptionPane.showInputDialog(null, "Select an operation" , "+ - * /");
			if (op==null) break;
			String oper1 = JOptionPane.showInputDialog(null, "Operand1" , "0.0");	
			String oper2=JOptionPane.showInputDialog(null, "Operand2" , "0.0");
			if(!(op.equals("+") || op.equals("-") || op.equals("*") || op.equals("/")))
			{
				JOptionPane.showMessageDialog(null, "Operation must be + - * /", "ERROR", JOptionPane.ERROR_MESSAGE);
			}
			else if(op.equals("/") && Double.parseDouble(oper2) == 0.0)
			{
				JOptionPane.showMessageDialog(null, "Cannot divide by Zero", "ERROR", JOptionPane.ERROR_MESSAGE);
			}
			else
			{
				double result=compute(oper1, op, oper2);
				JOptionPane.showMessageDialog(null, oper1+op+oper2+"="+result);
			}
			
		}	
	}
	//Desc: 	Evaluate an expression.  
	//Pre:	oper1 and oper2 are strings of legal real numbers.  
	//	op must be in [+, -, *, /]
	//Return:	Result of oper1 op oper2. 
	public static double compute (String oper1, String op, String oper2)
	{
		double num1=Double.parseDouble(oper1);
		double num2=Double.parseDouble(oper2);
		if (op.equals("+")) return num1+num2;			
		else if (op.equals("-")) return num1-num2;			
		else if (op.equals("*")) return num1*num2;			
		else return num1/num2;			
	}
}
