ArrayList<box> boxes;
int num;
void setup() {
  size(500,500,P3D);
  num = 1000;
  boxes = new ArrayList<box>();
  for(int i = 0; i < num; i++){
     boxes.add(new box());
  }
  
}

void draw() {
  background(0);
  
  for(box b : boxes){
    b.move();
    b.display();
    
  }
  
  
  
 
  
  
  
  
  camera(width *-1, height*-1, (height*-1) / tan(PI*30.0 / 180.0), width/2.0, height/2.0, 0, 0, 1, 0); 
  pushMatrix();
  translate(height/2,width/2,height/2);
   
  noFill();
  
  stroke(255,255,0);
  box(500);
  popMatrix();
 
  
  
  
  
  


}