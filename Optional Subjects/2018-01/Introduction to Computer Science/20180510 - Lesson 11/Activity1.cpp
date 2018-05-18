#include <stdio.h>
#include <conio.h>

int main(){

   float num1, num2;
   int option;
   do{
      printf ("\nType a number: ");
      scanf("%f",&num1);
      printf ("Type another number: ");
      scanf("%f",&num2);
      printf("\n\nType a option: \n");
      printf("[1] Sum \n");
      printf("[2] Subtraction \n");
      printf("[3] Multiplication \n");
      printf("[4] Division \n");
      printf("[e] Exit \n");
      //scanf("%d", &option);
      option = getch();
 
      switch(option){
         case '1': printf("\n%.f + %.f = %.f",num1, num2, num1+num2);
         break;
         case '2': printf("\n%.f - %.f = %.f",num1, num2, num1-num2);
         break;
         case '3': printf("\n%.f * %.f = %.f",num1, num2, num1*num2);
         break;
         case '4':
            if(num2 != 0){
      	       printf("\n%.f / %.f = %.2f",num1, num2, num1/num2);
            }else{
               printf("\nInfinity");
            }
         break;
         case 'e': printf("Bye bye");
         break;

         default: printf("\nInvalid option");
      }
   }while(option != 'e');

   return(0);

}