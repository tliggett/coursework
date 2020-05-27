//Example 35-2-3 is Example 34-2-3
//Manipulate a MyHashMap via its Key view's Iterator
import java.util.Iterator;
public class Demo23
{
    public static void main(String[] args)
    {
        MyHashMap <String, Integer> m=new MyHashMap <String, Integer>();
        m.put("CS", 35); m.put("Chem", 25); m.put("Educ", 60); m.put("Math", 40);
        MySet<String> ks =m.keySet();
        Iterator<String> iter= ks.iterator();
        while (iter.hasNext())
        {
            String name=iter.next();
            if (name.startsWith("C")) iter.remove();
        }
        System.out.println(m.size());
        System.out.println(m);
        System.out.println(ks.size());
        System.out.println(ks);
    }
}
