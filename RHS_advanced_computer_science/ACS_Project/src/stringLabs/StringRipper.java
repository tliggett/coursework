package stringLabs;

//� A+ Computer Science  -  www.apluscompsci.com
//Name -
//Date -
//Class -
//Lab  -

import static java.lang.System.*;

public class StringRipper
{
	private String word;
	
	public StringRipper()
	{
	}

	public StringRipper(String s)
	{
		word = s;
	}
	
 public void setString(String s)
 {
	 word = s;
 }	

	public String ripString(int x, int y)
	{
		int fst = 0;
		int lst = 0;		
		
		fst = x;
		lst = y;
			
		if(fst < 0){
			fst = 0;
			
		}
		int max = word.length() - 1;
		if(lst > max){
			lst = max;
			
		}
		return "" + word.substring(fst, lst);
	}

	public String toString()
	{
		return word + "\n\n";
	}
}