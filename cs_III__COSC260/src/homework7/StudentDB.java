//TJ Liggett
//CS 260: Homework 7
//10_24_2018
//Class StudentDB writes student data to file and allows for maintenance of file

import java.util.Vector;
import java.io.*;
import java.util.Scanner;

class Student implements java.io.Serializable
	{

	private static final int STRINGMAX = 30;
  	private int id;
	private char[] name = new char[STRINGMAX];
	private int age;
	private char[] major = new char[STRINGMAX];
	

	//Post:	Id, name, age, and major of this Student initialized to -1, "", -1. ""
	public Student() 
	{ 
		id = -1; 
		for(int j = 0; j < STRINGMAX; ++j) name[j] = ' ';
		age = -1; 
		for(int j = 0; j < STRINGMAX; ++j) major[j] = ' ';
	}
	//Post:	Id, name, age, and major of this Student initialized to the parameters id, name,
	//	age, and major.
	public Student (int id, String name, int age, String major) 
	{ 
		this.id = id; 
		for(int j = 0; j<name.length(); ++j)
		{
			if(j<STRINGMAX) this.name[j] = name.charAt(j);
			else break;
		}
		this.age = age;
		for(int j = 0; j<major.length(); ++j)
		{
			if(j<STRINGMAX) this.major[j] = major.charAt(j);
			else break;
		}
	}
	//Post:	Id of this Student set to s
	public void setId( int s ) 
	{ 
		this.id = s; 
	}
	//Return:	Id of this Student
	public int getId() 
	{ 
		return id; 
	}
	//Post:	Name of this Student set to s
	public void setName(String s) 
	{ 
		for (int j=0; j < s.length(); ++j)
		{
			if (j<STRINGMAX) name[j]=s.charAt(j);
			else return;
		}
		for (int j= s.length(); j < STRINGMAX; ++j)
			name[j] = ' ';
	}
	//Return:	Name of this Student
	public String getName() 
	{ 
		String s = new String(name);
		return s.trim();
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
	
	//Post:	Major of this Student set to s
	public void setMajor(String s) 
	{ 
		for (int j=0; j < s.length(); ++j)
		{
			if (j<STRINGMAX) major[j]=s.charAt(j);
			else return;
		}
		for (int j= s.length(); j < STRINGMAX; ++j)
			major[j] = ' ';
	}
	//Return: Major of this Student
	public String getMajor() 
	{ 
		String s = new String(major);
		return s.trim();				
	}
	
	//Return:	true if id of this Student == id of obj, false otherwise
	public boolean equals(Object obj)	
	{
		Student s = (Student) obj;
		if(this.id == s.id) return true;
		return false;
	}
	
	//Post: this Student is written to file
	public void write(RandomAccessFile f) throws IOException
	{
		f.writeInt(id);
		for (int j= 0; j<STRINGMAX; ++j) f.writeChar(name[j]);
		f.writeInt(age);
		for (int j= 0; j<STRINGMAX; ++j) f.writeChar(major[j]);
	}
	
	//Post: this Student is read from file; id, name, age and major set
	//		to the contents of file
	public void read( RandomAccessFile f ) throws IOException
	{
		id = f.readInt();
		for (int j= 0; j<STRINGMAX; ++j) name[j] = f.readChar();
		age = f.readInt();
		for (int j= 0; j<STRINGMAX; ++j) major[j] = f.readChar();
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
	//	 name Age: age Major: major" 
	public String toString()
	{
            return "ID: "+ this.getId() +" Name: "+ this.getName() + " Age: "
            		+ this.getAge() + " Major: " + this.getMajor();
	}
	
	//Return: The written size of this Student to file
	public static int size()
	{
		return 128; //4 (int) + 4 (int) + 60(30 Unicode Characters) + 60(30 Unicode Characters)
	}
}


public class StudentDB
{  
	private static Scanner keyboard=new Scanner(System.in);
	//Desc: 	Maintains a database of Student records.  The database is stored in binary file 
	//	Student.data
	//Input:	User enters commands from keyboard to manipulate database.
	//Output:Database updated as directed by user.
    public static void main(String[] args) throws IOException
    {
     	File s=new File("Student.data");
     	if (!s.exists()) createDatabase(s);
     	RandomAccessFile f = new RandomAccessFile(s, "rw");
     	int choice=6;					
     	do {
     		System.out.println("\t1. Add new Student record");	
     		System.out.println("\t2. Remove Student record");
     		System.out.println("\t3. Update Student record");	
     		System.out.println("\t4. Display Student record");	
     		System.out.println("\t5. Display all Student records");	
     		System.out.println("\t6. Quit");	
     		choice= keyboard.nextInt();
     		keyboard.nextLine();
     		switch (choice)						
     		{
     			case 1: addStudent(f); break;		
     			case 2: removeStudent(f); break;		
     			case 3: updateStudent(f); break;
     			case 4: printStudent(f); break;	
     			case 5: printAllStudent(f); break;
     			default:  break;	
     		}
     	} while (choice!=6);
     	
     	f.close();
     }		
     	
     	
     //Input: 	user enters an integer (id), a string (name), an integer (age), a string (major)  
     //	from the keyboard all on separate lines
     //Post: 	The input record added to file if Student does not exist
     //Output: various prompts as well as "Student added" or "Add failed: Student already exists" 
     //	printed on the screen accordingly
    public static void addStudent(RandomAccessFile f) throws IOException 
    {
    	Student stu=new Student();
		System.out.print("Student ID (1-100):");
		int id = keyboard.nextInt();
		keyboard.nextLine();					
		f.seek((long) (id - 1) * Student.size());
		stu.read(f);
		if(!(stu.getId() == -1))
		{
			System.out.println("Add failed: Student already exists");
			return;
		}
		stu.setId(id);
		
		System.out.print("Student Name:");
		stu.setName(keyboard.nextLine());						
		System.out.print("Student Age:");
		stu.setAge(keyboard.nextInt());	
		keyboard.nextLine();
		System.out.print("Student Major: ");
		stu.setMajor(keyboard.nextLine());
		
		f.seek((long) (stu.getId() - 1) * Student.size());
		stu.write(f);
		System.out.println("Student added");
    }
    
    //Input: 	user enters an integer (id) from the keyboard	
    //Post: 	The record in file whose id field matches the input removed from file.
    //Output: various prompts as well as "Student removed" or "Remove failed: Student does not 
    //	exist" printed on the screen accordingly
    public static void removeStudent(RandomAccessFile f) throws IOException 
    {
    	Student stu=new Student();
		System.out.print("Student ID (1-100):");
		int id = keyboard.nextInt();
		keyboard.nextLine();					
		f.seek((long) (id - 1) * Student.size());
		stu.read(f);
		if((stu.getId() == -1))
		{
			System.out.println("Remove failed: Student does not exist");
			return;
		}
		stu = new Student();
		f.seek((long) (id - 1) * Student.size());
		stu.write(f);
		System.out.println("Student removed.");
    }
    
    //Post: Student is updated in file
    //Input: Desired id (int) of student to update, y/n responses to prompts,
    //			desired updated values
    //Output: various user prompts, as well as "Student Updated" and 
    //			"Update failed: Student does not exist"
    public static void updateStudent(RandomAccessFile f) throws IOException 
    {
    	Student stu=new Student();
		System.out.print("Student ID (1-100):");
		int id = keyboard.nextInt();
		keyboard.nextLine();					
		f.seek((long) (id - 1) * Student.size());
		stu.read(f);
		if((stu.getId() == -1))
		{
			System.out.println("Update failed: Student does not exist");
			return;
		}
		stu.setId(id);
		
		System.out.print("Name: " + stu.getName() + " Change y/n: ");
		char s1 = keyboard.nextLine().toLowerCase().charAt(0);
		if(s1 == 'y')
		{
			System.out.print("Student name:");
			stu.setName(keyboard.nextLine());
		}
		System.out.println("Age: " + stu.getAge() + " Change y/n: ");
		s1 =  keyboard.nextLine().toLowerCase().charAt(0);
		if(s1 == 'y')
		{
			System.out.print("Student Age:");
			stu.setAge(keyboard.nextInt());	
			keyboard.nextLine();
		}
		System.out.println("Major: " + stu.getMajor() + " Change y/n: ");
		s1 =  keyboard.nextLine().toLowerCase().charAt(0);
		if(s1 == 'y')
		{
			System.out.print("Student Major: ");
			stu.setMajor(keyboard.nextLine());
		}
		
		f.seek((long) (stu.getId() - 1) * Student.size());
		stu.write(f);
		System.out.println("Student " + stu.getId() + " updated.");
		
    }
    
    //Input: 	user enters an integer (id) from the keyboard	
    //Output: various prompts as well as the record in file whose id field matches the input  
    //	printed on the screen or "Print failed: Student does not exist" printed on the screen 
    //	accordingly
    public static void printStudent(RandomAccessFile f) throws IOException 
    {
    	Student stu=new Student();
		System.out.print("Student ID (1-100):");
		int id = keyboard.nextInt();
		keyboard.nextLine();					
		f.seek((long) (id - 1) * Student.size());
		stu.read(f);
		if((stu.getId() == -1))
		{
			System.out.println("Print failed: Student does not exist");
			return;
		}
		System.out.println(stu);
    }
    //Output: All records in file printed on the screen.
    public static void printAllStudent(RandomAccessFile f) throws IOException 
    {
    	Student stu = new Student();
		f.seek(0);
    	for(int i = 0; i < 100; ++i)
    	{
    		stu.read(f);
    		if(!(stu.getId() == -1)) System.out.println(stu);
    	}
    }
    //Post: 100 blank students are written to file
    public static void createDatabase(File s) throws IOException
    {
    	Student blank = new Student();
    	RandomAccessFile f = new RandomAccessFile(s, "rw");
    	f.setLength(100 * Student.size());
    	for(int i = 0; i <100; ++i) blank.write(f);
    }

}
