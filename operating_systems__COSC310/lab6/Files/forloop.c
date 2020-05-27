#include <stdio.h>

int main()
{
   int i;	// Loop counter
   int myArray[10];
   
   for (i=0; i<10; ++i)
   {
      myArray[i] = i;
   }
   for (i=0; i<10; ++i)
   {
      printf("%d\n", i);
   }
}				

