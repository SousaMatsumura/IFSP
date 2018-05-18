/*08/05/2018
Author: Gabriel de Sousa Matsumura
Probability - Activity 01
Make a program that prompts the user to:

   - How many students are in the class?
   - The number of men in the class;
   - The number of men over 25 years of age;
   - The number of women over 25 years of age;
   - The number of men who were born in Caraguatatuba;
   - The number of women born in Caraguatatuba.

Report:

   - The probability of drawing a man;
   - The probability of winning a woma;n
   - The probability of drawing a man under the age of 25;
   - The probability of winning a woman over 25 years of age;
   - The probability of drawing a man over 25 years old and born in Caraguatatuba;
   - The probability of drawing a woman under 25 years old and born in Caragutatuba.*/

import java.util.Scanner;
import java.text.DecimalFormat;
import java.util.InputMismatchException;

public class Activity1{   
   static boolean check;
   static void report(double classSize, double menAmount, double grownMenAmount, double grownWomenAmount, double nativeMenAmount, double nativeWomenAmount){
      DecimalFormat d = new DecimalFormat("#.##");
      System.out.println("\nThe raffle odds are:\n   - Man: "+d.format(menAmount/classSize)+";\n   - Women: "+d.format((classSize-menAmount)/classSize)+
         ";\n   - Men with more then 25 years old: "+d.format(grownMenAmount/classSize)+
         ";\n   - Women with more then 25 years old: "+d.format(grownWomenAmount/classSize)+
         ";\n   - Caraguatatuba native men: "+d.format(nativeWomenAmount/classSize)+
         ";\n   - Caraguatatuba native women: "+d.format(nativeWomenAmount/classSize)+".");
   }
   
   static boolean input(){
      Scanner in = new Scanner(System.in);
      System.out.println("Type, respectively:\n   - the class size;\n   - the amount of men;\n   - the amount of men with more then 25 years old;"+
         "\n   - the amount of women with more then 25 years old;\n   - the amount of Caraguatatuba native men;"+
         "\n   - the amount of Caraguatatuba native women.\n");
      try{
         report(in.nextInt(), in.nextInt(), in.nextInt(), in.nextInt(), in.nextInt(), in.nextInt());
         return true;
      }catch(InputMismatchException e){
         System.err.println("\nInput mismatch, try again.\n");
         return false;
      }
   }
   
   public static void main(String args[]){
      do{
         check = input();
      }while(!check);
   }   
}