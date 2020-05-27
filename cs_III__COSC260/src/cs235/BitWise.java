package cs235;

public class BitWise
{
public static void printbit(int n)
{
   	int i, mask=0x80000000;	
   	for (i=1; i<=32; ++i)
      	{
         		System.out.print(((n&mask)==0)?'0':'1');
         		n<<=1;
      	}
System.out.println();
}
}

