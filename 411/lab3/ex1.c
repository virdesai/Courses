#include <stdio.h>

main()
{
  int r;

  printf("Please enter a number from 1 to 5: ");
  scanf("%i", &r);
  if(r > 5 || r < 1){
	printf("Number is not in the range from 1 to 5\n");
  }else{
  int i;
  for (i=0; i<r; i++) {
  printf("Hello World\n");
  }}
  return;
}
