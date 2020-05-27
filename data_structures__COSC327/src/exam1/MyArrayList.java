package exam1;//TJ Liggett
//Exam 1
//2_25_2019
//MyArrayList.java
/*

    Documentation listed above each method

*/

import java.lang.IndexOutOfBoundsException;

public class MyArrayList<T> implements MyList<T>
{
    private int listSize;
    private T[] listArr;

    //Excptn: throws IndexOutOfBoundsException if index < 0 or index > upperBound
    private void rangeCheck(int index, int upperBound)
    {
        if (index < 0 || index > upperBound)
            throw new IndexOutOfBoundsException("Index " + index +
                    " out of bounds. Should be in range 0 to " + upperBound);
    }

    //Desc: Ensures listArr has capacity for n elements
    //Post: If n > listArr.length, listArr is doubled in size while retaining its elements.
    private void ensureCapacity(int n)
    {
        if (n > listArr.length)
        {
            T[] oldListArr = listArr;
            listArr = (T[]) new Object[oldListArr.length*2];
            for (int i=0; i < listSize; i++)
                listArr[i] = oldListArr[i];
        }
    }

    //Post: 	The MyArrayList initialized to empty
    public MyArrayList()
    {
        listArr = (T[])new Object[10];
        listSize = 0;
    }

    //Return: 	The number of elements in this collection is returned.
    public int size()
    {
        return listSize;
    }

    //Return: 	True if this collection contains no elements; false otherwise.
    public boolean isEmpty()
    {
        return listSize == 0;
    }

    //Post: 	All elements from this collection removed.
    public void clear()
    {
        listSize = 0;
    }

    //Return: 	The index of first occurrence of item in List if item exists; -1 if item does not exist
    public int indexOf(Object item)
    {
        for (int i=0;i < listSize; i++)
            if (item.equals(listArr[i])) return i;
        return -1;
    }

    //Return: 	True if this collection contains the specified item; false otherwise.
    public boolean contains(Object item)
    {
        return indexOf(item) >= 0;
    }

    //Pre: 	0<= index <size()
    //Post: 	item at index removed from List; elements shifted up to fill vacant position; size
    //	decremented
    //Return: 	The item removed
    //Excptn: 	if pre is not met, throws IndexOutOfBoundsException
    public T remove(int index)
    {
        rangeCheck(index, listSize-1);
        T temp = listArr[index];
        for (int j=index; j< listSize-1; j++)
            listArr[j] = listArr[j+1];
        listSize--;
        return temp;
    }

    //Post: 	A single instance of item removed from this collection if item exists
    //Return: 	True if item removed; false otherwise
    public boolean remove(Object item)
    {
        int index = indexOf(item);
        if(index != -1)
        {
            remove(index);
            listSize--;
            return true;
        }
        else return false;
    }

    //Return: 	An array which contains all elements in this collection (references only).
    public Object[] toArray()
    {
        Object[] arr = new Object[listSize];
        for(int i = 0; i < listSize; ++i) arr[i] = listArr[i];
        return arr;
    }

    //Pre: 	0<= index <=size()
    //Post: 	item added to List at index; elements shifted down to make room; size incremented
    //Excptn: 	if pre is not met, throws IndexOutOfBoundsException
    public void add(int index, T item)
    {
        rangeCheck(index, listSize);
        ensureCapacity(listSize+1);
        for(int i = listSize; i > index; --i)
            listArr[i] = listArr[i-1];
        listArr[index] = item;
        listSize++;
    }

    //Post: 	item added to this collection if item does not exist or if it exists but the collection
    // 	permits duplicates
    //Return: 	True if the operation inserts a new element in this collection; false if this collection
    //	already contains the specified element and does not permit duplicates.
    public boolean add(T item)
    {
        ensureCapacity(listSize+1);
        listArr[listSize] =  item;
        listSize++;
        return true;
    }

    //Pre: 	0<= index <size()
    //Return: 	The item at index of List
    //Excptn: 	if pre is not met, throws IndexOutOfBoundsException
    public T get(int index)
    {
        rangeCheck(index, listSize - 1);
        return listArr[index];
    }

    //Pre: 	0<= index <size()
    //Post: 	element at index replaced by item
    //Return: 	The element replaced
    //Excptn: 	if pre is not met, throws IndexOutOfBoundsException
    public T set(int index, T item)
    {
        rangeCheck(index, listSize - 1);
        T temp = listArr[index];
        listArr[index] = item;
        return temp;
    }

    //Return: 	A string listing the elements in the MyArrayList's separated by commas enclosed by [].
    public String toString()
    {
        Object[] arr = toArray();
        if (arr.length == 0) return "[]";
        String str = "[" + arr[0];			//line 8
        for (int i = 1; i < arr.length; i++)
            str +=  ", " + arr[i];		//line 10
        str += "]";
        return str;
    }
}
