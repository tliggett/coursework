package homework5;

import java.io.FileNotFoundException;
import java.util.Scanner;

import homework4.Time24;

class Runner 
{
	private String name;
	private Time24 raceTime;
	
	//Post: The Runner object initialized with name="unknown" and raceTime=0:0.
	public Runner()
    {
        name="unknown";
		raceTime=new Time24(0,0);
    }
	
	//Post: The Runner object initialized with name=s and raceTime=t.
	public Runner(String s, Time24 t)
    {
       	name=s;		
       	raceTime=new Time24(t.getHour(), t.getMinute());	
    }
	
	//Return: The name of the Runner object.
	public String getName()
   	{ 
		return name; 
	}
	
	//Return: The raceTime of the Runner object.
	public Time24 getRaceTime()
   	{ 
		return new Time24(raceTime.getHour(), raceTime.getMinute()); 
	}
	
	//Post: The Runner object's name set to s.
	public void setName(String s)
	{
		this.name = s;
	}
	
	//Post: The Runner object's raceTime set to t. 
	public void setRaceTime(Time24 t)
	{
		this.raceTime = t;
	}
	
	//Pre: 	f has a line in the following format ready to be read:
	//			name hh:mm 
	//		where name is a String and hh, mm are integers.  The token delimiters of f have been set 
	//		to white space characters and the colon by the caller.
	//Post: The line read in from f, the name and the time stored in the Runner object 
	public void read(Scanner f)
	{
		this.setName(f.next());
		int h = f.nextInt();
		int m = f.nextInt();
		this.setRaceTime(new Time24(h,m));
	}
	
	//Desc:   Compare 2 Runner objects based on raceTime
	//Return: 1 if current object's raceTime > r's raceTime
	//		  0 if current object's raceTime == r's raceTime
	//		  -1 if current object's raceTime < r's raceTime
	public int compareTo(Runner r)
	{
		int h = this.getRaceTime().getHour() - r.getRaceTime().getHour();
		if(h > 0) return 1;
		if(h < 0) return -1;
		int m = this.getRaceTime().getMinute() - r.getRaceTime().getMinute();
		if(m > 0) return 1;
		if(m < 0) return -1;
		return 0;
	}
	
	//Return: 	A String object in the form "name hh:mm"
	public String toString()
	{
		return this.getName() + " " + this.getRaceTime();
	}
	
	
	
}


class Marathon
{
	
	//Desc:	  Output the name and time of the runner who came in first, as well as the name and time of 
	//		  the runner who came in last in a marathon race (assuming there are no ties).
	//Input:  A text file named marathon.txt containing the name and time of each participant in the 
	//		  following format (the file has at least 1 participant, name is just 1 word with no space, 
	//		  and name and time are separated by tabs, blanks, and newlines):
	//		  	John	2:40
	//		  	Paul	3:20
	//		  	Carl	2:10
	//Output: The name and time of the runner who came in first, as well as the name and time of the 
	//		  runner who came in last printed to the screen.
	public static void main(String[] args) throws FileNotFoundException
	{	
		Scanner input = new Scanner("marathon.txt");
		input = input.useDelimiter(":|[\r\n\t]+");
		
		Runner first = new Runner();
		Runner last = new Runner();
		Runner one = new Runner();
		one.read(input);
		input.nextLine();
		first = last = one;
		
		while(input.hasNextLine())
		{
			Runner r = new Runner();
			r.read(input);
			if(r.compareTo(first) < 0) first = r;
			if(r.compareTo(last) > 0) last = r;
			
		}
		
		System.out.println("First: " + first);
		System.out.println("Last: " + last);
		
	}
}

