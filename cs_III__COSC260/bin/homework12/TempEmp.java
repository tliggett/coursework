//Example 24-2-24
//TempEmp.java
class TempEmp extends Employee					
{
	private double hourlyPayRate;
	public TempEmp()				
	{
		super();							
		hourlyPayRate =-1.0;					
	}
	public TempEmp(int id, String name, double hourlyPayRate)	
	{
		super(id, name);					
		this.hourlyPayRate = hourlyPayRate;						
	}
	public double getHourlyPayRate()
	{
		return hourlyPayRate;
	}
	public void setHourlyPayRate(double s)
	{
		hourlyPayRate =s;
	}
	public String toString()								
	{
             	return "ID: "+ id +" Name: "+name+" HourlyPayRate: "+hourlyPayRate;
	}
	public void printCheck()
	{
		System.out.println("Pay employee "+id+" $" +(hourlyPayRate*40) + " for the week.");
	}
}

