//TJ Liggett
//5_19_2019
//FINAL EXAM
//MyHashMap.java

/*
 *
 *       Class MyHashMap stores map entries in a hash table of singly linked lists
 *
 */


import java.util.Iterator;
import java.util.NoSuchElementException;
public class MyHashMap<K,V> implements MyMap<K,V>, java.io.Serializable, Cloneable
{
    private class Entry<K,V> implements MyMap.Entry<K,V>
    {
        private K key;
        private V value;
        private int hashValue;
        private Entry<K,V> next;
        /**
         * Class constructor for Entry object
         * 
         * @param   key         the key for the Entry object
         * @param   value       the value for the Entry object
         * @param   hashValue   a hash value for storing the Entry
         * @param   next        link to another Entry object
         * 
         */
        public Entry(K key, V value, int hashValue, Entry<K,V> next)
        {
            this.key = key;
            this.value = value;
            this.hashValue = hashValue;
            this.next = next;
        }
        /**
         * getter for key of entry
         * @return    the key of the entry object
         */
        public K getKey()
        {
            return key;
        }
        /**
         * getter for value of entry
         * @return    the value of the entry object
         */
        public V getValue()
        {
            return value;
        }
        /**
         * Sets the value of the entry object
         * 
         * @param   value    the new value for the entry object  
         * @return           the old value for the entry object
         * 
         */
        public V setValue(V value)
        {
            V oldValue = this.value;
            this.value = value;
            return oldValue;
        }
        /**
         * Returns a string format of the entry
         * key=value
         * 
         * @return          'key=value' string
         * 
         */
        public String toString()
        {
            return key + "=" + value;
        }
    }
    private Entry<K,V>[] table;
    private int hashMapSize;
    private static final double MAX_LOAD_FACTOR = .75;
    private int tableThreshold;
    private MySet<K> ks = null;
    private MySet<MyMap.Entry<K,V>> es = null;
    /**
     * 
     */
    private void rehash(int newTableSize)
    {
        Entry<K,V>[] newTable = new Entry[newTableSize];
        Entry<K,V> entry, nextEntry;
        int index;
        for (int i=0; i < table.length; i++)
        {
            entry = table[i];
            if (entry != null)
            {
                table[i] = null;
                do
                {
                    nextEntry = entry.next;
                    index = entry.hashValue % newTableSize;
                    entry.next = newTable[index];
                    newTable[index] = entry;
                    entry = nextEntry;
                } while (entry != null);
            }
        }
        table = newTable;
        tableThreshold = (int)(table.length * MAX_LOAD_FACTOR);
    }
    /**
     * 
     */
    private Entry<K,V> findNode(Object key)
    {
        int index = (key.hashCode() & Integer.MAX_VALUE) % table.length;
        Entry<K,V> entry = table[index];
        while (entry != null)
        {
            if (entry.key.equals(key)) return entry;
            entry = entry.next;
        }
        return null;
    }
    public MyHashMap()
    {
        table = new Entry[17];
        hashMapSize = 0;
        tableThreshold = (int)(table.length * MAX_LOAD_FACTOR);
    }
    public int size()
    {
        return hashMapSize;
    }
    public void clear()
    {
        for (int i=0;i < table.length;i++)
            table[i] = null;
        hashMapSize = 0;
    }
    public boolean isEmpty()
    {
        return hashMapSize == 0;
    }
    public boolean containsKey(Object key)
    {
        return findNode(key) != null;
    }
    public V get(Object key)
    {
        Entry<K,V> p = findNode(key);
        if (p == null) return null;
        else return p.value;
    }
    /**
     * Places a key-value pair into the MyHashMap
     * 
     * @param   key     the key to insert into the MyHashMap
     * @param   value   the value to insert into the MyHashMap
     * @return          the value replaced at that key, null if none
     * 
     */
    public V put(K key, V value)
    {
        int hashValue = key.hashCode() & Integer.MAX_VALUE;
        int index = hashValue % table.length;
        Entry<K,V> entry = table[index];
        while (entry != null)
        {
            if (entry.key.equals(key))
            {
                V temp=entry.value;
                entry.value=value;
                return temp;
            }
            entry = entry.next;
        }
        entry = new Entry<K,V>(key, value, hashValue, table[index]);
        table[index] = entry;
        hashMapSize++;
        if (hashMapSize >= tableThreshold) rehash(2*table.length + 1);
        return null;
    }
    /**
     * Removes an item from the MyHashMap
     * @param   key     the key for the item to remove
     * @return          the value that has been removed, null if none
     * 
     */
    public V remove(Object key)
    {
        int index = (key.hashCode() & Integer.MAX_VALUE) % table.length;
        Entry<K,V> curr = table[index];
        Entry<K,V> prev = null;
        while (curr != null)
            if (curr.key.equals(key))
            {
                if (prev != null) prev.next = curr.next;
                else table[index] = curr.next;
                hashMapSize--;
                return curr.value;
            }
            else
            {
                prev = curr;
                curr = curr.next;
            }
        return null;
    }
    
