import homework12.BSTree;
import homework12.MySortedSet;

import java.io.Serializable;
import java.util.Iterator;

public class MyTreeSet<T> implements MySortedSet<T>, Cloneable, Iterable<T>,Serializable
{
    private BSTree<T> t;
    public MyTreeSet()
    {
        t=new BSTree<T>();
    }
    public int size()
    {
        return t.size();
    }
    public boolean isEmpty()
    {
        return t.isEmpty();
    }
    public void clear()
    {
        t.clear();
    }
    public boolean add(T item)
    {
        return t.add(item);
    }
    public boolean contains(Object item)
    {
        return t.contains(item);
    }
    public boolean remove(Object item)
    {
        return t.remove(item);
    }
    public Object clone()
    {
        MyTreeSet<T> copy = new MyTreeSet<T>();
        copy.t=(BSTree<T>)(t.clone());
        return copy;
    }
    public Iterator<T> iterator()
    {
        return t.iterator();
    }
    public Object[] toArray()
    {
        return t.toArray();
    }
    public String toString()
    {
        return t.toString();
    }
    public T first()
    {
        Iterator<T> iter=t.iterator();
        return iter.next();
    }
    public T last()
    {
        Iterator<T> iter=t.iterator();
        T item=null;
        while (iter.hasNext())
            item=iter.next();
        return item;
    }
}
