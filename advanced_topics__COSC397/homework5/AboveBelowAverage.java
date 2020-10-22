//TJ Liggett
//10_2_2020
//Homework 5
//AboveBelowAverage.java

/*
 *	Class AboveBelowAverage uses functional programming to generate
 *       15 random numbers and prints the numbers that are above and below
 *       the average.
 *
 */

import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class AboveBelowAverage
{
	public static void main(String[] args)
    	{
		Random r = new Random();
		IntStream is = r.ints(15, 1, 100);
		int[] arr = is.sorted().toArray();
		System.out.printf("15 random numbers in ascending order: %s\n", 
				IntStream.of(arr)
				.mapToObj(String::valueOf)
				.collect(Collectors.joining(" ")));
							
		double average = IntStream.of(arr)
					.average()
					.getAsDouble();			

		System.out.printf("Average: %.2f\n", average);

		System.out.printf("%d numbers above the average: %s\n",
				IntStream.of(arr)
				.filter(x -> x > average)
				.count(),
				IntStream.of(arr)
				.filter(x -> x > average)
				.mapToObj(String::valueOf)
				.collect(Collectors.joining(" ")));

		System.out.printf("%d numbers below the average: %s\n\n",
				IntStream.of(arr)
				.filter(x -> x < average)
				.count(),
				IntStream.of(arr)
				.filter(x -> x < average)
				.mapToObj(String::valueOf)
				.collect(Collectors.joining(" ")));
	}
}
