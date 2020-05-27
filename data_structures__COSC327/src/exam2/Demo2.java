//Exam 2
//4_1_2019
//exam2.Demo22.java


import java.util.Scanner;
public class Demo2
{
    //Desc : 	A simple calculator.
    //Input: 	The keyboard provides an infix expression on a line whose operators can only be *, +, -,
    //	        /, %, ^, and whose operands can be all real numbers
    //output:	The double value of the infix expression printed to the screen.
    public static void main(String[] args)
    {
        Scanner f = new Scanner(System.in);
        System.out.println("Enter infix expression, newline to end:");
        while (true)
        {
            String s = f.nextLine();
            if (s.equals("")) break;
            Infix infixObj = new Infix();
            Postfix postfixObj = new Postfix();
            infixObj.setExp(s);
            try
            {
                s = infixObj.toPostfix();
                System.out.println("\tPostfix: " + s );
                postfixObj.setExp(s);
                System.out.printf("\tValue: %.4f\n", postfixObj.evaluate());
            }
            catch (ArithmeticException e)
            {
                System.out.println("\t"+e.getMessage());
            }
        }
    }
}


