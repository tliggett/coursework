//TJ Liggett
//Homework 11
//Class Driver1 tests the binSearch methods in class Generic
//11_27_2018
//Driver1.java

public class Driver1 {
	public static void main (String[] args)
	{
		String[] strArr = {"blue", "green", "red"};
		Integer[] intArr = {40, 70, 150, 230};
		Time24[] timeArr = {new Time24(1,15), new Time24(2, 45),
                          				new Time24(3,00), new Time24(4,30)};
		Object[] objArr1 = {"alpha", "bravo", "charlie", "delta"};
		Object[] objArr2 = {new Manager(111, "John Johnson", 60000.0, "Accounting"), 
							new Manager(222, "Peter Peterson", 40000.0, "Meat"),
							new Manager(333, "Paul Paulson", 50000.0, "Produce")};
		int index= Generic.binSearch(strArr, "red");
		result(index);
		index= Generic.binSearch(strArr, "yellow");
		result(index);
		index= Generic.binSearch(intArr, 150);
		result(index);
		index= Generic.binSearch(timeArr, new Time24(4,30));
		result(index);
		index = Generic.binSearch(objArr1, "bravo");
		result(index);
		index = Generic.binSearch(objArr1, "epsilon");
		result(index);
		index = Generic.binSearch(objArr2, new Manager(333, "Paul Paulson", 
															50000.0, "Produce"));
		result(index);
	}
	
	public static void result(int index)
	{
		if (index!=-1) System.out.println("Found at " + index);
		else System.out.println("Not found");
	}

}
