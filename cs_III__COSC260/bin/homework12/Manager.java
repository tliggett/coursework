//Example 24-2-25
//Manager.java
class Manager extends Employee					
{
	private double salary;				
	private String dept;				
	public Manager ()				
	{
		super();							
		salary =0.0;					
		dept ="unknown";					
	}
	public Manager (int id, String name, double salary, String dept)	
	{
		super(id, name);					
		this.salary = salary;						
		this.dept = dept;						
	}
	public double getSalary()
	{
		return salary;
	}
	public void setSalary(double salary)		
	{
		this.salary = salary;						
	}
	public String getDept()
	{
		return dept;
	}
	public void setDept(String dept)		
	{
		this.dept = dept;	
	}
	public String toString()
	{
        return "ID: "+ id +" Name: "+ name +" Salary: "+salary +" Dept: "+dept;
	}
	public void printCheck()
	{
		System.out.println("Pay employee " + id + " $" +(salary/12) + " for the month.");
	}
}

