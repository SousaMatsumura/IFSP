#include <stdio.h>

int multTables(int num, int counter, int end){
	if(counter <= end){
	   printf("\n%d X %d = %d", num, counter, num*counter);
	   multTables(num, counter+1, end);
    }
		return 0;
}

int main(){
   int counter, num, end;
   printf ("\nType, respectivily, a number, the beginning and end of the multiplication table: ");
   scanf("%d %d %d", &num, &counter, &end);
   counter = multTables(num, counter, end);
   return(0);
}