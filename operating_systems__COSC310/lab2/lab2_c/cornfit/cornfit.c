// build with:
// gcc -o cornfit cornfit.c lsq.c -lm

#include <stdlib.h>
#include <stdio.h>
#include "lsq.h"
#define ON	1
#define OFF	0

int main()
{
FILE *corptr, *ticptr, *fopen();
int i;
long fit_type;
long active[4];
long recnum[4];
long ticnum,cornum;
long xdeg,ydeg,xmin,ymin;
long ul,ur,ll,lr;
float xsec,ysec;
double testx,testy;
double x[4];
double y[4];
double xtic[4];
double ytic[4];
double x0,x1,x2,x3;
double y0,y1,y2,y3;
double newy,newx;
double resy,resx;
double v[4];		/* Work buffer for LSQ fit */
double b1[4];		/* Vector containing X coordinates */
double b2[4];		/* Vector containing Y coordinates */
double A[12];		/* Matrix A (used for LSQ fit) */
double rms_acc; 	/* Accumulator for RMSe calc */
long count;
long k;			/* Loop counters */
long size;		/* Size of vectors (based on poly degree) */
int parmCnt;		/* Number of items returned from a scanf */

/* Read in data
  ------------*/
printf("Enter 4 corner coordinate pairs, line coordinate, then sample > \n");
for(i=0;i<4;i++) 
   parmCnt = scanf("%lf%lf",&ytic[i],&xtic[i]);

printf("Enter 4 corner coordinate pairs, Y coordinate, then X > \n");
for(i=0;i<4;i++) 
   parmCnt = scanf("%lf%lf",&y[i],&x[i]);

/* Report data back to user
  ------------------------*/
printf("You entered:\n");
for(i=0;i<4;i++) printf("%lf %lf %lf %lf\n",y[i],x[i],ytic[i],xtic[i]);

/* Loop on tie point pairs.  If the tie point is active, use it in the
   fit calculations; first for x, then for y.
  ------------------------------------------*/
for(k=0, i=0; i < 4; i++) {
      A[i] =   1.0;
      A[i+4] = xtic[i];
      A[i+8] = ytic[i];
      b1[i] = x[i];
      b2[i] = y[i];
      }
QR(A, 4, 3, v);
QR_solve(A, 4, 3, v, b1, 0);
QR_solve(A, 4, 3, v, b2, 0);

/* Loop on tie point pairs and calculate residuals 
  -----------------------------------------------*/
printf("\nResiduals are for points not used in the fit (in x/y \")\n\n");
for(i=0; i < 4; i++) {
   newx = b1[0] + b1[1]*xtic[i] + b1[2]*ytic[i];
   newy = b2[0] + b2[1]*xtic[i] + b2[2]*ytic[i];
   resx = x[i] - newx;
   resy = y[i] - newy;
   printf("%lf %lf\n",resy,resx);
   }

for(;;){
   printf("Enter point (line/sample), return (blank input) to exit >  ");
   parmCnt = scanf("%lf%lf",&testy,&testx);
   if (parmCnt != 2) break;
   newx = b1[0] + b1[1]*testx + b1[2]*testy;
   newy = b2[0] + b2[1]*testx + b2[2]*testy;
   printf("Calculated coordinate:  %lf %lf\n",newy,newx);
   }
printf("\n");
}
