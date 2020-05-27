package homework9;

import java.math.BigInteger;

public class Fibonacci {
	public static void main(String[] args)
	{
		System.out.println(iFib(5));
	}
	
	public static BigInteger iFib(int n)
	{
		BigInteger oneBack = new BigInteger("1");
		BigInteger twoBack = new BigInteger("0");
		BigInteger cur = new BigInteger("0");
		if(n<2) return new BigInteger("" + n);
		else for(int i = 2; i<=n; ++i)
			{
				cur = oneBack.add(twoBack);
				twoBack = oneBack;
				oneBack = cur;
			}
		return cur;
		
	}
}
