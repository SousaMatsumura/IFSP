import java.util.Random;

public class Exercicio1{
   public static void main(String[] args){
      Random rand = new Random();
      GeneticAlgorithm GA = new GeneticAlgorithm();
      GA.inicializaPopulacao();
      int bestFitness = 0;
      int i = 0;
      do{
         for(int j = 0; j < GA.getTamanhoPopulacao(); j++){
            GA.roundRobin(false);
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
               if(GA.fitness(filho, false)>GA.fitness(GA.getPopulacao()[GA.worstIndex(false)], false)){
                  GA.getPopulacao()[GA.worstIndex(false)] = filho;
               }              
            }
         }
         System.out.println("-------------Geração "+(i+1)+"-------------");
         int bestOne = GA.bestIndex(false);
         bestFitness = GA.fitness(GA.getPopulacao()[bestOne], false);
         System.out.println("Melhor individuo: "+bestOne);
         System.out.println("Fitness: "+bestFitness);
         System.out.print("Genes: ");
         for(int j = 0; j < GA.getQuantidadeGenes(); j++){
            System.out.print(" "+GA.getPopulacao()[bestOne][j]);
         }
         System.out.println("\nFitness médio: "+GA.sumFitness(false)/GA.getTamanhoPopulacao()+"\n");
         i++;
      }while(bestFitness != GA.getQuantidadeGenes() && i < GA.getGeracoes());
   }
}