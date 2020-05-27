class balls{
  ball[] pit;
  int num;
  balls(){
    num = 7;
    pit = new ball[num];
    for(int i = num-1; i >=0; i--){
        pit[i] = new ball(width- (width/num)*i); 
     
      
      
    }
  }
  void move(){
    for(int i = 0; i <num; i++){
      pit[i].move();
    }
    
  }
  void display(){
    for(int i = 0; i <num; i++){
      pit[i].display();
    }
  }
  
  
}


class ball{
  PVector size;
  PVector location;
  PVector velocity;
  float[] colorB;
  ball(float xloc){
    size = new PVector(25,25);
    location = new PVector(xloc, 200-(size.x));
    velocity = new PVector(-2, 0);
    colorB = new float[]{random(255),random(255),random(255)};
    
  }
  void move(){
    location.add(velocity);
    if(location.x < 0-size.x){
      location.x = width;
      size.y = 25;
    location.y = 200-(size.x);
    }
    
  }
  void display(){
    fill(colorB[0], colorB[1], colorB[2]);
    rect(location.x, location.y, size.x,size.y);
    
  }
  
  
  
}