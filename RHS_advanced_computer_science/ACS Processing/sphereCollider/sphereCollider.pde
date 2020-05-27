int num;
color c;
float theta = 0;
String s;
static ball ball1;
static ball ball2;
static boolean inCollision;

int collisionCount;
void setup() {
  size(900,900,P3D);
  num = 1;
  collisionCount = 0;
  c = color(random(255),random(255),random(255) );
  ball1 = new ball();
  ball2 = new ball();
  inCollision = false;
}
void draw() {
  background(0);
  update();
  camera(width *-1.25, height*-1.25, (height*-1.25) / tan(PI*30.0 / 180.0), width/2.0, height/2.0, 0, 0, 1, 0); 
  pushMatrix();
  rotateY(radians(theta));
  noFill();
  stroke(c);
  sphere(height);
  popMatrix();
  pushMatrix();
  fill(c);
  textSize(100);
  rotateY(PI);
  //text("" + s,-2500,-500,-400);
  popMatrix();
}
void update(){
  ball1.move();
  ball1.display();
  ball1.checkCollision(ball2);
  ball2.move();
  ball2.display();
  ball2.checkCollision(ball1);
  PVector l = new PVector(0,0,0); 
  PVector L = new PVector(0,0,0);
    l.add(ball1.location);
    L.add(ball2.location);
    if((l.dist(L) <= (ball1.radius + ball2.radius))){
      if(!inCollision){
      //velocity.limit(0);
    println("Ball One Velocity :: " + ball1.velocity);
   println("Ball Two Velocity :: " + ball2.velocity); 
    collisionCount++;
    println("Collision Occurred " + collisionCount);
    //ball1.velocity.mult(-1);
    //ball2.velocity.mult(-1);
    collide();
    
    
    
    inCollision = true;
  
}}else {
 inCollision = false; 
  
}


theta += 1;
}





class ball{
PVector location;
PVector velocity;
float radius;
color ballColor;
float mass;
PVector distance;
float KE;

ball(){
  KE = 10000000;
  location = new PVector(random(height), random(height), random(height));
  radius = random(200,225);
  location.limit(500);
  
  ballColor = color(random(255),random(255),random(255));
  mass = (float)((4/3) * PI * Math.pow(radius,3));
  
  velocity = new PVector(random(-10, 10),random(-10, 10),random(-20, 20));
  velocity.normalize();
  velocity.mult((float)Math.pow((2*KE/mass)*1000, 0.5));
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

void collide(ball bolby){
  
}

void checkCollision(ball bolby){
  
  
  if(location.mag() + radius > height){
      velocity.mult(-1);
      c = ballColor;    
  }
    
}


}
  
   
  
  
  

void collide(){
 PVector v = new PVector(0,0,0);
 PVector V = new PVector(0,0,0);
 float m = 0;
 float M = 0;
 v.add(ball1.velocity);
 V.add(ball2.velocity);
 m = ball1.mass;
 M = ball2.mass;
 ball1.velocity = V.mult(M/m);
 ball2.velocity = v.mult(m/M);
 
 
 println("Ball One Velocity :: " + ball1.velocity);
 println("Ball Two Velocity :: " + ball2.velocity); 
  
  
  
  
}