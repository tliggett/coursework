package homework5;//TJ Liggett
//3_21_2019
//Homework 5
//exam2.exam2.Demo22.java
/*
    Class exam2.exam2.Demo22 simulates the Josephus game using DoublyLinkedList
*/


import java.util.Random;
import java.util.Scanner;
public class Demo
{
    //Desc:	A professor selects 1 from n students for a 5 extra-point bonus.  The professor places the
    //	students in a circle and then draws a number m (1<=m<=n) from a hat.  The game is
    //	played by having the professor walk clockwise around a circle, stopping at every mth
    //	student, whom is asked to leave the game.  The professor continues to walk clockwise,
    //	asking every mth student to leave the game.  The last person in the game is the winner.
    //Input:	The number of students followed by names of students one on each line entered via the
    //	keyboard
    //Output:The random number m, and the winner of the game
    public static void main(String[] args)
    {
        Random r=new Random();
        Scanner f=new Scanner(System.in);
        System.out.println("Enter names of students, one on each line, -oo- to end: ");
        DNode<String> header = buildList();
        System.out.println("One of you will get 5 extra points.");
        int n = DoublyLinkedList.size(header);
        int m = r.nextInt(n)+1;
        System.out.println("Generating random number ....." + m);
        josephus(header, n, m);
    }

    //Desc:	A loop moves around the list of n elements circularly and erases every mth students until
    //	only 1 remains.
    //Pre:	header points to the list; n is length of list; m is a random number
    //Output:The list is printed each time a student is removed.
    //	The winner is printed at the end
    public static void josephus(DNode<String> header, int n, int m)
    {
        DNode<String> curr = header.next;
        System.out.println(DoublyLinkedList.toString(header));
        for(int i=1; i < n; i++)
        {
            for(int j=1; j<=m-1; j++)
            {
                curr= curr.next;
                if(curr==header) curr = curr.next;
            }
            System.out.print("delete " + curr.value + " ");
            curr=curr.next;
            DoublyLinkedList.remove(curr.prev);
            if(curr==header) curr = curr.next;
            System.out.println(DoublyLinkedList.toString(header));
        }
        System.out.println("Student " + curr.value + " wins the bonus");
    }

    //Pre: 	A sequence of names entered via keyboard, one on each line; -oo- is sentinel
    //Return:A linked list of the names
    public static DNode<String> buildList()
    {
        Scanner f=new Scanner(System.in);
        DNode<String> header=new DNode<String>(), p=null;
        String s=null;
        while (true)
        {
            s=f.nextLine();
            if (s.equals("-oo-")) break;
            p=new DNode<String>(s);
            p.next= header;
            p.prev= header.prev;
            header.prev.next=p;
            header.prev=p;
        }
        return header;
    }

}

