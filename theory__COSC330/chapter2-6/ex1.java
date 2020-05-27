//Example 2-6-1
import java.util.Scanner;
import java.io.*;

class Demo
{
	public static void main(String[] args) throws FileNotFoundException
	{
		int num = 0, sum = 0;
		Scanner input = new Scanner(new File("number.txt"));	//line 12
		input.useDelimiter(":|[\r\n]+");
		while(input.hasNextInt())
		{
			num = input.nextInt();
			sum += num;
		}
		System.out.printf("The sum is %d\n", sum);
	}

}
