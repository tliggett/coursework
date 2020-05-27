import peasy.*;
import peasy.org.apache.commons.math.*;
import peasy.org.apache.commons.math.geometry.*;

float[][] map;
PeasyCam cam;
int size;
int stretch;
void setup(){
  size(750,750, P3D);
  textSize(50);
  size = 129;
  stretch = 8;
  cam = new PeasyCam(this, 100);
  cam.setDistance(size*stretch);
  cam.lookAt(size*stretch/2, size*stretch/2, 0);
  
  
  
  map = new float[size][size];  
  int i = map.length-1;
  map[0][0] = random(-size/2,size/2)*stretch;
  map[0][i] = random(-size/2,size/2)*stretch;
  map[i][0] = random(-size/2,size/2)*stretch;
  map[i][i] = random(-size/2,size/2)*stretch;
  buildMap(0,i,0,i); 
  
  
  println("executed");
}

void draw(){
  background(125,125,255);
  noStroke();
  for(int x = 0; x < size-1; x++){
   for(int y = 0; y < size-1; y++){
      pushMatrix();
      //translate(x,y,0);
      beginShape();
      if(map[x][y] > 120){
        fill(255);
      }
      else if(map[x][y] > 70){
        fill(139,69,19);
      }else if(map[x][y] > 20){
       fill(0,123,12); 
      }else{
       fill(237, 201, 175); 
      }
      
      if(map[x][y] > 0){
      vertex(x*stretch,y*stretch,map[x][y]);
      vertex((x+1)*stretch, y*stretch, map[x+1][y]);
      vertex(x*stretch,(y+1)*stretch,map[x][y+1]);
      vertex((x+1)*stretch,(y+1)*stretch,map[x+1][y+1]);  
      }else{
      fill(0,0,255);
      vertex(x*stretch,y*stretch,0);
      vertex((x+1)*stretch, 0);
      vertex(x*stretch,(y+1)*stretch,0);
      vertex((x+1)*stretch,(y+1)*stretch,0);
      
      }
      endShape(CLOSE);
      popMatrix();
   }
  }

  cam.beginHUD();
  fill(255);
  textSize(20);
  text("TJ Fractal Landscape Prototype 3/3/17", 25,25);
  cam.endHUD();

}


void buildMap(int xmin, int xmax, int ymin, int ymax){
  if(xmax-xmin >= 2 && ymax -ymin >= 2){
      int xmid = (xmax+xmin)/2;
      int ymid = (ymax+ymin)/2;
      map[xmid][ymid] = (map[xmin][ymin] + map[xmin][ymax] + map[xmax][ymin] + map[xmax][ymax])/4 + random(-1.5,1.5)*stretch;
      map[xmid][ymin] = (map[xmin][ymin] + map[xmax][ymin] + map[xmid][ymid])/3 + random(-1,1)*stretch;
      map[xmin][ymid] = (map[xmin][ymin] + map[xmin][ymax] + map[xmid][ymid])/3 + random(-1,1)*stretch;
      map[xmid][ymax] = (map[xmin][ymax] + map[xmax][ymax] + map[xmid][ymid])/3 + random(-1,1)*stretch;
      map[xmax][ymid] = (map[xmax][ymin] + map[xmax][ymax] + map[xmid][ymid])/3 + random(-1,1)*stretch;
      buildMap(xmin,xmid,ymin,ymid);
      buildMap(xmid,xmax,ymin,ymid);
      buildMap(xmin,xmid,ymid,ymax);
      buildMap(xmid,xmax,ymid,ymax);
  }
}