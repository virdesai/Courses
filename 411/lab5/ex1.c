/* Example: bubble sort strings in array */

#include <stdio.h>  /* Need for standard I/O functions */
#include <string.h> /* Need for strlen() */


#define NUM 25   /* number of strings */
#define LEN 1000  /* max length of each string */

main()
{
  char Strings[NUM][LEN];
  int loop;

  printf("Please enter %d strings, one per line:\n", NUM);

  /* Write a for loop here to read NUM strings.

     Use fgets(), with LEN as an argument to ensure that an input line that is too
     long does not exceed the bounds imposed by the string's length.  Note that the
     newline and NULL characters will be included in LEN.
  */

  for (loop = 0; loop < NUM; loop++){
  	fgets(Strings[loop], LEN-2, stdin);
  }

  puts("\nHere are the strings in the order you entered:");

  /* Write a for loop here to print all the strings. */

  for (loop = 0; loop < NUM; loop++){
  	printf("%s", Strings[loop]);
  }
  

  /* Bubble sort */
  /* Write code here to bubble sort the strings in ascending alphabetical order

     Your code must meet the following requirements:
        (i) The comparison of two strings must be done by checking them one
            character at a time, without using any C string library functions.
            That is, write your own while/for loop to do this.
       (ii) The swap of two strings must be done by copying them 
            (using a temp variable of your choice) one character at a time,
            without using any C string library functions.
            That is, write your own while/for loop to do this.
      (iii) You are allowed to use strlen() to calculate string lengths.
  */

char order;
int i;
int j;
int o;

  for(loop = 0; loop < NUM; loop++){
	for(i = 0; i < NUM; i++){
		for(j = 0; j < strlen(Strings[loop])+1; j++){
			if(Strings[loop][j] < Strings[i][j] && loop > i){
				for(o = 0; o < LEN; o++){
					order = Strings[loop][o];
					Strings[loop][o] = Strings[i][o];
					Strings[i][o] = order;
				}
			}
		}
	}
  }

  /* Output sorted list */
  
  puts("\nIn alphabetical order, the strings are:");     
  /* Write a for loop here to print all the strings. Feel free to use puts/printf
     etc. for printing each string.
  */

  for (loop = 0; loop < NUM; loop++){
        printf("%s", Strings[loop]);
  }
  
  return 0;
}
