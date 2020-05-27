package exam1;

//MyList.java
public interface MyList<T> extends MyCollection<T>
{
    //Pre: 	0<= index <=size()
    //Post: 	item added to List at index; elements shifted down to make room; size incremented
    //Excptn: 	if pre is not met, throws IndexOutOfBoundsException
    void add(int index, T item);

    //Pre: 	0<= index <size()
    //Post: 	item at index removed from List; elements shifted up to fill vacant position; size
    //	decremented
    //Return: 	The item removed
    //Excptn: 	if pre is not met, throws IndexOutOfBoundsException
    T remove(int index);

    //Pre: 	0<= index <size()
    //Return: 	The item at index of List
    //Excptn: 	if pre is not met, throws IndexOutOfBoundsException
    T get(int index);

    //Pre: 	0<= index <size()
    //Post: 	element at index replaced by item
    //Return: 	The element replaced
    //Excptn: 	if pre is not met, throws IndexOutOfBoundsException
    T set(int index, T item);

    //Return: 	The index of first occurrence of item in List if item exists; -1 if item does not exist
    int indexOf(Object item);
}

