package chapter32;

//Example 32-3-4
public class Demo34
{
    public static void main(String[] args)
    {
        BSTree<Integer> t=new BSTree<Integer>();
        t.add(10); t.add(30); t.add(20); t.add(50); t.add(40);
        System.out.print("Tree in inorder: ");
        t.inorderOutput();
        System.out.println();
        t.remove(30);
        System.out.print("Tree in inorder: ");
        t.inorderOutput();
    }
}
