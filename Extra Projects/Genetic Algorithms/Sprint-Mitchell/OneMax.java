import java.util.Random;

public class OneMax{

   private int quantidadeGenes = 50;
   private int tamanhoPopulacao = 50;
   private int crossover = 20;
   private int geracoes = 5000;
   private double probMut = .1;
   private double probCruz = 1.;
   private int populacao [][] = new int[tamanhoPopulacao][quantidadeGenes];
   private int[] round;
   
   public int[][] getPopulacao(){
      return populacao;
   }
   
   public int[] getRound(){
      return round;
   }
   
   public int getCrossover(){
      return crossover;
   }
   
   public int getGeracoes(){
      return geracoes;
   }
   
   public double getProbMut(){
      return probMut;
   }
   
   public double getProbCruz(){
      return probCruz;
   }
   
   public int getQuantidadeGenes(){
      return quantidadeGenes;
   }
   
   public void inicializaPopulacao(){
      Random aleatorio = new Random();
      for(int i = 0; i < tamanhoPopulacao; i++){
         int[] individuo = new int[quantidadeGenes];
         for(int j = 0; j < quantidadeGenes; j++){
            int gene = aleatorio.nextInt(2);
            individuo[j] = gene;
         }
         populacao[i] = individuo;
      }
   }
   
   public void mostrarPopulacao(){
      System.out.print("-----------------População Atual-----------------");
      for(int i=0; i < tamanhoPopulacao; i++){
         System.out.print("Individuo "+i+": ");
         for(int j = 0; j < quantidadeGenes; j++){
            System.out.print(populacao[i][j]);
         }
         System.out.println("; Fitness: "+fitness(populacao[i]));
      }
   }
   
   public int fitness(int[] individuo){
      int sum = 0;
      for(int i = 0; i < quantidadeGenes; i++){
         sum += individuo[i];
      }
      return sum;
   }

   public void mutation(int[] individuo){
      Random aleatorio = new Random();
      int gene = aleatorio.nextInt(quantidadeGenes);
      individuo[gene] = individuo[gene] == 0 ? 1 : 0;
   }

   public void crossover(int indicePai, int indiceMae, int[] filho){
      Random aleatorio = new Random();
      int ponto = aleatorio.nextInt(quantidadeGenes);
      for(int i = 0; i <= ponto; i++){
         filho[i] = populacao[indicePai][i];
      }
      for(int i = ponto + 1; i < quantidadeGenes; i++){
         filho[i] = populacao[indiceMae][i];
      }
   }
   
   public int bestIndex(){
      int bestIndex = 0;
      int bestFitness = fitness(populacao[0]);
      for(int i = 1;  i < tamanhoPopulacao; i++){
         if(bestFitness < fitness(populacao[i])){
            bestIndex = i;
            bestFitness = fitness(populacao[i]);
         }
      }
      return bestIndex;
   }

   public int worstIndex(){
      int worstIndex = 0;
      int worstFitness = fitness(populacao[0]);
      for(int i = 1;  i < tamanhoPopulacao; i++){
         if(worstFitness > fitness(populacao[i])){
            worstIndex = i;
            worstFitness = fitness(populacao[i]);
         }
      }
      return worstIndex;
   }

   public int sumFitness(){
      int sum = 0;
      for(int i = 0; i < tamanhoPopulacao; i++){
         sum += fitness(populacao[i]);
      }
      return sum;
      
   }

   public void roundRobin(){
      this.round = new int[sumFitness()];
      int currentCount = 0;
      int previousCount = 0;
      for(int i = 0; i < tamanhoPopulacao; i++){
         currentCount += fitness(populacao[i]);
         for(int j = previousCount; j < currentCount; j++){
            round[j] = i;
         }
         previousCount = currentCount;
      }
   }
}