//TJ Liggett
//Homework 11
//Class Part2 tests class Entry by sorting a Vector of Entries.
//11_27_2018
//Part2.java

import java.util.*;
import java.io.*;

class Part2 {
	public static void main(String[] args) throws FileNotFoundException {
		Vector<Entry<String, Time24>> v = new Vector<Entry<String, Time24>>();
		Scanner input = new Scanner(new File("data.txt"));
		while (input.hasNextLine()) {
			String s = input.nextLine();
			String t = input.nextLine();
			v.add(new Entry<String, Time24>(s.trim(), Time24.parseTime(t)));
		}
		Collections.sort(v);
		for(Entry<String,Time24> e : v) System.out.println(e);
		input.close();
	}
}
