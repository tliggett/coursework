package chapter32;//Example 32-3-3
import java.io.*;
public class Demo33
{
    public static void main(String[] args) throws IOException, ClassNotFoundException
    {
        BSTree<Integer> t=new BSTree<Integer>();
        t.add(10); t.add(30); t.add(20); t.add(50); t.add(40);
        System.out.println("Tree size: "+t.size());
        if (t.contains(new Integer(44))) System.out.println("44 is in");
        else System.out.println("44 is not in");
        BSTree<Integer> copy= (BSTree<Integer>)(t.clone());
        copy.add(60);
        System.out.print("Copy in inorder: ");
        copy.inorderOutput();
        System.out.println();
        System.out.print("Original tree in inorder: ");
        t.inorderOutput();
        System.out.println();
        ObjectOutputStream f =new ObjectOutputStream(new FileOutputStream("exam3/object.dat"));
        f.writeObject(t);
        f.close();
        ObjectInputStream g =new ObjectInputStream(new FileInputStream("exam3/object.dat"));
        BSTree<Integer> t1 = (BSTree<Integer>)(g.readObject());
        System.out.print("Deserialized tree in inorder: ");
        t1.inorderOutput();
        System.out.println();
        t1.add(44);
        System.out.print("Deserialized tree in inorder: ");
        t1.inorderOutput();
        System.out.println();
    }
}

