package exam3;//Example 32-2-3

import java.util.Queue;
import java.util.LinkedList;
public class BinaryTree
{
    //Output: The values of the binary tree rooted by t printed on the screen in preorder
    public static <T> void preorderOutput(TNode<T> t)
    {
        if(t != null)
        {
            System.out.print(t.value + " ");
            preorderOutput(t.left);
            preorderOutput(t.right);
        }
    }
    //Output: The values of the binary tree rooted by t printed on the screen in inorder
    public static <T> void inorderOutput(TNode<T> t)
    {
        if(t != null)
        {
            inorderOutput(t.left);
            System.out.print(t.value + " ");
            inorderOutput(t.right);
        }
    }
    //Output: The values of the binary tree rooted by t printed on the screen in postorder
    public static <T> void postorderOutput(TNode<T> t)
    {
        if(t != null)
        {
            postorderOutput(t.left);
            postorderOutput(t.right);
            System.out.print(t.value + " ");
        }
    }
    //Output: The values of the binary tree rooted by t printed on the screen in levelorder
    public static <T> void levelorderOutput(TNode<T> t)
    {
        Queue<TNode<T>> q = new LinkedList<TNode<T>>();
        q.offer(t);
        while(!q.isEmpty())
        {
            TNode<T> p = q.poll();
            System.out.print(p.value + " ");
            if(p.left != null) q.offer(p.left);
            if(p.right != null) q.offer(p.right);
        }
    }

    //Desc: 	Find height of a binary tree
    //Pre: 	t points to a binary tree
    //Return:Height of the binary tree pointed to by t (-1 if tree is empty)
    public static <T> int height(TNode<T> t)
    {
        if(t == null) return -1;
        else
        {
            int heightLeft = height(t.left);
            int heightRight = height(t.right);
            return 1 + ((heightLeft > heightRight)?heightLeft:heightRight);
        }
    }
    //Desc: 	Count number of leaf nodes
    //Pre: 	t points to a binary tree
    //Return:	Number of leaf nodes in binary tree pointed to by t.
    public static <T> int leafCount (TNode<T> t)
    {
        if(t == null) return 0;
        if((t.left == null)&&(t.right == null)) return 1;
        return leafCount(t.left) + leafCount(t.right);
    }
    //Desc: 	Make a copy of a binary tree
    //Pre: 	t points to a binary tree
    //Return:A binary tree that is an exact copy of the one pointed to by t
    public static <T> TNode<T> copyTree(TNode<T> t)
    {
        if(t == null) return null;
        TNode<T> left = copyTree(t.left);
        TNode<T> right = copyTree(t.right);
        TNode<T> root = new TNode<T>(t.value, left, right);
        return root;

    }

    //Desc: 	Delete all nodes in a binary tree
    //Pre: 	t points to a binary tree
    //Post: 	All nodes in binary tree referenced by t released.
    //Note: 	The caller must assign the return value of clearTree to the root of the binary tree the
    //	caller is trying to clear
    public static <T> TNode<T> clearTree(TNode<T> t)
    {
        if (t != null)
        {
            t.left = clearTree (t.left);
            t.right = clearTree (t.right);
            return null;
        }
        else return null;
    }



}

