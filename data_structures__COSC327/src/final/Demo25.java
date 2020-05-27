//Example 35-2-5 is Example 34-2-5
//Manipulate a MyHashMap via its Entry view's Iterator
import java.util.Iterator;
public class Demo25
{
    public static void main(String[] args)
    {
        MyHashMap <String, Integer> m=new MyHashMap <String, Integer>();
        m.put("CS", 35); m.put("Chem", 25); m.put("Educ", 60); m.put("Math", 40);
        MySet<MyMap.Entry<String, Integer>> es =m.entrySet();
        Iterator<MyMap.Entry<String, Integer>> iter= es.iterator();
        while (iter.hasNext())
        {
            MyMap.Entry<String, Integer> entry=iter.next();
            String dept= entry.getKey();
            if (dept.equals("CS"))
            {
                Integer value=entry.getValue();
                value+=2;
                entry.setValue(value);
            }
        }
        System.out.println(m.size());
        System.out.println(m);
        System.out.println(es.size());
        System.out.println(es);
    }
}
