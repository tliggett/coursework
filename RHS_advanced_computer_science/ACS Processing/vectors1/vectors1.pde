int height = 500;
ArrayList<Ball> balls;


void setup(){
  size(500, 500);  
  balls = new ArrayList<Ball>();
  
  
}

void mousePressed(){
  Ball ball1 = new Ball(mouseX, mouseY);
  balls.add(ball1);
}

void draw(){
  background(0);
  for(Ball ball : balls){
    ball.display();
    ball.move();
  }
}

class Ball{
  PVector location;
  PVector velocity;
  PVector acceleration;
  float size;
  float[] colorB;
  
  Ball(float vx, float vy){
   size = 20;
   location = new PVector(5, height-5);
   colorB = new float[]{random(255),random(255),random(255)};
   velocity = new PVector(vx/10,((500-vy)/10)*-1);
   acceleration = new PVector(0, .98);
  }
  
  void move(){
   location.add(velocity); 
   velocity.add(acceleration);
   if(location.x > 500 || location.x < 0){
    velocity.x*=-1; 
   }
   if(location.y >= 490){
   location.y = 490;
   
    if(Math.abs(velocity.y) < 5){
     velocity.y = 0; 
     acceleration.y = 0;
   }else{
      velocity.y*=-1;
    }
     velocity.y/=1.5;
     velocity.x /= 1.5;
    
    
   }
   /*if(location.y > 499){
    velocity.x/=1.1; 
   }*/
   
  }
  void display() {
    fill(colorB[0], colorB[1], colorB[2]);
    ellipse(location.x, location.y,size,size);
  }
  
  
}