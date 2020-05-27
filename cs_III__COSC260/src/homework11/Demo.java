package homework11;

//Example 24-3-4
//Demo.java
class Demo
{  
 	public static void main(String[] args)
 	{
		Entry<String,Time24> r1=new Entry<String,Time24>("John", new Time24(8, 38));
		Entry<String,Time24> r2=new Entry<String,Time24>("Paul", new Time24(7, 38));
		System.out.println("Check in time of "+r1.getKey()+" is " +r1.getValue());	
		System.out.println("Check in time of "+r2.getKey()+" is " +r2.getValue());	
		if (r1.equals(r2)) System.out.println("Same name");			//line 11
		else System.out.println("Different name");
		if (r1.compareTo(r2)>0) System.out.println("John>Paul");		//line 13
		else if (r1.compareTo(r2)==0) System.out.println("John==Paul");
		else System.out.println("John<Paul");

	}
}

