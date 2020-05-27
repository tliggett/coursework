//Exam 2
//4_1_2019
//Infix.java

/*
*
* Class infix converts an infix expression to postfix
*
*/

import java.util.Stack;

public class Infix {
    private String infixExpr = null;
    private String postfixExpr = null;
    private Stack<Symbol> operatorStack = null;

    //Post: infixExpr and postfixExpr initialize to "", operatorStack initialize
    public Infix() {
        infixExpr = "";
        postfixExpr = "";
        operatorStack = new Stack<Symbol>();
    }

    //Post: infixExpr set to parameter; postfixExpr and operatorStack initialized
    public Infix(String infixExp) {
        infixExpr = infixExp;
        postfixExpr = "";
        operatorStack = new Stack<Symbol>();
    }

    //Post: operatorStack popped until empty or Symbol of higher priority than parameter
    private void popUntilLowerPriority(Symbol opToBePushed) {
        while ( !operatorStack.isEmpty() && operatorStack.peek().compareTo(opToBePushed) >= 0) {
            Symbol topOfStack = operatorStack.pop();
            postfixExpr += topOfStack.getOp() + " ";
        }
    }

    //Return:   True if char is one of six valid operators, false otherwise
    private boolean isOperator(char ch) {
        return ch == '+' || ch == '-' || ch == '*' || ch == '%' || ch == '/' || ch == '^';
    }


    //Pre: character at index i in infixExpr is digit
    //Return: String operand beginning at index i
    private String findOperand(int i)
    {
        String operand = "";
        int digit = i;
        char ch = infixExpr.charAt(digit);
        if(!(Character.isDigit(ch) || ch == '.'))  /*Should be an internal check, means error in code*/
            throw new NumberFormatException("Error: Improper use of findOperand: char not digit");
        while(Character.isDigit(ch) || ch == '.')
        {
            operand += ch;
            digit++;
            if(digit >= infixExpr.length()) break;
            ch = infixExpr.charAt(digit);
        }
        return operand;
    }

    //Post: InfixExpr set to parameter, postfixExpr set to "", operatorStack initialized
    public void setExp(String infixExp) {
        infixExpr = infixExp;
        postfixExpr = "";
        operatorStack = new Stack<Symbol>();
    }

    //To do error checking, we will use a variable called rank.  We will increment rank when we read
    //an operand, and we will decrement rank when we read an operator.  At the end of the processing
    //of infix expression that contains only binary operators, rank should be 1.  During the processing
    //of an infix expression, rank can only be 0 or 1.
    public String toPostfix() {
        int rank = 0;
        for (int i = 0; i < infixExpr.length(); i++) {
            char ch = infixExpr.charAt(i);
            if (Character.isDigit(ch) || ch == '.') {
                rank++;
                if (rank > 1) throw new ArithmeticException("Infix: Operator expected");
                String operand = findOperand(i);
                postfixExpr += operand + " ";
                i += operand.length()-1;
            } else if (isOperator(ch)) {
                rank--;
                if (rank < 0) throw new ArithmeticException("Infix: Operand expected");
                else {
                    Symbol op = new Symbol(ch);
                    popUntilLowerPriority(op);
                    operatorStack.push(op);
                }
            } else if (ch == '('){
                operatorStack.push(new Symbol(ch));
            }
            else if (ch == ')') {
                popUntilLowerPriority(new Symbol(ch));
                if (operatorStack.isEmpty()) throw new ArithmeticException("Infix: Missing left (");
            	else operatorStack.pop();
            } else if (!Character.isWhitespace(ch))
                throw new ArithmeticException("Infix: Invalid input");
        }
        if (rank != 1) throw new ArithmeticException("Infix: Operand expected");
        else while (!operatorStack.isEmpty()) {
            Symbol op = operatorStack.pop();
            if (op.getOp() == '(')
                throw new ArithmeticException("Infix: Missing ')'");
            else postfixExpr += op.getOp() + " ";
        }
        return postfixExpr;
    }
}


//Class Symbol
class Symbol implements Comparable<Symbol> {
    private char op;
    private int inputPriority;
    private int stackPriority;

    public Symbol(char ch) {
        op = ch;
        switch (op) {
            case '+':
            case '-':
                inputPriority = 1;
                stackPriority = 1;
                break;
            case '*':
            case '%':
            case '/':
                inputPriority = 2;
                stackPriority = 2;
                break;
            case '^':
                inputPriority = 4;
                stackPriority = 3;
                break;
            case '(':
                inputPriority = 5;
                stackPriority = -1;
                break;
            case ')':
                inputPriority = 0;
                stackPriority = 0;  //NOTE: Doesn't matter, will never be pushed
                break;
        }
    }

    //Return:-1 if the priority of the Symbol on stack is < item about to be pushed on stack
    //	0 if the priority of the Symbol on stack is = item about to be pushed on stack
    //	1 if the priority of the Symbol on stack is > item about to be pushed on stack
    public int compareTo(Symbol item) {
        if (stackPriority < item.inputPriority) return -1;
        else if (stackPriority == item.inputPriority) return 0;
        else return 1;
    }

    //Return: character of symbol
    public char getOp() {
        return op;
    }
}
