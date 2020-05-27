package exam1;//TJ Liggett
//Exam 1
//2_25_2019
//Driver.java


public class Driver
{
    //Desc: Runs tests on methods of MyArrayList
    //Output: The results of various tests on methods of MyArrayList
    public static void main(String[] args)
    {
        MyArrayList<Integer> intList = new MyArrayList<Integer>();

        //Basic test of add(index, item)
        //Note: As the list will exceed ten items, this will test ensureCapacity is
        //      implemented correctly
        System.out.println("Empty list: " + intList);
        intList.add(0, 1);
        intList.add(1, 2);
        intList.add(0,7);
        intList.add(1, 3);
        for(int i = 0; i<7; ++i) intList.add(2,4);
        System.out.println(intList);

        //Test add(item).
        //Note: As the list will exceed twenty items, this will test ensureCapacity is
        //      implemented correctly
        for(int i = 0; i < 11; ++i) intList.add((int)((Math.random()*1000)-500));
        System.out.println(intList + "\n");


        //Testing that rangeCheck is implemented correctly in each method
        try{
            intList.add(intList.size()+1, 0);
        }catch(IndexOutOfBoundsException e){
            System.out.println("Range out of bounds; Upperbound add rangeCheck implementation correct");
        }
        try{
            intList.add(-1, 0);
        }catch(IndexOutOfBoundsException e){
            System.out.println("Range out of bounds; Lowerbound add rangeCheck implementation correct");
        }
        try{
            intList.set(intList.size(), 0);
        }catch(IndexOutOfBoundsException e){
            System.out.println("Range out of bounds; Upperbound set rangeCheck implementation correct");
        }
        try{
            intList.set(-1, 0);
        }catch(IndexOutOfBoundsException e){
            System.out.println("Range out of bounds; Lowerbound set rangeCheck implementation correct");
        }
        try{
            intList.get(intList.size());
        }catch(IndexOutOfBoundsException e){
            System.out.println("Range out of bounds; Upperbound set rangeCheck implementation correct");
        }
        try{
            intList.get(-1);
        }catch(IndexOutOfBoundsException e){
            System.out.println("Range out of bounds; Lowerbound get rangeCheck implementation correct");
        }



        //Testing method get(index)
        int lowerBound = 0;
        int mid = 5;
        int upperBound = intList.size()-1;
        System.out.printf("\nGot Integer at min index %d: %d\n",lowerBound,intList.get(lowerBound));
        System.out.printf("Got Integer at middle index %d: %d\n",mid,intList.get(mid));
        System.out.printf("Got Integer at max index %d: %d\n",upperBound, intList.get(upperBound));
        System.out.println(intList);
        System.out.println();

        mid = 7;

        //Testing method set(index, item)
        System.out.printf("Set Integer %d at min index %d to %d\n", intList.set(lowerBound, 1729),
                                lowerBound, 1729);
        System.out.printf("Set Integer %d at middle index %d to %d\n", intList.set(mid, mid),mid,mid);
        System.out.printf("Set Integer %d at max index %d to %d\n", intList.set(upperBound, -12),
                                upperBound, -12);
        System.out.println(intList);
        System.out.println();

        //Testing method remove(obj)
        Integer j = 1729;
        Integer k = 4;
        Integer l = 628;
        System.out.printf("Removing first instance of Integer %d. Status: %b\n", j, intList.remove(j));
        System.out.printf("Removing first instance of Integer %d. Status: %b\n", k, intList.remove(k));
        System.out.printf("Removing first instance of Integer %d. Status: %b\n", l, intList.remove(l));
        System.out.println(intList);
        System.out.println();

        //Testing method toArray()
        Object[] o = intList.toArray();
        System.out.printf("toArray array length: %d | listSize: %d\n", o.length, intList.size());
        System.out.print("[" + o[0]);
        for(int i = 1; i<o.length; ++i) System.out.print(", " + o[i]);
        System.out.println("]\n");


    }

}
