package sets;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import static java.lang.System.*;

public class MathSetRunner
{
	public static void main(String args[]) throws IOException
	{
		ArrayList<MathSet> mather = new ArrayList<MathSet>();
		//!!!
		Scanner scan = new Scanner(new File("src/sets/mathsetdata1.txt"));
		while(scan.hasNextLine()){
			String o = scan.nextLine();
			String t = scan.nextLine();
			MathSet m = new MathSet(o,t);
			mather.add(m);
			
	
		}
		
		for(MathSet m : mather){
			System.out.println(m + "\n");
			System.out.println("Union :: " + m.union());
			System.out.println("Intersection :: " + m.intersection());
			System.out.println("A minus B :: " + m.differenceAMinusB());
			System.out.println("B minus A :: " + m.differenceBMinusA());
			System.out.println("Systematic Difference :: " + m.symmetricDifference());
			System.out.println("");
		}
	}
}
