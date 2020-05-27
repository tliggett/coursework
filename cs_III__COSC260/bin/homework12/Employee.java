//Example 24-2-23
//Employee.java
abstract class Employee implements Comparable<Employee>		//important
{
	protected int id;					
	protected String name;				
	public Employee()				
	{
		id=-1;	
		name="unknown";	
	}
	public Employee(int id, String name)	
	{
		this.id=id;	
		this.name=name;	
	}
	public int getId()				
	{	
		return id;		
	}
	public void setId(int id)			
	{
		this.id=id;	
	}
	public String getName()
	{
		return name;
	}
	public void setName(String name)		
	{
		this.name=name;	
	}
	public boolean equals(Object obj)	
	{
		Employee emp=(Employee)obj;
		if (id==emp.id) return true;		
		else return false;
	}
	public int compareTo(Employee emp)				
	{
		if (id>emp.id) return 1;		
		else if (id<emp.id) return -1;
		else return 0;
	}
	public String toString()
	{
              return "ID: "+ id +" Name: "+name;		
	}
	public abstract void printCheck();				
}

