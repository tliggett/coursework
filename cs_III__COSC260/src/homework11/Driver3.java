//TJ Liggett
//Homework 11
//Class Driver3 tests the bubbleSort methods in class Generic
//11_27_2018
//Driver3.java

public class Driver3 {
	public static void main (String[] args)
	{
		String[] strArr = {"red", "green", "blue"};
		Integer[] intArr = {40, 70, 50, 30, -99, 0};
		Time24[] timeArr = {new Time24(14,15), new Time24(10, 45),
                          				new Time24(22,00), new Time24(3,30)};
		Object[] intArr1 = {10, 9, 7, 5, 3, 3, 5, 99, -7, 0};
		Object[] manArr = { new Manager(111, "John Johnson", 60000.0, "Accounting"),
                          	 new Manager(333, "Paul Paulson", 50000.0, "Produce"),      
                    		 new Manager(222, "Peter Peterson", 40000.0, "Meat")};
		Generic.bubbleSort(strArr);
		System.out.println(Generic.arrToString(strArr));
		Generic.bubbleSort(intArr);
		System.out.println(Generic.arrToString(intArr));
		Generic.bubbleSort(timeArr);
		System.out.println(Generic.arrToString(timeArr));
		Generic.bubbleSort(intArr1);
		System.out.println(Generic.arrToString(intArr1));
		Generic.bubbleSort(manArr);
		System.out.println(Generic.arrToString(manArr));
	}

}
