package homework8;

import java.util.Random;
import java.util.Scanner;
import homework4.Time24;

public class BigORandom {
	public static void main(String[] args)
	{
		System.out.println("\t\tsortA\t\t\tsortB\t\t\tSortC");
		for(int SIZE = 20000; SIZE <= 640000; SIZE*=2)
		{
			StopWatch sw = new StopWatch();
			Random rnd = new Random();
			int[] arrOriginal=new int[SIZE];		
			int[] arr= new int[SIZE];
			for (int i = 0; i < SIZE; i++)
				arrOriginal [i] = rnd.nextInt(100000);
				//arrOriginal[i] = i;
			System.out.printf("%d",SIZE);

			IntArray.copyArr(arrOriginal, arr);
			sw.start();
			IntArray.bubbleSort(arr);			
			System.out.printf("\t\t%.6f", sw.stop());

			IntArray.copyArr(arrOriginal, arr);
			sw.start();
			IntArray.selectionSort(arr);			
			System.out.printf("\t\t%.6f", sw.stop());

			IntArray.copyArr(arrOriginal, arr);
			sw.start();
			IntArray.insertionSort(arr);			
			System.out.printf("\t\t%.6f", sw.stop());

			System.out.println();
	}
}





/**
Class StopWatch supports objects representing a stop watch for measuring the time required to execute a process.  Time is measured in seconds.
*/
static class StopWatch
{
      	private long startTime, stopTime;
/**
Initializes this StopWatch object<p>
<b>Post:</b><br> startTime and stopTime of this StopWatch object initialized to 0<p>
*/
	public StopWatch()
      	{
         		startTime = stopTime = 0;
      	}
/**
Starts this StopWatch <p>
<b>Post:</b><br> startTime of this StopWatch set to the current system clock time in 
nanosecond <p>
*/
      	public void start()
      	{
         		startTime = System.nanoTime();
      	}
/**
Stops this StopWatch <p>
<b>Post:</b><br> stopTime of this StopWatch set to the current system clock time in 
nanosecond <p>
<b>Return:</b><br>Interval of time from start to stop measured in seconds.
*/
      	public double stop()
      	{
         		stopTime = System.nanoTime();
         		return (stopTime - startTime)/1000000000.0;
   	}
}




/**
The IntArray class contains useful methods to manipulate int arrays.
*/
static class IntArray
{
/**
Sorts an int array using bubble sort<p>
<b>Post:</b><br> arr sorted in ascending order <p>
*/
public static void bubbleSort(int[] arr)                    
{                                                  			
  		 int pass, i, temp;                                           
  		 for (pass = 1; pass<arr.length; ++pass)       	
       			for (i = 0; i< arr.length -pass; ++i)        	  	
         				if (arr[i] > arr[i+1])    	 
				{				
					temp=arr[i];      	
					arr[i]=arr[i+1];   			
					arr[i+1]=temp;   			
				}		
} 	
/**
Sorts an int array using selcetion sort<p>
<b>Post:</b><br> arr sorted in ascending order <p>
*/
public static void selectionSort(int[] arr)                 
{                                                  			
   	int pass, min, i, temp;                                       
   	for (pass=0; pass<arr.length-1; ++pass)      
   	{   
        		min=pass;                                      		
         		for (i=pass+1; i< arr.length; ++i)  	
			if (arr[i]<arr[min])  	
				min=i;      			
		temp=arr[min];			
		arr[min]=arr[pass];	
		arr[pass]=temp;	
   	}                                           		
}                                                  		
/**
Sorts an int array using insertion sort<p>
<b>Post:</b><br> arr sorted in ascending order <p>
*/
public static void insertionSort(int[] arr)                 
{                                                  
   	int pass, pulled, i;	
   	for (pass=1; pass<arr.length; ++pass)         
   	{                                            
         		pulled=arr[pass];                  		
         		i=pass-1;                                 	
         		while (i>=0) 	
       	  	{                                      
              			if (pulled<arr[i])    	
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
Searches an int array using sequential search for a key<p>
<b>Return:</b><br>index of key in arr if found, -1 if not found<p>
*/
public static int seqSearch (int[] arr, int key)                 
{                                                  	
  	int index=0;
  	while (index <arr.length)          	
      		if (key==arr[index]) 		
			return index;           		
      		else index ++;                     	
  	return(-1);                              		
}	
/**
Searches an int array using binary search for a key<p>
<b>Pre:</b><br> arr sorted in ascending order <p>
<b>Return:</b><br>index of key in arr if found, -1 if not found<p>
*/
public static int binSearch (int[] arr, int key)                 
{                                                  
  	int low=0, high=arr.length-1, mid; 	
  	while (low<=high)                	
  	{                                          
mid=(low+high)/2;                   		
if (key==arr[mid])		
return mid;            		
else if (key<arr[mid]) 		
high=mid-1;        		
else low=mid+1;                         		
   	}                                          
   	return (-1);                             		
}			
/**
Copies and int array to another int array<p>
<b>Pre:</b><br>source and destination have the same length<p>
<b>Post:</b><br>destination is an exact copy of source<p>
*/
public static void copyArr(int[] source, int[] destination)
{
	for (int j=0; j< source.length; ++j)	
                           	destination [j]= source [j];
}
}

}




