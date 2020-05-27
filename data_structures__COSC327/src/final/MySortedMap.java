

//Example 34-1-2
public interface MySortedMap<K,V> extends MyMap<K,V>
{
    //Return:	The first (minimum) key currently in this MySortedMap.
//Exeptn:Throws NoSuchElementException if MySortedMap is empty.
    K firstKey();

    //Return:	The last (maximum) key currently in this MySortedMap.
//Exeptn:Throws NoSuchElementException if MySortedMap is empty.
    K lastKey();
}
