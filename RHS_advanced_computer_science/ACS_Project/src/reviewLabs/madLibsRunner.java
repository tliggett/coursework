package reviewLabs;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class madLibsRunner {
	public static void main(String[] args) throws Exception{
		Scanner scan = new Scanner(new File("src/reviewLabs/story.txt"));
		
		while(scan.hasNextLine()){
			String s = scan.nextLine();
			madLib mad = new madLib(s);
			System.out.println(mad);
		}
	}
	
}
