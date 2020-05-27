package exam3;//TJ Liggett
//4_27_2019
//Exam 3
//BTree.java

import java.lang.Number;

public class BTree {

    public static void main(String[] args)
    {
        TNode<Integer> root = buildTree();
        OutputVisitor<Integer> outputVisitorObj = new OutputVisitor<Integer>();
        FindSumVisitor findSumVisitorObj = new FindSumVisitor();

        //The Entire Tree
        System.out.print("Tree in inorder: ");
        inorder(root, outputVisitorObj);
        inorder(root, findSumVisitorObj);
        System.out.printf("\nSum of entire tree is %.1f\n", findSumVisitorObj.getSum());
        findSumVisitorObj.clearSum();

        //Left Subtree
        System.out.print("Left subtree in inorder: ");
        inorder(root.left, outputVisitorObj);
        inorder(root.left, findSumVisitorObj);
        System.out.printf("\nSum of left subtree is %.1f\n", findSumVisitorObj.getSum());
        findSumVisitorObj.clearSum();

        //Right Subtree
        System.out.print("Left subtree in inorder: ");
        inorder(root.right, outputVisitorObj);
        inorder(root.right, findSumVisitorObj);
        System.out.printf("\nSum of left subtree is %.1f\n", findSumVisitorObj.getSum());
    }

    //Post: Tree built like the tree in Programming Exercise 32-2 (a)
    public static TNode<Integer> buildTree()
    {
        TNode<Integer> N20  = new TNode<Integer>(20);
        TNode<Integer> N100 = new TNode<Integer>(100);
        TNode<Integer> N130 = new TNode<Integer>(130);
        TNode<Integer> N60  = new TNode<Integer>(60);
        TNode<Integer> N10  = new TNode<Integer>(10, null, N20);
        TNode<Integer> N120 = new TNode<Integer>(120, N100, N130);
        TNode<Integer> N80  = new TNode<Integer>(80, N60, N120);
        TNode<Integer> N30  = new TNode<Integer>(30, N10, null);
        TNode<Integer> N50  = new TNode<Integer>(50, N30, N80);
        return N50;
    }

    //Post Visitor v visits every node in tree with root t in inorder
    public static void inorder(TNode t, Visitor v)
    {
        if (t != null)
        {
            inorder(t.left, v);
            v.visit(t.value);
            inorder(t.right, v);
        }
    }

}

/*
*
*       Class FindSumVisitor finds the sum of nodes visited
*
* */
class FindSumVisitor implements Visitor<Number>
{
    private Number sum = 0;

    //Post: the double value of Number obj is added to sum
    public void visit(Number obj)
    {
        sum = sum.doubleValue() + obj.doubleValue();
    }
    //Return: The sum of nodes visited
    public Number getSum()
    {
        return sum;
    }
    //Post: sum of this FindSumVisitor set to 0.
    public void clearSum()
    {
        sum = 0;
    }
}
