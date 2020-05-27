package scannerFiles;


import static java.lang.System.*;

import java.util.Scanner;

public class Box
{
   private String let;
   private int size;

	public Box(){
		let = "";//
		size = 0;
		
	}
	public Box(String g){
		Scanner gage = new Scanner(g);
		let = gage.next();
		size = gage.nextInt();
		
	}

	public String toString()
	{
		String output="";
		for(int i = 0; i<size; i++){
			for(int j = 0; j<size; j++){
				System.out.print(let);	
			}
			System.out.println("");
		}
		


		return output+"\n";
	}
}