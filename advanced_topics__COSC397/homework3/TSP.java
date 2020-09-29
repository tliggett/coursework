//TJ Liggett
//9_18_2020
//Homework 3
//TSP.java

import java.util.*;
public class TSP
{	
	public static Character home='?';
	public static Vector< Character > otherCities=new Vector< Character >();
	public static Vector< String > pairs=new Vector< String >();
	public static Vector< String > reversePairs=new Vector< String >();//parallel to pairs
	public static Vector< Integer > pairWeight=new Vector< Integer >(); //parallel to pairs
	public static Vector< String > routes=new Vector< String >();
	public static Vector< Integer > routeWeight=new Vector< Integer >(); //parallel to routes

	public static int [][] pairsMatrix;

	public static Scanner input=new Scanner(System.in);
	
	public static void main(String[] args)
	{
		getHomeCity();	//set up home
		getOtherCities();	//set up otherCities
		findPairs(); 	//A possible pair is A, B.  All pairs stored in vector pairs
		getPairWeight();		//weight for each pair in pairs stored in pairWeight
		findReversePairs();	//all pairs in pairs stored in reversePairs in reverse
		
		double[] nearestNeighbor = nearestNeighbor();
		double[] actualWeight = bruteForceSearch();
		
		System.out.printf("\nNearest neighbor found minimum route of %d in %.3f seconds.\n",(int) nearestNeighbor[0], nearestNeighbor[1]);
		System.out.printf("Brute force found an accurate minimum route of %d in %.3f seconds.\n",(int) actualWeight[0], actualWeight[1]);
		System.out.printf("Nearest neighbor was %.2f percent greater than minimum route\n", (nearestNeighbor[0]/actualWeight[0] - 1)*100);
			
	}
	
	/**
	 * Finds minimum route between all cities by calculating every route weight
	 *
	 * @return double[]	first value is weight, second value is time for algorithm to complete
	 *
	 */
	public static double[] bruteForceSearch()
	{
		StopWatch timer = new StopWatch();
		timer.start();
		System.out.printf("***** Brute Force Method *****\n");
		
		/*
		 *	NOTE: This is the only surefire way to solve the TSP. We will use this to determine
		 *	the accuracy of the other searches
		 */

		routes = new Vector<String>();
		routeWeight = new Vector<Integer>();
		findRoutes(otherCities, 0);
		computeRouteWeight();
		
		for(int i = 0; i < routes.size(); ++i) 
			System.out.printf("%s: %d\n", routes.get(i), routeWeight.get(i));
		
		
		
		int minWeight=Collections.min(routeWeight);
		int minIndex=routeWeight.indexOf(minWeight);
		System.out.printf("Minimum weight %d is achieved by the route %s\n", minWeight, routes.get(minIndex));
		
		double time = timer.stop();
		System.out.printf("Brute Force Search completed in %.3f seconds.\n", time);	

		return new double[] {minWeight, time};
	}
	
	/**
	 * Finds a short route by finding the nearest neighbor to each city
	 *
	 * @return first value is weight, second value is time for algorithm to complete
	 *
	 */
	public static double[] nearestNeighbor()
	{
		StopWatch timer = new StopWatch();
		timer.start();
		System.out.printf("\n***** Nearest Neighbor Method *****\n");
		
		/*
		 *
		 *	The nearest neighbor algorithm
		 *
		 */

		Vector<Character> cities = (Vector<Character>) otherCities.clone();
		int weight = findNeighbor(home, cities);
		System.out.printf("%d\n", weight);
		
		double time = timer.stop();
		System.out.printf("Nearest Neighbor Search completed in %.3f seconds.\n\n", time);
		return new double[] {weight, time};
	}
	
	/**
	 * Finds the nearest neighbor to city in cities vector, then recursively finds neareast neighbor of each city. 
	 * 
	 * @param city		the city to find the neighbor of  
	 * @param cities	a list of cities to find the neighbor to city from
	 * @return int	a short route connecting each city to its nearest remaining neighbor
	 *
	 */
	public static int findNeighbor(char city, Vector<Character> cities)
	{
		System.out.printf("%s, ", city);
		if(cities.size() == 0)
		{
			System.out.printf("%s: ", home);
			return pairsMatrix[otherCities.size()][otherCities.indexOf(city)]; 
		}
		else
		{
			int index = otherCities.indexOf(city);
			int minIndex = 0;
			int minValue = Integer.MAX_VALUE;
			if(index == 0) minIndex = 1;
			char newCity = cities.get(0);
			if(index == -1) index = otherCities.size();
			for(int j = 0; j < pairsMatrix.length - 1; ++j)
			{
				if(index == j) continue;
				if(pairsMatrix[index][j] < minValue)
				{
					if(cities.indexOf(otherCities.get(j)) != -1)
					{
						minIndex = j;
						minValue = pairsMatrix[index][j];
						newCity = otherCities.get(j);
					}
					
				}
			}
			int count = pairsMatrix[index][minIndex];
			cities.remove(cities.indexOf(newCity));
			return count + findNeighbor(newCity, cities);
		}

	}
	
