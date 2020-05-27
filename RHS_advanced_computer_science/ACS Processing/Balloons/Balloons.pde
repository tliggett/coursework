ArrayList<balloon> balloons; 
float wind;

void setup(){
 size(1000,500); 
 balloons = new ArrayList<balloon>();
 wind = 0.1;
}

void draw(){
  background(135, 206, 250);
  //background(0, 191, 255);
  for(int i = balloons.size()-1; i >= 0; i--){
    balloons.get(i).display();
    balloons.get(i).move();
    if(!balloons.get(i).isAlive){
      balloons.remove(i);
    }
    
    
  }
  while(balloons.size() < 50){
    balloons.add(new balloon());
  }
  
}

void mousePressed(){
  for(balloon bloon : balloons){
    float x = mouseX - bloon.location.x; 
    float y = mouseY - bloon.location.y;
    x = pow(x, 2)/pow(bloon.size.x, 2);
    y = pow(y,2)/pow(bloon.size.y, 2);
    if(x+y <= 1){
      bloon.isAlive = false;
    }
    
  }
}

void keyPressed(){
  if(keyCode == LEFT){
    wind -= 0.5;
  }
  if(keyCode == RIGHT){
   wind += 0.5; 
  }
}

class balloon{
  PVector velocity;
  PVector location;
  float[]colorB;
  PVector size;
  boolean isAlive;
  balloon(){
    location = new PVector(random(1200) - 100, height);
    velocity = new PVector(0, (random(3)+2) * -1);
    colorB = new float[]{random(255),random(255),random(255), random(80) + 120};
    size = new PVector(0,0);
    size.x = random(25) + 20;
    size.y = size.x + 5 + random(5);
    isAlive = true;
    
  }
  void move(){
    location.add(velocity);
    location.x += wind;
    if(location.y < (0- size.y)){
     isAlive = false; 
    }
  }
  
  void display(){
    fill(colorB[0], colorB[1], colorB[2], colorB[3]);
    ellipse(location.x, location.y,size.x,size.y);
    
  }
  
  
  
  
}