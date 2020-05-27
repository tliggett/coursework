package chapter32;

//Example 32-2-4
public class Demo24
{
    public static void main(String[] args)
    {
        TNode<Character> root= buildTree();
        System.out.print("Preorder: ");
        BinaryTree.preorderOutput(root);
        System.out.println();
        System.out.print("Inorder: ");
        BinaryTree.inorderOutput(root);
        System.out.println();
        System.out.print("Postorder: ");
        BinaryTree.postorderOutput (root);
        System.out.println();
        System.out.print("Levelorder: ");
        BinaryTree.levelorderOutput (root);
        System.out.println();
    }
    public static TNode< Character> buildTree()
    {
        TNode<Character> H= new TNode< Character >('H');
        TNode<Character> E= new TNode< Character >('E', null, H);
        TNode<Character> F= new TNode< Character >('F');
        TNode<Character> C= new TNode< Character >('C', E, F);
        TNode<Character> G= new TNode< Character >('G');
        TNode<Character> D= new TNode< Character >('D', G, null);
        TNode<Character> B= new TNode< Character >('B', null, D);
        TNode<Character> A= new TNode< Character >('A', B, C);
        return A;
    }
}

