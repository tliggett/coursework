package homework6;//Homework 6
//3_27_2019
//TJ Liggett
//MyLinkedList.java
/*

    Documentation listed above each method

*/

import java.util.*;
import java.io.*;
public class MyLinkedList <T> implements MyList<T>, Cloneable, Serializable
{
    protected int listSize;         /* The number of elements in list */
    protected DNode<T> header;      /* Dummy header node */

    //Throw: IndexOutOfBoundsException if index < 0 or index > upperBound
    private void rangeCheck(int index, int upperBound)
    {
        if (index < 0 || index > upperBound)
            throw new IndexOutOfBoundsException("Index " + index +
                    " out of bounds. Should be in range 0 to " + upperBound);
    }

    //Return:	The DNode at position index
    //Throw: IndexOutOfBoundsException if (0<= index < listSize) is not true
    protected DNode<T> nodeAtIndex(int index)
    {
        rangeCheck(index, listSize-1);
        DNode<T> p = header;
        for(int i = 0; i<=index; ++i) p = p.next;
        return p;
    }

    //Post: DNode referenced by curr removed
    //	    listSize decremented by 1
    protected void remove(DNode<T> curr)
    {
        DNode<T> prevNode = curr.prev, succNode = curr.next;
        prevNode.next = succNode;
        succNode.prev = prevNode;
        listSize --;
    }

    //Post:     item added to list before DNode referenced by curr
    //	        listSize incremented by 1
    //Return:	The DNode inserted
    protected DNode<T> addBefore(DNode<T> curr, T item)
    {
        DNode<T> prevNode = curr.prev, newNode = new DNode<T>(item);
        newNode.next = curr;
        newNode.prev = prevNode;
        prevNode.next = newNode;
        curr.prev = newNode;
        listSize++;
        return newNode;
    }

    //Post: listSize initialized to zero, header initialized to new DNode<T>.
    public MyLinkedList()
    {
        this.listSize = 0;
        this.header = new DNode<T>();
    }

    //Return: The number of elements in this list
    public int size()
    {
        return listSize;
    }

    //Return: True if list contains no elements; false otherwise
    public boolean isEmpty()
    {
        return listSize == 0;
    }

    //Post: All elements removed from list. listSize set to 0
    public void clear()
    {
        listSize = 0;
        header.prev = header.next = header;
    }

    //Post:     item added to end of the list.
    //Return:   True if successful; false otherwise.
    public boolean add(T item)
    {
        addBefore(header, item);
        return true;
    }

    //Post:     a single instance of item removed from list if it exists.
    //Return:   True if item is removed; false otherwise.
    public boolean remove(Object item)
    {
        boolean retValue = false;
        DNode<T> curr;
        for(curr = header.next; curr != header; curr = curr.next)
            if(item.equals(curr.value)) break;
        if(curr != header)
        {
            remove(curr);
            retValue = true;
        }
        return retValue;
    }

    //Return: An array which contains all the elements in this list.
    public Object[] toArray()
    {
        Object[] arr = new Object[listSize];
        DNode<T> curr = header.next;
        for(int i = 0; i<listSize; ++i)
        {
            arr[i] = curr.value;
            curr = curr.next;
        }
        return arr;
    }

    //Pre:      0 <= index <= size
    //Post:     item added to list at index; elements shifted down to make room; size incremented.
    //Throws:   if pre is not met, throws IndexOutOfBoundsException
    public void add(int index, T item)
    {
        rangeCheck(index, listSize);
        DNode<T> p = null;
        if(index == listSize) p = header;
        else p = nodeAtIndex(index);
        addBefore(p, item);
    }

    //Return: The index of first occurrence of item in list if item exists; -1 if item DNE
    public int indexOf(Object item)
    {
        int index = 0;
        for(DNode<T> curr = header.next; curr != header; curr = curr.next)
        {
            if(item.equals(curr.value)) return index;
            else index++;
        }
        return -1;
    }

    //Return: True if list contains specified item; false otherwise.
    public boolean contains(Object item)
    {
        return indexOf(item) >= 0;
    }

    //Pre: 	    0 <= index < size()
    //Return: 	The item at index of list.
    //Throws: 	if pre is not met, throws IndexOutOfBoundsException.
    public T get(int index)
    {
        rangeCheck(index, listSize - 1);
        return nodeAtIndex(index).value;
    }

    //Pre: 	    0 <= index < size()
    //Post: 	Replace element at index with item
    //Return: 	The replaced element.
    //Throws: 	if pre is not met, throws IndexOutOfBoundsException.
    public T set(int index, T item)
    {
        rangeCheck(index, listSize-1);
        DNode<T> p = nodeAtIndex(index);
        T oldValue = p.value;
        p.value = item;
        return oldValue;
    }

    //Post:	    First element of this list removed
    //Return:	Element removed
    //Throw:	NoSuchElementException if this list is empty.
    public T removeFirst()
    {
        if (listSize == 0)
            throw new NoSuchElementException("MyLinkedList removeFirst(): list empty");
        T result = header.next.value;
        remove(header.next);
        return result;
    }

    //Post:	    Last element of this list removed
    //Return:	Element removed
    //Throw:	NoSuchElementException if this list is empty.
    public T removeLast()
    {
        if (listSize == 0)
            throw new NoSuchElementException("MyLinkedList removeLast(): list empty");
        T result = header.prev.value;
        remove(header.prev);
        return result;
    }

    //Return:	The last element in this list.
    //Throw:	NoSuchElementException if this list is empty.
    public T getLast()
    {
        if (listSize == 0) throw new NoSuchElementException();
        return header.prev.value;
    }

    //Return: A string listing the elements in the MyLinkedList separated by commas enclosed by [].
    public String toString()
    {
        Object[] arr = toArray();
        if (arr.length == 0) return "[]";
        String str = "[" + arr[0];
        for (int i = 1; i < arr.length; i++)
            str +=  ", " + arr[i];
        str += "]";
        return str;
    }

    //Return: A clone of this MyLinkedList
    public Object clone()
    {
        MyLinkedList<T> copy = null;
        try
        {
            copy = (MyLinkedList<T>)super.clone();
        }
        catch (CloneNotSupportedException cnse)
        {
        }
        copy.listSize = 0;
        copy.header = new DNode<T>();
        DNode<T> curr = header.next;
        while(curr != header)
        {
            copy.add(curr.value);
            curr = curr.next;
        }
        return copy;
    }


    //My defined methods ------------------------------------------------------------------


    //Pre:    0 <= index < listSize
    //Post:   item at index removed from list, elements shifted up to fill vacant position
    //Return: The item removed.
    //Throw:  if pre is not met, throws IndexOutOfBoundsException
    public T remove(int index)
    {
        rangeCheck(index, listSize-1);
        T retValue = get(index);
        remove(nodeAtIndex(index));
        return retValue;
    }

    //Post: item inserted at the beginning of this list.
    public void addFirst(T item)
    {
        addBefore(header.next, item);
    }

    //Post: item inserted at the end of this list.
    public void addLast(T item)
    {
        addBefore(header, item);
    }

    //Return: The first element in this list.
    //Throw:  NoSuchElementException if this list is empty.
    public T getFirst()
    {
        if(listSize == 0) throw new NoSuchElementException("No such element in list to retrieve. ");
        return header.next.value;
    }

}
