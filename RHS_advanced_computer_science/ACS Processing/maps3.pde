import java.util.*;

Map<Integer, TreeSet<Integer>> math;
int max;


void setup(){
  math = new TreeMap<Integer, TreeSet<Integer>>();
  max = 20;
  while(max > 0){
   TreeSet<Integer> temp = new TreeSet<Integer>();
   for(int i = max; i > 0; i--){
    if(max%i == 0)
    temp.add(i);
   }
   math.put(max, temp);
   println("loaded" + max);
   max--; 
  }
  
  Iterator<Map.Entry<Integer, TreeSet<Integer>>> it = math.entrySet().iterator();
  while(it.hasNext()){
   Map.Entry<Integer, TreeSet<Integer>> element = it.next();
   println("Number :: " + element.getKey());
   println("Factors :: " + element.getValue());
   println();
   println();
  }
  
  
}