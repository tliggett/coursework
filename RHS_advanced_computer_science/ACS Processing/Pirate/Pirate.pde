PImage pirate;
PImage geek;
String[] lines;
String lineToTranslate;
String arrrr;
String regex = "(.*)[.?!](.*)";
void setup(){
 size(700,700);
  pirate = loadImage("pirate.png"); 
  geek = loadImage("geek.gif"); 
  lines = loadStrings("pirate.txt");
  lineToTranslate = lines[(int)random(lines.length)];
  arrrr = pirateSpeak(lineToTranslate);
  String[] fontList = PFont.list();
  printArray(fontList);
}
void mousePressed(){
    lineToTranslate = lines[(int)random(lines.length)];
    arrrr = pirateSpeak(lineToTranslate);
  
  
}
void draw(){
  background(255);
  image(pirate, 0,300,300,300);
  image(geek, 0,0,300,300);
  pushMatrix();
  fill(0);
  PFont font1 = createFont("Marlett", 16);
  textFont(font1);
  text(lineToTranslate, 310, 150, 350, 150);
  popMatrix();
  
  pushMatrix();
  fill(255,0,0);
  PFont font2 = createFont("Zapfino", 16);
  textFont(font2);
  text(arrrr, 310, 400, 350, 400);
  popMatrix();
}

String pirateSpeak(String reg){
  String o = reg;
  o = o.toLowerCase();
  o = o.replace("hello" , "ahoy");
  o = o.replace("hi " , "yo-ho-ho ");
  o = o.replace("my" , "me");
  o = o.replace("friend" , "bucko");
  o = o.replace("sir" , "matey");
  o = o.replace("miss" , "proud beauty");
  o = o.replace("stranger" , "scurvy dog");
  
  
  o = o.replace("officer" , "foul blaggart");
  o = o.replace("where" , "whar");
  o = o.replace(" is" , " be");
  o = o.replace("is " , "be ");
  o = o.replace("the " , "th' ");
  o = o.replace("you " , "ye ");
  o = o.replace(" you" , " ye");
  o = o.replace("old " , "barnacle covered ");
  o = o.replace(" old" , " barnacle covered");
  o = o.replace("happy" , "grog-filled");
  o = o.replace("drink" , "rum");
  o = o.replace("nearby" , "broadside");
  o = o.replace("restroom" , "head");
  o = o.replace("restaurant" , "galley");
  o = o.replace("hotel" , "fleabag inn");
  o = o.replace("oh my gosh" , "shiver me timbers");
  o = o.replace("hotel" , "fleabag inn");
  
  String []conv = o.split(" ");
  for(int i = 0; i<conv.length; i++){
    if(conv[i].equals("i")){
     conv[i] = "I";
    }
    
   if(conv[i].matches(regex)){
    int r = (int)random(2);
    switch(r){
     case 0: 
     conv[i] = conv[i].replace(".", " Arrrr");
     conv[i] = conv[i].replace("!", " Arrrr");
     conv[i] = conv[i].replace("?", " Arrrr");
      
     break;
    }
     if(i != conv.length-1){
       String s1 = conv[i+1].substring(0,1).toUpperCase();
       conv[i+1] = s1 + conv[i+1].substring(1);
     }
     
   }
  }
  o = "\"";
  
  conv[0] = conv[0].substring(0,1).toUpperCase() + conv[0].substring(1);
  for(String s : conv){
     o+= s + " ";
   }
  
  o += "\"";
  
  
  
  
  return o;
  
}