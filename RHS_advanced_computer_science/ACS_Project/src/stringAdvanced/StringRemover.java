package stringAdvanced;
//(c) A+ Computer Science
//www.apluscompsci.com
//Name -

import static java.lang.System.*;

import java.util.regex.Pattern;

class StringRemover
{
   private String sentence;
   private String remove;
   
   public StringRemover(String s, String rem){
	   setRemover(s,rem);
	   
   }

	//add in constructors
	

	public void setRemover(String s, String rem)
	{
		sentence = s;
		remove = rem;
		
	}

	public String removeStrings()
	{
		String cleaned = sentence;
		String rep = "";
		cleaned = cleaned.replaceAll(remove, rep);
		return cleaned;
	}

	public String toString()
	{
		return "" + removeStrings();
	}
}