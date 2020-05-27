package reviewLabs;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class madLib {
	String sentence;
	String story;
	ArrayList<String> verbs;
	ArrayList<String> nouns;
	ArrayList<String> adjectives;
	public madLib(String st) throws Exception{
		sentence = "";
		story = st;
		verbs = new ArrayList<String>();
		nouns = new ArrayList<String>();
		adjectives = new ArrayList<String>();
		Scanner scan = new Scanner(new File("src/reviewLabs/verbs.txt"));
		while(scan.hasNextLine()){
		 verbs.add(scan.nextLine());
		}
		scan = new Scanner(new File("src/reviewLabs/noun.txt"));
		while(scan.hasNextLine()){
		 nouns.add(scan.nextLine());
		 
		}
		scan = new Scanner(new File("src/reviewLabs/adjectives.txt"));
		while(scan.hasNextLine()){
		 adjectives.add(scan.nextLine());
		 
		}
		setSentence();
		
		
		
		
		
	}
	public void setSentence(){
		Scanner scan = new Scanner(story);
		while(scan.hasNext()){
			String s = scan.next();
			if(s.equals("#")){
				int i = (int)(Math.random() * (nouns.size()));
				sentence += nouns.get(i);
			}else if(s.equals("@")){
				int i = (int)(Math.random() * (adjectives.size()));
				sentence += adjectives.get(i);
			}else if(s.equals("*")){
				int i = (int)(Math.random() * (verbs.size()));
				sentence += verbs.get(i);
			}else{
				sentence += s;
			}
			sentence += " ";
					}
		
	}
	
	
	public String toString(){
		return sentence;
		
	}
}
