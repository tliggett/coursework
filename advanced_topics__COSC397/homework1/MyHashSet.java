//Example 35-3-1
import java.util.Iterator;
import java.util.NoSuchElementException;


public class MyHashSet<T> implements MySet<T>, Cloneable, Iterable<T>, java.io.Serializable
{
    private static final Object DUMMY = new Object();
    private MyHashMap<T, Object> map;
    
    /**
     * No argument constructor for class MyHashSet
     */
    public MyHashSet ()
    {
        map = new MyHashMap <T,Object>();
    }
    
    /**
     * A count of items in the MyHashSet
     * 
     * @return      the number of items in the MyHashSet
     */
    public int size()
    {
        return map.size();
    }

    /**
     * Adds an item to the MyHashSet
     * 
     * @param   item    the item to insert into the MyHashSet 
     * @return          the value replaced in the MyHashSet
     * 
     */
    public boolean add(T item)
    {
        return map.put(item, DUMMY) == null;
    }
    public void clear()
    {
        map.clear();
    }
    public boolean contains(Object obj)
    {
        return map.containsKey(obj);
    }
    public boolean isEmpty()
    {
        return map.isEmpty();
    }
    public Iterator<T> iterator()
    {
        return map.keySet().iterator();
    }
    public boolean remove(Object item)
    {
        return map.remove(item) == DUMMY;
    }
    public Object[] toArray()
    {
        return map.keySet().toArray();
    }
    public String toString()
    {
        return map.keySet().toString();
    }
    public Object clone()
    {
        MyHashSet<T> copy = null;
        try
        {
            copy = (MyHashSet<T>)super.clone();
        }
        catch (CloneNotSupportedException cnse)
        {
        }
        copy.map = (MyHashMap<T,Object>)map.clone();	//line 58
        return copy;
    }
}
