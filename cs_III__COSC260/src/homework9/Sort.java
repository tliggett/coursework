package homework9;

//TJ Liggett
//Homework 9
//Sort.java
//Class Sort takes an array of random integers and sorts it in ascending order using insertion sort

import java.util.Random;

public class Sort {
	
	public static void main(String[] args)
	{
		Random r = new Random();
		final int SIZE = 25;
		int[] test = new int[SIZE];
		for(int i = 0; i<SIZE; ++i) 
		{
			test[i] = r.nextInt(SIZE);
			System.out.print(test[i]+ " ");
		}
		System.out.println();
		insertionSort(test, SIZE-1);
		for(int i : test) System.out.print(i+ " ");
		
		
	}
	//Desc: Sorts an array of integers in ascending order
	//Post: Integer array arr is sorted in ascending order from 0 to high
	public static void insertionSort(int[] arr, int high)
	{
		if(high == 0) return;
		insertionSort(arr, high-1);
	   	int pulled=0, i=0;                                           
	    pulled=arr[high];                           	
	    i=high-1;                                 	
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
