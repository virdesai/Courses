#include <stdio.h>
#include <string.h>

main(){

char input[] = "";
int ascii = 1;

do{
        fgets(input, 20, stdin);
        if(strcmp(input, "0\n") != 0){
                ascii = a_to_i(input);
                printf("%d\n", ascii);
        }else {
                break;
        }

}while(ascii != 0);

return 0;
}       

int a_to_i (char* str){
        int i;
        int num = 0;
        
        for (i = 0; str[i] != '\n'; ++i){ 
	        num = num*10 + str[i] - 48;
        }
         
        return num;
}

