package homework2;//TJ Liggett
//Homework 2
//2_21_2019
//exam2.exam2.Demo22.java

/*
	Class exam2.exam2.Demo22 stores Circles in a TreeSet using class CircleComparator to compare the Circles
 */


import java.util.*;

public class Demo
{
    public static void main(String[] args)
    {
        TreeSet<Circle> v=new TreeSet<Circle>(new CircleComparator());
        v.add(new Circle(2));
        v.add(new Circle(2));
        System.out.println(v);
        if (v.remove(new Circle(2))) System.out.println("removed");
        else System.out.println("not removed");

    }
}

class CircleComparator implements Comparator<Circle>
{
    public int compare(Circle x, Circle y)
    {
        if (x.getRadius()==y.getRadius()) return 0;
        else if (x.getRadius()<y.getRadius()) return -1;
        else return 1;
    }
}