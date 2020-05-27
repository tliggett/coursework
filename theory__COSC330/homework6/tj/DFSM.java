/* DFSM.java */
// Homework 6
// CS 330
// TJ Liggett
// Izzy Sommers

import java.util.Vector;
import java.util.Scanner;

public class DFSM
{
    public static void main(String args[])
    {
        String s, line;                             // s is the start state 
        Vector<String> A = new Vector<String>();    // A is the final state vector
        Vector<String> t = new Vector<String>();    // t is the transition vector
        Scanner input = new Scanner(System.in);

        /** Read in the start state */
        System.out.print("Enter start state: ");
        s = input.nextLine();
    
        /** Read in final states (vector) */
        System.out.println("Enter final states, 1 on each line. Enter 0 to end:");    
        line = input.nextLine();
        while(!line.equals("0"))
        {
            A.add(line);
            line = input.nextLine(); 
        }

        /** Read in transitions (vector) */
        System.out.println("Enter transitions, 1 on each line with no ws: state,letter,state. Enter 0,0,0 to end");
        line = input.nextLine();
        while(!line.equals("0,0,0"))
        {
            t.add(line);
            line = input.nextLine(); 
        }
        
        /** User enters string and dfsm is simulated to accept or reject */
        System.out.print("Enter input string, enter done to end:");
        line = input.nextLine();
        while(!line.equals("done"))
        {
            dfsmSimulate(t, s, A, line);
            System.out.print("Enter input string, enter done to end:");
            line = input.nextLine();
        }

    }

    public static void dfsmSimulate(Vector<String> t, String s, Vector<String> A, String word)
    {
        /**
         * Inputs:
         *      Vector<String> t: transition states
         *      String s: start state
         *      Vector<String> A: final states
         *      String word: the user input string
         * Output: 'accept' if string is accepted, 'reject' otherwise
         */
        String state = s;   /** The current program state */
        char c;             /** The next input character in input string */
        boolean exists;
        do
        {
            c = word.charAt(0);
            word = word.substring(1, word.length());
            
            exists = false;
            for(String transition : t)
            {
                if(transition.startsWith(state))
                {
                    if(transition.charAt(2) == c)
                    {
                        state = transition.substring(4,5);
                        exists = true;
                    }
                }
            }
            
            if(!exists)
            {
                System.out.println("reject");
                return;
            }


        } while(word.length() > 0);
        if(A.contains(state)) System.out.println("accept");
        else System.out.println("reject");        
    }

}