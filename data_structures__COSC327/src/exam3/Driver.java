package exam3;/* DO NOT NEED TO TURN IN, TEST HEIGHT AND FINDLEVELOF*/

public class Driver {

    public static void main(String[] args)
    {
        BSTree<Integer> t=new BSTree<Integer>();
        System.out.printf("Height of tree: %d\n", t.height());
        System.out.printf("Level of 10 in tree: %d\n", t.findLevelOf(10));

        t.add(10);
        System.out.printf("Height of tree: %d\n", t.height());
        System.out.printf("Level of 10 in tree: %d\n", t.findLevelOf(10));
        System.out.printf("Level of 20 in tree: %d\n", t.findLevelOf(20));

        t.add(10); t.add(30); t.add(20); t.add(50); t.add(40); t.add(60); t.add(50);
        System.out.printf("Height of tree: %d\n", t.height());
        System.out.printf("Level of 10 in tree: %d\n", t.findLevelOf(10));
        System.out.printf("Level of 20 in tree: %d\n", t.findLevelOf(20));
        System.out.printf("Level of 40 in tree: %d\n", t.findLevelOf(40));
        System.out.printf("Level of 50 in tree: %d\n", t.findLevelOf(50));
        System.out.printf("Level of 65 in tree: %d\n", t.findLevelOf(65));

    }

}
