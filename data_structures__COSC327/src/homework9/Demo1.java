package homework9;//Example 31-3-2
import java.io.*;
public class Demo1
{
    public static void main(String[] args) throws IOException, ClassNotFoundException
    {
        BoundedQueue < String > q = new BoundedQueue < String > (10);

        q.offer("John");
        q.offer("Paul");
        q.offer("George");
        q.offer("Ringo");
        System.out.println("There are "+q.size()+" elements");
        System.out.println(q);

        q.clear();
        System.out.println("There are "+q.size()+" elements");
        System.out.println(q);


        q.offer("John");
        q.offer("Paul");
        q.offer("George");
        q.offer("Ringo");
        System.out.println("There are "+q.size()+" elements");
        System.out.println(q);

        BoundedQueue < String > copy = (BoundedQueue < String > )q.clone();
        System.out.println("There are "+ copy.size()+" elements");
        while (!copy.isEmpty())
        {
            String name= copy.poll();
            System.out.println(name);
        }
        ObjectOutputStream out=new ObjectOutputStream(new FileOutputStream("homework9/data.bin"));
        out.writeObject(q);
        out.close();
        ObjectInputStream in=new ObjectInputStream (new FileInputStream("homework9/data.bin"));
        BoundedQueue<String> q1=(BoundedQueue<String>)(in.readObject());
        in.close();
        System.out.println(q1);
    }
}
