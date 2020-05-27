import java.util.*;
Map<Object, Integer> photos;
PImage a,b,c,d,e;
color tint1;
color tint2;
void preload(){
  a= loadImage("a.jpg");
  b= loadImage("b.jpg");
  c= loadImage("c.jpg");
  d= loadImage("d.jpg");
  e= loadImage("e.jpg");
}

void setup(){
  size(1500,750);
  preload();
  tint1 = color(random(255),random(255),random(255));
  tint2 = color(random(255),random(255),random(255));
  photos = new HashMap<Object, Integer>();
  photos.put(a, (int)random(1,10));
  photos.put(b, (int)random(1,10));
  photos.put(c, (int)random(1,10));
  photos.put(d, (int)random(1,10));
  photos.put(e, (int)random(1,10));
}

void draw(){
  background(0);
  for(int i = 0; i < 10; i++){
    if(i > photos.get(a))
    tint(tint1);
    else
    tint(tint2);
    image(a, 150*i, 0, 150, 150);
  }
    for(int i = 0; i < 10; i++){
    if(i > photos.get(b))
    tint(tint1);
    else
    tint(tint2);
    image(b, 150*i, 150, 150, 150);
  }
    for(int i = 0; i < 10; i++){
    if(i > photos.get(c))
    tint(tint1);
    else
    tint(tint2);
    image(c, 150*i, 300, 150, 150);
  }
    for(int i = 0; i < 10; i++){
    if(i > photos.get(d))
    tint(tint1);
    else
    tint(tint2);
    image(d, 150*i, 450, 150, 150);
  }
    for(int i = 0; i < 10; i++){
    if(i > photos.get(e))
    tint(tint1);
    else
    tint(tint2);
    image(e, 150*i, 600, 150, 150);
  }
  
}

class img{
  Integer i;
  String name;
  
  img(String n, Integer p){
   name = n;
   i = p;
  }
  
  
  
}