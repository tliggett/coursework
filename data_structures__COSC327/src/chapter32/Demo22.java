package chapter32;

//Example 32-2-2
public class Demo22
{
    //Builds the tree depicted above.
    public static void main(String[] args)
    {
        TNode<Character> root= buildTree();
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
