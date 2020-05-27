package exam1;//TJ Liggett
//Exam 1
//2_25_2019
//Words.java

/*
    Class Words reads user String list from keyboard, stores it in an ArrayList, writes the
    ArrayList to a file, reads it back from the file, and outputs the list from file.
*/

import java.io.*;
import java.util.*;

public class Words {

    //Input: A sequence of word separated by white space, then Ctrl-Z
    //Output: User prompts, followed by the ArrayList from file
    public static void main(String[] args) throws IOException
    {
        Scanner input = new Scanner(System.in);
        ArrayList<String> words = new ArrayList<String>();

        System.out.println("Enter a sequence of word separated by white space.");
        System.out.println("Enter Ctrl-Z on a line by itself when done.");
        while(input.hasNext()) words.add(input.next());

        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(("exam1/words.data")));
            out.writeObject(words);
        } catch (IOException e) {
            System.err.println("error writing file");
            System.exit(1);
        }

        ArrayList<String> fromFile = null;

        try {
            ObjectInputStream f = new ObjectInputStream(new FileInputStream("exam1/words.data"));
            fromFile = (ArrayList<String>) f.readObject();
        } catch (IOException e) {
            System.err.println("error reading file");
            System.exit(1);
        } catch (ClassNotFoundException e) {
            System.err.println("error reading file");
            System.exit(1);
        }

        System.out.println(fromFile);




    }

}
