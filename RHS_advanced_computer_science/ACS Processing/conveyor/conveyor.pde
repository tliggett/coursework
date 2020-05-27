balls b;
smash s;

void setup(){
  size(700,300);
  b = new balls();
  s = new smash();
  
  
}
void draw(){
   background(255);
   fill(s.colorB[0],s.colorB[1],s.colorB[2]);
   noStroke();
   rect(0,200, width, height);
   rect(0,0, width, 100);
   this.update();
   b.display();
   s.display();
  
}

void update(){
  s.move();
  b.move();
   for(int i = 0; i <b.pit.length; i++){
      if((s.velocity.y > 0) &&(b.pit[i].location.y <= s.location.y + s.size.y) &&(Math.abs(s.location.x - b.pit[i].location.x) <= b.pit[i].size.x)){
        b.pit[i].size.y = 200-(s.size.y + 100);
        b.pit[i].location.y = 200-b.pit[i].size.y;
        
      }
    }
  
 
}