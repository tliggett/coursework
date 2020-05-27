package exam3;//TJ Liggett
//4_28_2019
//Exam 3
//DemoH.java

/*
*
*       Class DemoH performs part 2 of the 32-3 Assignment
*
*/


public class Demo {

    //Output: The size and height of randomly generated BSTree
    //        Ten number searches and comparisons required
    //        Average number of comparisons required
    public static void main(String[] args)
    {
        BSTree<Integer> tree = new BSTree<Integer>();
        while(tree.size() < 1000) tree.add( ((int)(Math.random()*2000)) );      /* (1) */
        System.out.printf("Size of tree: %d\n", tree.size());                   /* (2) */
        System.out.printf("Height of tree: %d\n", tree.height());               /* (3) */

        //NOTE: counters to be used for step #6
        double sum = 0.0;
        int count = 0;

        /* (4) + (5) */
        while(count < 10)
        {
            int key = ((int)(Math.random()*2000));
            int comp = tree.findLevelOf(key) + 1;
            if(comp > 0)
            {
                System.out.printf("Search %d requires %d comparisons\n", key, comp);
                count++;
                sum+= comp;
            }
        }

        System.out.printf("Average number of comparisons: %.1f\n", sum/count);  /* (6) */
    }
}
