package exam3;

//Example 32-2-1
public class TNode<T>
{
    public T value;
    public TNode<T> left;
    public TNode<T> right;
    public  TNode(T item)
    {
        value = item;
        left = null;
        right = null;
    }
    public TNode(T item, TNode<T> left, TNode<T> right)
    {
        value = item;
        this.left = left;
        this.right = right;
    }
}
