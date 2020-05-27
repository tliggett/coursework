class box{
  PVector location;
PVector velocity;
float size;
color a;

box(){
   location = new PVector(random(400)+ 51,random(400) + 51,random(400) + 51);
  velocity = new PVector(random(6)-3,random(6)-3,random(10)-5);
  a = color(random(255),random(255),random(255) );
  
}
void move(){
  location.add(velocity);
  if(location.x > width-50 || location.x < 50){
    velocity.x *= -1;
  }
  if(location.y > height-50 || location.y < 50){
    velocity.y *= -1;
  }
  if(location.z > height-50 || location.z < 50){
    velocity.z *= -1;
  }
  
}
void display(){
  pushMatrix();
  translate(location.x, location.y, location.z);
  rotateX(radians(location.x));
  rotateY(radians(location.y));
  rotateZ(radians(location.z));
  fill(a);
  stroke(150);
  box(100);
  popMatrix();
}
  
  
  
}