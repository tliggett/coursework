


class Person{
  double age;
  double deathDate;
  double birthDate;
  String[] name;
  String fromFile;
  float x;
  color c;
  Person(String fromFile){
    this.fromFile = fromFile;
    name = new String[]{"","",""};
    c = color(random(255), random(255), random(255));
    setName();
    setAgeAndDeath();
    setBirth();
  }
   
  void setName(){
    String n = fromFile.substring(0, 24);
    String[] na = n.split("\\s+");
    name[0] = na[0];
    name[2] = na[na.length-1].substring(0, 1) + na[na.length-1].substring(1, na[na.length-1].length()).toLowerCase();
    if(na.length == 3){
      name[1] = na[1];
    }else{
     name[1] = ""; 
      
    }
  }
  
  
  void setBirth(){
   birthDate = deathDate- (age*365); 
   println((int)(birthDate/365));
    x = (float)(10*((birthDate/365)-1729))+ 35;
  }
  
  
  void setAgeAndDeath(){
  String d = fromFile.substring(24,42);
  String[] da = d.split("\\s+");
  
  //sets age
  if(da[4].contains("d")){
    da[4] = da[4].replace("d", "");
    age = Double.parseDouble(da[4]);
    age/=365;   
  }if(da[4].contains("w")){
    da[4] = da[4].replace("w", "");
    age = Double.parseDouble(da[4]);
    age/=52;
  }else{
    age = Double.parseDouble(da[4]);
  }
  
  
  //sets dayofdeath
  deathDate = 0.0;
  if(!da[1].equals("xx")){
    deathDate+= Double.parseDouble(da[1]);
  }
  switch(da[2]){
   case "Jan": deathDate+= 0;
   break;
   case "Feb": deathDate+= 31;
   break;
   case "Mar": deathDate+= 31+28;
   break;
   case "Apr": deathDate+= 31+28+31;
   break;
   case "May": deathDate+= 31+28+31+30;
   break;
   case "Jun": deathDate+= 31+28+31+30+31;
   break;
   case "Jul": deathDate+= 31+28+31+30+31+30;
   break;
   case "Aug": deathDate+= 31+28+31+30+31+30+31;
   break;
   case "Sep": deathDate+= 31+28+31+30+31+30+31+31;
   break;
   case "Oct": deathDate+= 31+28+31+30+31+30+31+31+30;
   break;
   case "Nov": deathDate+= 31+28+31+30+31+30+31+31+30+31;
   break;
   case "Dec": deathDate+= 31+28+31+30+31+30+31+31+30+31+30;
   break;
  }
  deathDate += Double.parseDouble(da[3])*365;
  
  
  
  
  
  }
  
  
  
  
  
  
  
  String toString(){
    String s = "";
    
    s += name[2] + ", " + name[0] + " "+name[1];
    s += "\nBorn :: " + (int)birthDate/365;
    s += "\nDied :: " + (int)deathDate/365;
    s += "\nAge :: " + age;
    
    return s;
    
  }

}