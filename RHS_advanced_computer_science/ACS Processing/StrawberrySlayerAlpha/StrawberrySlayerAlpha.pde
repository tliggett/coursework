Viking warrior;
PImage map;
PImage back;
PVector mapSize;
HashMap<String, PImage> blueberry;
HashMap<String, PImage> strawberry;
HashMap<String, PImage> viking;
PImage logo;
ArrayList<Mob> mobs;
float count = 750;
PImage mixed;
float score;
boolean gameLive;
float sCount;
float bCount;
boolean gameOver;

void setup(){
  size(750,750);
  mapSize = new PVector(10000,10000);
  score = 0;
  sCount = 0;
  bCount = 0;
  
  warrior = new Viking();
  gameLive = false;
  gameOver = false;
  map = loadImage("images/grass.jpg");
  mixed = loadImage("images/mixed.png");
  logo = loadImage("images/slayer.png");
  //loads Viking HashMap
  viking = new HashMap<String, PImage>();
  viking.put("baseR", loadImage("images/viking/vright.png"));
  viking.put("baseL", loadImage("images/viking/vleft.png"));
  viking.put("swingR", loadImage("images/viking/swingright.png"));
  viking.put("swingL", loadImage("images/viking/swingleft.png"));
  viking.put("slain", loadImage("images/viking/slain.png"));
  
  //loads Strawberry HashMap
  strawberry = new HashMap<String, PImage>();
  strawberry.put("proto", loadImage("images/chaitea/proto.png"));
  strawberry.put("baseL", loadImage("images/strawberry/baseL.png"));
  strawberry.put("baseR", loadImage("images/strawberry/baseR.png"));
  strawberry.put("sliced", loadImage("images/strawberry/sliced.png"));
  for(int i = 0; i <4; i++){
  strawberry.put("kickL_0" + i, loadImage("images/strawberry/kickL_0" + i + ".png"));}
  for(int i = 0; i <4; i++)
  {strawberry.put("kickR_0" + i, loadImage("images/strawberry/kickR_0" + i + ".png"));}
  
  blueberry = new HashMap<String, PImage>();
  blueberry.put("sliced", loadImage("images/blueberry/sliced.png"));
  for(int i = 0; i <5; i++){
  blueberry.put("rollL_0" + i, loadImage("images/blueberry/rollL_0" + i + ".png"));}
  for(int i = 0; i <5; i++)
  {blueberry.put("rollR_0" + i, loadImage("images/blueberry/rollR_0" + i + ".png"));}
  
  
  
  mobs = new ArrayList<Mob>();
  for(int i = 0; i <count; i++)
  mobs.add(getRandomMob());
  
  
}
void draw(){
  
  if(gameLive){
  background(0,125,0);
  //image(map.get((int)warrior.location.x,(int)warrior.location.y, width, height), 0,0);
  
  for(Mob m : mobs){
   m.tick();
   m.display();
  }
 
  for(int i = mobs.size()-1; i >= 0; i--){
    if(!(mobs.get(i).getHealth() > 0) &&  !mobs.get(i).isOnScreen()){
      mobs.remove(i);
      mobs.add(getRandomMob());
    }
  }
  
  warrior.display();
  warrior.tick();
  
  displayHUD();
  if(warrior.health <= 0){
  gameLive = false;
  gameOver = true;
  }
  
  }else if(gameOver){
    background(0,125,0);
    textSize(50);
    fill(255,0,0);
    text("GAME OVER", 250,200);
    textSize(25);
    text("Press r to restart", width/2 - 85, 250);
    image(viking.get("slain"), width/2 -100, height/2 -100, 200,200);
    
  }else{
    background(175,225,175);
    warrior.display();
    image(mixed, width/2 - 100, height/2 + 150, 200,200);
    image(logo, width/2 - 350,75, 700,150);
    image(strawberry.get("kickL_02"), 375, 260, 200, 200);
    image(strawberry.get("kickR_02"), 185, 260, 200, 200);
    
    textSize(25);
    fill(255,0,0);
    text("Press r to start", width/2 - 85, 250);
  }
  
}

