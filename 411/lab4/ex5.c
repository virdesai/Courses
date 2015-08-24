#include <stdio.h>
 
int factorial(int);
 
int main(){

  int number;

  do{
  printf("Enter a number: ");
  scanf("%d", &number);
 
  printf("%d! = %d\n", number, factorial(number));
  }
  while(number !=0);
  return 0;
}
 
int factorial(int n)
{
  int c;
  int result = 1;
 
  for (c = 1; c <= n; c++)
    result = result * c;
 
  return result;
}
