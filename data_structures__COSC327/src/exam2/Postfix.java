//Exam 2
//4_1_2019
//Postfix.java

/*
 *
 * Class postfix evaluates a postfix expression
 *
 */

import java.util.Stack;

public class Postfix
{
    private String exp;
    private Stack<Double> operandStack;

    //Pre:      The operandStack is not empty
    //Return:   The operand on top of operandStack
    //Post:     Top operand removed from operandStack
    //Throws:   ArithmeticException if pre is not met
    private double getOperand()
    {
        if (operandStack.isEmpty())
            throw new ArithmeticException("Postfix: Too many operators");
        return operandStack.pop();
    }

    //Return: True if character is one of six valid operators, false otherwise
    private boolean isOperator(char ch)
    {
        return ch == '+' || ch == '-' || ch == '*' || ch == '%' || ch == '/' || ch == '^';
    }

    //Pre: character at index i in infixExpr is digit
    //Return: String operand beginning at index i
    private String findOperand(int i)
    {
        String operand = "";
        int digit = i;
        char ch = exp.charAt(digit);
        if(!(Character.isDigit(ch) || ch == '.'))  /*Should be an internal check, means error in code*/
            throw new NumberFormatException("Error: Improper use of findOperand: char not digit");
        while(Character.isDigit(ch) || ch == '.')
        {
            operand += ch;
            digit++;
            if(digit >= exp.length()) break;
            ch = exp.charAt(digit);
        }
        return operand;
    }

    //Pre: If op is % or /, right != 0; If op is ^, left and right != 0
    //Return: value of computed expression
    //Throws ArithmeticException if pre is not met
    private double compute(double left, double right, char op)
    {
        double value = 0;
        switch(op) {
            case '+':
                value = left + right;
                break;
            case '-':
                value = left - right;
                break;
            case '*':
                value = left * right;
                break;
            case '%':
                if (right == 0) throw new ArithmeticException("Postfix: divide by 0");
                value = left % right;
                break;
            case '/':
                if (right == 0) throw new ArithmeticException("Postfix: divide by 0");
                value = left / right;
                break;
            case '^':
                if (right == 0 && left == 0) throw new ArithmeticException("Postfix: 0^0");
                value = (int) Math.pow(left, right);
                break;
        }
        return value;
    }

    //Post: exp, operandStack initialized
    public Postfix()
    {
        exp = new String();
        operandStack = new Stack<Double>();
    }

    //Return:   The postfix expression to be evaluated
    public String getExp()
    {
        return exp;
    }

    //Post:     exp set to parameter
    public void setExp(String s)
    {
        exp = s;
    }

    //Pre:    exp is valid postfix expression
    //Return: value of evaluated postfix expression
    //Throws: Various exceptions if pre is not met
    public double evaluate()
    {
        for (int i=0; i < exp.length(); i++)
        {
            char ch = exp.charAt(i);
            if (Character.isDigit(ch) || ch == '.')
            {
                String operand = findOperand(i);
                try {
                    operandStack.push(Double.parseDouble(operand));
                }catch(NumberFormatException e)
                {
                    System.out.println("Postfix: Invalid Input");
                }
                i += operand.length()-1;
            }
            else if (isOperator(ch))
            {
                double right = getOperand();
                double left = getOperand();
                operandStack.push(compute(left, right, ch));
            }
            else if (!Character.isWhitespace(ch))
                throw new ArithmeticException("Postfix: Improper char");
        }
        double expValue = operandStack.pop();
        if (!operandStack.isEmpty())
            throw new ArithmeticException("Postfix: Too many operands");
        return expValue;
    }
}

