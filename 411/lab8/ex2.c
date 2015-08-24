#include <stdio.h>
#include <string.h>

unsigned int factorial(unsigned int n);

main(){

char input[] = "";
unsigned int ascii = 1; 
unsigned int fact = 1;

do{
        fgets(input, 20, stdin);
        ascii = a_to_i(input);
	fact = factorial(ascii);
        printf("%d\n", fact);
        if(fact == 1)
                break;

}while(ascii != 0);

return 0;
}

unsigned int factorial(unsigned int n)
{

  if(n == 0)
	return 1;
  else
	return(n*factorial(n-1));

}

int a_to_i (char* str){
        int i; 
        unsigned int num = 0;  
         
        for (i = 0; str[i] != '\n'; ++i){
                num = num*10 + str[i] - 48;
        }

        return num;
} 