void displayHUD(){
  fill(255,0,0);
  strokeWeight(2);
  textSize(25);
  text("Health:", 0, 30); 
  fill(149,233,255);
  rect(100, 10, 300 * (warrior.health/100), 25);
  //text("Score: " + score, width*4/5, 25);
  pushMatrix();
  noFill();
  stroke(0,0,255);
  strokeWeight(3);
  rect(10,height-110, 100,100);
  rect(120,height-110, 100,100);
  image(strawberry.get("baseL"), 10,height-110, 100,100);
  image(blueberry.get("rollL_00"), 120,height-110, 100,100);
  textSize(20);
  text("" + (int)sCount, 20,height-90);
  text("" + (int)bCount, 130,height-90);
  rect(width-160,height-160, 150,150);
  stroke(255,255,0);
  strokeWeight(6);
  point(width-160 + (warrior.location.x/mapSize.x)*150, height-160 + (warrior.location.y/mapSize.y)*150);
  stroke(255,0,0);
  strokeWeight(3);
  for(Mob m : mobs)
  point(width-160 + (m.getLocation().x/mapSize.x)*150, height-160 + (m.getLocation().y/mapSize.y)*150);
  popMatrix();
  
  
}

void keyPressed(){
   
  if(gameLive){
  switch(keyCode){
    case UP: warrior.velocity.y = -warrior.speed;
    break;
    case LEFT: warrior.velocity.x = -warrior.speed;
    break;
    case DOWN: warrior.velocity.y = warrior.speed;
    break;
    case RIGHT: warrior.velocity.x = warrior.speed;
    break;
    
   }
   if(key == ' ')
    warrior.strike = true; 
  }
   if(key == 'r'){
    gameLive = true;
    gameOver = false;
    bCount = 0;
    sCount = 0;
    score = 0;
    warrior = new Viking();
    mobs = new ArrayList<Mob>();
    for(int i = 0; i <count; i++)
    mobs.add(getRandomMob());
   }
   if(key == 'q'){
    gameLive = false;
    gameOver = false;
   }
}

void keyReleased(){
 switch(keyCode){
    case UP: 
    if(warrior.velocity.y == -warrior.speed)
    warrior.velocity.y = 0;
    break;
    case LEFT: 
    if(warrior.velocity.x == -warrior.speed)
    warrior.velocity.x = 0;
    break;
    case DOWN: 
    if(warrior.velocity.y == warrior.speed)
    warrior.velocity.y = 0;
    break;
    case RIGHT: 
    if(warrior.velocity.x == warrior.speed)
    warrior.velocity.x = 0;
    break;
   }
   if(key == ' '){
    warrior.strike = false; 
   }
   
}








// VIKING









class Viking{
  PVector location;
  PVector velocity;
  PVector size;
  float health;
  float speed;
  boolean strike;
  boolean left;
  String imageKey;
  Viking(){
    size = new PVector(200,200);
    //location = new PVector(10,10);
    location = new PVector(mapSize.x/2, mapSize.y/2);
    velocity = new PVector(0,0);
    health = 100;
    speed = 5;
    strike = false;
    imageKey = "baseL";
    left = true;
  }
  void tick(){
   move(); 
   update(); 
  }
  
  void update(){
    
    if(velocity.x > 0){
    imageKey = "baseR";
    left = false;
    }
    if(velocity.x < 0){
      left = true;
      imageKey = "baseL";
    }
    if(strike){
     if(imageKey == "baseL")
     {imageKey = "swingL";}
     if(imageKey == "baseR")
     {imageKey = "swingR";}
    }else{
     if(imageKey == "swingL"){ 
      imageKey = "baseL";
     }if(imageKey == "swingR"){
      imageKey = "baseR"; 
       
     }
    }
    
  }
  void display(){
      image(viking.get(imageKey), (width-size.x)/2, (height-size.y)/2, size.x, size.y);
    }
    
    
  
  void move(){
   location.add(velocity);
   if(location.x < 0){ 
    velocity.x = 0; 
    location.x = 0;
   }
   if(location.y < 0){ 
    velocity.y = 0; 
    location.y = 0;
   }
   if(location.x > mapSize.x-size.x/4){
    velocity.x = 0; 
    location.x = mapSize.x-size.x/4;
   }
   if(location.y > mapSize.y-size.y/4){
    velocity.y = 0; 
    location.y = mapSize.y - size.y/4;
   }
    
  }
  
}











