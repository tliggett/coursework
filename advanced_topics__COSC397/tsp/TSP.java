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
		double[] christofides = christofides();
		double[] actualWeight = bruteForceSearch();
		
		/*	
		for(int i = 0; i < pairsMatrix.length; ++i)
		{
			for(int j = 0; j < pairsMatrix[i].length; ++j)
			{
				System.out.printf("%2d ", pairsMatrix[i][j]);
			}
			System.out.printf("\n");
		}
		*/
		
		/*
		computeRouteWeight();//weight for each route stored in routeWeight
		System.out.println("\nWeight for each of the following route:");
		for (int i=0; i < routes.size(); i++)
		{
			String path=routes.get(i);
			int pathWeight= routeWeight.get(i);
			System.out.println(path+": "+pathWeight);
		}
		int minWeight=Collections.min(routeWeight);
		int minIndex=routeWeight.indexOf(minWeight);
		System.out.printf("\nMinimum weight %d is achieved by the route %s\n", 
					minWeight, routes.get(minIndex));
	
		*/
	}

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

	public static double[] christofides()
	{
		StopWatch timer = new StopWatch();
		timer.start();
		System.out.printf("\n***** Christofides Algorithm *****\n");

		/*
		 *
		 * 	Christofides Algorithm
		 *
		 * 	got some help from http://matejgazda.com/christofides-algorithm-in-python/
		 */

		int weight = 0;
		// create a minimum spanning tree
		Vector<String> edges = minimumSpanningTree();
		// get odd degree vertices
		Vector<Character> oddVertices = getOddVertices(edges);
		// From odd degree vertices, make minimal-weight perfect matching
		//Vector<String> mwpm = mwpm(oddVertices);
		// Add edges from 3 into minimal spanning tree
		//for(String s : mwpm) edges.add(s);
		// Create euler tour
		// remove repeated vertices	

		System.out.println(edges);
		System.out.println(oddVertices);


		double time = timer.stop();
		System.out.printf("Christofides Algorithm Search completed in %.3f seconds.\n\n", time);
		return new double[] {weight, time};
		
	}
	/*
	public static Vector<String> mwpm(Vector<Character> oddVertices)
	{

	}
	*/
	public static Vector<Character> getOddVertices(Vector<String> edges)
	{
		Vector<Character> vertices = new Vector<Character>();
		Vector<Character> cities = (Vector<Character>)otherCities.clone();
		cities.add(home);
		for(Character city : cities)
		{
			int count = 0;
			for(String edge : edges) if(edge.indexOf(city) != -1) ++count;
			if(count % 2 != 0) vertices.add(city);
		}
		return vertices;
	}

	public static Vector<String> minimumSpanningTree()
	{
		// find a minimum spanning tree using prim's algorithm
		Vector<String> edges = new Vector<String>();
		Vector<Character> visitedCities = new Vector<Character>();
		Vector<Character> unvisitedCities = (Vector<Character>) otherCities.clone();
		visitedCities.add(home);
		
		while(unvisitedCities.size() > 0)
		{
			int visitedIndex = 0;		// keeps locations in local vectors
			int unvisitedIndex = 0;
			int minValue = Integer.MAX_VALUE;
			
			for(int i = 0; i < visitedCities.size(); ++i)
			{
				int iloc = otherCities.indexOf(visitedCities.get(i));
				if(iloc == -1) iloc = otherCities.size();
				for(int j = 0; j < unvisitedCities.size(); ++j)
				{
					int jloc = otherCities.indexOf(visitedCities.get(i));
	                                if(jloc == -1) jloc = otherCities.size();
					if(pairsMatrix[iloc][jloc] < minValue)
					{
						visitedIndex = i;
						unvisitedIndex = j;
						minValue = pairsMatrix[iloc][jloc];
					}
				}
			}

			String edge = visitedCities.get(visitedIndex) + ", " + unvisitedCities.get(unvisitedIndex);
			edges.add(edge);
			visitedCities.add(unvisitedCities.remove(unvisitedIndex));

		}
		return edges;

	}

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

	public static void getHomeCity()
	{
		System.out.printf("Home city: \n");
		home = input.nextLine().charAt(0);
	}

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

//https://www.sanfoundry.com/java-program-implement-edmonds-algorithm-maximum-cardinality-matching/
public static int lca(int[] match, int[] base, int[] p, int a, int b)
{
        boolean[] used = new boolean[match.length];
        while (true)
        {
            a = base[a];
            used[a] = true;
            if (match[a] == -1)
                break;
            a = p[match[a]];
        }
        while (true)
        {
            b = base[b];
            if (used[b])
                return b;
            b = p[match[b]];
        }
    }

    static void markPath(int[] match, int[] base, boolean[] blossom, int[] p,
            int v, int b, int children)
    {
        for (; base[v] != b; v = p[match[v]])
        {
            blossom[base[v]] = blossom[base[match[v]]] = true;
            p[v] = children;
            children = match[v];
        }
    }

    static int findPath(List<Integer>[] graph, int[] match, int[] p, int root)
    {
        int n = graph.length;
        boolean[] used = new boolean[n];
        Arrays.fill(p, -1);
        int[] base = new int[n];
        for (int i = 0; i < n; ++i)
            base[i] = i;
        used[root] = true;
        int qh = 0;
        int qt = 0;
        int[] q = new int[n];
        q[qt++] = root;
        while (qh < qt)
        {
            int v = q[qh++];
            for (int to : graph[v])
            {
                if (base[v] == base[to] || match[v] == to)
                    continue;
                if (to == root || match[to] != -1 && p[match[to]] != -1)
                {
                    int curbase = lca(match, base, p, v, to);
                    boolean[] blossom = new boolean[n];
                    markPath(match, base, blossom, p, v, curbase, to);
                    markPath(match, base, blossom, p, to, curbase, v);
                    for (int i = 0; i < n; ++i)
                        if (blossom[base[i]])
                        {
                            base[i] = curbase;
                            if (!used[i])
                            {
                                used[i] = true;
                                q[qt++] = i;
                            }
                        }
                }
                else if (p[to] == -1)
                {
                    p[to] = v;
                    if (match[to] == -1)
                        return to;
                    to = match[to];
                    used[to] = true;
                    q[qt++] = to;
                }
            }
        }
        return -1;
    }

    public static int maxMatching(List<Integer>[] graph)
    {
        int n = graph.length;
        int[] match = new int[n];
        Arrays.fill(match, -1);
        int[] p = new int[n];
        for (int i = 0; i < n; ++i)
        {
            if (match[i] == -1)
            {
                int v = findPath(graph, match, p, i);
                while (v != -1)
                {
                    int pv = p[v];
                    int ppv = match[pv];
                    match[v] = pv;
                    match[pv] = v;
                    v = ppv;
                }
            }
        }
        int matches = 0;
        for (int i = 0; i < n; ++i)
            if (match[i] != -1)
                ++matches;
        return matches / 2;
    }
