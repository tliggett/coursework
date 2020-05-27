package isosceles;
//Example 12-2-11
//Shape2D.java
/**
Class Shape2D is abstract.  Its subclass objects represent different shapes such as triangle and rectangle.  Each Shape2D object knows its area and perimeter.
*/
public abstract class Shape2D					
{
	protected double area;					
	protected double perimeter;				
	//Post: area and perimeter of this Shape2D object initialized to 0.0
	public Shape2D()				
	{
		area=0.0;	
		perimeter =0.0;	
	}
	//Return:	area of this Shape2D object
	public double getArea()			
	{
		return area;
	}
	//Return:	perimeter of this Shape2D object
	public double getPerimeter()			
	{
		return perimeter;
	}
	//Desc:	Compare this Shape2D to another Shape2D based on area
	//Return:	true if the area of this Shape2D object is the same as the area of obj, false otherwise
	public boolean equals(Object obj)	
	{
		if (obj instanceof Shape2D)
		{
			Shape2D s=(Shape2D)obj;
			if (area==s.area) return true;		
			else return false;
		}
		return false;
	}
	//Desc:	Compare this Shape2D to another Shape2D based on area
	//Return: 1 if the area of this Shape2D object is > the area of s
	//	0 if the area of this Shape2D object is == the area of s
	//	-1 if the area of this Shape2D object is < the area of s <p>
	public int compareTo(Shape2D s)
	{
		if (area>s.area) return 1;
		else if (area<s.area) return -1;
		else return 0;
	}
	public abstract String toString();	
	public abstract void draw();	
} 
