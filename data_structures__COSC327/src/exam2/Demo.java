//Exam 2
//4_1_2019
//exam2.exam2.Demo22.java

/*

     Class exam2.exam2.Demo22 tests EVERY method of exam2.LLStack.java

 */

public class Demo
{
    public static void main(String[] args)
    {
        LLStack< String > s = new LLStack< String > ();
        System.out.println("There are "+s.size()+" elements");
        s.push("John");
        s.push ("Paul");
        s.push ("George");
        s.push ("Ringo");
        System.out.println("There are "+s.size()+" elements");
        System.out.println(s);
        s.push("Joe");
        System.out.println("You just put " + s.peek() + " on stack. " + s.peek() + " is NO BEATLE!!!");
        System.out.println("Removed " + s.pop());
        LLStack< String > copy = ( LLStack< String > )s.clone();
        System.out.println("There are "+ copy.size()+" elements");
        while (!copy.isEmpty())
        {
            String name= copy.pop();
            System.out.println(name);
        }

        System.out.println("Copy is empty. There are "+  s.size()+" elements in original.");
        System.out.println("Copy: " + copy);
        System.out.println("Original: " + s);
        System.out.println("Clearing Original...");
        s.clear();
        System.out.println("Original: " + s);
    }
}
