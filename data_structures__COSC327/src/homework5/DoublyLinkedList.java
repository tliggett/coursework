package homework5;

import java.util.NoSuchElementException;
//TJ Liggett
//3_21_2019
//Homework 5
//DoublyLinkedList.java
/*
    Documentation listed above each method
*/

public class DoublyLinkedList<T>
{
    //Pre: 	header points to the header node of a circular doubly-linked list
    //Return: "null" if list is empty; "[data1, data2, …]" otherwise
    public static <T> String toString(DNode<T> header)
    {
        if (header.next==header) return "null";
        DNode<T> p=header.next;
        String s="["+p.value;
        while (p.next!=header)
        {
            p=p.next;
            s+=", "+p.value;
        }
        s+="]";
        return s;
    }

    //Pre: 	header points to the header node of a circular doubly-linked list
    //Return: null if key not in list; the node in list that matches key otherwise
    public static <T> DNode<T> seqSearch (DNode<T> header, T key)
    {
        DNode<T> p=header.next;
        while (p!=header)
        {
            if (p.value.equals(key)) return p;
            p=p.next;
        }
        return null;
    }

    //Post: 	item inserted after the node referenced by p
    //Return:The new node inserted
    public static <T> DNode<T> insertAfter(DNode<T> p, T item)
    {
        DNode<T> newNode=new DNode<T>(item);
        newNode.next = p.next;
        newNode.prev = p;
        p.next.prev = newNode;
        p.next = newNode;
        return newNode;
    }

    //Post: 	item inserted before the node referenced by p
    //Return:The new node inserted
    public static <T> DNode<T> insertBefore(DNode<T> p, T item)
    {
        DNode<T> newNode=new DNode<T>(item);
        newNode.next = p;
        newNode.prev = p.prev;
        p.prev.next = newNode;
        p.prev = newNode;
        return newNode;
    }

    //Pre: 	p points to a data node in this DoublyLinkedList
    //Post: 	node p removed from list
    public static <T> void remove(DNode<T> p)
    {
        p.next.prev = p.prev;
        p.prev.next = p.next;
    }

    //Pre: 	header points to the header node of a circular doubly-linked list
    //Return: The number of data nodes in the list
    public static <T> int size(DNode<T> header)
    {
        DNode<T> curr = header.next;
        int size = 0;
        while(curr != header)
        {
            size++;
            curr = curr.next;
        }
        return size;
    }
    //Throws:   NoSuchElementException if list has no elements
    //Post: 	First element removed from list.
    public static <T> void removeFirst(DNode <T> header)
    {
        if(size(header) == 0) throw new NoSuchElementException("List has no such element to remove");
        remove(header.next);
    }
    //Pre: 	list referenced by header cannot be empty
    //Post: 	Last element removed from list.
    public static <T> void removeLast (DNode <T> header)
    {
        if(size(header) == 0) throw new NoSuchElementException("List has no such element to remove");
        remove(header.prev);
    }
    //Post: 	item inserted as first element of list.
    public static <T> void insertFirst (DNode <T> header, T item)
    {
        insertAfter(header, item);
    }
    //Post: 	item inserted as last element of list.
    public static <T> void insertLast (DNode <T> header, T item)
    {
        insertBefore(header, item);
    }
}