	/**
	 * Recursively finds all routes between cities in Vector cities, and adds them to routes vector
	 *
	 * @param cities	a vector of cities to find routes between
	 * @param index		the current index to swap from
	 *
	 */
	public static void findRoutes(Vector<Character> cities, int index)
	{
		 
		if(index == cities.size() - 1)
		{
			String route = "";
			route += home + ", ";
			for(Character c : cities)
			{
				route += c + ", ";
			}
			route += home;
			routes.add(route);
		}
		else
		{
			Vector<Character> newArr = (Vector<Character>)(cities.clone());
			findRoutes(newArr, index+1);
			for (int i=index+1; i < cities.size(); i++)
			{
				newArr = (Vector<Character>) (cities.clone()); 
				Collections.swap(newArr, index, i);//swap newArr[index] newArr[i]
				findRoutes(newArr, index+1);
			}
		}
	}

	/**
	 * Computes the route weight for each route between the cities
	 *
	 */
	public static void computeRouteWeight()
	{
		for(int i = 0; i < routes.size(); ++i)
		{
			int weight = 0;
			int index = 0;
			String pair = "";
			while(index+4 < routes.get(i).length() + 1)
			{
				pair = routes.get(i).substring(index,index+4);
				int pairIndex = pairs.indexOf(pair);
				if(pairIndex == -1) pairIndex = reversePairs.indexOf(pair);
				weight += pairWeight.get(pairIndex);
				index = index + 3;
			}
			routeWeight.add(weight);
		}
	}

	/**
	 * Prompts user for home city
	 *
	 * @output: prompt for home city
	 * @input: the home city character
	 *
	 */
	public static void getHomeCity()
	{
		System.out.printf("Home city: \n");
		home = input.nextLine().charAt(0);
	}

	/**
	 * Prompts user for other cities
	 *
	 * @output: prompt for other cities
	 * @input: a series of city characters followed by newline to end
	 */
	public static void getOtherCities()
	{
		System.out.printf("Enter cities, one on each line, newline to end: \n");
		String line = input.nextLine();
		while(line.length() > 0)
		{
			otherCities.add(line.charAt(0));
			line = input.nextLine();
		}
	}

	
	/**
	 * generates a vector of pairs of cities
	 *
	 */
	public static void findPairs()
	{
		for(int i = 0; i < otherCities.size(); ++i)
		{
			pairs.add(home + ", " + otherCities.get(i));
			for(int j = i + 1; j < otherCities.size(); ++j)
			{
				pairs.add(otherCities.get(i) + ", " + otherCities.get(j));
			}
		}
		Collections.sort(pairs);
	}

	/**
	 * Prompts user for weights of each pair
	 *
	 * @output: Various user prompts
	 * @input: the weight of each pair (integer)
	 *
	 */
	public static void getPairWeight()
	{
		System.out.printf("Enter weight for each of the following pair: \n");
		pairsMatrix = new int [otherCities.size()+1][otherCities.size()+1];
		for(int i = 0; i < pairs.size(); ++i)
		{
			System.out.printf("%s: ", pairs.get(i));
			
			int weight = input.nextInt();
			pairWeight.add(weight);

			char first = pairs.get(i).charAt(0);
			char second = pairs.get(i).charAt(3);
			if(first == home)
			{
				pairsMatrix[otherCities.size()][otherCities.indexOf(second)] = weight;
				pairsMatrix[otherCities.indexOf(second)][otherCities.size()] = weight;
			}
			else
			{
				pairsMatrix[otherCities.indexOf(first)][otherCities.indexOf(second)] = weight;
				pairsMatrix[otherCities.indexOf(second)][otherCities.indexOf(first)] = weight;
			}

			input.nextLine();
		}
	}

	/**
	 * Finds the reverse pair for each pair of cities
	 *
	 * example: A, E becomes E, A
	 */
	public static void findReversePairs()
	{
		for(String pair : pairs)
		{
			String reverse = pair.charAt(3) + ", " + pair.charAt(0);
			reversePairs.add(reverse);
		}
	}
}


/**
 Class StopWatch supports objects representing a stop watch for measuring the time required to
 execute a process. Time is measured in seconds.
 */
class StopWatch
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
