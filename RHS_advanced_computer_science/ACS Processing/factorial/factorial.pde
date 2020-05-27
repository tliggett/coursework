import java.util.*;
List<Factorial> facts;
float count;
void setup(){
  fullScreen();
  count = 15;
  
  facts = new ArrayList<Factorial>();
  for(float i = count; i > 0; i--)
  facts.add(new Factorial());
  
  
  
}

void draw(){
  background(255);
  
  for(Factorial f : facts){
   f.move();
   f.display();
  }
  
  for(int i = facts.size()-1; i >= 0; i--)
    for(int j = facts.size()-1; j >= 0; j--){
     if(i != j){
      PVector bVect = PVector.sub(facts.get(i).location, facts.get(j).location);
      float bVectMag = bVect.mag();
      if(bVectMag < facts.get(i).size.x/2 + facts.get(j).size.x/2 ){
        if(facts.get(i).getProduct() + facts.get(j).getProduct() == 48){
          if(i < j){
          facts.remove(i);
          facts.remove(j);
          facts.add(new Factorial());
          facts.add(new Factorial());
          }else if(i > j){
          facts.remove(i);
          facts.remove(j);
          facts.add(new Factorial());
          facts.add(new Factorial());
          }
          
        }
        
        else if(facts.get(i).getProduct() > facts.get(j).getProduct()){
          for(int x : facts.get(j).ints){
           facts.get(i).ints.add(x); 
          }
          facts.remove(j);
          facts.add(new Factorial());
         }
         else if(facts.get(i).getProduct() > facts.get(j).getProduct()){
          for(int x : facts.get(j).ints){
           facts.get(i).ints.add(x); 
          }
          facts.remove(i);
          facts.add(new Factorial());
         }
        }
        
        
      }
     }
    }
  
  


class Factorial{
  Set<Integer> ints;
  PVector location;
  PVector velocity;
  PVector size;
  color c;
  Factorial(){
    
    ints = new TreeSet<Integer>();
    ints.add((int)random(1,5));
    c = color(random(255), random(255), random(255));
    size = new PVector(10 + getProduct(), 10 + getProduct());
    location = new PVector(random( width/5 , width * 0.8), random(height/5, height * 0.8));
    velocity = new PVector(random(-1, 1), random(-1,1));
    velocity.normalize();
    velocity.mult((4/getProduct()));
    
  }
  int getProduct(){
   int pro = 1;
   for(Integer i : ints){
     pro *= i;
   }
    return pro;
  }
  
  void move(){
   location.add(velocity);
   if(location.x > (width-size.x/2) || location.x < 0+ (size.x/2))
    velocity.x *= -1;
    if(location.y > height-(size.y/2) || location.y < 0+ (size.y/2))
    velocity.y *= -1;
    size.x = 20*getProduct();
    size.y = 20*getProduct();
  }
  void display(){
   //fill(0);
   noFill();
   stroke(c);
   strokeWeight(4);
   textSize(15);
   
   ellipse(location.x, location.y, size.x, size.y);
   fill(c); 
   text(getProduct(), location.x, location.y); 
  }
  
  
  
}