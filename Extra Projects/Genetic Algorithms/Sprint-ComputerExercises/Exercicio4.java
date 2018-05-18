import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Exercicio4{
   public static void main(String[] args){
      GeneticAlgorithm GA = new GeneticAlgorithm();
      GA.setTamanhoPopulacao(1);
      GA.inicializaPopulacao();
      int[] mutado;
      int i = 0;
      do{
         mutado = GA.getPopulacao()[0];
         GA.mutation(mutado);
         if(GA.fitness(mutado, true)>GA.fitness(GA.getPopulacao()[0], true)){
            GA.getPopulacao()[0] = mutado;
         }
         if((i+1)%100 == 0 || GA.fitness(GA.getPopulacao()[0], true) == GA.getQuantidadeGenes()){
            System.out.println("-------------Geração "+((i+1)/100)+"-------------");
            System.out.println("Fitness: "+GA.fitness(GA.getPopulacao()[0], true));
            System.out.print("Genes: ");
            for(int j = 0; j < GA.getQuantidadeGenes(); j++){
               System.out.print(" "+GA.getPopulacao()[0][j]);
            }
            System.out.println(" ");
         }
      i++;
      }while(i<GA.getGeracoes() && GA.fitness(GA.getPopulacao()[0], true) != GA.getQuantidadeGenes());

      saveResults("C://Users//GabrieldeSousaMatsum//Desktop//IFSP//Algoritmos Genéticos//exercicio4-1.txt" , String.valueOf("Geração "+((i+1)/100)+" - Fitness: "+GA.fitness(GA.getPopulacao()[0], true)));

      GA.inicializaPopulacao();                  
      do{
         mutado = GA.getPopulacao()[0];
         GA.mutation(mutado);
         if(GA.fitness(mutado, true)>GA.fitness(GA.getPopulacao()[0], true)){
            GA.getPopulacao()[0] = mutado;
         }
         if((i+1)%100 == 0 || GA.fitness(GA.getPopulacao()[0], true) == GA.getQuantidadeGenes()){
            System.out.println("-------------Geração "+((i+1)/100)+"-------------");
            System.out.println("Fitness: "+GA.fitness(GA.getPopulacao()[0], true));
            System.out.print("Genes: ");
            for(int j = 0; j < GA.getQuantidadeGenes(); j++){
               System.out.print(" "+GA.getPopulacao()[0][j]);
            }
            System.out.println(" ");
         }
      i++;
      }while(i<GA.getGeracoes() && GA.fitness(GA.getPopulacao()[0], true) != Math.pow(2, GA.getQuantidadeGenes())-1);

      saveResults("C://Users//GabrieldeSousaMatsum//Desktop//IFSP//Algoritmos Genéticos//exercicio4-2.txt" , String.valueOf("Geração "+((i+1)/100)+" - Fitness: "+GA.fitness(GA.getPopulacao()[0], true)));
      
   }

   public static void saveResults(String file, String text){
      try(BufferedWriter bw = new BufferedWriter(new FileWriter(new File(file), true))){
         bw.write(text);
         bw.newLine();
         bw.flush();
         bw.close();
      }catch(IOException e){
         System.err.println("IOException: "+e.getMessage()+"\n"+new File(file).getAbsolutePath());
      }
   }
}