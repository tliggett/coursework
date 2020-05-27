//Example 35-3-3 is Example 33-1-5
//Input:	A dictionary file dict.txt (25000 entries) which contains a sequence of words in lowercase
//	separated by WS.  There are hyphenated words.
//	The name of a file to be spell checked which contains a sequence of words in lowercase separated
//	by WS.  The only punctuation allowed is the hyphen, for hyphenated words.
//	If a word is not in the dictionary, the user enters a to add, i to ignore, or m to confirm misspelled.
//	Note that the misspelled word is only added to the dictionary for the current session, not
//	permanently to the dictionary on disk.
//Output:A prompt for a, i, or m when a word not in the dictionary is found.
//	The list of misspelled words are printed when the program ends.
import java.util.*;
import java.io.*;


public class Demo33
{
    public static Scanner f=new Scanner(System.in);
    public static void main(String[] args) throws FileNotFoundException
    {
        System.out.print("Enter the document to spell check: ");
        String fileName = f.nextLine();
        spellCheck(fileName);
    }
    public static void spellCheck(String filename) throws FileNotFoundException
    {
        MyHashSet <String> dictionary = new MyHashSet <String>();
        MyHashSet <String> misspelledWords = new MyHashSet <String>();
        Scanner dictFile = new Scanner(new File("dict.txt"));
        Scanner docFile = new Scanner(new File(filename));
        while (dictFile.hasNext())
            dictionary.add(dictFile.next());
        while (docFile.hasNext())
        {
            String word = docFile.next();
            if (!dictionary.contains(word))
            {
                System.out.println(word);
                System.out.print("   'a' (add)  'i' (ignore)  'm'  (misspelled) ");
                String response = f.nextLine();
                if (response.charAt(0)=='a') dictionary.add(word);
                else if (response.charAt(0)=='m') misspelledWords.add(word);
            }
        }
        dictFile.close();
        docFile.close();
        System.out.println("Misspelled words" + misspelledWords);
    }
}
