#include <stdio.h>

main()
{
  int a;
  int zero = 0;
  int prime = 1;

  while(zero==0){
  	prime = 1;
  	printf("Number [1-100]: ?\n");
  	scanf("%d", &a);
  	if(a == 0){
		printf("Done\n");
		zero=1;
  	}else{
		int i;
  		for (i=2; i < a; i++){
			if(prime == 1){
      				if (a % i == 0 && a != i){
					printf("Non-prime, divisible by %d\n",i);
					prime = 0;
    				}
			}
		}
		if (prime == 1){
			printf("Prime\n");
		}
	}
  }
}
