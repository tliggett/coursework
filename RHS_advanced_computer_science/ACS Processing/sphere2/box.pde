class ball{
  PVector location;
PVector velocity;
float radius;
color a;

ball(PVector loc){
   location = loc;
   location.limit(0);
  velocity = new PVector(random(20)-10,random(20)-10,random(40)-20);
  a = color(random(255),random(255),random(255) );
  radius = 50;
}
void move(){
  location.add(velocity);
}
void display(){
  pushMatrix();
  translate(location.x, location.y, location.z);
  fill(0);
  stroke(a);
  sphere(radius);
  popMatrix();
}
  
  
  
}