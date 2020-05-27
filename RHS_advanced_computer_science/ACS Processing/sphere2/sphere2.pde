ArrayList<ball> pit;
float[][] token;
int num;
color c;
float theta = 0;
String s;
void setup() {
  size(500,500,P3D);
  num = 100;
  addToken();
   s = "";
    for(float[] ray : token){
      for(float t : ray){
      s += t + " "; 
    }
   s += "\n";
  }
  c = color(random(255),random(255),random(255) );
  pit = new ArrayList<ball>();
  for(int i = 0; i < num; i++){
    PVector loc = new PVector(random(width*2)-(width), random(width*2)-(width),random(width*2)-(width)); 
    
    pit.add(new ball(loc));
  }}
void draw() {
  background(0);
  update();
  camera(width *-1.25, height*-1.25, (height*-1.25) / tan(PI*30.0 / 180.0), width/2.0, height/2.0, 0, 0, 1, 0); 
  pushMatrix();
  rotateY(radians(theta));
  noFill();
  stroke(c);
  sphere(500);
  popMatrix();
  pushMatrix();
  fill(c);
  textSize(100);
  rotateY(PI);
  text("" + s,-2500,-500,-400);
  popMatrix();
}
void update(){
  for(int i = 0; i < pit.size(); i++){
    pit.get(i).move();
    pit.get(i).display();
    if(pit.get(i).location.mag()+ pit.get(i).radius >= 500){
      pit.get(i).velocity.mult(-1);
      c = pit.get(i).a;    
  }}
  theta += 1;
}
void addToken(){
  token = new float[40][5];
  for(int i = 0; i < token.length; i++){
   for(int j = 0; j < token[i].length; j++){
     token[i][j]=Math.round(random(9));
   }   }  }