#include <stdio.h>

unsigned int fibonacci(unsigned int n);

main(){

   int n, c = 1;
 
do{
   scanf("%d",&n);
   c = fibonacci(n);
   printf("%u\n", c);

}while(c != 0);

   return 0;
}
 
unsigned int fibonacci(unsigned int n){
   if ( n == 0 )
      return 0;
   else if ( n == 1 )
      return 1;
   else
      return ( fibonacci(n-1) + fibonacci(n-2) );
}
