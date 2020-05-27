package homework4;//Homework 4
//TJ Liggett
//3_8_2019
//SinglyLinkedList.java
/*

    Documentation listed above each method

*/

public class Node<T>
{
    public T value;
    public Node<T> next;
    public Node()
    {
        value = null;
        next = null;
    }
    public Node(T item)
    {
        value = item;
        next = null;
    }
}

