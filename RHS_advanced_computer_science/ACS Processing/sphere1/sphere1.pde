Sphere ball;



void setup(){
  size(1000,1000,P3D);
  ball = new Sphere();
}

void draw(){
  background(255);
  
  camera(width *-1, (height*-1), (height*-1) / tan(PI*30.0 / 180.0), width/2.0, height/2.0, 0, 0, 1, 0);
  translate(height/2,width/2,height/2);
  //fill(255,0,255, 0);
  noFill();
  stroke(0);
  box(1000);
  ball.display();
  ball.move();
}

void keyPressed(){
 if(keyCode == UP){
   ball.velocity.z += 5; 
 }if(keyCode == DOWN){
   ball.velocity.z -= 5; 
 }if(keyCode == LEFT){
   ball.velocity.x += 5; 
 }if(keyCode == RIGHT){
   ball.velocity.x -= 5; 
 }if(keyCode == SHIFT){
   ball.velocity.y -= 100;
   ball.acceleration.y += 5;
 }
 
}


class Sphere{
  
  PVector location;
  PVector velocity;
  PVector acceleration;
  Sphere(){
    location = new PVector(width/2,950, height/2);
    velocity = new PVector(0, 0, 0);
    acceleration = new PVector(0,0,0);
  }
  void move(){
    location.add(velocity);
    velocity.add(acceleration);
    if(location.x > 950 || location.x < -100){
      velocity.x *= -1;
    }
    if(location.z > 950 || location.z < 50){
      velocity.z *= -1;
    }
    if(location.y > 950){
     location.y = 950;
     velocity.y = 0;
     acceleration.y = 0;
    }
  }
  void display(){
    translate(location.x, location.y, location.z);
    fill(0,0,0);
    sphere(50);
  }
  
  
  
}