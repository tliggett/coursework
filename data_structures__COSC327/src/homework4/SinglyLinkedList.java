package homework4;//Homework 4
//TJ Liggett
//3_8_2019
//SinglyLinkedList.java
/*

    Documentation listed above each method

*/


public class SinglyLinkedList<T>
{

    // Pre: front points to the first node in a linked list, whose last node must have a null reference
    //Return:"null" if front is null; all the values in the list in the form "[value1, value2, …]"
    //        otherwise
    public static <T> String toString(Node<T> front)
    {
        if (front == null) return "null";
        Node<T> frontCopy = front;
        String result = "[" + front.value;
        while (frontCopy.next != null)
        {
            frontCopy = frontCopy.next;
            result += ", " + frontCopy.value;
        }
        result += "]";
        return result;
    }

    // Pre: front points to the first node in a linked list, whose last node must have a null reference
    // Return: null if key not in list; a reference to the node in list that matches key otherwise
    public static <T> Node<T> seqSearch (Node<T> front, T key)
    {
        Node<T> node  = front;
        while (node != null)
        {
            if (node.value.equals(key)) return node;
            node = node.next;
        }
        return null;
    }

    //Pre:	p cannot be null
    //Post: 	item inserted after the node referenced by p
    //Return:The new node inserted
    public static <T> Node<T> insertAfter(Node<T> p, T item)
    {
        Node<T> newNode = new Node<T>(item);
        newNode.next = p.next;
        p.next = newNode;
        return newNode;
    }


    //Post: 	item removed from list if exists.
    //Return:	Since the first element can be the element removed, front might change.  Thus the
    //	        method returns front.  The caller must assign the return value to the list pointer.
    public static <T> Node<T> remove(Node<T> front, T item)
    {
        Node<T> curr = front, prev = null;
        while (curr != null)
        {
            if (curr.value.equals(item))
            {
                if (prev == null) front = front.next;
                else prev.next = curr.next;
                break;
            }
            else
            {
                prev = curr;
                curr = curr.next;
            }
        }
        return front;
    }

    //Desc: 	Find the mode with max value
    //Pre:	front cannot be null
    //Return:	The node with max value
    public static <T extends Comparable<? super T>> Node<T> getMaxNode(Node<T> front)
    {
        Node<T> maxNode = front, curr = front.next;
        while (curr != null)
        {
            if (maxNode.value.compareTo(curr.value) < 0) maxNode = curr;
            curr = curr.next;
        }
        return maxNode;
    }

    //Pre: 	front cannot be null
    //Post: 	First element removed from list.
    //Return:The updated front reference
    public static <T> Node<T> removeFirst(Node<T> front)
    {
        front = front.next;
        return front;
    }

    //Pre: 	front cannot be null
    //Post: 	Last element removed from list.
    //Return:Since the first element can be the element removed, front might change.  Thus the
    //	method returns front
    public static <T> Node<T> removeLast(Node<T> front)
    {
        if(front.next == null) return front;
        Node<T> p = front;
        while(p.next.next != null) p = p.next;
        p.next = null;
        return front;
    }

    //Post: 	item inserted as first element of list.
    //Return:The updated front reference
    public static <T> Node<T> insertFirst(Node<T> front, T item)
    {
        Node<T> p = front;		// Saving the old front
        front = new Node<T>(item);	// Making new node with item
        front.next = p;			//    N->O->O->O
        return front;
    }

    //Post: 	item inserted as last element of list.
    //Return:Since item can be inserted as the first element, front might change.  Thus the
    //	method returns front
    public static <T> Node<T> insertLast(Node<T> front, T item)
    {
        if(front.value == null) front.value = item;
        else {
            Node<T> p = front;
            while (p.next != null) p = p.next;
            p.next = new Node<T>(item);
        }
        return front;
    }
}

