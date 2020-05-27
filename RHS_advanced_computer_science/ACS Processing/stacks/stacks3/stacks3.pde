
import java.util.*;

void setup(){
String[] file = loadStrings("stack3.txt");
for(String $ : file){
 Stack<Float> stack = new Stack<Float>();
 print($ + " = "); 
 String[] postfix = $.split(" ");
 for(String s : postfix){
  try{
  float num = Integer.parseInt(s);
  stack.push(num);
  // is an integer!
} catch (NumberFormatException e) {
  // not an integer!
  float two = stack.pop();
  float one = stack.pop();
  float push = 0;
  switch(s){
   case "+": push = one + two;
   break;
   case "-": push = one - two;
   break;
   case "/": push = one/two;
   break;
   case "*": push = one*two;
   break;
  }
  stack.push(push);
}
 }
  print(stack.pop());
  println();
}

}