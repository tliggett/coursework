import java.util.Queue;
import java.util.LinkedList;
import static java.lang.System.*;

BinarySearchTree<Integer> bst;


void setup() {
  bst = new BinarySearchTree<Integer>();
  bst.add(70);
  bst.add(80);
  bst.add(85);
  bst.add(90);
  bst.add(98);
  bst.add(100);
  bst.add(120);
  bst.inOrder();
  bst.preOrder();
  bst.postOrder();
}

void draw() {
  
}


public interface Treeable<T extends Comparable<T>>
{
  public Object getValue();
  public Treeable<T> getLeft();
  public Treeable<T> getRight();
  public void setValue(T value);
  public void setLeft(Treeable<T> left);
  public void setRight(Treeable<T> right);
}

public class TreeNode<T extends Comparable<T>> implements Treeable<T>
{
  private T treeNodeValue;
  private TreeNode<T> leftTreeNode;
  private TreeNode<T> rightTreeNode;

  public TreeNode( )
  {
    treeNodeValue = null;
    leftTreeNode = null;
    rightTreeNode = null;
  }

  public TreeNode(T value)
  {
    treeNodeValue = value;
    leftTreeNode = null;
    rightTreeNode = null;
  }

  public TreeNode(T value, TreeNode<T> left, TreeNode<T> right)
  {
    treeNodeValue = value;
    leftTreeNode = left;
    rightTreeNode = right;
  }

  public T getValue()
  {
    return treeNodeValue;
  }

  public TreeNode<T> getLeft()
  {
    return leftTreeNode;
  }

  public TreeNode<T> getRight()
  {
    return rightTreeNode;
  }

  public void setValue(T value)
  {
    treeNodeValue = value;
  }

  public void setLeft(Treeable<T> left)
  {
    leftTreeNode = (TreeNode<T>)left;
  }

  public void setRight(Treeable<T> right)
  {
    rightTreeNode = (TreeNode<T>)right;
  }
}


public class BinarySearchTree<T extends Comparable<T>>
{
  private TreeNode<T> root;

  public BinarySearchTree()
  {
    root = null;
  }

  public void makeEmpty(){
    root = null;
  }

  public void add(T val)
  {
    root = add(val, root);
  }

  private TreeNode<T> add(T val, TreeNode<T> tree)
  {
     if(tree == null)
      tree = new TreeNode<T>(val);
    
    T treeValue = tree.getValue();
    int dirTest = val.compareTo(treeValue);    
    
    if(dirTest < 0)
      tree.setLeft(add(val, tree.getLeft()));
    else if(dirTest > 0)
      tree.setRight(add(val, tree.getRight()));
    
    return tree;
  }

  public void inOrder()
  {
    print("In Order: ");
    inOrder(root);
    println();

  }

  private void inOrder(TreeNode tree)
  {
    if(tree != null){
      inOrder(tree.getLeft());
      System.out.print(tree.getValue() + " ");
      inOrder(tree.getRight());
    }
  }

  //add in pre, post, level, and rev order traversals
  
  public void preOrder()
  {
    print("Pre Order: ");
    preOrder(root);
    println();

  }

  private void preOrder(TreeNode tree)
  {
    if(tree != null){
      System.out.print(tree.getValue() + " ");
      preOrder(tree.getLeft());
      preOrder(tree.getRight());
    }
  }
  
  
    public void postOrder()
  {
    print("Post Order: ");
    postOrder(root);
    println();

  }
  
  private void postOrder(TreeNode tree)
  {
    if(tree != null){
      postOrder(tree.getLeft());
      postOrder(tree.getRight());
      System.out.print(tree.getValue() + " ");
      
    }
  }
  
  
  
  
  
  

  public int getNumLevels()
  {
    return getNumLevels(root);
  }

  private int getNumLevels(TreeNode tree)
  {
    if(tree==null)
      return 0;
    else if(getNumLevels(tree.getLeft())>getNumLevels(tree.getRight()))
      return 1+getNumLevels(tree.getLeft());
    else
      return 1+getNumLevels(tree.getRight());
  }

  
  //add in numLeaves, width, height, numNodes, ifFull etc
  
  
  


  public String toString()
  {
    return toString(root);
  }

  private String toString(TreeNode tree)
  {
     if(tree==null)
        return "";
     else return "" + toString(tree.getLeft())+ " " + tree.getValue() + " "  + toString(tree.getRight());
  }
}