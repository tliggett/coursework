package homework12;

//Example 34-1-3
/*
public class MyTreeMap<K,V> implements MySortedMap<K,V>, java.io.Serializable
Supports a set of elements in the form of (key, value) pairs.

Usage:	public MyTreeMap();
Post: 	MyTreeMap initialized to empty.
*/
public class MyTreeMap<K,V> implements MySortedMap<K,V>, java.io.Serializable
{
    private class Entry<K,V> implements MyMap.Entry<K,V>
    {
        private K key;
        private V value;
        private Entry<K,V> left, right, parent;
        public Entry(K key, V value, Entry<K,V> parent)
        {
            this.key = key;
            this.value = value;
            this.parent = parent;
            left = null;
            right = null;
        }
        public K getKey()
        {
            return key;
        }
        public V getValue()
        {
            return value;
        }
        public V setValue(V value)
        {
            V oldValue = this.value;
            this.value = value;
            return oldValue;
        }
        public String toString()
        {
            return key + "=" + value;
        }
    }
    private Entry<K,V> root;
    private int mapSize;
    //Return:	A reference to the node containing key or null if the search fails
    private Entry<K,V> findNode(K key)
    {
        Entry<K,V> t = root;
        while(t != null)
        {
            int result = ((Comparable)key).compareTo(t.getKey());
            if (result == 0) return t;
            else if (result < 0) t = t.left;
            else t = t.right;
        }
        return null;
    }
    private void removeNode(Entry<K,V> D)
    {
        Entry<K,V> pOfD=D.parent, R, pOfR;
        if ((D.left == null) && (D.right == null))
            R = null;
        else if ((D.left == null) && (D.right != null))
        {
            R = D.right;
            R.parent = pOfD;
        }
        else if ((D.left != null) && (D.right == null))
        {
            R = D.left;
            R.parent = pOfD;
        }
        else if (D.right.left== null)
        {
            R = D.right;
            R.left = D.left;
            R.parent = pOfD;
            D.left.parent = R;
        }
        else
        {
            pOfR = D;
            R = D.right;
            while(R.left != null)
            {
                pOfR = R;
                R = R.left;
            }
            pOfR .left = R.right;
            if (R.right != null)
                R.right.parent = pOfR;
            R.left = D.left;
            R.right = D.right;
            R.parent = pOfD;
            R.left.parent = R;
            R.right.parent = R;
        }
        if (pOfD == null) root = R;
        else if (((Comparable)D.getKey()).compareTo(pOfD.getKey())<0)
            pOfD.left = R;
        else pOfD.right = R;
    }
    public MyTreeMap()
    {
        root = null;
        mapSize = 0;
    }
    public int size()
    {
        return mapSize;
    }
    public boolean isEmpty()
    {
        return mapSize == 0;
    }
    public void clear()
    {
        mapSize = 0;
        root = null;
    }
    public boolean containsKey (Object key)
    {
        Entry<K,V> t = findNode((K)key);
        return (t == null) ? false : true;
    }
    public V get(Object key)
    {
        Entry<K,V> t = findNode((K)key);
        if (t != null) return t.value;
        else return null;
    }
    public V put(K key, V value)
    {
        Entry<K,V> t = root, parent = null, newNode;
        int result = 0;
        while(t != null)
        {
            parent = t;
            result = ((Comparable<K>)key).compareTo(t.getKey());
            if (result == 0)
            {
                V temp=t.value;
                t.value=value;
                return temp;
            }
            else if (result < 0) t = t.left;
            else t = t.right;
        }
        newNode = new Entry<K,V>(key, value, parent);
        if (parent == null) root = newNode;
        else if (result < 0) parent.left = newNode;
        else parent.right = newNode;
        mapSize++;
        return null;
    }
    public V remove(Object key)
    {
        Entry<K,V> t  = findNode((K)key);
        if (t == null) return null;
        V temp = t.value;
        removeNode(t);
        mapSize --;
        return temp;
    }
    public K firstKey()
    {
        Entry<K,V> t = root;
        if (t == null) return null;
        while (t.left != null)
            t = t.left;
        return t.getKey();
    }
    public K lastKey()
    {
        Entry<K,V> t = root;
        if (t == null) return null;
        while (t.right != null)
            t = t.right;
        return t.getKey();
    }
}
