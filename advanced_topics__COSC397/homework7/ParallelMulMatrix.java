//TJ Liggett
//10_16_2020
//Homework 7
//ParallelMulMatrix.java

import java.util.*;
import java.util.concurrent.*;

public class ParallelMulMatrix
{
	//Desc: 	Multiply 2 square matrices
	//Input: 	User enters the dimension of the square matrix
	public static void main(String[] args) throws InterruptedException
	{
		Random rnd=new Random(2L);		
		Scanner input=new Scanner(System.in);		
		System.out.print("Enter dimension of square matrix: ");		
		int n=input.nextInt();
		int[][] m1=new int[n][n];
		System.out.println("Generating values for m1...");		
      		for (int i=0; i<m1.length; ++i)	
      			for (int j=0; j<m1[i].length; ++j)	
                            			m1[i][j]=rnd.nextInt(10);
		int[][] m2=new int[n][n];
		System.out.println("Generating values for m2...");		
      		for (int i=0; i<m2.length; ++i)	
      			for (int j=0; j<m2[i].length; ++j)	
                            			m2[i][j]=rnd.nextInt(10);
		int[][] result=new int[n][n];
      	
		

		MulMatrixEvenTask even = new MulMatrixEvenTask(m1, m2, result);
		MulMatrixOddTask odd = new MulMatrixOddTask(m1, m2, result);
		ExecutorService executor = Executors.newCachedThreadPool();
		
		executor.execute(even);	
		executor.execute(odd);
		executor.shutdown();
		

		// Waits for the threads to finish before printing the array
		boolean threadsDone = executor.awaitTermination(10, TimeUnit.MINUTES);
		if (threadsDone) 
		{
			System.out.println("Product of m1 and m2:");		
			if (n<20) printMatrix(result);	
			else System.out.println("***********matrix too big for printing");		
		}
		else 
		{
			System.out.println("The process took longer than 10 minutes! Something went wrong or your array is too big!");
		}
		

	}
	//Output:matrix m printed on the screen
	public static void printMatrix(int[][] m)
	{
		for (int i=0; i <m.length; ++i)	
		{			
			for (int j=0; j <m[i].length; ++ j)			
				System.out.printf("%5d", m[i][j]);
				System.out.println();
		}
	}
}

//Class MulMatrixEvenTask multiplies the even rows of matrix
class MulMatrixEvenTask implements Runnable
{
	private int[][] m1, m2, result;
	public MulMatrixEvenTask(int[][] arr1, int[][] arr2, int[][] r)
	{
		m1 = arr1;
		m2 = arr2;
		result = r;
	}
	public void run()
	{
		Date d = new Date();
      		System.out.println("MulMartixEvenTask started at\t" + d.getMinutes()+":"+d.getSeconds());		
		for (int i=0; i <m1.length; i +=2)
		{
			for (int j=0; j <m1[i].length; ++ j)
                        {
                                int sum=0;
                                for (int k=0;k <m1[i].length; ++k)
                                        sum+= m1[i][k]*m2[k][j];
                                result[i][j]=sum;
                        }
		}

		d = new Date();
      		System.out.println("MulMartixEvenTask finished at\t" + d.getMinutes()+":"+d.getSeconds());		
	}

}

//Class MulMatrixOddTask multiplies the odd rows of matrix
class MulMatrixOddTask implements Runnable
{
	private int[][] m1, m2, result;
	public MulMatrixOddTask(int[][] arr1, int[][] arr2, int[][] r)
	{
		m1 = arr1;
		m2 = arr2;
		result = r;
	}
	public void run()
	{
      		Date d = new Date();
      		System.out.println("MulMartixOddTask started at\t" + d.getMinutes()+":"+d.getSeconds());		
		
		for (int i=1; i <m1.length; i +=2)
		{
			for (int j=0; j <m1[i].length; ++ j)
                        {
                                int sum=0;
                                for (int k=0;k <m1[i].length; ++k)
                                        sum+= m1[i][k]*m2[k][j];
                                result[i][j]=sum;
			}
		}

		d = new Date();
      		System.out.println("MulMartixOddTask finished at\t" + d.getMinutes()+":"+d.getSeconds());		
	}

}
