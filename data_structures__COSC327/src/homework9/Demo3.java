package homework9;

import java.util.Comparator;

//Example 31-5-2
public class Demo3
{
    public static void main(String[] args)
    {
        LLPriorityQueue<String> q = new LLPriorityQueue<String>(new maxComparator());
        q.offer("John");
        q.offer ("Paul");
        q.offer ("Ringo");
        q.offer ("George");
        System.out.println("There are "+q.size()+" elements");
        while (!q.isEmpty())
        {
            String name= q.poll();
            System.out.println(name);
        }
    }

    static class maxComparator implements Comparator<String>
    {
        @Override
        public int compare(String o1, String o2) {
            return -1 * o1.compareTo(o2);
        }
    }
}
