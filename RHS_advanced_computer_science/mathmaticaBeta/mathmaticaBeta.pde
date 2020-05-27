import peasy.*;
import peasy.org.apache.commons.math.*;
import peasy.org.apache.commons.math.geometry.*;

PeasyCam cam;
Menu menu;
float max;
float dx;
PVector step;
Function function;
float x1;
float x2;
float y1;
float y2;
PVector dA;
void setup() {
  size(1500, 900, P3D);
  max = 40;
  menu = new Menu();
  function = new Function(("2x+y"));
  cam = new PeasyCam(this, 100);
  cam.setDistance(160);
  cam.lookAt(0, 0, 0);
  
  dA = new PVector(.001,.001);
  x1 = -2;
  x2 = 2;
  y1 = -2;
  y2 = 2;
  
  
  dx = .2;
  step = new PVector(0, 0, 0);
  
  println("Integral for the function from [" + x1 + "," + x2+"] x ["+ y1 + "," + y2+ "] is approximately :: " + function.integrate(x1, x2, y1, y2, dA.x, dA.y));
}

void draw() {
  background(255);
  stroke(0);
  strokeWeight(4);
  noFill();
  //stroke(125,0,0);
  line(-max, 0, max, 0);
  //stroke(0,125,0);
  line(0, -max, 0, max);
  //stroke(0,0,125);
  line(0, 0, -max, 0, 0, max);
  box(max*2);
  function.display();
  function.displayIntegral(x1, x2, y1, y2, 0.1, 0.1);
  menu.display(function);
  
}
void keyPressed() {
  if (keyCode == UP) {
    step.y ++;
  }
  if (keyCode == DOWN) {
    step.y --;
  }
  if (keyCode == LEFT) {
    step.x --;
  }
  if (keyCode == RIGHT) {
    step.x ++;
  }
  if (keyCode == ENTER) {
    step.z++;
  }
  if (keyCode == SHIFT) {
    step.z--;
  }else{
   menu.setKey(); 
    
  }
}


class Function {
  String name; 


  Function(String n) {
    name = n;
  }
  float get(float x, float y) {
    return(float)(  (3*x+ 2*y) );
  }

  void display() {
    strokeWeight(1);
    stroke(0, 21, 255);
    float z = 0;
    for (float x = (-max) + step.x; x<(max) + step.x; x+= dx) {
      for (float y = (-max) + step.y; y < (max) + step.y; y+= dx) {
        z = (float) function.get(x, y);

        if ((z < max + step.z) && (z > -max))
          point(x+ step.x, -y+ step.y,z + step.z);
      }
    }
    
    
   
  }

  float integrate(float x1, float x2, float y1, float y2, float dx, float dy) {
    double z = 0;
    for (float x = x1; x < x2; x += dx) {
      for (float y = y1; y < y2; y += dy) {
        z += (function.get(x,y));
      }
    }
    return (float)(z* (dx) * (dy));
  }
  void displayIntegral(float x1, float x2, float y1, float y2, float dx, float dy){
    for (float x = x1; x < x2; x += dx) {
      for (float y = y1; y < y2; y += dy) {
        stroke(255,0,0); 
        line(x,-y,get(x,y), x,-y,0);
      }
    } 
  }  
  String toString(){
   return name; 
  }
}

class Menu{
  String menuKey;
  Menu(){
    menuKey =  "main";
    
    
  }
  void setKey(){
    if(menuKey.equals("main")){
      switch(key){
       case '1':
       menuKey = "value";
       break;
       case '2':
       break;
       case '3':
       break;
        
      }
      
    }
    
  }
  void display(Function function){
  cam.beginHUD();
  fill(0);
  textSize(25);
  text("TJ Mathmatica Beta", 50,50);
  text("Function: " + function.toString(),50, 100);
    switch(menuKey){
     case "main":
     text("(1) Value", 50, 150);
     text("(2) DZ", 50, 200);
     text("(3) Integrate", 50, 250);
     break;
     case "value":
     //text("Enter x: ");
     break;
     
     
   }
    cam.endHUD();
    
  }




}