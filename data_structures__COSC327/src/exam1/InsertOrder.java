package exam1;//TJ Liggett
//Exam 1
//2_25_2019
//InsertOrder.java

/*

    Class Insert Order reads in a list of strings from gradlist.txt and inserts them in
    sorted order without using sorting methods.

*/

import java.io.*;
import java.util.*;

public class InsertOrder
{

    public static void main(String[] args) throws FileNotFoundException
    {
        Scanner input = new Scanner(new File("exam1/gradlist.txt"));
        ArrayList<String> list = new ArrayList<String>();

        while(input.hasNextLine()) insertOrder(list, input.nextLine());

        ListIterator<String> iter = list.listIterator();
        System.out.println("Graduate List: ");
        while(iter.hasNext()) System.out.println("\t" + iter.next());

        input.close();

    }

    public static <T extends Comparable<? super T>> void insertOrder(List<T> list, T item)
    {
        if(list.isEmpty()) list.add(item);
        else
        {
            for(int i = 0; i < list.size(); ++i)
            {
                if(item.compareTo(list.get(i)) <= 0)
                {
                    list.add(i, item);
                    return;
                }
            }
            list.add(item);
        }
    }

}
