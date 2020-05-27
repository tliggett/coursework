
ballPit balls;
hippo po;


void setup(){
  size(900,900);
  balls = new ballPit(2000);
  po = new hippo(new PVector(400,400));
}

void keyPressed(){
  if(keyCode == UP){
    po.velocity.y -= 1;
    
  }if(keyCode == LEFT){
    po.velocity.x -= 1;
    
  }if(keyCode == RIGHT){
    po.velocity.x += 1;
    
  }if(keyCode == DOWN){
    po.velocity.y += 1;
    
  }
  
  
  
}
void keyReleased(){
 
}
void draw(){
  background(0);
  fill(255);
  noStroke();
  rect(100,100,700,700);
  balls.move();
 
  balls.display();
   po.move();
   po.display();
  for(int i = balls.balls.size()-1; i >= 0; i--){
      ball b = balls.balls.get(i);
      if(((b.location.x < po.location.x + po.size) &&(b.location.x > po.location.x)) && (b.location.y > po.location.y && b.location.y < po.location.y+po.size)){
        po.count += 1;
        po.hippoColor = b.ballColor;
        balls.setSpeed();
        balls.balls.remove(i);
      }
    }
}