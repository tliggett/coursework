package homework9;//TJ Liggett
//4_25_2019
//Homework 9
//BoundedQueue.java

/*
 *
 *       Documentation listed above each method
 *
 */

import java.io.Serializable;
public class BoundedQueue <T> implements Cloneable, Serializable
{
    private int capacity;
    private int back;
    private T[] arr;
    private int front;
    private int size;

    //Post: 	BoundedQueue initialized to capacity of p elements with size 0.
    public BoundedQueue (int capacity)
    {
        this.capacity = capacity;
        arr = (T[])new Object[capacity];
        back = 0;
        front = 1;
        size = 0;
    }

    //Return: 	number of elements in the BoundedQueue
    public int size()
    {
        return size;
    }

    //Post: 	BoundedQueue cleared.
    public void clear()
    {
        back = 0;
        front = 1;
        size = 0;
    }

    //Return: 	true if BoundedQueue is empty and false if not empty
    public boolean isEmpty()
    {
        return size <= 0;
    }

    //Return: 	true if BoundedQueue is full; false otherwise.
    public boolean full()
    {
        return size >= capacity;
    }

    //Post: 	item inserted at the back of this BoundedQueue, if possible.
    //Return:	true if it was possible to add the element to this BoundedQueue, else false
    public boolean offer(T item)
    {
        if(full()) return false;
        back = (back + 1) % capacity;
        arr[back] = item;
        ++size;
        return true;
    }

    //Post:	Head of this queue removed if nonempty queue.
    //Return: 	The head of this queue, or null if this BoundedQueue is empty
    public T poll ()
    {
        if (isEmpty()) return null;
        T item = arr[front];
        //arr[front] = null;
        front = (front + 1) % capacity;
        --size;
        return item;
    }

    //Return: 	The head of this queue, or null if this BoundedQueue is empty
    public T peek()
    {
        if(isEmpty()) return null;
        return arr[front];
    }

    //Return: 	A comma-separated list of elements enclosed in brackets.
    public String toString()
    {
        String s = new String();
        if (isEmpty()) return "[]";
        s+="[";
        for (int i=front; i != back; i = (i+1) % capacity)
            s+= arr[i] + ", ";
        s+= arr[back] + "]";
        return s;
    }

    //Return:	A copy of this BoundedQueue.
    public Object clone()
    {
        BoundedQueue <T>copy = null;
        try
        {
            copy = (BoundedQueue <T>)super.clone();
        }
        catch (CloneNotSupportedException cnse)
        {
        }
        copy.arr = this.arr.clone();
        return copy;
    }
}
