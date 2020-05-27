#include <stdio.h>
#include <stdlib.h>

// Main line routine that processes command-line inputs.   Argc is the number of arguments
// argv[] is a vector (array) containing the arguments
int main(int argc, char *argv[]) {
   unsigned int a,b,result;		// Fixed-point variables
   unsigned long int temp;		// Long integer to hold intermediate results
   unsigned int aR, cR;			// Fixed-point result variables
   float fa,fb,fresult;		// Floating point variables

   // Grab parameters
   if (argc != 3) {
      printf("Use:  fixedpt num1 num2 (for small nums)\n\n");
      exit(EXIT_FAILURE);
   }
   sscanf(argv[1],"%f",&fa);
   sscanf(argv[2],"%f",&fb);
   printf("\na = %f, b= %f\n\n", fa, fb);
   printf("Performing Q8 Fixed-Point\n");
   printf("   Variable size is %d bytes\n", (int)sizeof(int));
   printf("   Temp Variable size is %d bytes\n\n", (int)sizeof(long int));

   // Do it in floating-pt
   printf("Float calcs:\n");
   fresult = fa * fb;
   printf("   Multiplication:  %f * %f = %f\n", fa, fb, fresult);
   fresult = fa / fb;
   printf("   Division:        %f / %f = %f\n", fa, fb, fresult);
   fresult = fa + fb;
   printf("   Addition:        %f + %f = %f\n", fa, fb, fresult);
   fresult = fa - fb;
   printf("   Subtraction:     %f - %f = %f\n", fa, fb, fresult);
   printf("\n");

   // Integer part, scaled by 8 bits (256).  These are Q8 operations
   a = fa * 256.0;	// Convert fa to fixed point scaled integer a
   b = fb * 256.0;	// Convert fb to fixed point scaled integer b
   printf("Fixed-Pt (scaled integer:  Factor = 256 -- 8 bits) calcs:\n");

   // Multiplication
   temp = a * b;		// This result is in Q16
   cR = temp >> 8; 		// Rescale back to Q8
   fresult = (float)cR/256.0;	// Convert (with rescale) back to floating point
   printf("   Multiplication:  %d * %d = %ld -> %d (rescaled) (%f)\n", a, b, temp, cR, fresult);

   // Division
   temp = a<<8;				// Up-Scale to Q16
   result = temp / b;			// Divide as usual.  This result is Q8
   fresult = (float)result/256.0;	// Convert (with rescale) back to floating point
   printf("   Division:        %d -> %ld (rescaled) / %d = %d (%f)\n", a, temp, b, result, fresult);

   // Addition
   result = a + b;			// These stay Q8
   fresult = (float)result/256.0;	// Convert (with rescale) back to floating point
   printf("   Addition:        %d + %d = %d (%f)\n", a, b, result, fresult);

   // Subtraction
   result = a - b;			// These stay Q8
   fresult = (float)result/256.0;	// Convert (with rescale) back to floating point
   printf("   Subtraction:     %d - %d = %d (%f)\n", a, b, result, fresult);
}
