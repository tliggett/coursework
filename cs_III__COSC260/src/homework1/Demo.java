//TJ Liggett
//Homework 10
//Class Demo uses method rotateLeft to rotate integers left x number of bits
//11_13_2018
//Demo.java

public class Demo {
	public static void main(String[] args) 
	{
		int x1=3, x2=-11;
		x1 = rotateLeft(x2, 4);
		System.out.print("x1:                ");			
   		BitWise.printbit(x1);			
   		System.out.print("rotateLeft(x1,2):  ");					
   		BitWise.printbit(rotateLeft(x1,2));	
   		System.out.print("x2:                ");			
   		BitWise.printbit(x2);			
   		System.out.print("rotateLeft(x2,2):  ");					
   		BitWise.printbit(rotateLeft(x2,2)); 		
	}
	public static int rotateLeft(int i, int distance)
	{
		int store = 0, mask = -1, ret = i;
		
		mask >>>= distance;
		mask = ~mask;
		store = ret&mask;
		
		store >>>= 32-distance;
		ret <<= distance;
		
		ret = ret|store;
		
		return ret;
	}

}
