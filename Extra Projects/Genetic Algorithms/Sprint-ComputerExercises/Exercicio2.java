import java.util.Random;

public class Exercicio2{
   public static void main(String[] args){
      Random rand = new Random();
      GeneticAlgorithm GA = new GeneticAlgorithm();
      GA.inicializaPopulacao();
      int bestFitness = 0;
      int i = 0;
      do{
         for(int j = 0; j < GA.getTamanhoPopulacao(); j++){
            GA.roundRobin(true);
            double probabilidade = Math.random();
            if(probabilidade < GA.getProbCruz()){
               int indicePai = GA.getRound()[rand.nextInt(GA.getRound().length)];
               int indiceMae;
               do{
                  indiceMae = GA.getRound()[rand.nextInt(GA.getRound().length)];
               }while(indicePai == indiceMae);
               int[] filho = new int[GA.getQuantidadeGenes()];
               GA.crossover(indicePai, indiceMae, filho);
               probabilidade = Math.random();
               if(probabilidade < GA.getProbMut()){
                  GA.mutation(filho);
               }
               if(GA.fitness(filho, true)>GA.fitness(GA.getPopulacao()[GA.worstIndex(true)], true)){
                  GA.getPopulacao()[GA.worstIndex(true)] = filho;
               }              
            }
         }
         System.out.println("-------------Geração "+(i+1)+"-------------");
         int bestOne = GA.bestIndex(true);
         bestFitness = GA.fitness(GA.getPopulacao()[bestOne], true);
         System.out.println("Melhor individuo: "+bestOne);
         System.out.println("Fitness: "+bestFitness);
         System.out.print("Genes: ");
         for(int j = 0; j < GA.getQuantidadeGenes(); j++){
            System.out.print(" "+GA.getPopulacao()[bestOne][j]);
         }
         System.out.println("\nFitness médio: "+GA.sumFitness(true)/GA.getTamanhoPopulacao()+"\n");
         i++;
      }while(bestFitness != Math.pow(2, GA.getQuantidadeGenes())-1 && i < GA.getGeracoes());
   }
}