package homework11;

//Example 24-3-5
import java.util.*;
import java.io.*;

class Part1 {
	public static void main(String[] args) throws FileNotFoundException {
		Vector<Entry<String, Time24>> v = new Vector<Entry<String, Time24>>();
		Scanner keyboard = new Scanner(System.in);
		Scanner input = new Scanner(new File("data.txt"));
		while (input.hasNextLine()) {
			String s = input.nextLine();
			String t = input.nextLine();
			v.add(new Entry<String, Time24>(s.trim(), Time24.parseTime(t)));
		}
		while (true) {
			System.out.print("Enter name: ");
			String name = keyboard.nextLine();
			if (name.equals("DONE"))
				break;
			Entry<String, Time24> temp = new Entry<String, Time24>(name, new Time24(0, 0));
			Iterator<Entry<String, Time24>> iter = v.iterator();
			String output = "No such person";
			while (iter.hasNext()) {
				Entry<String, Time24> runner = iter.next();
				if (runner.equals(temp)) {
					output = runner.getValue().toString();
					break;
				}
			}
			System.out.println(output);
		}
	}
}
