package queues;

public class PQTestRunner
{
	public static void main ( String[] args )
	{
		PQTester qt = new PQTester("one two three four five six seven");	
		System.out.println(qt);
		System.out.println(qt.getMin());
		System.out.println(qt.getNaturalOrder());
		
		qt = new PQTester("1 2 3 4 5 one two three four five");	
		System.out.println(qt);
		System.out.println(qt.getMin());
		System.out.println(qt.getNaturalOrder());
		
		qt = new PQTester("a p h j e f m c i d k l g n o b");	
		System.out.println(qt);
		System.out.println(qt.getMin());
		System.out.println(qt.getNaturalOrder());
	}
}