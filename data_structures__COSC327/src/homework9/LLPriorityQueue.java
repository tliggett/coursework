//4_25_2019
//Homework 9
//LLPriorityQueue.java

/*
*
*       Documentation listed above each method
*
* */


import java.util.Comparator;
import java.util.LinkedList;
import java.util.ListIterator;
import java.io.Serializable;

public class LLPriorityQueue<T> implements Cloneable, Serializable
{
    private LinkedList <T> q= null;
    private Comparator<? super T> c= null;

    //Post: LLPriorityQueue initialized to empty.
    public LLPriorityQueue()
    {
        q= new LinkedList <T>();
    }

    //Post:	The LLPriorityQueue initialized to empty. Its elements are ordered according
    //      to the specified comparator c.
    public LLPriorityQueue(Comparator<? super T> c)
    {
        q = new LinkedList<T>();
        this.c = c;
    }

    //Post: LLPriorityQueue cleared.
    public void clear()
    {
        q.clear();
    }

    //Return: 	number of elements in the LLPriorityQueue
    public int size()
    {
        return q.size();
    }

    //Return: 	true if LLPriorityQueue is empty and false if not empty
    public boolean isEmpty()
    {
        return q.isEmpty();
    }

    //Post: 	item inserted at the back of the subqueue with the same priority as the element
    //          to be added.
    //Return:	true
    public boolean offer(T item)
    {
        ListIterator<T> iter = q.listIterator();
        while (iter.hasNext())
        {
            if(c == null)       /* For non-comparator constructor */
            {
                if(((Comparable)item).compareTo(iter.next())<0)	//line 55
                {
                    iter.previous();
                    break;
                }
            }
            else                /* For comparator constructor */
            {
                if(c.compare(item, iter.next())<0)
                {
                    iter.previous();
                    break;
                }
            }
        }

        iter.add(item);
        return true;
    }

    //Post:	  Head of this LLPriorityQueue removed if nonempty queue.
    //Return: The head of this LLPriorityQueue, or null if this LLPriorityQueue is empty
    public T poll ()
    {
        if (isEmpty()) return null;
        return q. removeFirst ();
    }

    //Return: The head of this LLPriorityQueue, or null if this LLPriorityQueue is empty
    public T peek()
    {
        if (isEmpty())return null;
        return q.getFirst ();
    }

    //Return: A comma-separated list of elements in this LLPriorityQueue enclosed in brackets.
    public String toString()
    {
        return q.toString();
    }

    //Return: A copy of this LLPriorityQueue.
    public Object clone()
    {
        LLPriorityQueue <T> copy = null;
        try
        {
            copy = (LLPriorityQueue <T>)super.clone();
        }
        catch (CloneNotSupportedException cnse)
        {
        }
        copy.q= (LinkedList<T>)q.clone();
        return copy;
    }
}

