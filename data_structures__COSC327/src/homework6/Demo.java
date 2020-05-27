package homework6;//Homework 6
//3_27_2019
//TJ Liggett
//exam2.exam2.Demo22.java

import java.util.Random;
import java.util.Scanner;

class Demo
{
    //Desc:	A professor selects 1 from n students for a 5 extra-point bonus.  The professor places the
    //	students in a circle and then draws a number m (1<=m<=n) from a hat.  The game is
    //	played by having the professor walk clockwise around a circle, stopping at every mth
    //	student, whom is asked to leave the game.  The professor continues to walk clockwise,
    //	asking every mth student to leave the game.  The last person in the game is the winner.
    //Input:Names of students, one on each line, -oo- to end, entered via the keyboard
    //Output:The random number m, and the winner of the game
    public static void main(String[] args)
    {
        Random r=new Random();
        System.out.println("Enter names of student, one on each line, -oo- to end: ");
        MyLinkedList<String> students=buildRandomList();
        int n= students.size();
        System.out.println("There are "+n+" students.  One of you will get 5 extra points.");
        int m = r.nextInt(n)+1;
        System.out.println("Generating random number ....." +m);
        josephus(students, m);
    }
    //Pre: 	A sequence of lines entered via keyboard; -oo- is sentinel
    //Return:MyLinkedList<String> of the input lines inserted at random position, 1 line per node
    public static MyLinkedList<String> buildRandomList()
    {
        MyLinkedList<String> list = new MyLinkedList<String>();
        Scanner input = new Scanner(System.in);
        Random r = new Random();
        int index = 0;
        while(true)
        {
            String line = input.nextLine();
            if(line.equals("-oo-")) break;
            index = 0;
            if(list.size() > 0) index = r.nextInt(list.size());
            list.add(index, line);
        }
        return list;
    }
    //Desc:	A loop moves around a list of students circularly and erases every mth students until
    //	only 1 remains.
    //Output:The list is printed each time a student is removed.
    //	The winner is printed at the end
    public static void josephus(MyLinkedList<String> students, int m)
    {
        System.out.println(students);
        while(students.size() > 1)
        {
            int index = (m-1)%students.size();
            System.out.print("delete " + students.remove(index) + " ");
            System.out.println(students);
        }
        System.out.println("Student " + students.get(0) + " wins the bonus");
    }
}
