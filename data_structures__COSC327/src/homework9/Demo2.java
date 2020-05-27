package homework9;//Example 31-3-3

public class Demo2
{
    public static void main(String[] args)
    {
        BoundedQueue < String > q = new BoundedQueue < String > (3);
        q.offer("John");
        q.offer("Paul");
        q.offer("George");
        q.offer("Ringo");
        System.out.println("There are "+q.size()+" elements");
        System.out.println(q);
        while (!q.isEmpty())
        {
            String name= q.poll();
            System.out.println(name);
        }
    }
}