//Mob








interface Mob{
 PVector getLocation();
 float getHealth();
 boolean isOnScreen();
 boolean alive();
 void tick();
 void display();
 
}










//Strawberry













class Strawberry implements Mob{
  PVector location;
  PVector velocity;
  PVector size;
  float health;
  float speed;
  PVector direction;
  float kick;
  boolean strike;
  boolean left;
  Strawberry(){
    
    size = new PVector(200,200);
    //size = new PVector(100,100);
    location = new PVector(random(mapSize.x-size.x), random(mapSize.y-size.y));
    velocity = new PVector(0,0);
    health = 1;
    speed = 3;
    direction = new PVector(0,0);
    
    kick = 0;
    strike = false;
    left = true;
  }
  void tick(){
   move();
   update();
  }
  void update(){
    if(warrior.strike && warrior.left){
    if(location.x - (warrior.location.x -(width/2)) > (width/2)-(warrior.size.x) + 25 && location.x - (warrior.location.x -(width/2)) < (width/2)-(warrior.size.x) + 75 && location.y-(warrior.location.y-(height/2)) > (height/2)-size.y+ 80 && location.y-(warrior.location.y-(height/2)) <(height/2) - size.y + 145){
    if(health > 0)
      sCount++;
      health = 0;
    }
    }
    if(warrior.strike && !warrior.left){
    if(location.x - (warrior.location.x -(width/2)) > (width/2)-(warrior.size.x) + 120 && location.x - (warrior.location.x -(width/2)) < (width/2)-(warrior.size.x) + 175 && location.y-(warrior.location.y-(height/2)) > (height/2)-size.y+ 80 && location.y-(warrior.location.y-(height/2)) <(height/2) - size.y + 145){
    if(health > 0)
      sCount++;
      health = 0;
    
    } 
      
      
      
    }
    
    
  }
  void move(){
    if(onScreen() && health > 0){
      direction.mult(0);
      direction.add(warrior.size);
      direction.mult(0.5);
      direction.add(location);
      direction.sub(warrior.location);
      if(direction.mag() < 50){
       strike = true;
       
       kick += 0.25;
       if(kick == 2) warrior.health -= 5;
       if(kick > 3) kick = 0;
       
       
      }else{
        strike = false;
        kick = 0;
      }
      direction.normalize();
      direction = direction.mult(-speed);
      location.add(direction);
    }
    if(direction.x > 0)
     left = false; 
    if(direction.x < 0)
     left = true;
  }
  boolean alive(){
   return !(getHealth() > 0) && !onScreen(); 
    
    
  }
  boolean onScreen(){
   return  ((location.x-(warrior.location.x-(width/2))) < width && (location.x-(warrior.location.x-(width/2))) > 0 - size.x && (location.y-(warrior.location.y-(height/2))) < height && (location.y-(warrior.location.y-(height/2))) > 0 - size.y );
    
    
  }
  void display(){
    if(onScreen()){
    if(health > 0){
      //image(strawberry.get("proto"),location.x-(warrior.location.x-(width/2)), location.y-(warrior.location.y-(height/2)), size.x, size.y);
      
    if(!strike){
    if(left) image(strawberry.get("baseL"), location.x-(warrior.location.x-(width/2)), location.y-(warrior.location.y-(height/2)), size.x, size.y);
    else image(strawberry.get("baseR"), location.x-(warrior.location.x-(width/2)), location.y-(warrior.location.y-(height/2)), size.x, size.y);
    }else{
     if(left){
       image(strawberry.get(("kickL_0" + (int)kick)), location.x-(warrior.location.x-(width/2)), location.y-(warrior.location.y-(height/2)), size.x, size.y);
     }else{
       image(strawberry.get(("kickR_0" + (int)kick)), location.x-(warrior.location.x-(width/2)), location.y-(warrior.location.y-(height/2)), size.x, size.y);
     }
      
    }
    

    }else{
    image(strawberry.get("sliced"), location.x-(warrior.location.x-(width/2)), location.y-(warrior.location.y-(height/2)), size.x, size.y);
    }
      
    }
    
    

}
  boolean isOnScreen(){
  return onScreen();
  }
  float getHealth(){
    return health;  
  }
  PVector getLocation(){
   return location; 
    
  }
  
  
  
}








