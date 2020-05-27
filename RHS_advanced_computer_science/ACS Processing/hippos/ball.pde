class ball{
 PVector location;
  PVector velocity;
  PVector acceleration;
  float size;
  float[] ballColor;
  
  ball(){
    ballColor = new float[] {random(255), random(255), random(255)};
    size = 20;
    location = new PVector(random(2000)-500,0,random(2000)-500);
    velocity = new PVector(1,0,1);
    acceleration = new PVector(0,0,0);
  }
  void move(){
    location.add(velocity);
    velocity.add(acceleration);
  }
  void display(){
    translate(location.x, location.y, location.z);
    fill(ballColor[0], ballColor[1], ballColor[2]);
    sphere(size);
  }
  
  
}

class ballPit{
 ball[]balls;
 
 ballPit(int count){
   balls = new ball[count];
   for(int i = 0; i < count; i++){
    balls[i] = new ball(); 
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