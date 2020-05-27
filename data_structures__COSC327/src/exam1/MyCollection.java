package exam1;

//MyCollection.java
public interface MyCollection<T>
{
    //Return: 	The number of elements in this collection is returned.
    int size();

    //Return: 	True if this collection contains no elements; false otherwise.
    boolean isEmpty();

    //Post: 	All elements from this collection removed.
    void clear();

    //Return: 	True if this collection contains the specified item; false otherwise.
    boolean contains(Object item);

    //Post: 	item added to this collection if item does not exist or if it exists but the collection
    // 	permits duplicates
    //Return: 	True if the operation inserts a new element in this collection; false if this collection
    //	already contains the specified element and does not permit duplicates.
    boolean add(T item);

    //Post: 	A single instance of item removed from this collection if item exists
    //Return: 	True if item removed; false otherwise
    boolean remove(Object item);

    //Return: 	An array which contains all elements in this collection (references only).
    Object[] toArray();
}

