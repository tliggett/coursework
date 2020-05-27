class hippo{
  PVector location;
  PVector velocity;
  float size;
  float count;
  float[]hippoColor;
  hippo(PVector loc){
    hippoColor = new float[] {random(255), random(255), random(255)};
    this.location = loc;
    velocity = new PVector(random(7),random(7));
    size = 50;
    
  }
  void move(){
    location.add(velocity);
    velocity.limit(7);
    if(location.x > width- size-100 || location.x < 0 +100){
      velocity.x *= -1;
    
    }
    if(location.y > height - size-100 || location.y < 0 + 100){
      velocity.y *= -1;
      
    }
  }
  void display(){
    fill(hippoColor[0], hippoColor[1], hippoColor[2]);
    noStroke();
    rect(location.x, location.y, size, size);
    text("" + count, location.x, location.y);
  }
  
  
  
}


class hippoPod{
   ArrayList<hippo> hippoRay = new ArrayList<hippo>();
   
  
  
  
}