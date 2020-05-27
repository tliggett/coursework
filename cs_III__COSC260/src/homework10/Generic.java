package homework10;

//Generic.java
public class Generic
{
	/**
	Convert an array to a string<p>
	<b>Pre:</b><br>The element type of arr should have method toString overridden<p>
	<b>Return:</b><br>A string in the format of [ele1, ele2, …] where ele's are the elements 
	of arr converted to a string using the elements' toString method.  Return [] if the array has no element.  Return null if arr is null<p>
	 */
	public static String arrToString(Object[] arr)
	{
		if (arr == null) return "null";
		else if (arr.length == 0) return "[]";
		String str = "[" + arr[0];			//line 8
		for (int i = 1; i < arr.length; i++)
			str +=  ", " + arr[i];		//line 10
		str += "]";
		return str;
	}
	
	/**
	Searches a portion of an Object array for a key using sequential search<p>
	<b>Pre:</b><br> The element type of arr should have method equals overridden<p>
	<b>Return:</b><br>index of key in arr if found, -1 if not found<p>
	*/
	public static int seqSearch (Object[] arr, Object key)                 
	{                                                  	
	  	int index=0;
	  	while (index <arr.length)          	
	      	 if (arr[index].equals(key)) 		//line 8
	      		 return index;           	
	      	 else index ++;                            	
	  	return(-1);                              	
	}

	
	
	
}

