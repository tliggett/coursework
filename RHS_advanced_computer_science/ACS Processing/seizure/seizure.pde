void setup(){
  size(1000,1000);
  noStroke(); 
}
void draw(){
  
  int a = (int)random(1000);
  int b = (int)random(1000);
  for(int i = 0; i< a; i++){
   for(int j = 0; j < b; j++){
     fill(random(255), random(255), random(255));
     rect(width/a * i, height/b * j, width/a, height/b);
   }
  }
  
}