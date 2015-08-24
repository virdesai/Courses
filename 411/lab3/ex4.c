#include <stdio.h>

main()
{
  int a,b,c,d,e,f;

  printf("Enter six integers:\n");
  scanf("%d %d %d %d %d %d", &a, &b, &c, &d, &e, &f);
  printf("1234567890bb1234567890\n");
  printf("%10d", a);
  printf("%12d\n", b); 
  printf("%10d", c); 
  printf("%12d\n", d); 
  printf("%10d", e); 
  printf("%12d\n", f);
  return;
}

