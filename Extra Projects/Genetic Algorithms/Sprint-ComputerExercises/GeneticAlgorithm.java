import java.util.Random;

public class GeneticAlgorithm{

   private int quantidadeGenes = 20;
   private int tamanhoPopulacao = 100;
   private int geracoes = 1000000000;
   private double probMut = .1;
   private double probCruz = 1;
   private int populacao [][] = new int[tamanhoPopulacao][quantidadeGenes];
   private int[] round;
   
   public int[][] getPopulacao(){
      return populacao;
   }
   
   public int[] getRound(){
      return round;
   }
   
   public int getTamanhoPopulacao(){
      return tamanhoPopulacao;
   }

   public void setTamanhoPopulacao(int size){
      this.tamanhoPopulacao = size;
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
   
   
   public int fitness(int[] individuo, boolean kind){
      int sum = 0;
      if(kind == false){
         for(int i = 0; i < quantidadeGenes; i++){
            sum += individuo[i];
         }
      }else{
         for(int i = 0; i < quantidadeGenes; i++){
            sum += individuo[i]*Math.pow(2, (quantidadeGenes-1)-i);
         }
         
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
   
   public int bestIndex(boolean kind){
      int bestIndex = 0;
      int bestFitness = fitness(populacao[0], kind);
      for(int i = 1;  i < tamanhoPopulacao; i++){
         if(bestFitness < fitness(populacao[i], kind)){
            bestIndex = i;
            bestFitness = fitness(populacao[i], kind);
         }
      }
      return bestIndex;
   }

   public int worstIndex(boolean kind){
      int worstIndex = 0;
      int worstFitness = fitness(populacao[0], kind);
      for(int i = 1;  i < tamanhoPopulacao; i++){
         if(worstFitness > fitness(populacao[i], kind)){
            worstIndex = i;
            worstFitness = fitness(populacao[i], kind);
         }
      }
      return worstIndex;
   }

   public int sumFitness(boolean kind){
      int sum = 0;
      for(int i = 0; i < tamanhoPopulacao; i++){
         sum += fitness(populacao[i], kind);
      }
      return sum;
   }
   
   
   public int sumShortFitness(boolean kind){
      assert kind;
      int sum = 0;
      for(int i = 0; i<tamanhoPopulacao; i++){
         sum += shortFitness(populacao[i], kind);
      }
      return sum;      
   }

   public int shortFitness(int[] individuo, boolean kind){
      int shortFit = 0;
      int totalFitness = fitness(individuo, kind);
      for(int i = 0; i<quantidadeGenes; i++){
         if(totalFitness>=Math.pow(2,i)){
            totalFitness -= Math.pow(2,i);
            shortFit += i+1;
         }
      }
      return shortFit;
   }
   
   public void roundRobin(boolean kind){
      int currentCount = 0;
      int previousCount = 0;

      if(!kind){
         this.round = new int[sumFitness(kind)];
         for(int i = 0; i < tamanhoPopulacao; i++){
            currentCount += fitness(populacao[i], kind);
            for(int j = previousCount; j < currentCount; j++){
               round[j] = i;
            }
            previousCount = currentCount;
         }

      }else{
         this.round = new int[sumShortFitness(kind)];
         for(int i = 0; i < tamanhoPopulacao; i++){
            currentCount += shortFitness(populacao[i], kind);
            for(int j = previousCount; j < currentCount; j++){
               round[j] = i;
            }
            previousCount = currentCount;
      }

      }

   }
   
}