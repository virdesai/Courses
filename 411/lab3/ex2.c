#include <stdio.h>

main()            
{
  float a,b,c,sum, product;
  
  printf("Enter three floating-point numbers:\n");
  scanf("%f %f %f", &a, &b, &c);
  sum =a+b+c;
  product =a*b*c;
  printf("Sum is %.4f\n", sum);
  printf("Product is %.4f\n", product);
  return;
}

