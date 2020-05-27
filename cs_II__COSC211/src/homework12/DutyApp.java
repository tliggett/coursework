package homework12;

//TJ Liggett
//Homework 12
//Class DutyApp allows users to create Students and Professors


import java.util.Scanner;

public class DutyApp 
{
	//Desc:	A program which allows the user to create either a Student or a Professor.  
	//		The program displays a menu asking for 1. Student, 2. Professor, 3. Quit.  The 
	//		user enters 1 or 2, followed by the id or name of the person.  The program prints the duty 
	//		of the person, and then asks the user to enter 1, 2, or 3 again.  The program will continue 
	//		until the user selects 3 to quit. 
	//Input:   The user enters 1 or 2 followed by the id or name of the person via the keyboard.
	//Output:For each person entered by the user, the duty of the person displayed on the screen 		
	public static void main(String[] args) 
	{
		Scanner f=new Scanner(System.in);
		int response =0;
		Duty person;		
		while (true)
		{
			System.out.print("1. Student, 2. Professor, 3. Quit: ");
			response =f.nextInt();
			f.nextLine();	//WHY????
			if (response==3) break;
			if (response==1) person =getStudent(f);
			else person =getProfessor(f);
			System.out.println(person.getDuty());
		}
	}

	//Input:   The user enters the id of the Student via the keyboard.
	//Post: One line read from f
	//Return: The Student with the input id.
	private static Duty getStudent(Scanner f) 
	{
		System.out.println("Enter id: ");
		Duty student = new Student(f.nextInt());
		f.nextLine();
		return student;
	}
	//Input:   The user enters the name of the Professor via the keyboard.
	//Post: One line read from f
	//Return: The Professor with the input name.
	private static Duty getProfessor(Scanner f)
	{
		System.out.println("Enter name: ");
		Duty professor = new Professor(f.nextLine());
		return professor;
	}
}

interface Duty 
{
	public String getDuty();
}


class Student implements Duty
{
	private int id;
	//Post: Student id initialized to 0
	public Student()
	{
		id = 0;
	}
	//Post: Student id initialized to id
	public Student(int id)
	{
		this.id = id;
	}
	//Post: Student id is set to id
	public void setId(int id)
	{
		this.id = id;
	}
	//Return: id of this Student object
	public int getId()
	{
		return id;
	}
	//Return: The duty of this Student object
	public String getDuty() 
	{
		return "Duty of student id = " + id + " is to study 40 hours a week";
	}
}

class Professor implements Duty
{
	private String name;
	
	//Post: Name of this professor initialized to ""
	public Professor()
	{
		name = "";
	}
	//Post: Name of this professor initialized to name
	public Professor(String name)
	{
		this.name = name;
	}
	//Post: Name of this professor set to name
	public void setName(String name)
	{
		this.name = name;
	}
	//Return: The name of this Professor object
	public String getName()
	{
		return this.name;
	}
	//Return: The duty of this Professor object
	public String getDuty() 
	{
		return "Duty of professor " + name + " is to return homework on time";
	}

}
