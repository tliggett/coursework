import peasy.*;
import peasy.org.apache.commons.math.*;
import peasy.org.apache.commons.math.geometry.*;

int num;
color c;
float theta = 0;
String s;
static Ball ball;
PeasyCam cam;
Pong playerOne;
Pong playerTwo;

int collisionCount;
void setup() {
  size(750,750,P3D);
  num = 1;
  ball = new Ball();
  PVector oneLoc = new PVector(0,0,750);
  playerOne = new Pong(oneLoc);
  PVector twoLoc = new PVector(0,0,-750);
  playerTwo = new Pong(twoLoc);
  cam = new PeasyCam(this, 100);
  cam.setDistance(2500);
  cam.lookAt(0, 0, 0);
  
}
void draw() {
  background(0);
  update();
  
  pushMatrix();
  noFill();
  stroke(255);
  strokeWeight(5);
  box(1500);
  popMatrix();
  
  /*pushMatrix();
  fill(255);
  translate(0,0,750);
  rect(-750,-750,1500,1500);
  popMatrix();
  
  pushMatrix();
  fill(255);
  translate(0,0,-750);
  rect(-750,-750,1500,1500);
  popMatrix();*/
  
  
}
void update(){
  ball.move();
  ball.display();
  ball.checkCollision();
  playerOne.move();
  playerOne.display();
  playerTwo.move();
  playerTwo.display();
  
}





class Ball{
PVector location;
PVector velocity;
float radius;
color ballColor;
float mass;
PVector distance;

Ball(){
  location = new PVector(random(height), random(height), random(height));
  radius = 100;
  location.limit(300);
  velocity = new PVector(random(-10, 10),random(-10, 10),random(-10, 10));
  velocity.normalize();
  velocity.mult(40);
  ballColor = color(0,125,0);
  mass = (float)((4/3) * PI * Math.pow(radius,3));
}
void move(){
  location.add(velocity);
}
void display(){
  pushMatrix();
  translate(location.x, location.y, location.z);
  noFill();
  stroke(ballColor);
  sphere(radius);
  popMatrix();
}
void checkCollision(){
  
  
  if(Math.abs(location.z) + radius > height){
      velocity.z *= -1;
          
  }
  if(Math.abs(location.x) + radius > height){
      velocity.x *= -1;
        
  }
  if(Math.abs(location.y) + radius > height){
      velocity.y *= -1;
          
  }}}
  
  
class Pong{
  PVector location;
  PVector size;
  
  
  Pong(PVector loc){
    location = loc;
    size = new PVector (500,500, 10);
    
    
  }
  
  void move(){
    PVector a = new PVector(0,0,0);
    a.sub(location);
    a.add(ball.location);
    a.normalize();
    a.mult(35);
    location.x += a.x;
    location.y += a.y;
    
  }
  void display(){
    pushMatrix();
    stroke(255,0,0);
    strokeWeight(5);
    noFill();
    translate(location.x, location.y, location.z);
    box(size.x,size.y, size.z);
    
    popMatrix();
    
    
  }
  
  
  
  
  
  
}