//Example 2-6-2


public class ex2
{
	public static void main(String[] args)
	{
		String s = new String("aaabbb");
		System.out.println(s.replaceAll("ab", "AB"));
		System.out.println(s.replaceAll("a+b", "AB"));
		System.out.println(s.replaceAll("a*b", "AB"));
		System.out.println(s.replaceAll("[ab]", "AB"));
		System.out.println(s.replaceAll("(bb)|(aa)", "AB"));
		System.out.println(s.replaceAll("(aa)+", "AB"));
		System.out.println(s.replaceAll("(aa)*", "AB"));
	}
}
