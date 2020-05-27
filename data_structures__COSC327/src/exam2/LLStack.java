//Exam 2
//4_1_2019
//exam2.LLStack.java

/*

Supports a stack of objects.
Documentation listed above each method.

*/

import java.util.EmptyStackException;
import java.util.LinkedList;
import java.io.Serializable;

public class LLStack<T> implements Cloneable, Serializable
{
    private LinkedList<T> arr= null;

    //Post: 	Stack initialized to empty.
    public LLStack()
    {
        arr= new LinkedList<T>();
    }

    //Post: 	Stack cleared.
    public void clear()
    {
        arr.clear();
    }

    //Return: 	number of elements on the stack
    public int size()
    {
        return arr.size();
    }

    //Return: 	true if empty and false if not empty
    public boolean isEmpty()
    {
        return arr.isEmpty();
    }

    //Post: 	item inserted at the top of the stack.
    //Return: 	item
    public T push(T item)
    {
        arr.addLast(item);
        return item;
    }

    //Post: 	the element at the top of the stack removed.
    //Return: 	value of the element removed from the top of the stack
    //Exceptn: throws EmptyStackException if the stack is empty
    public T pop()
    {
        if (isEmpty()) throw new EmptyStackException();
        return arr.removeLast();
    }

    //Return: 	value of the element at the top of the stack
    //Exceptn: throws EmptyStackException if the stack is empty
    public T peek()
    {
        if (isEmpty()) throw new EmptyStackException();
        return arr.getLast();
    }

    //Return: 	A comma-separated list of elements enclosed in brackets.
    public String toString()
    {
        return arr.toString();
    }

    //Return:	A shallow copy of this ALStack.
    public Object clone()
    {
        LLStack<T> copy = null;
        try
        {
            copy = (LLStack<T>)super.clone();
        }
        catch (CloneNotSupportedException cnse)
        {
        }
        copy.arr= (LinkedList<T>)arr.clone();
        return copy;
    }
}
