//TJ Liggett
//Final Exam
//12_2_2018
//QuickSort.java

/*
	Class QuickSort contains parameterized and generic methods to sort arrays using the
	quickSort algorithm. It also contains parameterized and generic methods to find the
	kth largest element in an array.
 */

public class QuickSort
{
    /**
     Sorts an array of Ts into ascending order<p>
     <b>Post:</b><br>Elements in arr sorted in ascending order <p>
     */
    public static <T extends Comparable<? super T>> void quickSort(T[] arr)
    {
        quickSort(arr, 0, arr.length);
    }

    //Desc: 	Sorts an array of Ts into ascending order
    //Pre: 	arr[first]..arr[last-1] contain Ts to be sorted
    //Post: 	arr[first]..arr[last-1] sorted in ascending order
    public static <T extends Comparable<? super T>> void quickSort(T[] arr, int first, int last)
    {
        if (last - first <= 1) return;		//no element or 1 element in array
        else if (last - first == 2)		//2 elements in array
        {
            if (arr[last-1].compareTo(arr[first]) < 0)
            {
                T temp = arr [last-1];
                arr [last-1] = arr [first];
                arr [first] = temp;
            }
            return;
        }
        else
        {
            int pivotLoc = rearrange(arr, first, last);
            quickSort(arr, first, pivotLoc);
            quickSort(arr, pivotLoc +1, last);
        }
    }

    //Pre	:arr [first]..arr [last-1] contain Ts
    //Post	: arr[pivotLoc].compareTo(arr [first]..arr [pivotLoc-1]) >= 0
    //        arr[pivotLoc].compareTo(arr[pivotLoc+1]..arr [last-1]) <= 0
    //Return:	pivotLoc
    private static <T extends Comparable<? super T>> int rearrange (T[] arr, int first, int last)
    {
        int mid = (last + first)/2;
        T pivot = arr [mid];
        arr [mid] = arr [first];
        arr [first] = pivot;
        int scanUp = first + 1;
        int scanDown = last - 1;
        while (true)
        {
            while ((scanUp <= scanDown) && (arr[scanUp].compareTo(pivot) <= 0))
                scanUp++;
            while ((scanDown >= scanUp) && (arr[scanDown].compareTo(pivot) >= 0))
                scanDown--;
            if (scanUp > scanDown) break;
            T temp = arr [scanUp];
            arr [scanUp] = arr [scanDown];
            arr [scanDown] = temp;
            scanUp++;
            scanDown--;
        }
        arr [first] = arr [scanDown];
        arr [scanDown] = pivot;
        return scanDown;
    }


    /**
     Sorts an Object array into ascending order<p>
     <b>Post:</b><br>Elements in arr sorted in ascending order <p>
     */
    public static void quickSort(Object[] arr)
    {
        quickSort(arr, 0, arr.length);
    }

    //Desc: 	Sorts an Object array into ascending order
    //Pre: 	arr[first]..arr[last-1] contain objects to be sorted
    //Post: 	arr[first]..arr[last-1] sorted in ascending order
    public static void quickSort(Object[] arr, int first, int last)
    {
        if (last - first <= 1) return;		//no element or 1 element in array
        else if (last - first == 2)		//2 elements in array
        {
            if (((Comparable)arr[last-1]).compareTo(arr[first]) < 0)
            {
                Object temp = arr [last-1];
                arr [last-1] = arr [first];
                arr [first] = temp;
            }
            return;
        }
        else
        {
            int pivotLoc = rearrange(arr, first, last);
            quickSort(arr, first, pivotLoc);
            quickSort(arr, pivotLoc +1, last);
        }
    }

    //Pre	:arr [first]..arr [last-1] contain Objects
    //Post	: arr[pivotLoc].compareTo(arr [first]..arr [pivotLoc-1]) >= 0
    //        arr[pivotLoc].compareTo(arr[pivotLoc+1]..arr [last-1]) <= 0
    //Return:	pivotLoc
    private static int rearrange (Object[] arr, int first, int last)
    {
        int mid = (last + first)/2;
        Object pivot = arr[mid];
        arr [mid] = arr [first];
        arr [first] = pivot;
        int scanUp = first + 1;
        int scanDown = last - 1;
        while (true)
        {
            while ((scanUp <= scanDown) && (((Comparable)arr[scanUp]).compareTo(pivot) <= 0))
                scanUp++;
            while ((scanDown >= scanUp) && (((Comparable)arr[scanDown]).compareTo(pivot) >= 0))
                scanDown--;
            if (scanUp > scanDown) break;
            Object temp = arr [scanUp];
            arr [scanUp] = arr [scanDown];
            arr [scanDown] = temp;
            scanUp++;
            scanDown--;
        }
        arr [first] = arr [scanDown];
        arr [scanDown] = pivot;
        return scanDown;
    }


    /**
     Find the kth largest element in a T array<p>
     <b>Post:</b><br>The elements in arr has been rearranged in such a way that arr[k]
     now contains the kth largest element.
     */
    public static <T extends Comparable<? super T>>  void findKth(T[] arr, int k)
    {
        findKth(arr, 0, arr.length, k);
    }
    //Pre: 	arr[first]..arr[last-1] contain Ts
    //	k must be in [first..last-1]
    //Post:	The elements in arr has been rearranged in such a way that arr[k] now contains the kth
    //	largest element
    public static <T extends Comparable<? super T>>  void findKth(T[] arr, int first, int last, int k)
    {
        int pivotLoc = rearrange(arr, first, last);
        if (pivotLoc==k) return;
        else if (pivotLoc>k) findKth(arr, first, pivotLoc, k);
        else findKth (arr, pivotLoc +1, last, k);
    }


    /**
     Find the kth largest element in an Object array <p>
     <b>Post:</b><br>The elements in arr has been rearranged in such a way that arr[k]
     now contains the kth largest element.
     */
    public static void findKth(Object[] arr, int k)
    {
        findKth(arr, 0, arr.length, k);
    }
    //Pre: 	arr[first]..arr[last-1] contain Objects
    //	k must be in [first..last-1]
    //Post:	The elements in arr has been rearranged in such a way that arr[k] now contains the kth
    //	largest element
    public static void findKth(Object[] arr, int first, int last, int k)
    {
        int pivotLoc = rearrange(arr, first, last);
        if (pivotLoc==k) return;
        else if (pivotLoc>k) findKth(arr, first, pivotLoc, k);
        else findKth (arr, pivotLoc +1, last, k);
    }

}
