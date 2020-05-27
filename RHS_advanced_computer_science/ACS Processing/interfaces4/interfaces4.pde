import java.util.*;
TreeSet<Site> sites;

void setup(){
  sites = new TreeSet<Site>();
  String[] fromFile = loadStrings("websites.txt");
  for(String s : fromFile)
  sites.add(new Site(s));
  size(500,500);
}

void draw(){
  background(255);
  fill(0);
  int i = 25;
  int step = (height-25)/sites.size();
  for(Site p : sites){
  text("" + p, 0, i);
  i += step;
  }
}

class Site implements Comparable{
  private String address;
  private String domain;
  Site(String fromFile){
    String[] f = fromFile.split("\\.");
    setAddress(f);
    setDomain(f);
    
  }
  
  void setAddress(String [] fromFile){
    
    address = fromFile[0];
    
    
  }
  void setDomain(String [] fromFile){
    
    domain = fromFile[1];
    
  }
  
  
  int compareTo(Object o){
    Site s = (Site)o;
    if(!this.domain.equals(s.domain)){
      return this.domain.compareTo(s.domain);
    }
    return this.address.compareTo(s.address);
  }
  
  
  String toString(){
    return address + "." + domain;
  }
  
}