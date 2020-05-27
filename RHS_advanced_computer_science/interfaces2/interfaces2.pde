import java.util.*;

String[] words;
ArrayList<Word> wordList; 
void setup() {
  size(800,800);
  
  words = loadStrings("vowelCount.txt");
  wordList = new ArrayList<Word>();
  for(String w : words){
   wordList.add(new Word(w)); 
  }
  Collections.sort(wordList);
  noLoop();
}

void draw() {
  background(0);
  fill(255);
  text("" + wordList, 25,25);
  
  for(Word w : wordList){
    fill(random(255),random(255),random(255));
    textSize(10 * w.vowelCount());
    text("" + w, random(width), random(height));
    
    
  }
  
}

class Word implements Comparable {
  String string;
  Word(String s) {
    string = s;
  }
  int vowelCount() {
    String vowels = "AEIOUaeiou";
    int vCount = 0;
    for (int i = 0; i < vowels.length(); i++)
    {
      if (string.indexOf(vowels.charAt(i)) >= 0) vCount++;
    }
    return vCount;
  }
  int compareTo(Object o) {
    Word w = (Word)o;
    
    if (this.vowelCount() > w.vowelCount() )
      return 1;
    if (this.vowelCount() < w.vowelCount() )
      return -1;
    return this.string.compareTo(w.string);
  }
  String toString(){
   return string; 
    
  }
}