//Blueberry












class Blueberry implements Mob{
  PVector location;
  PVector velocity;
  PVector size;
  float health;
  float speed;
  PVector direction;
  PVector distance;
  float roll;
  boolean strike;
  boolean left;
  Blueberry(){
    size = new PVector(100,100);
    location = new PVector(random(mapSize.x-size.x), random(mapSize.y-size.y));
    velocity = new PVector(0,0);
    health = 1;
    speed = 6;
    direction = new PVector(0,0);
    roll = 0;
    strike = false;
    left = true;
    distance = new PVector(0,0);
  }
  void tick(){
   move();
   update();
  }
  void update(){
    if(warrior.strike && warrior.left){
    if(location.x - (warrior.location.x) > 75 -(warrior.size.x) && location.x - (warrior.location.x) < 110 - (warrior.size.x) && location.y-(warrior.location.y) > 50 - size.y && location.y-(warrior.location.y) < 70 - size.y){
      if(health > 0)
      bCount++;
      health = 0;
    }
    }
    if(warrior.strike && !warrior.left){
    if(location.x - (warrior.location.x) > 180 -(warrior.size.x) && location.x - (warrior.location.x) < 220 -(warrior.size.x) && location.y-(warrior.location.y) > 50 - size.y && location.y-(warrior.location.y) <70 - size.y){
      if(health > 0)
      bCount++;
      health = 0;
    } 
      
      
      
    }
    if(location.x > mapSize.x || location.x < 0 || location.y > mapSize.y || location.y < 0)
    health = 0;
    
  }
  void move(){
    if(health > 0){
    if(onScreen() && health > 0 && !strike){
      direction.mult(0);
      direction.add(warrior.size);
      direction.mult(0.5);
      direction.add(location);
      direction.sub(warrior.location);
      direction.normalize();
      direction = direction.mult(-speed);
      strike = true;
    }
     if(location.x - (warrior.location.x) > 130 -(warrior.size.x) && location.x - (warrior.location.x) < 170 -(warrior.size.x) && location.y-(warrior.location.y) > 5 - size.y && location.y-(warrior.location.y) <90 - size.y)
     warrior.health -= 1;
     location.add(direction);
     roll+= 0.25;
     if(roll>4) roll = 0;
     
    if(direction.x > 0)
     left = false; 
    if(direction.x < 0)
     left = true;
     
     if(strike && !onScreen()){
      health = 0; 
     }
    }
    
  }
  
  boolean onScreen(){
   return  ((location.x-(warrior.location.x-(width/2))) < width && (location.x-(warrior.location.x-(width/2))) > 0 - size.x && (location.y-(warrior.location.y-(height/2))) < height && (location.y-(warrior.location.y-(height/2))) > 0 - size.y );
    
    
  }
  void display(){
    if(onScreen()){
    if(health > 0){
     if(left){
       image(blueberry.get(("rollL_0" + ((int)roll))), location.x-(warrior.location.x-(width/2)), location.y-(warrior.location.y-(height/2)), size.x, size.y);
     }else{
       image(blueberry.get(("rollR_0" + ((int)roll))), location.x-(warrior.location.x-(width/2)), location.y-(warrior.location.y-(height/2)), size.x, size.y);
     }
      
    
    

    }else{
    image(blueberry.get("sliced"), location.x-(warrior.location.x-(width/2)), location.y-(warrior.location.y-(height/2)), size.x, size.y);
    }
      
    }
    
    

}


  boolean alive(){
     return (getHealth() < 0) && !onScreen(); 
     
  }
  boolean isOnScreen(){
  return onScreen();
  }
  float getHealth(){
    return health;  
  }
  PVector getLocation(){
   return location; 
    
  }
  
  
}

Mob getRandomMob(){
 int f = (int)(random(0,4));
 Mob m;
 if(f == 0){
  m = new Blueberry(); 
    
 }else{
  m = new Strawberry();
 }
 
  return m;
  
}
