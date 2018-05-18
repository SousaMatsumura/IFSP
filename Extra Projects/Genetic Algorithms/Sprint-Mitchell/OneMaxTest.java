import java.util.Random;

public class OneMaxTest{
   public static void main(String[] args){
      Random rand = new Random();
      OneMax oneMax = new OneMax();
      oneMax.inicializaPopulacao();
      int bestFitness = 0;
      int i = 0;
      do{
         for(int j = 0; j < oneMax.getCrossover(); j++){
            oneMax.roundRobin();
            double probabilidade = Math.random();
            if(probabilidade < oneMax.getProbCruz()){
               int indicePai = oneMax.getRound()[rand.nextInt(oneMax.getRound().length)];
               int indiceMae;
               do{
                  indiceMae = oneMax.getRound()[rand.nextInt(oneMax.getRound().length)];
               }while(indicePai == indiceMae);
               int[] filho = new int[oneMax.getQuantidadeGenes()];
               oneMax.crossover(indicePai, indiceMae, filho);
               probabilidade = Math.random();
               if(probabilidade < oneMax.getProbMut()){
                  oneMax.mutation(filho);
               }
               if(oneMax.fitness(filho)>oneMax.fitness(oneMax.getPopulacao()[oneMax.worstIndex()])){
                  oneMax.getPopulacao()[oneMax.worstIndex()] = filho;
               }              
            }
         }
         System.out.println("-------------Geração "+(i+1)+"-------------");
         int bestOne = oneMax.bestIndex();
         bestFitness = oneMax.fitness(oneMax.getPopulacao()[bestOne]);
         System.out.println("Melhor individuo: "+bestOne);
         System.out.println("Fitness: "+bestFitness);
         System.out.print("Genes: ");
         for(int j = 0; j < oneMax.getQuantidadeGenes(); j++){
            System.out.print(" "+oneMax.getPopulacao()[bestOne][j]);
         }
         System.out.println("");
         i++;
      }while(bestFitness != oneMax.getQuantidadeGenes() && i < oneMax.getGeracoes());
   }
}