package scannerChoppah;

import java.util.Scanner;

public class test {

	public static void main(String [] args){
		Scanner s = new Scanner("1 2 3 4 5 6");
		int x = 0;
		double z = 0;
		while(s.hasNext()){
			x+= s.nextInt();
			z++;
		}
		System.out.println(x/z);
	}
}
