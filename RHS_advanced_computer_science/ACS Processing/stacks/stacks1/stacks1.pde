import java.util.*;
Stack<Stack<String>> sos;


void setup(){
sos = new Stack<Stack<String>>();
String[] temp = loadStrings("stacks1.txt");  
for(String s : temp){
 String[] ray = s.split(" ");
 Stack<String> ack = new Stack<String>();
 for(String r : ray){
  ack.add(r); 
 }
 sos.add(ack);
  
}
  
  
while(!sos.empty()){
  Stack<String> ack = sos.pop();
  println("popping all items from the stack");
  while(!ack.empty()){
    print(ack.pop() + " ");
  }
  println();
  println();
  
  
  
}
  
  
}