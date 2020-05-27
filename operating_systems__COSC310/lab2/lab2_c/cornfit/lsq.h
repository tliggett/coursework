// Function prototypes for lsq.c
// -----------------------------
void QR(double *A,	/* Matrix A (stored by columns) */
        long m,         /* Number of rows in matrix A */
        long n,         /* Number of columns in matrix A */
        double *v);      /* Vector v */

void QR_solve(double *A,      /* Matrix A (stored by columns) */
              long m,         /* Number of rows in matrix A */
              long n,         /* Number of columns in matrix A */
              double *v,      /* Vector v */
              double *b,	/* Vector b */
              long iflag);

void house(long n,		/* Input--dimension of vectors x and v */
           double *x,		/* Input--vector x */
           double *v,		/* Output--vector v */
           double *vtv,		/* Output--Product of vector v transpose and v */
           double *Px);		/* Output--Product of Householder matrix P and vector x */


void row_house(double *A,	/* Matrix A (stored by columns) */
               long m,		/* Number of rows in matrix A */
               long n,		/* Number of columns in matrix A */
               long mdim,	/* Row dimension */
               double *v,	/* Vector v */
               double vtv);	/* Product of vector v transpose and vector v */
double norm_2(double *x, long n);
int sign(double val);

