//Example 35-3-2 is Example 33-1-4
import java.util.Iterator;
public class Demo32
{
    public static void main(String[] args)
    {
        MyHashSet <Integer> t=new MyHashSet <Integer>();
        t.add(10); t.add(30); t.add(20); t.add(50); t.add(40);
        System.out.println("MyHashSet: "+t);
        //System.out.println("First element: "+t.first());
        //System.out.println("Last element: "+t.last());
        MyHashSet <Integer> t1= (MyHashSet <Integer>)t.clone();
        t.remove(30);
        System.out.println("MyHashSet: "+t);
        System.out.println("MyHashSet Copy: "+t1);
        Iterator<Integer> iter= t.iterator();
        while (iter.hasNext())
        {
            int n=iter.next();
            if (n==40) iter.remove();
        }
        System.out.println("MyHashSet: "+t);
        System.out.println("MyHashSet Copy: "+t1);
    }
}
