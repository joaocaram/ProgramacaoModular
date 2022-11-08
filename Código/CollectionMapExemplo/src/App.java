/** 
 * MIT License
 *
 * Copyright(c) 2022 João Caram <caram@pucminas.br>
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.function.BiFunction;


/**
 * App/demo do uso de coleções
 */
@SuppressWarnings("unchecked")    
 public class App {

   static Veiculo novo = new Veiculo("ZPM2O22", 2003, 0, 50_000d);
   static Veiculo busca = new Veiculo("NACH0TI",0,0,0);

   static Veiculo stringParaVeiculo(String linha){
        String [] detalhes  = linha.split(";");     //separamos usando o ;
        String placa = detalhes[0];
        int kmRodados = Integer.parseInt(detalhes[1]);
        int ano = Integer.parseInt(detalhes[2]);
        double valor = Double.parseDouble(detalhes[3]);

        Veiculo novoVeiculo = new Veiculo(placa, ano, kmRodados, valor);
        return novoVeiculo;
   }

    /**
     * Carrega os dados em uma estrurura para uso no sistema, parametrizado pelo método de inserção da estrutura.
     * @param nomeArq Arquivo texto com os dados.
     * @param garagem Objeto que armazenará os dados. Precisa, necessariamente, ser uma estrutura para vários objetos Veículo.
     * @param func Função delegada para armazenamento na garagem.
     * @throws IOException Exceção em caso de problemas com o arquivo de dados
     */
    static void carregarDados(String nomeArq, Object garagem, BiFunction<Object, Veiculo, Boolean> func) throws IOException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
        Scanner arquivo = new Scanner(new File(nomeArq));   //criamos o leitor de arquivos
        int quantidade = Integer.parseInt(arquivo.nextLine()); //primeira linha com a quantidade de dados     
        
        for(int i = 0 ; i<quantidade; i++){    //vamos ler cada linha a partir da 2ª
            String linha = arquivo.nextLine();
            Veiculo novoVeiculo = stringParaVeiculo(linha);
            func.apply(garagem, novoVeiculo);
        }
        arquivo.close();
    }


    public static void collectionDemo(Scanner teclado) throws IOException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
        
        Collection<Veiculo> garagemCollection = new ArrayList<Veiculo>();
        BiFunction<Object, Veiculo, Boolean> func  =
                     (colecao, veiculo) -> ((Collection<Veiculo>)colecao).add(veiculo);
        
       
        carregarDados("veiculos.txt", garagemCollection, func); 
        
        System.out.println("Coleção genérica");
        System.out.println("1 - Coleção: ");
        for (Veiculo veiculo : garagemCollection) {
            System.out.println(veiculo);
        }
        System.out.println("\nEnter para continuar");
        teclado.nextLine();
        
        System.out.println("2 - Tamanho da garagem: "+garagemCollection.size());
        
        System.out.println("3 - Adicionando novo:");
        garagemCollection.add(novo);
        System.out.println("Novo tamanho: "+garagemCollection.size());
        teclado.nextLine();
        
        System.out.println("4 - Não permite localizar elemento automaticamente (necessário iterar)");
        System.out.println("5 - Buscando com contains: (não retorna)");
        if (garagemCollection.contains(busca))
            System.out.println("Contém "+busca);
        
        System.out.println("6 - Não permite retirar o primeiro");    
        System.out.println("7 - Removendo "+busca.placa);
        garagemCollection.remove(busca);
        
        
        System.out.println("8 - Nova coleção:");
        for (Veiculo veiculo : garagemCollection) {
            System.out.println(veiculo);
        }
        
         teclado.nextLine();   
    }
    
    public static void LinkedListDemo(Scanner teclado) throws IOException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
        LinkedList<Veiculo> garagemLista = new LinkedList<Veiculo>();
        
        BiFunction<Object, Veiculo, Boolean> func  = (colecao, veiculo) -> ((Collection<Veiculo>)colecao).add(veiculo);
        
        carregarDados("veiculos.txt", garagemLista, func); 

        
        System.out.println("Linked list");
        System.out.println("1 - Coleção: ");
        for (Veiculo veiculo : garagemLista) {
            System.out.println(veiculo);
        }
        System.out.println("Enter para continuar");
        teclado.nextLine();
        
        System.out.println("2 - Tamanho da garagem: "+garagemLista.size());
        System.out.println("3 - Adicionando novo: ");
        garagemLista.add(novo);
        System.out.println("Novo tamanho: "+garagemLista.size());
        teclado.nextLine();

        System.out.println("4 - Busca por posição: ");
        int pos = garagemLista.indexOf(busca);
        if (pos !=-1)
            System.out.println("Contém "+garagemLista.get(pos)+ " na posição "+pos);

            
        System.out.println("5 - Buscando com contains: (não retorna)");
        if (garagemLista.contains(busca))
                System.out.println("Contém  "+busca);
               
        teclado.nextLine();
        
        System.out.println("6 - Primeiro da lista: (retirado)");
        System.out.println(garagemLista.removeFirst());
        teclado.nextLine();
        System.out.println("7 - Removendo "+busca.placa);
        garagemLista.remove(busca);       
        
        System.out.println("8 - Nova coleção:");
        for (Veiculo veiculo : garagemLista) {
            System.out.println(veiculo);
        }
        
         teclado.nextLine();   
    }

    public static void heapDemo(Scanner teclado) throws IOException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{    
        PriorityQueue<Veiculo> garagemHeap = new PriorityQueue<Veiculo>();
        
        
        BiFunction<Object, Veiculo, Boolean> func  = (colecao, veiculo) -> ((Collection<Veiculo>)colecao).add(veiculo);
        
        carregarDados("veiculos.txt", garagemHeap, func); 
       
       
       
        System.out.println("Heap");
        System.out.println("1 - Coleção: ");
        for (Veiculo veiculo : garagemHeap) {
            System.out.println(veiculo);
        }
        System.out.println("\nEnter para continuar");
        teclado.nextLine();

        System.out.println("2 - Tamanho da garagem: "+garagemHeap.size());
        System.out.println("3 - Adicionando novo:");        
        garagemHeap.add(novo);
        System.out.println("Novo tamanho: "+garagemHeap.size());
        teclado.nextLine();
        
        System.out.println("4 - Não permite localizar elemento automaticamente (necessário iterar)");
        System.out.println("5 - Buscando com contains: (não retorna)");
        if (garagemHeap.contains(busca))
            System.out.println("Contém "+busca);

        System.out.println("6 - Primeiro da lista/mais importante: (retirado)");
        System.out.println(garagemHeap.poll());
        teclado.nextLine();
        
        System.out.println("7 - Removendo "+busca.placa);
        garagemHeap.remove(busca);       
        
        System.out.println("8 - Nova coleção:");
        for (Veiculo veiculo : garagemHeap) {
            System.out.println(veiculo);
        }
         teclado.nextLine();  
    }

    public static void hashMapDemo(Scanner teclado) throws IOException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{        
        HashMap<String,Veiculo> garagemHash = new HashMap<>();

        BiFunction<Object, Veiculo, Boolean> func  = 
              (colecao, veiculo) -> (((Map<String,Veiculo>)colecao).put(veiculo.placa,veiculo)==null?false:true);
        
        carregarDados("veiculos.txt", garagemHash, func); 

                
        System.out.println("Hash");
        System.out.println("1 - Coleção: (precisa capturar a coleção para iterar)");
        for (Veiculo veiculo : garagemHash.values()) {
            System.out.println(veiculo);
        }
        System.out.println("Enter para continuar");
        teclado.nextLine(); 

        System.out.println("2 - Tamanho da garagem: "+garagemHash.size());
        System.out.println("3 - Adicionando novo: (necessário associar chave)");
        garagemHash.put(novo.placa, novo);
        System.out.println("Novo tamanho: "+garagemHash.size());
        teclado.nextLine();

        System.out.println("4 - Buscando por chave (placa):");
        Veiculo chave = garagemHash.get(busca.placa);
        System.out.println("Veículo com placa "+busca.placa+" - "+chave);
        teclado.nextLine();

        System.out.println("5 - Buscando com contains: (não retorna)");
        if (garagemHash.containsKey(busca.placa))
            System.out.println("Contém veículo com placa "+busca.placa);

        System.out.println("6 - Não permite localizar 'maior'(necessário iterar)");      
        teclado.nextLine();
        
        System.out.println("7 - Removendo por chave "+busca.placa);
        garagemHash.remove(busca.placa);
        
        
        System.out.println("8 - Nova coleção: (verifique a ordem");
        for (Veiculo veiculo : garagemHash.values()) {
            System.out.println(veiculo);
        }
        

         teclado.nextLine();   

    }

    public static void treeMapDemo(Scanner teclado) throws IOException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{            
        TreeMap<String,Veiculo> garagemArvore = new TreeMap<>();
       
        BiFunction<Object, Veiculo, Boolean> func  = (colecao, veiculo) -> (((Map<String,Veiculo>)colecao).put(veiculo.placa,veiculo)==null?false:true);
        
                
        carregarDados("veiculos.txt", garagemArvore, func); 
        
        System.out.println("Árvore");
        System.out.println("1 - Coleção: (precisa capturar a coleção para iterar. Veja a ordem)");
        for (Veiculo veiculo : garagemArvore.values()) {
            System.out.println(veiculo);
        }
        System.out.println("Enter para continuar");
        teclado.nextLine(); 

        System.out.println("2 - Tamanho da garagem: "+garagemArvore.size());
        System.out.println("3 - Adicionando novo: (necessário associar chave)");
        garagemArvore.put(novo.placa, novo);
        System.out.println("Novo tamanho: "+garagemArvore.size());
        teclado.nextLine(); 

        System.out.println("4 - Buscando por chave (placa):");
        Veiculo chave = garagemArvore.get(busca.placa);
        System.out.println("Veículo com placa "+busca.placa+" - "+chave);
        teclado.nextLine();

        System.out.println("5 - Buscando com contains: (não retorna)");
        if (garagemArvore.containsKey(busca.placa))
            System.out.println("Contém veículo com placa "+busca.placa);

        System.out.println("6 - Localizando maior: ");      
        Veiculo maior = garagemArvore.lastEntry().getValue();
        System.out.println("Maior:  "+maior);
        teclado.nextLine();
        
        System.out.println("7 - Removendo por chave "+busca.placa);
        garagemArvore.remove(busca.placa);
                
        System.out.println("8 - Nova coleção: (verifique a ordem");
        for (Veiculo veiculo : garagemArvore.values()) {
            System.out.println(veiculo);
        }
        teclado.nextLine();
    }
       
    /**
     * "Limpa" a tela (códigos de terminal VT-100)
     */
    public static void limparTela(){
        System.out.print("\033[H\033[2J");  
        System.out.flush();  
    }

    /**
     * Menu para o app
     * @param teclado Scanner de leitura
     * @return Opção do usuário (int)
     */
    
    public static int menu(Scanner teclado){
        limparTela();
        System.out.println("MAPAS E COLEÇÕES");
        System.out.println("==========================");
        System.out.println("1 - Coleção genérica");
        System.out.println("2 - Linked list");
        System.out.println("3 - Priority queue");
        System.out.println("4 - Hash");
        System.out.println("5 - Árvore");
        System.out.println("0 - Sair");

        int opcao = teclado.nextInt();
        teclado.nextLine();
        return opcao;
    }
    public static void main(String[] args) throws Exception {
        
            Scanner teclado = new Scanner(System.in);
            
            int opcao;
            do{
                
                opcao = menu(teclado);
                limparTela();
                switch (opcao) {
                    case 1:
                         collectionDemo(teclado);
                        break;
                    case 2:
                        LinkedListDemo(teclado);
                        break;
                    case 3:
                        heapDemo(teclado);
                        break;
                    case 4:
                        hashMapDemo(teclado);
                        break;
                    case 5:
                        treeMapDemo(teclado);
                        break;
                    
                }
                limparTela();
            }while (opcao!=0);
        

     
        //================================
        
        


    }

    
}
