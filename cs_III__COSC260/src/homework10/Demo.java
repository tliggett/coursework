//TJ Liggett
//Homework 10
//Class Demo tests the equals method of class Time24 using class Generic
//11_13_2018
//Demo.java


public class Demo {
	
	public static void main(String[] args)
   	{
		Time24[] tArr = {new Time24(4, 20), new Time24(7, 11), new Time24(3, 14),
							new Time24(11, 11), new Time24(7, 37), new Time24(15, 16)};
		int index= Generic.seqSearch (tArr, new Time24(7, 37));
		if (index!=-1) System.out.println("Found at "+index);
		else System.out.println("Not found");

		index= Generic.seqSearch (tArr, new Time24(8, 14));
		if (index!=-1) System.out.println("Found at "+index);
		else System.out.println("Not found");
		
		index= Generic.seqSearch (tArr, new Time24(3, 14));
		if (index!=-1) System.out.println("Found at "+index);
		else System.out.println("Not found");
		
}

}
