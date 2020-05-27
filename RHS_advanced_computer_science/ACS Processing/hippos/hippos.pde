
ballPit balls;



void setup(){
  size(1000,1000, P3D);
  balls = new ballPit(10);
}


void draw(){
  background(255);
  //camera(width *-1, (height*-1), (height*-1) / tan(PI*30.0 / 180.0), width/2.0, height/2.0, 0, 0, 1, 0);
  noFill();
  translate(0,0,0);
  rotateX(-PI/2);
  rotateY(0);
  rotateZ(0);
  rect(-500,-500,1500,1500);
  balls.move();
  balls.display();
  
}