package isosceles;

public class IsoscelesTriangle extends Shape2D
{
	private double base;
	private double height;
	
	//Post: base, height, area, and perimeter set to 0.0;
	public IsoscelesTriangle()
	{
		super();
		base = 0.0;
		height = 0.0;
	}
	
	//Post: base and height set to base and height, area and perimeter are calculated
	public IsoscelesTriangle(double base, double height)
	{
		super();
		this.base = base;
		this.height = height;
		this.calcAreaAndPerimeter();
	}
	//Post: Area and perimeter of this IsoscelesTriangle are set based on accurate mathematical formulas
	private void calcAreaAndPerimeter()
	{
		area = (height*base)/2;
		perimeter = base + 2*Math.sqrt(Math.pow(height, 2) + (Math.pow(base, 2)/4));
	}
	//Post: Base of this IsoscelesTriangle set to base; area and perimeter are re-calculated
	public void setBase(double base)
	{
		this.base = base;
		this.calcAreaAndPerimeter();
	}
	
	//Return: Base of this IsoscelesTriangle
	public double getBase()
	{
		return this.base;
	}
	//Post: Height of this IsoscelesTriangle set to height; area and perimeter are re-calculated
	public void setHeight(double height)
	{
		this.height = height;
		this.calcAreaAndPerimeter();
	}
	
	//Return: height of this IsoscelesTriangle
	public double getHeight()
	{
		return this.height;
	}
	
	//Desc: Compares two IsoscelesTriangle objects based on base and height
	//Returns: true if both triangles have same base and height, false otherwise
	public boolean equals(Object obj)
	{
		if(obj instanceof IsoscelesTriangle)
		{
			IsoscelesTriangle iso = (IsoscelesTriangle)obj;
			if(iso.height == this.height && iso.base == this.base) return true;
		}
		return false;
	}
	
	//Return: The base, height, perimeter, and area of this IsoscelesTriangle
	public String toString() {
		double p = Math.round(perimeter*100);
		p/=100;
		return  "Base: " + base + " Height: " + height + " Area: " + area + " Perimeter: " + p;
	}

	//Desc: Draw this IsoscelesTriangle on the screen
	//Output: This IsoscelesTriangle object drawn on the screen
	public void draw() {
		for (int row=(int) Math.round(height)-1; row>= 0 ; --row)	
		{						
   			for (int blank=0; blank<row; ++blank)						
				System.out.print(" ");	
   			for (int star=0; star<height-row; ++star)						
				System.out.print("* ");	
			System.out.println();	
		}
		
	}
	
	
}
