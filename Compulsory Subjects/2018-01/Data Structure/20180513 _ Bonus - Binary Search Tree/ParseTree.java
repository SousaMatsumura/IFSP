import java.io.IOException;
import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;

public class ParseTree{
   static Node root = null;
   static Node list = null;
   public static void main(String[] args){
      readFile("C://Users//GabrieldeSousaMatsum//Desktop//IFSP//Estrutura de Dados//20180513 _ Bonus - Binary Search Tree//Dados//10000.txt");
      System.out.println("\nFile 5000.txt: \n");
      System.out.println("\nBinary Tree -> Depth = "+depth(root)+"\nNodes amount: "+countNodes(root));
      treeBalancing();
      System.out.println("\nBinary Search Tree -> Depth = "+depth(root)+"\nNodes amount: "+countNodes(root));
   }
    
   public static void add(int v){
      Node n = new Node(v);
        
      if(root == null){
         root = n;
      }else{
         boolean add = false;
         Node temp = root;
           
         while(!add){
            if(n.value < temp.value){
               if(temp.left == null){
                  temp.left = n;
                  add = true;
               }else{
                  temp = temp.left;
               }
            }else{
               if(temp.right == null){
                  temp.right = n;
                  add = true;
               }else{
                  temp = temp.right;
               }
            }
         }
      }
   }
    
   public static void print(Node temp){
      if(temp != null){
         System.out.println(temp.value);
         print(temp.left);
         print(temp.right);
      }
   }
   
   public static int countNodes(Node temp){
      if(temp != null){
         if(temp.left != null && temp.right != null){
            return 1+ countNodes(temp.left)+countNodes(temp.right);
         }else if(temp.left != null){
            return 1+ countNodes(temp.left);
         }else{
            return 1+ countNodes(temp.right);
         }
      }else{
         return 0;
      }
   }
      
   public static void treeToList(Node temp){
      if(temp == null){
         return;
      }
      Node t = new Node(temp.value);
      treeToList(temp.left);
      if(list == null){
         list = t;
      }else{
         if(list.right == null){
            list.right = t;
            list.left = t;
            t.right = list;
            t.left = list;
         }else{
            list.left.right = t;
            t.left = list.left;
            list.left = t;
            t.right = list;
         }
      }
      treeToList(temp.right);
   }
   
   static void centralizedInsertion(Node temp, Node first, Node last){
      if(first.equals(first.right)){
         add(first.value);
      }else if(!(first.right.equals(last.left) || first.right.equals(last))){
         centralizedInsertion(temp, first.right, last.left);
      }else{
         Node prev = temp;
         Node next = temp.left;
         if(first.right.equals(last.left)){
            add(first.right.value);
         }else{
            add(last.value);
            last = last.right;
         }
         if(prev.equals(first)){
            prev.right = prev;
            prev.left = prev;
            centralizedInsertion(prev, prev, prev);
         }else{
            prev.left = first;
            first.right = prev;
            centralizedInsertion(prev, prev, first);
         }
         if(!first.right.equals(last)){
            if(last.equals(next)){
               last.right = last;
               last.left = last;
               centralizedInsertion(last, last, last);
            }else{
               last.left = next;
               next.right = last;
               centralizedInsertion(last, last, next);
            }
         }
      }
   }

   public static void treeBalancing(){
      treeToList(root);
      root = null;
      centralizedInsertion(list, list, list.left);
   }
   
   static void printList(Node temp){
      Node t = temp;
      do{
         System.out.println(t.value);
         t = t.right;
      }while(!t.equals(temp));
   }
   
   static int depth(Node temp){
      if(temp == null){
         return -1;
      }
      if(temp.left == null && temp.right == null){
         return 0;
      }else{
         if(temp.left != null && temp.right != null){
            if(depth(temp.left)<depth(temp.right)){
               return 1+depth(temp.right);
            }else{
               return 1+depth(temp.left);
            }
         }else{
            if(temp.left != null){
               return 1+depth(temp.left);
            }else{
               return 1+depth(temp.right);
            }
         }
      }
   }   
   
   static void readFile(String file){
      try(BufferedReader br = new BufferedReader(new FileReader(new File(file)))){
         String line = br.readLine();
         while(line != null){
            add(Integer.parseInt(line));
            line = br.readLine();
         }
         br.close();
      }catch(IOException e){
         System.err.println("IOException: "+e.getMessage()+"\n"+new File(file).getAbsolutePath());
      }
   }
}