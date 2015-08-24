#include <stdio.h>
#include <string.h>
 
void bedtimestory(char word[20][15], int current, int number){
   int i = 0;

   if(current == 0){

   printf("A %s couldn't sleep, so her mother told a story about a little ", word[current]);
   bedtimestory(word, current+1, number);
   printf("... and then the %s fell asleep.\n", word[current]);

   }else if(current == number-2){

   printf("%s,\n", word[current]);
   for(i = 0; i < current; i++){
	printf("%s","  ");
   }
   printf("%s\n","... who fell asleep.");

   }else if(current < number - 2){

   printf("%s,\n", word[current]);
   for(i = 0; i < current; i++){
	printf("%s","  ");
   }
   printf("who couldn't sleep, so the %s's mother told a story about a little ", word[current]);
   bedtimestory(word, current+1, number);
   for(i = 0; i < current; i++){
	printf("%s","  ");
   }
   printf("... and then the little %s fell asleep;\n", word[current]);

   }
}
                  
main(){
   char names[20][15];
   char end[] = "END";
   int num = 0;

do{
   fgets(names[num], 15, stdin);
   char* p = strchr(names[num], '\n');
   if (p)
	*p = '\0';
   num++;

}while((num < 20) && (strcmp(end, names[num-1]) != 0));

bedtimestory(names, 0, num);

return 0;
}
