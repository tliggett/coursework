class hippo{
  PVector location;
  float size;
  float count;
  float[]hippoColor;
  hippo(PVector loc){
    hippoColor = new float[] {random(255), random(255), random(255)};
    this.location = loc;
    size = 50;
    
  }
  void move(){
    
  }
  void display(){
    fill(hippoColor[0], hippoColor[1], hippoColor[2]);
    rect(location.x, location.y, size, size);
    text("" + count, location.x, location.y);
  }
  
  
  
}


class hippoPod{
   ArrayList<hippo> hippoRay = new ArrayList<hippo>();
   
  
  
  
}