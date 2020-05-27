package cemetery;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;




public class CemeteryRunner {
	static ArrayList<ArrayList<String>> file;
	
	
	public static void main(String[] args) throws Exception{
		Scanner scan = new Scanner(new File("src/cemetery/cemetery.txt"));
		file = new ArrayList<ArrayList<String>>();
		while(scan.hasNextLine()){
			ArrayList<String> line = new ArrayList<String>();
			Scanner s = new Scanner(scan.nextLine());
			while(s.hasNext()){
				line.add(s.next());
				
			}
			file.add(line);
			
		}
		for(ArrayList<String> line : file){
			for(String s : line){
				System.out.print(s + " ");
			}
			System.out.println();;
			
		}
		
	}
}
