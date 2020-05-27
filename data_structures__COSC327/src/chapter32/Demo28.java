package chapter32;

//Example 32-2-8
public class Demo28
{
    public static void main(String[] args)
    {
        TNode<Integer> root = buildTree();
        OutputVisitor<Integer> outputVisitorObj = new OutputVisitor<Integer>();
        FindMaxVisitor<Integer> findMaxVisitorObj = new FindMaxVisitor<Integer>();
        System.out.print("Tree in inorder: ");
        inorder(root, outputVisitorObj);
        System.out.println();
        inorder(root, findMaxVisitorObj);
        System.out.println("Max value is " + findMaxVisitorObj.getMax());
    }
    public static TNode<Integer> buildTree()
    {
        TNode<Integer> N15= new TNode<Integer>(15);
        TNode<Integer> N30= new TNode<Integer>(30);
        TNode<Integer> N65= new TNode<Integer>(65);
        TNode<Integer> N10= new TNode<Integer>(10, null, N15);
        TNode<Integer> N37= new TNode<Integer>(37, N30, N65);
        TNode<Integer> N25= new TNode<Integer>(25, N10, N37);
        return N25;
    }
    public static <T> void inorder(TNode<T> t, Visitor<T> v)
    {
        if (t != null)
        {
            inorder(t.left, v);
            v.visit(t.value);
            inorder(t.right, v);
        }
    }
}

