package homework7;//TJ Liggett
//4_3_2019
//Homework 7
//DemoH.java

/*

    Class DemoH tests every method in LLQueue

 */



public class Demo
{
    public static void main(String[] args)
    {
        LLQueue<String> q = new LLQueue< String > ();
        q.offer("John");
        q.offer ("Paul");
        q.offer ("George");
        q.offer ("Ringo");
        System.out.println("There are " +  q.size() + " elements");
        System.out.println(q);
        LLQueue< String > copy = ( LLQueue< String > )q.clone();
        System.out.println("Copied Q. There are "+ copy.size()+" elements in Copy");
        while (!copy.isEmpty())
        {
            String name= copy.poll();
            System.out.println(name);
        }

        System.out.println("Top element of q is "+q.peek());
        q.clear();
        System.out.println("Cleared Q. Top element of q is "+q.peek());
    }
}

