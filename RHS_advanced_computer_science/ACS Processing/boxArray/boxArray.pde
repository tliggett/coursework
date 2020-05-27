float loc;
float []boxes;
int size;
color a;
color b;
color c;

void setup(){
  size(1000,100);  
  size = 10;
  a = color(random(255), random(255), random(255));
  b = color(random(255), random(255), random(255));
  c = color(random(255), random(255), random(255));
  
  boxes = new float[size];
  for(int i = 0; i < size; i++){
    boxes[i] = i;
  }
  loc = 0;
}

void draw(){
  background(0);
  noStroke();
  for(float f : boxes){
    if(f == loc){
     fill(a);
   }else{
     fill(b);
   }
   rect(width/size * f, 0, width/size, 100);
   
     
  }
  update();
  
  
}

void update(){
  /*loc += 1;
  
  if(loc >= size){
   loc = 0; 
   
  }*/
  loc = (int)random(size);
  if(loc >= size){
    loc = size;
  }
  
}