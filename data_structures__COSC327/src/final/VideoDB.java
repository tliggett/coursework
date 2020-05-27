//TJ Liggett
//5_19_2019
//FINAL EXAM
//VideoDB.java



/*
 *
 * Class VideoDB.java uses a TreeMap to maintain inventory for a video store.
 *
 */


//Desc:	Maintain inventory for a video store. The video store operates on an honor system.  That is,
//	the video store only maintains the inventory.  It trusts that a customer will always return a
//	film, and therefore the program does not keep rental records.
//Input:	The database file video.txt has the inventory in the following format:
//		title:int
//		title:int
//		…
//		Each line represents the title of the film and the number of copies in inventory.  Note that a
//		film title could contain blanks.  Thus, we use only colons and newlines to separate tokens in
//		the data file. This file should be created before the program is run for the first time.
//	In an interactive loop the clerk inputs whether the customer wishes to rent a film, return a film,
//	or whether business is over for the day.
//Output:A menu asking the clerk for input.
//	At the beginning of the business day, the program outputs the list of films in inventory.
//	At the end of the business day, the program outputs the list of films remaining in inventory.
//	The database video.txt is updated at the end of each day.

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.Scanner;


public class VideoDB
{
    public static void main(String[] args) throws FileNotFoundException, IOException
    {
        Scanner f=new Scanner(System.in);
        MyTreeMap<String, Integer> inventory=new MyTreeMap<String, Integer>();
        loadDB(inventory);
        System.out.println("Inventory:");
        display(inventory);
        while (true)
        {
            System.out.print("1. Rent  2. Return  3. Done -- Your choice:");
            int task =f.nextInt();
            f.nextLine();
            if (task == 3) break;
            System.out.print("Enter film name:");
            String filmName= f.nextLine();
            if (task == 2) returnTransaction(inventory, filmName);
            else rentalTransaction(inventory, filmName);
        }
        System.out.println("Films Remaining in Inventory:");
        display(inventory);
        updateDB(inventory);
    }
    //Input: Video.txt
    //Post: Video.txt loaded in inventory
    public static void loadDB(MyTreeMap<String, Integer> inventory) throws FileNotFoundException
    {
        Scanner file = new Scanner(new File("Video.txt"));
        file.useDelimiter("[\r\n:]+");
        while(file.hasNext())
        {
            String title = file.next();
            int copies = file.nextInt();
            inventory.put(title, copies);
        }
        file.close();
    }
    //Post: All records in t printed on the screen
    public static void display(MyTreeMap<String, Integer> t)
    {
        Iterator<MyMap.Entry<String, Integer>> iter = t.entrySet().iterator();
        while(iter.hasNext())
        {
            MyMap.Entry<String, Integer> entry = iter.next();
            System.out.println(entry.getKey() + ":" + entry.getValue());
        }
    }
    //Desc	: process returning of a movie.
    //Post	: the film added to inventory if film does not exist, or its copies count incremented.
    public static void returnTransaction(MyTreeMap<String, Integer> inventory, String filmName)
    {
        Integer count = inventory.get(filmName);
        if(count == null) inventory.put(filmName, 1);
        else inventory.put(filmName, count + 1);
    }
    //Desc	: process renting of a movie.
    //Post	: the film is deleted from inventory if it exists in inventory and its count is 1, or its
    //	count decremented if it exists in inventory and its count is >1.
    //Output	: If the film is not in inventory, a message is printed on the screen to that effect.
    public static void rentalTransaction(MyTreeMap<String, Integer> inventory, String filmName)
    {
        Integer count = inventory.get(filmName);
        if(count == null) System.out.println("Film is not available for rent.");
        else if(count <= 1) inventory.remove(filmName);
        else inventory.put(filmName, count - 1);

    }
    //Post: All records in inventory saved in video.txt
    public static void updateDB(MyTreeMap<String, Integer> inventory) throws IOException
    {
        PrintWriter output = new PrintWriter(new File("Video.txt"));
        Iterator<MyMap.Entry<String,Integer>> iter = inventory.entrySet().iterator();
        while(iter.hasNext())
        {
            MyMap.Entry<String, Integer> entry = iter.next();
            output.println(entry.getKey() + ":" + entry.getValue());
        }
        output.close();
    }
}

