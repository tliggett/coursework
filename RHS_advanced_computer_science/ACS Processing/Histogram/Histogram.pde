import java.util.*;
ArrayList<Gram> crackers;
int count, step;

void setup(){
  fullScreen();
  crackers = new ArrayList<Gram>();
  String[] fromFile = loadStrings("gram.txt");
  count = 0;
  step = 0;
  for(String $ : fromFile){
    crackers.add(new Gram($));
  }
  frameRate(25);
  
}
void draw(){
    background(170,171,211); 
    stroke(84,69,246);
    strokeWeight(5);
    crackers.get(step).display();
    count++;
    if(count >= 100){
     count = 0;
     step++;
     if(step > crackers.size()-1)
     step = 0;
    }
}

class Gram{
 private Map<Character, Integer> histogram;
 private String text;
  
  
  Gram(String fromFile){
   text = "";
   histogram = new TreeMap<Character, Integer>();
   text += (fromFile);
     for(char c : fromFile.toCharArray()){
       if(histogram.containsKey(c)){
          histogram.put(c, histogram.get(c) + 1); 
       }else{
         histogram.put(c, 1);
       }
   }
 }
 
 public void display(){
   Iterator<Map.Entry<Character, Integer>> it = histogram.entrySet().iterator();
   fill(146,77,230);
   textSize(50);
   text("" + text,25,25, width, height);
   int h = 100;
   
   while(it.hasNext()){
     Map.Entry<Character, Integer> element = it.next();
     textSize(20);
     text("" + element.getKey(), 25, h+20);
     rect(50, h, 125*element.getValue(), 40);
     text("" + element.getValue(), 125*element.getValue() + 55 , h+20);
     h+= 40;
   }
   
 }
 
 
 public String toString(){
  return text; 
 }
  
  
  
  
  
   
}