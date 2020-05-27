package homework6;

import java.util.Vector;
import java.io.*;
import java.util.Scanner;

class Student implements java.io.Serializable
	{

  	private int id;
	private String name;
	private int age;

	//Post:	Id, name, and age of this Student initialized to -1, "unknown", -1 
	public Student () 
	{ 
		id = -1; 
		name="unknown"; 
		age = -1; 
	}
	//Post:	Id, name, age of this Student initialized to the parameters id, name,
	//	and age.
	public Student (int id, String name, int age, String major) 
	{ 
		this.id = id; 
		this.name=name; 
		this.age = age; 
	}
	//Post:	Id of this Student set to s
	public void setId( int s ) 
	{ 
		id = s; 
	}
	//Return:	Id of this Student
	public int getId() 
	{ 
		return id; 
	}
	//Post:	Name of this Student set to s
	public void setName(String s) 
	{ 
		name=s;
	}
	//Return:	Name of this Student
	public String getName() 
	{ 
		return name; 				
	}
	//Post:	Age of this Student set to s
	public void setAge( int s ) 
	{ 
		age = s; 
	}
	//Return:	Age of this Student
	public int getAge()
	{ 
		return age; 
	}
	//Return:	true if id of this Student == id of obj, false otherwise
	public boolean equals(Object obj)	
	{
		Student s = (Student) obj;
		if(this.id == s.id) return true;
		return false;
	}
	//Return:	1 if id of this Student > id of stu
	//	0 if id of this Student == id of stu
	//	-1 if id of this Student < id of stu
	public int compareTo(Student stu)				
	{
		if(this.id == stu.id) return 0;
		if(this.id > stu.id) return 1;
		return -1;			
	}
	//Return:The id, name, age of this Student in the format "ID: id Name: 
	//	 name Age: age" 
	public String toString()
	{
            return "ID: "+ id +" Name: "+name+" Age: "+age;
	}
}


public class StudentDB2
{  
	private static Scanner keyboard=new Scanner(System.in);
	//Desc: 	Maintains a database of Student records.  The database is stored in binary file 
	//	Student.data
	//Input:	User enters commands from keyboard to manipulate database.
	//Output:Database updated as directed by user.
    public static void main(String[] args) throws IOException
    {
     	Vector<Student> v=new Vector<Student>();
     	File s=new File("Student.data");
     	if (s.exists()) loadStudent(v);
     	int choice=5;					
     	do {
     		System.out.println("\t1. Add a Student record");	
     		System.out.println("\t2. Remove a Student record");	
     		System.out.println("\t3. Print a Student record");	
     		System.out.println("\t4. Print all Student records");	
     		System.out.println("\t5. Quit");	
     		choice= keyboard.nextInt();
     		keyboard.nextLine();
     		switch (choice)						
     		{
     			case 1: addStudent(v); break;		
     			case 2: removeStudent(v); break;		
     			case 3: printStudent(v); break;		
     			case 4: printAllStudent(v); break;		
     			default:  break;	
     		}
     	} while (choice!=5);
     	storeStudent(v); 
     }		
     	
     	
     //Input: 	user enters an integer (id), a string (name), an integer (age) from the 
     //	keyboard all on separate lines
     //Post: 	The input record added to v if id does not exist
     //Output: various prompts as well as "Student added" or "Add failed: Student already exists" 
     //	printed on the screen accordingly
    public static void addStudent(Vector<Student> v) 
    {
    	Student stu=new Student();
		System.out.print("Student ID:");
		stu.setId(keyboard.nextInt());
		keyboard.nextLine();	
		for(Student s : v)
		if(s.equals(stu))
		{
			System.out.println("Add failed: Student already exists");
			return;
		}				
		System.out.print("Student name:");
		stu.setName(keyboard.nextLine());						
		System.out.print("Student Age:");
		stu.setAge(keyboard.nextInt());	
		keyboard.nextLine();					
		v.add(stu);
		System.out.println("Student added");
    }
    //Input: 	user enters an integer (id) from the keyboard	
    //Post: 	The record in v whose id field matches the input removed from v.
    //Output: various prompts as well as "Student removed" or "Remove failed: Student does not 
    //	exist" printed on the screen accordingly
    public static void removeStudent(Vector<Student> v) 
    {
    	System.out.print("Student ID:");
		int id= keyboard.nextInt();						
		boolean removed = false;
		for(int i = v.size()-1; i >= 0; i--)
		{
			if(v.get(i).getId() == id) 
			{
				v.remove(i);
				removed = true;
				System.out.println("Student removed");
			}	
		}
		if(!removed) System.out.println("Remove failed: Student does not exist");
    }
    //Input: 	user enters an integer (id) from the keyboard	
    //Output: various prompts as well as the record in v whose id field matches the input printed on the 
    //	screen or "Print failed: Student does not exist" printed on the screen accordingly
    public static void printStudent(Vector<Student> v) 
    {
    	System.out.print("Student ID:");
		int id = keyboard.nextInt();						
		for(Student s : v)
			if(s.getId() == id)
			{
				System.out.println(s);
				return;
			}
		System.out.println("Print failed: Student does not exist");
    }
    //Output: All records in v printed on the screen.
    public static void printAllStudent(Vector<Student> v) 
    {
    	for(Student s : v) System.out.println(s);
    }
    //Input: 	Binary file Student.data must exist and contains student records.
    //Post: 	All records in Student.data loaded into vector v.
    public static void loadStudent(Vector<Student> v) throws IOException
    {
    	ObjectInputStream f = new ObjectInputStream(new FileInputStream("Student.data"));
    	try		{
    				while(true)
    				{
    					v = (Vector<Student>) (f.readObject());
    					f.close();
    				}
    			}
    			catch(IOException e)
    			{
    				System.err.println("error reading file");
    				System.exit(1);
    			} catch (ClassNotFoundException e) {
    				System.err.println("error reading file");
    				System.exit(1);
				}
    }
    //Output: All records in v written to binary file Student.data.
    public static void storeStudent(Vector<Student> v) throws IOException
    {
    	ObjectOutputStream f = new ObjectOutputStream(new FileOutputStream("Student.data"));
    	f.writeObject(v);
    }
}
