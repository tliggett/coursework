
ballPit balls;
hippo po;


void setup(){
  size(900,900);
  balls = new ballPit(1500);
  po = new hippo(new PVector(50,400));
}

void keyPressed(){
  if(keyCode == UP){
    po.location.x = 100;
    for(int i = balls.balls.size()-1; i >= 0; i--){
      ball b = balls.balls.get(i);
      if((b.location.x < 150) && (b.location.y > po.location.y && b.location.y < po.location.y+po.size)){
        po.count += 1;
        balls.balls.remove(i);
      }
    }
  }
  
  
}
void keyReleased(){
 if(keyCode == UP){
  po.location.x = 50; 
 }
}
void draw(){
  background(255);
  noFill();
  rect(100,100,700,700);
  balls.move();
 
  balls.display();
   po.display();
  
}