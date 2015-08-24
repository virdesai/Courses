#include <stdio.h>
#include <stdlib.h>

struct point{
        int x, y;
        struct point *next;
}*root = NULL;

void push(int x, int y){

	struct point *new, *current;
	new = (struct point *) malloc(sizeof(struct point));
	new -> x = x;
	new -> y = y;
	new -> next = NULL;

        if(root == NULL){
                root = (struct point *) malloc(sizeof(struct point));
                root = new;
		current = new;
        }else {
		current = root;
		while(current -> next != NULL){
			current = current -> next;}

                current -> next = new;
		current = new;
        }
}

int main(){
	int x = -1, y = -1;
	do{

		scanf("%d %d", &x, &y);
		push(x, y);

	}while(x !=0 || y != 0);
           
        while(root -> next != NULL){
                x = root -> x;
                y = root -> y;
                int squared = ((x*x)+(y*y));
                printf("%d\n",squared);
                root = root -> next;
        }
}
