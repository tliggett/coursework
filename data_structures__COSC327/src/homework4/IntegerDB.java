package homework4;//Homework 4
//TJ Liggett
//3_8_2019
//IntegerDB.java
/*

    Class IntegerDB allows operations on a list of integers

*/

import java.util.Scanner;

public class IntegerDB {

    //Input: Various keys and commands of integer type by user
    //Output: Various user prompts, as well as the updated list after each command
    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in);
        Node<Integer> front = new Node<Integer>();
        int key = 0;
        while(key != 10)
        {
            prompt();
            key = input.nextInt();
            switch(key)
            {
                case 1:
                    front = buildList();
                    break;
                case 2:
                    System.out.print("Enter key: ");
                    key = input.nextInt();
                    front = SinglyLinkedList.insertFirst(front, key);
                    break;
                case 3:
                    System.out.print("Enter key: ");
                    key = input.nextInt();
                    front = SinglyLinkedList.insertLast(front, key);
                    break;
                case 4:
                    System.out.print("Enter key: ");
                    key = input.nextInt();
                    System.out.print("\nEnter item to be inserted after: ");
                    int item = input.nextInt();
                    Node<Integer> p = SinglyLinkedList.seqSearch(front, item);
                    if(p != null) SinglyLinkedList.insertAfter(p, key);
                    else System.out.println("Item is not in list!");
                    break;
                case 5:
                    if(front != null) front = SinglyLinkedList.removeFirst(front);
                    break;
                case 6:
                    if(front != null) front = SinglyLinkedList.removeLast(front);
                    break;
                case 7:

                    System.out.print("Enter item to remove: ");
                    key = input.nextInt();
                    if(front != null) front = SinglyLinkedList.remove(front, key);
                    break;
                case 8:
                    System.out.print("Enter key: ");
                    key = input.nextInt();
                    if(SinglyLinkedList.seqSearch(front, key) != null)
                        System.out.println("Item found in list");
                    else System.out.println("Item is not in list!");
                    break;
                case 9:
                    System.out.println(SinglyLinkedList.toString(front));
                    break;
            }
            System.out.println(SinglyLinkedList.toString(front));
        }
        input.close();


    }

    //Output: List of possible user commands
    public static void prompt()
    {
        System.out.println("\t1. Build list");
        System.out.println("\t2. Insert key as first");
        System.out.println("\t3. Insert key as last");
        System.out.println("\t4. Insert key after item");
        System.out.println("\t5. Remove first");
        System.out.println("\t6. Remove last");
        System.out.println("\t7. Remove item");
        System.out.println("\t8. Search key in list");
        System.out.println("\t9. Print list");
        System.out.println("\t10. Exit");
    }

    //Pre: 	A sequence of integers entered via keyboard; -1 is sentinel
    //Return:A linked list of the integers
    public static Node<Integer> buildList()
    {
        System.out.print("Enter a sequence of integers: -1 is sentinel: ");
        //read integers and call SinglyLinkedList.insertLast and return the list
        Scanner f = new Scanner(System.in);
        Node<Integer> front = new Node<Integer>();
        while (true)
        {
            int i = f.nextInt();
            if(i == -1) break;
            front = SinglyLinkedList.insertLast(front, i);
        }
        return front;
    }

}

