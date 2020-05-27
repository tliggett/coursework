//TJ Liggett
//Homework 11
//Class Part4 tests class Entry by sorting and searching a Vector of Entries
//11_27_2018
//Part4.java

import java.util.*;
import java.io.*;

class Part4 {
	public static void main(String[] args) throws FileNotFoundException {
		Vector<Entry<String, Time24>> v = new Vector<Entry<String, Time24>>();
		Scanner keyboard = new Scanner(System.in);
		Scanner input = new Scanner(new File("data.txt"));
		while (input.hasNextLine()) {
			String s = input.nextLine();
			String t = input.nextLine();
			v.add(new Entry<String, Time24>(s.trim(), Time24.parseTime(t)));
		}
		Collections.sort(v);
		while (true) {
			System.out.print("Enter name: ");
			String name = keyboard.nextLine();
			if (name.equals("DONE")) break;
			Entry<String, Time24> temp = new Entry<String, Time24>(name, new Time24(0, 0));
			int index = Collections.binarySearch(v, temp);
			String output = "No such person";
			if(index > -1) output = v.get(index).getValue().toString();
			System.out.println(output);
		}
		keyboard.close();
		input.close();
	}
}
