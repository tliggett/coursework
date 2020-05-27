package homework3;//TJ Liggett
//Homework 3
//2_28_2019
//MyArrayList.java


public class Demo
{
    //Output: The contents of arr1 and deep clone arr2
    public static void main(String[] args)
    {
        MyArrayList <Time24> arr1=new MyArrayList < Time24>();
        MyArrayList < Time24> arr2=null;
        arr1.add(new Time24(1,1));
        arr1.add(new Time24(2,2));
        arr2= (MyArrayList < Time24>) arr1.clone();
        arr2.clear();
        for(int i = 0; i < arr1.size(); ++i) arr2.add((Time24) arr1.get(i).clone());
        arr1.add(new Time24(3,3));
        System.out.println(arr1);
        System.out.println(arr2);
        arr1.get(1).addTime(10);
        System.out.println(arr1);
        System.out.println(arr2);
    }
}

