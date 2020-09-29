//TJ Liggett
//9_4_2020
//Homework1
//Demo.java

/*
 *
 *       Class Demo tests the cloneability of class HashMap
 *
 */


import java.util.Iterator;

public class Demo {

    public static void main(String[] args)
    {
        MyHashMap<String, Integer> m = new MyHashMap<String, Integer>();
        m.put("TJ", 43);
        m.put("JR", 24);
        m.put("Al", 42);
        m.put("Clev", 87);
        System.out.println("Original Hash Map: ");
        printHashMapDetails(m);

        MyHashMap<String, Integer> copy = (MyHashMap<String, Integer>) m.clone();
        System.out.println("Clone Hash Map: ");
        printHashMapDetails(copy);


        m.remove("JR");        
        m.put("Hoops", 41);
        copy.remove("Clev");
        copy.put("Joe", 46);
        copy.put("Kyle", 47);

        System.out.println("Original Hash Map: ");
        printHashMapDetails(m);
        System.out.println("Clone Hash Map: ");
        printHashMapDetails(copy);



        Iterator<String> iter = copy.keySet().iterator();
        while(iter.hasNext())
        {
            iter.next();
            iter.remove();
        }

        System.out.println("Original Hash Map: ");
        printHashMapDetails(m);
        System.out.println("Clone Hash Map: ");
        printHashMapDetails(copy);
    }

    //Output: Basic information about MyHashMap including toString, size, keySet, entrySet
    public static void printHashMapDetails(MyHashMap m)
    {
        System.out.println();
        System.out.println(m);
        System.out.printf("hashMapSize: %d\n", m.size());
        System.out.printf("Key Set: %s\n", m.keySet());
        System.out.printf("Entry Set: %s\n", m.entrySet());
        System.out.println();
    }

}
