color a;
void setup(){
  size(800,800);
  noStroke();
  background(random(255), random(255), random(255));
  a = color(random(255), random(255), random(255));

}
void draw(){
  
  int a = height;
  int b = width;
  
  
  
  for(int i = 50; i< a; i+= 100){
   for(int j = 50; j < b; j+= 100){
    double x = Math.pow(mouseX-i, 2);
     double y = Math.pow(mouseY-j, 2);
     
     if(x+y <= 2500){
       fill(random(255), random(255), random(255));
       
     }
    
     else{
      fill(a); 
       
     }
    
     ellipse(i, j, 100, 100);
   }
  }
  
}