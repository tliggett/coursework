package homework12;

//MySortedSet.java
public interface MySortedSet<T> extends MySet<T>
{
    //Return:	The first (minimum) element currently in this ordered set.
    //Exeptn:Throws NoSuchElementException if set is empty.
    T first();

    //Return:	The last (maximum) element currently in this ordered set.
    //Exeptn:Throws NoSuchElementException if set is empty.
    T last();
}
