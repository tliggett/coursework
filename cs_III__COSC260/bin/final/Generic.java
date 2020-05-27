//TJ Liggett
//Homework 11
//Class Generic provides many search and sort methods as well as a toString for arrays 
//using generics
//11_27_2018
//Generic.java

public class Generic
{	
	
	/**
	Sort an array of elements of type T using bubbleSort<p>
	<b>Pre:</b><br>T must be a Comparable<T> or Comparable<some superclass of 
	T><p>
	<b>Post:</b><br>The elements in arr sorted in ascending order based on the ordering as 
	defined by the compareTo method provided by class T.<p>
	*/ 
	public static <T extends Comparable<? super T>> void bubbleSort(T[] arr)                   
	{                                                  			
	  		 T temp;                                           
	  		 for (int pass = 1; pass<arr.length; ++pass)       	
	       			for (int i = 0; i< arr.length -pass; ++i)        	  	
	         				if (arr[i].compareTo(arr[i+1])>0)    	 
	         				{				
	         					temp=arr[i];      	
	         					arr[i]=arr[i+1];   			
	         					arr[i+1]=temp;   			
	         				}		
	} 	
	
	/**
	Sort an Object array using bubbleSort<p>
	<b>Pre:</b><br>The actual element type of arr is a Comparable<p>
	<b>Post:</b><br>The elements in arr sorted in ascending order based on the ordering as 
	defined by the compareTo method provided by the element type.<p>
	*/ 
	public static void bubbleSort(Object[] arr)
	{
		Object temp;                                           
 		for (int pass = 1; pass<arr.length; ++pass)       	
 			for (int i = 0; i< arr.length -pass; ++i)        	  	
 				if (((Comparable)arr[i]).compareTo(arr[i+1])>0)    	 
        		{				
        			temp=arr[i];      	
        			arr[i]=arr[i+1];   			
        			arr[i+1]=temp;   			
        		}	                                       
	}
	
	/**
	Sort an array of elements of type T using insertionSort<p>
	<b>Pre:</b><br>T must be a Comparable<T> or Comparable<some superclass of 
	T><p>
	<b>Post:</b><br>The elements in arr sorted in ascending order based on the ordering as 
	defined by the compareTo method provided by class T.<p>
	*/ 
	public static <T extends Comparable<? super T>> void insertionSort(T[] arr)                   
	{                                                  			
	   	int i;
		T pulled;	
	   	for (int pass=1; pass<arr.length; ++pass)         
	   	{                                            
	   		pulled=arr[pass];                  		
	        i=pass-1;                                 	
	        while (i>=0) 	
	       	{                                      
	        	if (pulled.compareTo(arr[i])<0)    	
	            {                                
	        		arr[i+1]=arr[i];                  	
	                i=i-1;                        	
	            }                                
	            else break;                  		
	       	 }                                      		
	         arr[i+1]=pulled;                            	
		}
	} 	
	
	/**
	Sort an Object array using insertionSort<p>
	<b>Pre:</b><br>The actual element type of arr is a Comparable<p>
	<b>Post:</b><br>The elements in arr sorted in ascending order based on the ordering as 
	defined by the compareTo method provided by the element type.<p>
	*/ 
	public static void insertionSort(Object[] arr)
	{
		int i;
		Object pulled;	
	   	for (int pass=1; pass<arr.length; ++pass)         
	   	{                                            
	   		pulled=arr[pass];                  		
	        i=pass-1;                                 	
	        while (i>=0) 	
	       	{                                      
	        	if (((Comparable)pulled).compareTo(arr[i])<0)    	
	            {                                
	        		arr[i+1]=arr[i];                  	
	                i=i-1;                        	
	            }                                
	            else break;                  		
	       	 }                                      		
	        arr[i+1]=pulled;
	   	}
	}
	
	/**
	Sort an array of elements of type T using selectionSort<p>
	<b>Pre:</b><br>T must be a Comparable<T> or Comparable<some superclass of 
	T><p>
	<b>Post:</b><br>The elements in arr sorted in ascending order based on the ordering as 
	defined by the compareTo method provided by class T.<p>
	*/ 
	public static <T extends Comparable<? super T>> void selectionSort(T[] arr)
	{
		int min;
	    T temp;                                  	     
	    for (int pass=0; pass<arr.length-1; ++pass)      
	   	{   
	    	min=pass;                                      	
	        for (int i=pass+1; i< arr.length; ++i)  	            		
	        	if (arr[i].compareTo(arr[min])<0) min=i;      	
			temp=arr[min];	
			arr[min]=arr[pass];	
			arr[pass]=temp;	
	   	}                                            
	}

	/**
	Sort an Object array using selectionSort<p>
	<b>Pre:</b><br>The actual element type of arr is a Comparable<p>
	<b>Post:</b><br>The elements in arr sorted in ascending order based on the ordering as 
	defined by the compareTo method provided by the element type.<p>
	*/ 
	public static void selectionSort(Object[] arr)
	{
		int min;
		Object temp;                                  	     
	   	for (int pass=0; pass<arr.length-1; ++pass)      
	   	{   
	   		min=pass;                                      	
	        for (int i=pass+1; i< arr.length; ++i)  	            		
	        if (((Comparable)arr[i]).compareTo(arr[min])<0) 	//line 9
						min=i;      	
			temp=arr[min];	
			arr[min]=arr[pass];	
			arr[pass]=temp;	
	   	}                                            
	}

	/**
	Search an array of elements of type T for a key using binary search <p>
	<b>Pre:</b><br>T must be a Comparable<T> or Comparable<some superclass of 
	T><br>
	Elements in arr sorted in ascending order based on compareTo provided by class T <p>
	<b>Return:</b><br>index of key in arr if found, -1 if not found<p>
	*/ 
	public static <T extends Comparable<? super T>> int binSearch(T[] arr, T key)
	{
	  	int low=0, high=arr.length-1, mid; 	
	    T midValue;    
	  	while (low<=high)                	
	    {
	  		mid=(low+high)/2;                   		
	        midValue = arr[mid];
	        if (key.compareTo(midValue) == 0) return mid;       
	        else if (key.compareTo(midValue) < 0) high = mid-1;       
	        else low = mid+1;
	    }
	    return -1;              
	}
	
	/**
	Searches a portion of an Object array for a key using binary search<p>
	<b>Pre:</b><br> The element type of arr should have method equals overridden<p>
	<b>Return:</b><br>index of key in arr if found, -1 if not found<p>
	*/
	public static int binSearch (Object[] arr, Object key)                 
	{                                                  	
		int low=0, high=arr.length-1, mid; 	
	    Object midValue;    
	  	while (low<=high)                	
	    {
	  		mid=(low+high)/2;                   		
	        midValue = arr[mid];
	        if (((Comparable)key).compareTo(midValue)== 0) return mid;       
	        else if (((Comparable)key).compareTo(midValue) < 0) high = mid-1;       
	        else low = mid+1;
	    }
	    return -1;                              	
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
	
	/**
	Convert an array to a string<p>
	<b>Pre:</b><br>The element type of arr should have method toString overridden<p>
	<b>Return:</b><br>A string in the format of [ele1, ele2, …] where ele's are the elements 
	of arr converted to a string using the elements' toString method.  Return [] if the array 
	has no element.  Return null if arr is null<p>
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
	
	
}

