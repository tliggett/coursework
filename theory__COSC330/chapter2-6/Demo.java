//Example 2-6-9

import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.*;
import java.io.*;

class Demo
{
	public static void main(String[] args) throws FileNotFoundException
	{
		Scanner keyboard = new Scanner(System.in);
		System.out.print("Enter the file name:");
		String filename = keyboard.nextLine();
		concordance(filename);
	}

	public static void concordance(String filename) throws FileNotFoundException
	{
		TreeMap<String, LinkedList<Integer>> m = new TreeMap<String, LinkedList<Integer>>();
		int lineNumber = 0;
		Scanner f = new Scanner(new File(filename));
		Pattern p = Pattern.compile("[a-zA-Z_$][a-zA-Z0-9_$]*");
		while(f.hasNextLine())
		{
			String s = f.nextLine();
			lineNumber++;
			Matcher matcher = p.matcher(s);
			while(matcher.find())
			{
				String identifier = s.substring(matcher.start(), matcher.end());
				LinkedList<Integer> lineNumbers = m.get(identifier);
				if(lineNumbers == null) lineNumbers = new LinkedList<Integer>();
				lineNumbers.add(lineNumber);
				m.put(identifier, lineNumbers);
			}
		}
		writeConcordance(m);
	}

	public static void writeConcordance(TreeMap<String, LinkedList<Integer>> m)
	{
		Set<Map.Entry<String, LinkedList<Integer>>> entrySetObj = m.entrySet();
		Iterator<Map.Entry<String, LinkedList<Integer>>> iter = entrySetObj.iterator();
		while(iter.hasNext())
		{
			Map.Entry<String, LinkedList<Integer>> e = iter.next();
			System.out.printf("%-20s", e.getKey());
			LinkedList<Integer> lineNumbers = e.getValue();
			System.out.printf("%4d:", lineNumbers.size());
			Iterator<Integer> setIter = lineNumbers.iterator();
			while(setIter.hasNext())
				System.out.printf("%4d", setIter.next());
			System.out.println();
		}
	}
}
