/*****************************************************************************
NAME:				LSQ

PURPOSE:  Calculate a Least Squares Fit via routines QR and QR_solve

VERSION		DATE	AUTHOR
-------		----	------	
1.0		8/92	D. Steinwand

ALGORITHM Reference:

1.  "Matrix Computations (2nd ed.)", Gene H. Golub & Charles F. Van Loan, 
    John Hopkins University Press, Baltimore 1989 ISBN 0-8018-3739-1
****************************************************************************/
#include <math.h>
#include "lsq.h"

/* Calculate the QR factorization of the m by n matrix A.  On return, matrix A 
   will contain R in its upper triangle.  The strict lower triangle will 
   contain information about Q.  Vector v is a work vector, but on return will 
   contain additional information about Q.
  ---------------------------------------*/
void QR(
    double *A,	/* Matrix A (stored by columns) */
    long m,         /* Number of rows in matrix A */
    long n,         /* Number of columns in matrix A */
    double *v)      /* Vector v */
    {
    long N;
    long k,j;		/* Loop counters */
    double *cur_col_A;	/* Pointer to current column of matrix A */
    double vtv;

    N = ((m-1) > n) ? n : (m-1);
    for (k = 0; k < N; k++)
       {
       cur_col_A = A + (k * m);
       house ((m-k), &(cur_col_A[k]), &(v[k]), &vtv, &(cur_col_A[k]));
	
       for (j = k + 1; j < m; j++)
          cur_col_A[j] = v[j];

       row_house(&(A[(k+1)*m+k]), (m-k), (n-k-1), m, &(v[k]), vtv);
       v[k] = vtv;
       }
    return;
    }

/* Find the least squares solution of Ax = b where A is a full rank m by n 
   matrix with m >= n.  On entry, matrix A should contain the results of the 
   QR routine.  If iflag == 0, then v is a vector of length m containing the 
   results of the QR routine.  If iflag != 0, the entries in v are 
   reconstructed--the vector v is not changed.  The solution will be stored 
   in the first n entries of vector b.
  -----------------------------------*/
void QR_solve(
    double *A,      /* Matrix A (stored by columns) */
    long m,         /* Number of rows in matrix A */
    long n,         /* Number of columns in matrix A */
    double *v,      /* Vector v */
    double *b,	/* Vector b */
    long iflag)
    {
    long N;
    long k,j;		/* Loop counters */
    double *cur_col_A;      /* Pointer to current column of matrix A */
    double sum;		/* Accumulator */
    double vtv;
    double alpha;

    N = ((m-1) > n) ? n : (m-1);
    for (k = 0; k < N; k++)
       {
       cur_col_A = A + (k * m);
       sum = b[k];
       for (j = k + 1; j < m; j++)
          sum += (b[j] * cur_col_A[j]);
       if (iflag == 0) vtv = v[k];
       else
          {
          vtv = 1.0;
          for (j = k + 1; j < m; j++)
             vtv += (cur_col_A[j] * cur_col_A[j]);
          }
       alpha = -2.0 / vtv * sum;
       b[k] += alpha;
       for (j= k + 1; j < m; j++)
          b[j] += (alpha * cur_col_A[j]);
       }

    /* Backsolve
      ---------*/
    b[n-1] /= A[(n-1)*m+n-1];
    for (k = n-1; k--;)
       {
       cur_col_A = A + ((k+1) * m);
       for (j = 0; j < k+1; j++)
          b[j] -= (b[k+1] * cur_col_A[j]);
       b[k] /= A[k*m+k];
       }
    return;
    }

/* Given vector x of length n, find a vector v of length n such that v[0] = 1, 
   and for the Householder matrix P defined by v, Px = (Px, 0, ..., 0) 
   transpose.
  ----------*/
void house(
   long n,		/* Input--dimension of vectors x and v */
   double *x,	/* Input--vector x */
   double *v,	/* Output--vector v */
   double *vtv,	/* Output--Product of vector v transpose and v */
   double *Px)	/* Output--Product of Householder matrix P and vector x */
   {
   long i;		/* Loop counter */
   double u;
   long sign_x0;
   double beta;
   double norm_2();

   for (i = 0; i < n; i++)
      v[i] = x[i];
   u = norm_2(x, n);
   if (u != 0.0)
      {
      sign_x0 = sign(x[0]);
      beta = x[0] + sign_x0 * u;
      for (i = 1; i < n; i++)
         v[i] /= beta;
      *vtv = 2.0 * ((u * u) + (u * fabs(x[0]))) / (beta * beta);
      }
   else *vtv = 1.0;
   v[0] = 1.0;
   *Px = -sign_x0 * u;
   return;
   }

/* Replace the m by n matrix A by the product PA, where P is the Householder
   Transformation defined by vector v.  vtv should contain the product of
   v transpose v when this routine is called.
  ------------------------------------------*/
void row_house(
   double *A,	/* Matrix A (stored by columns) */
   long m,		/* Number of rows in matrix A */
   long n,		/* Number of columns in matrix A */
   long mdim,	/* Row dimension */
   double *v,	/* Vector v */
   double vtv)	/* Product of vector v transpose and vector v */
   {
   double *cur_col_A;	/* Pointer to current column of matrix A */
   double alpha;
   long i,k;		/* Loop counters */

   for (k = 0; k < n; k++)
      {
      cur_col_A = A + (k * mdim);
      for (alpha = 0.0, i = 0; i < m; i++)
         alpha += v[i] * cur_col_A[i];
      alpha *= -(2.0 / vtv);
      for (i = 0; i < m; i++)
         cur_col_A[i] += (alpha * v[i]);
      }
   return;
   }
   
/* Function to calculate the 2-norm of a vector
  --------------------------------------------*/
double norm_2(double *x, long n) {
   long i; double sum = 0.0;
   for (i = 0; i < n; i++) sum += x[i] * x[i];
   return(sqrt(sum)); }

/* Function to return the sign of a number
  ---------------------------------------*/
int sign(double val) { if (val < 0.0) return(-1); return(1); }

