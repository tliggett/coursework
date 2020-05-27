package chapter32;

//Example 32-2-10
public class Demo210
{
    public static void main(String[] args)
    {
        TNode<Integer> root= buildTree();
        System.out.print("Original: ");
        BinaryTree.inorderOutput(root);
        System.out.println("\nHeight: "+ BinaryTree.height(root));
        System.out.println("Number of leaves: "+ BinaryTree.leafCount(root));

        TNode<Integer> copy= BinaryTree.copyTree(root);
        System.out.print("Copy: ");
        BinaryTree.inorderOutput(copy);
        System.out.println("\nHeight: "+ BinaryTree.height(copy));
        System.out.println("Number of leaves: "+ BinaryTree.leafCount(copy));
        copy= BinaryTree.clearTree(copy);
        System.out.println("Copy Tree destroyed");
        System.out.println("Height: "+ BinaryTree.height(copy));
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
}
