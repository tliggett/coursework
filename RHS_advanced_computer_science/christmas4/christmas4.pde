import processing.sound.*;
import java.util.Iterator;
String message;
String[] keys;
int shift;
int asciiShift;
SoundFile file;
String[] goal;
float time;
PImage background;
float startTime;
String unEncrypted;
PFont[] fonts;
String timeString;
ArrayList<Flake>flakes;
void setup(){
    fullScreen();
    background(0);
    frameRate(60);
    background = loadImage("backgroundx.jpg");
    fonts = new PFont[2];
    file = new SoundFile(this, "xmas_music2.mp3");
    file.play();
    flakes = new ArrayList<Flake>();
    while(flakes.size() < 100)flakes.add(new tJFlake());
    fonts[0] = loadFont("courier.vlw");
    fonts[1] = loadFont("FrenchScriptMT-48.vlw");
    textFont(fonts[1], 32);
    fill(0);
    startGame();
   
   keys = new String []  
   {"qwertyuiop","asdfghjkl","zxcvbnm","QWERTYUIOP","ASDFGHJKL","ZXCVBNM"};
  

}


interface Flake {
  void snow(); //resets flake
  void display(); //flake identity, ellipse
  void addPhysics(); //Velocity, acceleration, location (experiement with these!)
  void somethingSpecial();
  boolean isDead(); //snow at bottom of screen(will be provided, but can be changed!)
  
}

class tJFlake implements Flake{
  PVector location;
  PVector velocity;
  PVector acceleration;
  float size;
  color flakeColor1;
  color flakeColor2;
  
  
  tJFlake(){
    location = new PVector(random(-3, width), random(-10,height));
    velocity = new PVector(random(-.22, .22), random(3,20));
    acceleration = new PVector(random(0,0.01),random(0.01));
    flakeColor1 = color(random(255),random(255),random(255));
    flakeColor2 = color(random(255),random(255),random(255));
    size =  random(10);
    
  }
  void snow(){
    location.y=random(-10, 0);
    location.x=random(width);
    flakeColor1 = color(random(255),random(255),random(255));
    flakeColor2 = color(random(255),random(255),random(255));
    velocity = new PVector(random(-.22, .22), random(3, 20));
  }
  void display(){
    noStroke();
    fill(flakeColor1);
    ellipse(location.x, location.y, size, size);
    
    
  }
  void addPhysics(){
    location.add(velocity);
    velocity.add(acceleration);
    
    
  }
  void somethingSpecial(){
    if(flakeColor1 != flakeColor2){
         float red = red(flakeColor1);
         float green = green(flakeColor1);
         float blue = blue(flakeColor1);
         if(red(flakeColor1) > red(flakeColor2)){
          red-= 20;
         }else if(red(flakeColor1) < red(flakeColor2)){
           red+= 20;
         }
         if(Math.abs(red(flakeColor1) - red(flakeColor2)) < 20){
          red =  red(flakeColor2);
         }
         if(green(flakeColor1) > green(flakeColor2)){
          green-= 20;
         }else if(green(flakeColor1) < green(flakeColor2)){
           green+= 20;
         }
         if(Math.abs(green(flakeColor1) - green(flakeColor2)) < 20){
          green =  green(flakeColor2);
         }
         
         if(blue(flakeColor1) > blue(flakeColor2)){
          blue-= 20;
         }else if(blue(flakeColor1) < blue(flakeColor2)){
           blue+= 20;
         }if(Math.abs(blue(flakeColor1) - blue(flakeColor2)) < 20){
          blue =  blue(flakeColor2);
         }
      
      flakeColor1 = color(red,green,blue);
      
    }else{
      flakeColor2 = color(random(255),random(255),random(255));
      
      
    }
    
  }
  boolean isDead(){
        if(location.y > height)return true;
        return false;
  
  }
  
  
  
  
  
  
}


void startGame(){
  time = 0;
  timeString = "";
    startTime = millis();
    goal = new String[] {"Merry Christmas", "We wish you a _____ ________"};
   message = "";
   unEncrypted = "";
   shift = 0;
   asciiShift = 0;
   while(shift == 0)shift = Math.round(random(6)-3);
   while(asciiShift == 0)asciiShift = Math.round(random(6)-3);
   
   file.jump(0);
  
  
  
}
void draw(){
  background(0);
  image(background, 0,0, width,height);
  textSize(64);
  fill(255);
  
  Iterator<Flake> it = flakes.iterator();
  while (it.hasNext()) {
    Flake f = it.next();
    f.display();
    f.addPhysics();
    f.somethingSpecial();

    if (f.isDead()) {
      f.snow();
    }
  }
  
  fill(255);
  textFont(fonts[0], 32);
  
  //text(frameRate + "", 500,500);
  
  if(!message.equals(goal[0])){
    text("You typed : " + unEncrypted, 0, 200, 900, 1000);
    text("Hint: " + goal[1], 0, 500);
    float timeElapsed = millis()-startTime;
    timeElapsed /= 1000;
    time = 120- (int)timeElapsed;
    String seconds = "";
    if(((time%60)/1) < 10){
     seconds =  "0" + (int)((time%60)/1);
    }else{
      seconds = "" + (int)((time%60)/1);
    }
    timeString = "" + ((int)(time/60)) + ":" + seconds; 
    if(time == 0){
     startGame(); 
    }
    
    
    
    
  }else{
    textFont(fonts[1], 100);
    
  }
  
  text("Message   : " + message, 0,100, 900, 1000);
  
  text("Time Left :: " + (timeString), 1400, 100);


}
void keyPressed(){
 if(keyCode == BACKSPACE)
  if(message.length() > 0){ message = message.substring(0, message.length()-1); unEncrypted = unEncrypted.substring(0, unEncrypted.length()-1);}
 if(key == ' ') {message += " "; unEncrypted += " ";}
 
   if(keyCode == ENTER){
    startGame();
   }
 //97-122 lowercase
 //65-90 uppercase
 
 
 
 
 
 
 for(int i = 0; i< keys.length; i++){
   
   
   if(keys[i].contains(key + "")){
    unEncrypted += key;
    int keyPos = keys[i].indexOf(key);
    if(shift >= 0){ keyPos = (keyPos + shift)%keys[i].length();}
    if(shift<0) {keyPos = (keys[i].length() + shift + keyPos)%keys[i].length() ;}
    char character = keys[i].charAt(keyPos);  
    boolean isUpperCase = false;
    if(character < 91){
       isUpperCase = true;
       character += 32;
   }
   if(asciiShift < 0){
   character+= asciiShift;
   println(character);
   if(character < 97){
    character += 26;
   }
   }else{
    character += asciiShift; 
    if(character > 122){
    character-=26;
    }
     
   }
   
   
   
   if(isUpperCase){
     character-=32;
   }
   message += character;
   }
   
 }
}