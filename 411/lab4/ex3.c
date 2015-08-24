#include <stdio.h>

main(){

 int A[3][3];
 int B[3][3];
 int i, j, k;

	printf("Please enter the values for A[0..2][0..2], one row per line:\n");   
	  	for(i = 0; i < 3; i++){
                        scanf("%d", &A[0][i]);
                }
                for(j = 0; j < 3; j++){
                        scanf("%d", &A[1][j]);
                }
                for(k = 0; k < 3; k++){
                        scanf("%d", &A[2][k]);
                }


	printf("Please enter the values for B[0..2][0..2], one row per line:\n");   
		 for(i = 0; i < 3; i++){
                        scanf("%d", &B[0][i]);
                }
                for(j = 0; j < 3; j++){
                        scanf("%d", &B[1][j]);
                }
                for(k = 0; k < 3; k++){
                        scanf("%d", &B[2][k]);
                }

  int C[3][3];

  printf("A=\n   %3i   %3i   %3i\n", 
          A[0][0], A[0][1], A[0][2]);
  printf("   %3i   %3i   %3i\n", 
          A[1][0], A[1][1], A[1][2]);
  printf("   %3i   %3i   %3i\n", 
          A[2][0], A[2][1], A[2][2]);
  
  printf("B=\n   %3i   %3i   %3i\n", 
          B[0][0], B[0][1], B[0][2]);
  printf("   %3i   %3i   %3i\n", 
          B[1][0], B[1][1], B[1][2]);
  printf("   %3i   %3i   %3i\n", 
          B[2][0], B[2][1], B[2][2]);
  
  /* multiply C = A.B: */
  
  for (i = 0; i < 3; i++)
    for (j = 0; j < 3; j++)
    {
      C[i][j] = 0;
      for (k =0; k < 3; k++)
        C[i][j] += A[i][k] * B[k][j];
    }
  
  printf("C=A.B=\n   %3i   %3i   %3i\n", 
          C[0][0], C[0][1], C[0][2]);
  printf("   %3i   %3i   %3i\n", 
          C[1][0], C[1][1], C[1][2]);
  printf("   %3i   %3i   %3i\n", 
          C[2][0], C[2][1], C[2][2]);
  
  /* multiply C = B.A: */
  
  for (i = 0; i < 3; i++)
    for (j = 0; j < 3; j++)
    {
      C[i][j] = 0;
      for (k =0; k < 3; k++)
        C[i][j] += B[i][k] * A[k][j];
    }
  
  printf("C=B.A=\n   %3i   %3i   %3i\n", 
          C[0][0], C[0][1], C[0][2]);
  printf("   %3i   %3i   %3i\n", 
          C[1][0], C[1][1], C[1][2]);
  printf("   %3i   %3i   %3i\n", 
          C[2][0], C[2][1], C[2][2]);
}
