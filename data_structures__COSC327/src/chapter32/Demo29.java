package chapter32;//Example 32-2-9
import java.util.LinkedList;
import java.util.Collection;

class Demo29
{
    public static void main(String[] args)
    {
        Integer s=null;
        LinkedList < Integer > list = new LinkedList < Integer >();
        list.add(10);
        list.add(20);
        list.add(30);
        list.add(40);
        list.add(25);
        OutputVisitor<Integer> outputVisitorObj = new OutputVisitor<Integer>();
        FindMaxVisitor<Integer> findMaxVisitorObj = new FindMaxVisitor<Integer>();
        System.out.print("List: ");
        scan (list, outputVisitorObj);
        System.out.println();
        scan (list, findMaxVisitorObj);
        System.out.println("Max value is " + findMaxVisitorObj.getMax());
    }
    public static <T> void scan (Collection<T> c, Visitor<T> v)
    {



    }
}