    /**
     * Returns a string format of all the entries in the MyHashMap
     * [key=value, key=value, key=value,...]
     * 
     * @return      a string representation of the MyHashMap
     * 
     */
    public String toString()
    {
        String s = "[";
        Iterator<MyMap.Entry<K,V>> iter = entrySet().iterator();
        while (iter.hasNext())
        {
            s += iter.next();
            if (iter.hasNext())	s += ", ";
        }
        s += "]";
        return s;
    }
    public MySet<K> keySet()
    {
        if (ks == null)
        {
            ks = new MySet<K>()
            {
                public Iterator<K> iterator()
                {
                    return new KeyIterator();
                }
                public int size()
                {
                    return MyHashMap.this.size();
                }
                public boolean isEmpty()
                {
                    return MyHashMap.this.size() == 0;
                }
                public boolean contains(Object item)
                {
                    return containsKey(item);
                }
                public boolean remove(Object item)
                {
                    int oldSize = size();
                    MyHashMap.this.remove(item);
                    return size() != oldSize;
                }
                public void clear()
                {
                    MyHashMap.this.clear();
                }
                public boolean add(K item)
                {
                    throw new UnsupportedOperationException();
                }
                public Object[] toArray()
                {
                    Object[] arr = new Object[size()];
                    Iterator<K> iter = iterator();
                    for (int i=0;i < arr.length;i++)
                        arr[i] = iter.next();
                    return arr;
                }
                public String toString()
                {
                    String s = "[";
                    Iterator<K> iter = iterator();
                    while (iter.hasNext())
                    {
                        s += iter.next();
                        if (iter.hasNext())	s += ", ";
                    }
                    s += "]";
                    return s;
                }
            };
        }
        return ks;
    }
    public MySet<MyMap.Entry<K,V>> entrySet()
    {
        if (es == null)
        {
            es = new MySet<MyMap.Entry<K,V>>()
            {
                public Iterator<MyMap.Entry<K,V>> iterator()
                {
                    return new ElementIterator();
                }
                public int size()
                {
                    return MyHashMap.this.size();
                }
                public boolean isEmpty()
                {
                    return size() == 0;
                }
                public boolean contains(Object item)
                {
                    if (!(item instanceof MyMap.Entry)) return false;
                    MyMap.Entry<K,V> entry=
                            (MyMap.Entry<K,V>)item;
                    V value = entry.getValue();
                    MyMap.Entry<K,V> p = findNode(entry.getKey());
                    return p!= null && p.getValue().equals(value);
                }
                public boolean remove(Object item)
                {
                    if (!(item instanceof MyMap.Entry)) return false;
                    MyMap.Entry<K,V> entry=
                            (MyMap.Entry<K,V>)item;
                    K key = entry.getKey();
                    return MyHashMap.this.remove(key) != null;
                }
                public void clear()
                {
                    MyHashMap.this.clear();
                }
                public boolean add(MyMap.Entry<K,V> item)
                {
                    throw new UnsupportedOperationException();
                }
                public Object[] toArray()
                {
                    Object[] arr = new Object[size()];
                    Iterator<MyMap.Entry<K,V>> iter = iterator();
                    for (int i=0;i < arr.length;i++)
                        arr[i] = iter.next();
                    return arr;
                }
                public String toString()
                {
                    return MyHashMap.this.toString();
                }
            };
        }
        return es;
    }
    private class IteratorImpl<T> implements Iterator<T>
    {
        private Entry<K,V> nextNode;
        private Entry<K,V> lastReturned;
        private int index;
        public IteratorImpl()
        {
            if (hashMapSize == 0)
            {
                nextNode = null;
                index = table.length;
            }
            else
            {
                index = 0;
                while ((nextNode = table[index]) == null)
                    index ++;
            }
            lastReturned = null;
        }
        public boolean hasNext()
        {
            return nextNode!= null;
        }
        public T next()	//to be overridden; T is either K or Entry<K,V>
        {
            return null;
        }
        protected Entry<K,V> nextEntry()
        {
            if (nextNode == null) throw new NoSuchElementException();
            lastReturned = nextNode;
            Entry<K,V> n = nextNode.next;
            if (n == null)
            {
                index++;
                while (index < table.length && (n = table[index]) == null)
                    index++;
            }
            nextNode = n;
            return lastReturned;
        }
        public void remove()
        {
            if (lastReturned == null) throw new IllegalStateException(
                    "Iterator call to next() " + "required before calling remove()");
            MyHashMap.this.remove(lastReturned.key);
            lastReturned = null;
        }
    }
    private class KeyIterator extends IteratorImpl<K>
    {
        public K next()
        {
            return nextEntry().key;
        }
    }
    private class ElementIterator extends IteratorImpl<MyMap.Entry<K,V>>
    {
        public MyMap.Entry<K,V> next()
        {
            return nextEntry();
        }
    }


    /**
     * Creates a clone of class MyHashMap
     * 
     * @return  a clone of this MyHashMap
     * 
     */
    public Object clone()
    {
        MyHashMap<K,V> copy = null;
        try
        {
            copy = (MyHashMap<K,V>)super.clone();
        }
        catch (CloneNotSupportedException cnse)
        {
        }
        copy.hashMapSize = 0;
        copy.tableThreshold = this.tableThreshold;
        copy.es = null;
        copy.ks = null;
        copy.table = new Entry[this.table.length];
        Iterator<MyMap.Entry<K,V>> iter = this.entrySet().iterator();
        while(iter.hasNext())
        {
            MyMap.Entry<K,V> entry = iter.next();
            copy.put(entry.getKey(), entry.getValue());
        }
        return copy;
    }

}
