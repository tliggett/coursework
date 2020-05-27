package homework5;//TJ Liggett
//3_21_2019
//Homework 5
//DNode.java


public class DNode<T>
{
    public T value;
    public DNode<T> prev;
    public DNode<T> next;
    public  DNode()
    {
        value = null;
        prev = this;
        next = this;
    }
    public DNode(T item)
    {
        value = item;
        prev = this;
        next = this;
    }
}
