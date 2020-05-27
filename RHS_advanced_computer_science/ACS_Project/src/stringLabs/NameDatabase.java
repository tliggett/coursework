package stringLabs;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class NameDatabase {
	static ArrayList<String> nameDatabase;
	public static void main(String[] args) throws Exception {
		nameDatabase = new ArrayList<String>();
		Scanner file = new Scanner(new File("src/stringLabs/NameDatabase.txt"));
		while(file.hasNextLine()){
			nameDatabase.add(file.nextLine());
			
		}
		int i = (int) (Math.random() * nameDatabase.size());
		System.out.println(nameDatabase.get(i));
		
	}

}
