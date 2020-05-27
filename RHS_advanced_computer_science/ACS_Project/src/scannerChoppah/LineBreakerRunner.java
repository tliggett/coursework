//� A+ Computer Science  -  www.apluscompsci.com
//Name -
//Date -
//Class - 
//Lab  -
package scannerChoppah;
import java.util.ArrayList;
import java.util.Scanner;
import static java.lang.System.*;

import java.io.File;
import java.io.FileNotFoundException;

public class LineBreakerRunner
{
   public static void main(String args[]) throws Exception
   {
	   Scanner gage = new Scanner(new File ("src/scannerfiles/scannerfiles2.txt"));
	   ArrayList<String> mat = new ArrayList<String>();
	   while(gage.hasNextLine()){
		   mat.add(gage.nextLine());
	   }
	   for(int i = 0; i < mat.size(); i+= 2){
		   int e = 0;
          e = Integer.parseInt(mat.get(i+1));
          LineBreaker lin = new LineBreaker(mat.get(i), e);
   	   System.out.println(lin);
	   }
	   
	}
}