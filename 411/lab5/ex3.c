/* Example: bubble sort strings in array */

#include <stdio.h>  /* Need for standard I/O functions */
#include <string.h> /* Need for string functions */
#include <stdlib.h> /* Need for malloc */


#define NUM 25   /* number of strings */
#define LEN 1000  /* max length of each string */

main()
{
  char* Strings[NUM];
  char temp[LEN];
  char string[NUM];
  char* Order[NUM];

  printf("Please enter %d strings, one per line:\n", NUM);

  /* Write a for loop here to read NUM strings, using fgets(). */
int i, j;

  for (i=0; i < NUM; i++) {

    /* Read one line of input into a temp string that is long enough (LEN long) */

    fgets(temp, LEN-2, stdin);

    /* Allocate memory space for String[i] that is only large enough to copy the
       string just read into it.  Suppose the length of the string read into
       temp is "length", then you can use the following: */

    Strings[i] = malloc(strlen(temp)+1); /* Plus one for the NULL at end */
    string[i] = temp;
    Strings[i] = &string[i];
  }

  puts("\nHere are the strings in the order you entered:");

  /* Write a for loop here to print all the strings. */


int loop;

  for (loop = 0; loop < NUM; loop++){
        printf("%s", string[loop]);
  }


  /* Bubble sort */
  /* Write code here to bubble sort the strings in ascending alphabetical order

     Your code must meet the following requirements:
        (i) The comparison of two strings must be done by strcmp() or strncmp().

       (ii) The swap of two strings must be done by swapping pointer values.
            You must not swap two string using strcpy()/strncpy() or using your 
            own loop to swap them a character at a time.
  */

        
for (i=0;i<NUM;i++)
        for (j=i+1;j<NUM+1;j++) {
                if (strcmp(string[i], string[j])>0) {
                        *Order[i] = *Strings[j];
                        *Strings[j] = *Strings[i];
                        *Strings[i] = *Order[i];
                }
        }

  
  
  /* Output sorted list */
  
  puts("\nIn alphabetical order, the strings are:");     
  /* Write a for loop here to print all the strings. */


for (loop = 0; loop < NUM; loop++){
        printf("%s", string(Strings[loop]));
  }


  /* Write a loop here to use free() to free up space allocated 
     for all of the strings above.  */


  for(loop = 0; loop < NUM; loop++){
        free(Strings[loop]);
	free(Order[loop]);
  }

  return 0;
}
