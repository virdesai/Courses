#include <stdio.h>

int numcmpA(float *s1, float *s2);
int numcmpD(float *s1, float *s2);
void bubblesort_float_generic(void *a[], int length, int (*comp)(void *,void *));
main(){
  int i, total = 0;
  float *x[100], b[100], input = 1;
  
  /* Input values: */
  
  printf("Enter floating point values:\n");
  while(input > 0 || input < 0){
    scanf("%f", &input);
    if(input > 0 || input < 0){
	b[total] = input;
	total++;
    }
  }

  for(i = 0; i < total; i++)
	x[i] = &b[i];

  puts("\nThe original values are:");
  for (i = 0; i < total; i++)
    printf("%G ", *x[i]);

  puts("\nThe sorted values are:");
  bubblesort_float_generic((void **) x, total, (int (*)(void*, void*))(1 ? numcmpA : numcmpD));
  for(i = 0; i < total; i++)
	printf("%G ", *x[i]);

  puts("\nThe sorted values are:");
  bubblesort_float_generic((void **) x, total, (int (*)(void*, void*))(0 ? numcmpA : numcmpD));
  for(i = 0; i < total; i++)
	printf("%G ", *x[i]);

  puts("");
}

void swap(void *a[], int x, int y);

void bubblesort_float_generic(void *a[], int length, int (*comp)(void *,void *)){
   int i, sorted = 1;

do{
   for (i = 0, sorted = 1; i < length-1; i++){
	if((*comp)(a[i], a[i+1])>0){
		sorted = 0;
		swap(a, i, i+1);
	}
   }
}while(!sorted);
}

void swap(void *a[], int x, int y){
	void *temp;
	temp = a[x];
	a[x] = a[y];
	a[y] = temp;
}

int numcmpA(float *s1, float *s2){
	if(*s1 < *s2)
		return -1;
	else if(*s1 > *s2)
		return 1;
	else
		return 0;
}

int numcmpD(float *s1, float *s2){
	if(*s1 < *s2)
		return 1;
	else if(*s1 > *s2)
		return -1;
	else
		return 0;
}
