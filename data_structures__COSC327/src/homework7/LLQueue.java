package homework7;//TJ Liggett
//4_3_2019
//Homework 7
//LLQueue.java

/*

    Class LLQueue supports a Queue of objects.
    Documentation listed above each method

 */


import java.util.LinkedList;
import java.io.Serializable;

public class LLQueue<T> implements Cloneable, Serializable
{
    private LinkedList<T> arr= null;

    //Post: 	Queue initialized to empty.
    public LLQueue()
    {
        arr= new LinkedList<T>();
    }

    //Post: 	Queue cleared.
    public void clear()
    {
        arr.clear();
    }

    //Return: 	number of elements in the Queue
    public int size()
    {
        return arr.size();
    }

    //Return: 	true if empty and false if not empty
    public boolean isEmpty()
    {
        return arr.isEmpty();
    }

    //Post: 	item inserted at the back of the queue, if possible.
    //Return:	true if it was possible to add the element to this queue, else false
    public boolean offer(T item)
    {
        arr.addLast(item);
        return true;
    }

    //Post:	Head of this queue removed if nonempty queue.
    //Return: 	The head of this queue, or null if this queue is empty
    public T poll ()
    {
        if (isEmpty()) return null;
        return arr.removeFirst();
    }

    //Return: 	The head of this queue, or null if this queue is empty
    public T peek()
    {
        if (isEmpty())return null;
        return arr.getFirst();
    }

    //Return: 	A comma-separated list of elements enclosed in brackets.
    public String toString()
    {
        return arr.toString();
    }

    //Return:	A copy of this ALQueue.
    public Object clone()
    {
        LLQueue<T> copy = null;
        try
        {
            copy = (LLQueue<T>)super.clone();
        }
        catch (CloneNotSupportedException cnse)
        {
        }
        copy.arr= (LinkedList<T>)arr.clone();
        return copy;
    }
}

