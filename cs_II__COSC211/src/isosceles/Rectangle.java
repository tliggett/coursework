package isosceles;

//Example 12-2-12
//Rectangle.java
/**
Class Rectangle supports objects representing rectangles.  Each rectangle object knows its height, width, area, and perimeter.
*/
public class Rectangle extends Shape2D
{
	private double height;				
	private double width;				
	private void calcAreaAndPerimeter()		
	{
		area = height * width;		
		perimeter = 2.0* (height + width);	
	}
	public Rectangle()
	{
		super();
		height = width = 0.0;
	}
	public Rectangle(double height, double width)
	{
		super();
		this.height = height;
		this.width = width;
		calcAreaAndPerimeter();		
	}
	public void setHeight(double height)			
	{
		this.height = height;
		calcAreaAndPerimeter();		
	}
	public void setWidth(double width)			
	{
		this.width = width;
		calcAreaAndPerimeter();		
	}
	public double getHeight()			
	{
		return height;
	}
	public double getWidth()			
	{
		return width;
	}
//Desc:	Compare this Rectangle to another Rectangle based on dimensions
//Return:	true if the width and height of this Rectangle object is the same as the width and height of 
//	obj, false otherwise
public boolean equals(Object obj)	
{
	if (obj instanceof Rectangle)
		{
			Rectangle c=(Rectangle)obj;	
			if ((width==c.width)&&(height ==c.height))
				return true;
			else return false;
		}
		return false;
	}
	public String toString()
	{
		return "Width: "+width+" Height: "+height +" Area: "+area+" Perimeter: "+perimeter;
	}
	//Desc:	Draw this Rectangle on the screen
	//Output:This Rectangle object drawn on the screen
	public void draw()
	{
		String s="";
		for (int i=1; i<=height; ++i)         				                          
		{                                                      
         			for (int j=1; j<=width; ++j)				
				s+="*";                                    
			System.out.println(s);				
			s="";						
		}  
	}
}
