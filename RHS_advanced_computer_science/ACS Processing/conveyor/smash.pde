class smash{
  
  PVector location;
  PVector size;
  float[] colorB;
  PVector velocity;
  
  smash(){
    size = new PVector(25,10);
    location = new PVector(width/2, 100);
    colorB = new float[]{random(255),random(255),random(255)};
    velocity = new PVector(0, 3);
  }
  void move(){
    size.add(velocity);
    if(size.y <= 0 || size.y >= 100){
     velocity.y *= -1;  
    }
   
    
  }
  void display(){
    fill(colorB[0], colorB[1], colorB[2]);
    noStroke();
    rect(location.x, location.y, size.x, size.y);
    
  }
  
  
}