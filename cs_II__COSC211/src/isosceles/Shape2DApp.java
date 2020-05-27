package isosceles;

import java.util.Scanner;

public class Shape2DApp 
{
	//Desc:	A program which allows the user to create either a Rectangle or an IsoscelesTriangle.  
	//	The program displays a menu asking for 1. Rectangle, 2. IsoscelesTriangle, 3. Quit.  The 
	//	user enters 1 or 2, followed by the dimensions of the shape.  The program draws the 
	//	shape on the screen followed by the area and perimeter of the shape, and then asks the 
	//	user to enter 1, 2, or 3 again.  The program will continue until the user selects 3 to quit. 
	//Input:   The user enters 1 or 2 followed by the dimensions of the shape via the keyboard.
	//Output:For each shape entered by the user, the object displayed on the screen followed by the 
	//            area and perimeter of the shape.
	
	public static void main(String[] args) 
   	{
		Scanner f=new Scanner(System.in);
		int response =0;
		Shape2D obj;
		while (true)
		{
			System.out.print("1. Rectangle, 2. IsoscelesTriangle, 3. Quit: ");
			response =f.nextInt();
			if (response==3) break;
			if (response==1) obj=getRectangle(f);
			else obj=getIsoscelesTriangle(f);
			obj.draw();
			System.out.println(obj);
		}
	}

	//Desc: Takes a base and height input from the user and creates an IsoscelesTriangle with
	//		desired base and height
	//Input: The desired base and height for the IsoscelesTriangle object
	//Output: Various prompts for the user
	//Return: IsoscelesTriangle with desired base and height
	private static Shape2D getIsoscelesTriangle(Scanner f) {
		IsoscelesTriangle iso = new IsoscelesTriangle();
		System.out.println("Triangle base: ");
		iso.setBase(f.nextDouble());
		f.nextLine();
		System.out.println("Triangle height: ");
		iso.setHeight(f.nextDouble());
		f.nextLine();
		return iso;
	}
	
	//Desc: Takes a width and height input from the user and creates a Rectangle with
	//		desired width and height
	//Input: The desired base and height for the Rectangle object
	//Output: Various prompts for the user
	//Return: Rectangle with desired width and height
	private static Shape2D getRectangle(Scanner f) {
		Rectangle rect = new Rectangle();
		System.out.println("Rectangle width: ");
		rect.setWidth(f.nextDouble());
		f.nextLine();
		System.out.println("Rectangle height: ");
		rect.setHeight(f.nextDouble());
		f.nextLine();
		return rect;
	}
		
}



