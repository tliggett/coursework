import java.util.*;
TreeSet<Person> people;

void setup(){
  people = new TreeSet<Person>();
  String[] fromFile = loadStrings("people.txt");
  for(String s : fromFile)
  people.add(new Person(s));
  size(500,500);
}

void draw(){
  background(255);
  fill(0);
  int i = people.size() * 25;
  for(Person p : people){
  text("" + p, 0, i);
  i -= 25;
  }
}

class Person implements Comparable{
  private String name;
  private int[] age;
  Person(String fromFile){
    String[] f = fromFile.split(" ");
    name = setName(f);
    age = setAge(f);
    
  }
  
  String setName(String [] fromFile){
    
    return fromFile[3];
    
    
  }
  int[] setAge(String [] fromFile){
    
    return new int[] {Integer.parseInt(fromFile[0]),Integer.parseInt(fromFile[1]),Integer.parseInt(fromFile[2]) };
    
  }
  
  
  int compareTo(Object o){
    Person p = (Person)o;
    for(int i = 0 ; i < age.length; i++){
     if(age[i] > p.age[i]) return 1;
     if(age[i] < p.age[i]) return -1;
    }
    return this.name.compareTo(p.name); 
    
  }
  
  
  String toString(){
    return name;
  }
  
}