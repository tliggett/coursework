//TJ Liggett
//Final Exam
//12_2_2018
//Driver.java

/*
	Class Driver tests the sorting and searching methods in class QuickSort
 */

public class Driver
{
    public static void main(String[] args)
    {
        String[] strArr = {"red", "green", "blue"};
        Integer[] intArr = {10, 20, 40, 70, 50, 30, -99, 0, 85, 92, 24, 23};
        Time24[] timeArr = {new Time24(14,15), new Time24(10, 45),
                new Time24(22,00), new Time24(3,30)};
        Object[] intArr1 = {10, 9, 7, 5, 3, 3, 5, 99, -7, 0, 6, 3, 2, 1, 5, 4};

        System.out.println(Generic.arrToString(intArr));
        int highest=intArr.length-1;       //Test parameterized findKth methods
        int lowest=0;
        int ninetyPercent= intArr.length*9/10;
        int median= intArr.length/2;
        QuickSort.findKth (intArr, highest);
        System.out.println("Largest:"+intArr[highest]);
        QuickSort.findKth (intArr, lowest);
        System.out.println("Smallest:"+intArr[lowest]);
        QuickSort.findKth (intArr, median);
        System.out.println("Median:"+intArr[median]);
        QuickSort.findKth (intArr, ninetyPercent);
        System.out.println("The 90% largest:"+intArr[ninetyPercent] + "\n");

        System.out.println(Generic.arrToString(intArr1));
        highest=intArr1.length-1;       //Test generic findKth methods
        lowest=0;
        ninetyPercent= intArr1.length*9/10;
        median= intArr1.length/2;
        QuickSort.findKth (intArr1, highest);
        System.out.println("Largest:"+intArr1[highest]);
        QuickSort.findKth (intArr1, lowest);
        System.out.println("Smallest:"+intArr1[lowest]);
        QuickSort.findKth (intArr1, median);
        System.out.println("Median:"+intArr1[median]);
        QuickSort.findKth (intArr1, ninetyPercent);
        System.out.println("The 90% largest:"+intArr1[ninetyPercent] + "\n");

        QuickSort.quickSort(strArr);    //Test parameterized quickSort methods
        System.out.println(Generic.arrToString(strArr));
        QuickSort.quickSort(intArr);
        System.out.println(Generic.arrToString(intArr));
        QuickSort.quickSort(timeArr);
        System.out.println(Generic.arrToString(timeArr));
        QuickSort.quickSort(intArr1);   //Test generic quickSort methods
        System.out.println(Generic.arrToString(intArr1));
    }


}
