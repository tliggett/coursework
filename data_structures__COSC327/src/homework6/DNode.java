package homework6;//3_27_2019
//Homework 6
//DNode.java

public class DNode<T>
{
    public T value;
    public DNode<T> prev;
    public DNode<T> next;
    public DNode()
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
