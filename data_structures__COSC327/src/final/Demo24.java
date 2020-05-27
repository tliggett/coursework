//Example 35-2-4 is Example 34-2-4
//Manipulate a MyHashMap via its Entry view.
public class Demo24
{
    public static void main(String[] args)
    {
        MyHashMap <String, Integer> m=new MyHashMap <String, Integer>();
        m.put("CS", 35); m.put("Chem", 25); m.put("Educ", 60); m.put("Math", 40);
        MySet <MyMap.Entry<String, Integer>> es=m.entrySet();
        System.out.println("map size: " + m.size());
        System.out.println("entrySet size: "+ es.size());
        es.remove("Math");					//line 14
        System.out.println("map size: " + m.size());
        System.out.println("entrySet size: "+ es.size());
        m.put("Biology", 70);
        System.out.println("map size: " + m.size());
        System.out.println("map: "+m);
        System.out.println("entrySet size: "+ es.size());
        System.out.println("entrySet: "+ es);
    }
}
