//Example 35-2-2 is Example 34-2-2
//Manipulate a MyHashMap via its Key view.
public class Demo22
{
    public static void main(String[] args)
    {
        MyHashMap <String, Integer> m=new MyHashMap <String, Integer>();
        m.put("CS", 35); m.put("Chem", 25); m.put("Educ", 60); m.put("Math", 40);
        MySet<String> ks=m.keySet();
        System.out.println("map size: " + m.size());
        System.out.println("keySet size: "+ks.size());
        ks.remove("Math");
        System.out.println("map size: " + m.size());
        System.out.println("keySet size: "+ks.size());
        m.put("Biology", 70);
        System.out.println("map size: " + m.size());
        System.out.println("map: "+m);
        System.out.println("keySet size: "+ks.size());
        System.out.println("keySet: "+ks);
    }
}
