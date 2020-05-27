package homework12;//4_28_2019
//Exam 3
//BSTree.java

/*
 *
 *       Supports a binary search tree of objects.
 *       Class BSTree will eventually implement our own MyCollection<T>.
 *       Right now it has all the MyCollection methods except toString and toArray.
 *       Documentation listed above each method
 *
 */

import java.io.Serializable;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class BSTree<T> implements MyCollection<T>, Cloneable, Serializable, Iterable<T>
{
    private BSTNode<T> root;
    private int treeSize;
    //Return:	A reference to the node containing item or null if the search fails
    private BSTNode<T> findNode(T item)
    {
        BSTNode<T> t = root;
        while(t != null)
        {
            int result = ((Comparable)item).compareTo(t.value);
            if (result == 0) return t;
            else if (result < 0) t = t.left;
            else t = t.right;
        }
        return null;
    }
    //Post: 	BSTree initialized to empty.
    public BSTree()
    {
        root = null;
        treeSize = 0;
    }

    //Return: The number of items in tree
    public int size()
    {
        return treeSize;
    }

    //Return: True if tree is empty, false otherwise
    public boolean isEmpty()
    {
        return treeSize == 0;
    }

    //Post: All nodes are cleared from tree
    public void clear()
    {
        treeSize = 0;
        root = null;
    }

    //Post:	item inserted to this tree if item is not already in the tree.
    //Return:	true if item inserted to this tree; false otherwise
    public boolean add(T item)
    {
        BSTNode<T> t = root, parent = null;
        int result=0;
        while(t != null)
        {
            parent = t;
            result = ((Comparable)item).compareTo(t.value);
            if (result == 0) return false;
            else if (result < 0) t = t.left;
            else t = t.right;
        }
        BSTNode<T> newNode = new BSTNode<T>(item, parent);
        if(parent == null) root = newNode;
        else if(result < 0) parent.left = newNode;
        else parent.right = newNode;
        treeSize++;
        return true;
    }

    //Return: True if tree contains item, false otherwise
    public boolean contains(Object item)
    {
        BSTNode<T> t = findNode((T)item);
        return (t == null) ? false : true;
    }
    //Post:	The inorder traversal of the values of BSTree printed on screen.
    public void inorderOutput()
    {
        inorder(root);
    }
    //Output: The inorder traversal of BSTree
    private void inorder(BSTNode<T> t)
    {
        if (t != null)
        {
            inorder(t.left);
            System.out.print(t.value + " ");
            inorder(t.right);
        }
    }


    //Return: copy of this BSTree. All nodes in the BSTree are cloned, but the data elements are not.
    public Object clone()
    {
        BSTree<T> copy = null;
        try
        {
            copy = (BSTree<T>)super.clone();
        }
        catch (CloneNotSupportedException cnse)
        {
        }
        copy.root = copyTree(root);
        return copy;
    }
    //Desc: 	Make a copy of a BSTree
    //Pre: 	t points to a BSTree
    //Return:A BSTree that is an exact copy of the one pointed to by t
    private BSTNode<T> copyTree(BSTNode<T> t)
    {
        if (t == null) return null;
        BSTNode<T> left = copyTree(t.left);
        BSTNode<T> right = copyTree(t.right);
        BSTNode<T> copyRoot = new BSTNode<T>(t.value, null);
        copyRoot.left = left;
        copyRoot.right = right;
        if(left != null) left.parent = copyRoot;
        if(right != null) right.parent = copyRoot;
        return copyRoot;
    }

    //Post: item is removed from tree
    //Return: true if item is removed, false otherwise
    public boolean remove(Object item)
    {
        BSTNode<T> t  = findNode((T)item);
        if (t == null) return false;
        removeNode(t);
        treeSize--;
        return true;
    }
    //Post BSTNode D is removed from tree
    private void removeNode(BSTNode<T> D)
    {
        BSTNode<T> pOfD=D.parent;
        BSTNode<T> R;
        BSTNode<T> pOfR;
        if ((D.left == null) && (D.right == null))		//D is a leaf
            R = null;
        else if ((D.left == null) && (D.right != null))	//D has 1 son, right
        {
            R = D.right;
            R.parent = pOfD;
        }
        else if ((D.left != null) && (D.right == null))	//D has 1 son, left
        {
            R = D.left;
            R.parent = pOfD;
        }
        else if (D.right.left== null)				//D has 2 sons, right son has
        {						//no left
            R = D.right;
            R.left = D.left;
            R.parent = pOfD;
            D.left.parent = R;
        }
        else						//D has 2 sons, right son has
        {						//left.
            pOfR = D;
            R = D.right;
            while(R.left != null)
            {
                pOfR = R;
                R = R.left;
            }
            pOfR .left = R.right;
            if (R.right != null) R.right.parent = pOfR;
            R.left = D.left;
            R.right = D.right;
            R.parent = pOfD;
            R.left.parent = R;
            R.right.parent = R;
        }
        //Set pOfD to reference R for all cases.
        if (pOfD == null) root = R;			//D is the root
        else if (((Comparable)(D.value)).compareTo(pOfD.value)<0)
            pOfD.left = R;
        else pOfD.right = R;
    }


    //Return: Height of the BSTree; -1 if tree is empty
    public int height()
    {
        return height(root);
    }
    //Return: the height of tree with root t
    private int height(BSTNode<T> t)
    {
        if(t == null) return -1;
        else
        {
            int heightLeft = height(t.left);
            int heightRight = height(t.right);
            return 1 + ((heightLeft > heightRight)?heightLeft:heightRight);
        }
    }


    //Return: The level of the node that contains item; -1 if item is not in BSTree
    public int findLevelOf(T item)
    {
        BSTNode<T> t = root;
        int level = -1;
        while(t != null)
        {
            level++;
            int result = ((Comparable)item).compareTo(t.value);
            if (result == 0) return level;
            else if (result < 0) t = t.left;
            else t = t.right;
        }
        return -1;
    }

    public Iterator<T> iterator()
    {
        return new BSTreeIterator(); 		//line 77
    }
    protected class BSTreeIterator implements Iterator<T>
    {
        private BSTNode<T> lastReturned = null;
        private BSTNode<T> nextNode = null;
        //Post: 	nextNode is the leftmost node in the tree
        public BSTreeIterator()
        {
            nextNode = root;		//line 88
            if (nextNode != null)	//line 99
                while (nextNode.left != null)
                    nextNode = nextNode.left;
        }				//line 100
        public boolean hasNext()
        {
            return nextNode != null;
        }
        public T next()
        {
            if (nextNode == null)
                throw new NoSuchElementException("No more elements");
            lastReturned = nextNode;
            if(nextNode.right != null)
            {
                nextNode = nextNode.right;
                while(nextNode.left != null) nextNode = nextNode.left;
            }
            else
            {
                BSTNode<T> p = nextNode.parent;
                while(p != null && nextNode == p.right)
                {
                    nextNode = p;
                    p = p.parent;
                }
                nextNode = p;
            }
            return lastReturned.value;
        }
        public void remove()
        {
            if (lastReturned == null) throw new IllegalStateException(
                    "Iterator call to next() required before calling remove()");
            removeNode(lastReturned);
            treeSize--;
            lastReturned = null;
        }
    }


    public Object[] toArray()
    {
        Object[] arr = new Object[treeSize];
        Iterator iter = iterator();
        int i = 0;
        while (iter.hasNext())
        {
            arr[i] = iter.next();
            i++;
        }
        return arr;
    }
    //Return:	A string representation of this tree, which is a comma separated list in iterator order
    //	enclosed in square brackets.
    public String toString()
    {
        String s = "[";
        Iterator iter = iterator();
        for (int i = 0; i < treeSize; i++)
        {
            s += iter.next();
            if (i < treeSize - 1) s += ", ";
        }
        s += "]";
        return s;
    }



}


/*
*
* Class BSTNode<T> stores T value and left, right, and parent nodes
*
* */
class BSTNode<T> implements Serializable
{
    public T value;
    public BSTNode<T> left;
    public BSTNode<T> right;
    public BSTNode<T> parent;
    public  BSTNode(T item, BSTNode<T> parent)
    {
        value = item;
        left = null;
        right = null;
        this.parent = parent;
    }
}
