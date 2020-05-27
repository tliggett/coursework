
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class SortLowercaseWords {
    public static void main(String[] args) throws FileNotFoundException
    {
        ArrayList<String> arr =new ArrayList<String>();
        Scanner input = new Scanner(new File("words.txt"));
        while (input.hasNext())
            arr.add(input.next());
        
	System.out.println(arr);
	StrRadixSort.strRadixSort(arr);
	System.out.println(arr);
	/*
        for (int i=0; i < arr.size(); i++)
            System.out.println(arr.get(i));
    	*/
    }
}
