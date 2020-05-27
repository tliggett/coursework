package exam3;

//Example 32-2-7
public class FindMaxVisitor<T extends Comparable<? super T>> implements Visitor<T>
{
    private T max = null;
    //Pre: 	This FindMaxVisitor object contains the current max among all the nodes it has visited.
    //Post: 	This FindMaxVisitor object updates its max if its max is less than the node it is visiting
    public void visit(T obj)
    {
        if (max == null) max = obj;
        else if (obj.compareTo(max) > 0) max = obj;
    }
    //Return:	The current maximum
    public T getMax()
    {
        return max;
    }
}

