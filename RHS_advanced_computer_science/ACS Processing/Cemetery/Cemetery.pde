Person[]cemetery;
String[] file;
String highlight;
void setup(){
  
  size(1330,800);
  background(255);
  fill(149,0,255);
  textSize(32);
  text("Cemetery Lab :: List of Enhabitants by Date of Birth", 0, 32);
  noStroke();
  rect(30, height/2, 1250, 2);
  rect(25, (height/2) - 20, 5, 40);
  rect(1280, (height/2) - 20, 5, 40);
  file = loadStrings("cemetery2.txt");
  cemetery = new Person[file.length-4];
  for(int i = 0; i < cemetery.length; i++){
    cemetery[i] = new Person(file[i+4]);
    //println(cemetery[i].x);
    //println(cemetery[i]);
  }
  highlight = "Click on a bar to start!";
  /*double min = -999999;
  for(Person p : cemetery){
    if(min < p.birthDate){
     min =  p.birthDate;
    }
    
  }
  println((int)min/365);*/
  
  for(Person p : cemetery){
  fill(p.c);
  
  float h = (float)(5*(p.age));
  rect(p.x, (height/2) - (h/2), 3, h);
  
 }
  
  
}
void mousePressed(){
  for(Person b : cemetery){
    float h = (float)(5*(b.age));
   if((mouseX > b.x && mouseX < b.x+3) && ((mouseY > (height/2) - (h/2)) && (mouseY < (height/2) + (h/2)) )) {
     highlight = b.toString();
   }
  }
  
  
}

void draw(){
 fill(255);
 rect(width/2, 600, width/2, 300);
 
 fill(0);
 textSize(16);
 text(highlight, 1100,700);

}