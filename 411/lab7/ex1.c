#include <stdio.h>

int main(){

  int r=0,g=0,b=0, PGM_MAX = 255;
  int columns=0, col=0, rows=0, row=0, PPM_MAX=0;
  int GRAY_VALUE=0;
  char type[2];

  scanf("%s%d%d%d", &type, &columns, &rows, &PPM_MAX);
  printf("P2\n%d\n%d\n%d\n", columns, rows, PGM_MAX);

  do{
  row = 0;
  do{
  scanf("%d%d%d", &r, &g, &b);
  
  GRAY_VALUE = ((r+g+b)*255)/(3*PPM_MAX);
  
  printf("%i\n", GRAY_VALUE);
  row = row + 1;
  }
  while(row < rows);
  col = col + 1;
  }
  while(col < columns);
  return (0);
}
