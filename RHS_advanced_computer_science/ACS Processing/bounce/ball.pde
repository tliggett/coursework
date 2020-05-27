
class ball{
 PVector location;
  PVector velocity;
  PVector acceleration;
  float size;
  float radius; 
  float[] ballColor;
  
  ball(){
    ballColor = new float[] {random(255), random(255), random(255)};
    size = 20;
    radius = size/2;
    location = new PVector(random(width-size-200)+ radius+100,random(height-size-200) + radius + 100);
    velocity = new PVector(random(4)-2, random(4)-2);
    acceleration = new PVector(0,0);
  }
  void move(){
    location.add(velocity);
    velocity.add(acceleration);
    velocity.limit(10);
    
    if(location.x > width- radius-100 || location.x < 0 + radius+100){
      velocity.x *= -1;
      acceleration.x *= -1;
    }
    if(location.y > height - radius-100 || location.y < 0 + radius+100){
      velocity.y *= -1;
      acceleration.y *= -1;
    }
  }
  void display(){
    noStroke();
    fill(ballColor[0], ballColor[1], ballColor[2]);
    ellipse(location.x, location.y, size, size);
  }
  
  
}

class ballPit{
 ArrayList<ball>balls;
 float size;
 
 ballPit(int count){
   balls = new ArrayList<ball>();
   size = count;
   for(int i = 0; i < count; i++){
    balls.add( new ball()); 
   }
   
 }
 void setSpeed(){
   for(ball b : balls){
    int curSize = balls.size();
     if(b.velocity.x > 0){
       b.velocity.x = size/ curSize; 
     }else{
       b.velocity.x = -(size/ curSize);
     }
     if(b.velocity.y > 0){
       b.velocity.y = size/ curSize; 
     }else{
       b.velocity.y = -(size/ curSize);
     }
     b.velocity.limit(8);
     
   }
 }
 void move(){
   for(ball b : balls){
     b.move();
    
   }
 }
 void display(){
   for(ball b : balls){
     b.display();
    
   }
 }
  
}