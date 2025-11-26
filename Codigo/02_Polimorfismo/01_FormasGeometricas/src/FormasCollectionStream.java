import java.nio.charset.Charset;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;
import java.util.TreeMap;


/** 
 * MIT License
 *
 * Copyright(c) 2023-4 João Caram <caram@pucminas.br>
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

/**
 * Para demonstração simples do uso de coleções (JCF), mapas e fluxos de dados
 * (streams)
 */
public class FormasCollectionStream {
    static Scanner teclado = new Scanner(System.in, Charset.forName("UTF-8"));

    static LinkedList<FormaGeometrica> listaFormas = new LinkedList<>();
    static HashSet<FormaGeometrica> conjunto = new HashSet<>();
    static HashMap<Integer, FormaGeometrica> hashFiguras = new HashMap<>();
        
    static void limparTela() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    static void pausa() {
        System.out.println("Tecle Enter para continuar.");
        teclado.nextLine();
    }

    static int menuPrincipal() {
        limparTela();
        String linha = "=========================";
        System.out.println("MUITAS FORMAS GEOMÉTRICAS");
        System.out.println(linha);
        System.out.println("CRIAR / LISTAR / LOCALIZAR");
        System.out.println("1 - Criar novo conjunto");
        System.out.println("2 - Adicionar forma fixa");
        System.out.println("3 - Mostrar conjunto");
        System.out.println("4 - Pegar elemento");
        System.out.println(linha);
        System.out.println("FILTROS");
        System.out.println("5 - Filtrar formas por área");
        System.out.println("6 - Nomes e áreas com filtro por área");
        System.out.println("7 - Filtro por tamanho e tipo da forma");
        System.out.println("8 - Formas distintas por área mínima por tamanho e tipo da forma");
        System.out.println(linha);
        System.out.println("TOTALIZADORES");
        System.out.println("9 - Maior forma em área");
        System.out.println("10 - Menor forma em perímetro");
        System.out.println("11 - Soma das áreas");
        System.out.println("12 - Soma e média dos perímetros");
        System.out.println("13 - Média dos perímetros de um tipo");        
        System.out.println(linha);
        System.out.println("MAP / REDUCE ");
        System.out.println("14 - Mostrar conjunto ordenado ");
        System.out.println("15 - Mostrar subconjunto ordenado por área");
        System.out.println(linha);

        System.out.println("0 - Sair");
        System.out.print("\nOpção: ");
        return Integer.parseInt(teclado.nextLine());
    }

    /**
     * Cria um conjunto aleatório de formas geométricas. Para propósito de testes,
     * estamos usando um gerador aleatório de semente fixa.
     * 
     * @param tamanho Tamanho do conjunto a ser criado.
     * @return Uma Collection aleatório com a quantidade de figuras especificadas em
     *         "tamanho"
     */
    public static Collection<FormaGeometrica> criaFormasAleatorias(int tamanho,
            Collection<FormaGeometrica> colecao) {
        Random sorteio = new Random(42);

        for (int i = 0; i < tamanho; i++) {
            int tipo = 1 + sorteio.nextInt(4);
            double dimensao1 = sorteio.nextDouble(2, 10);
            double dimensao2 = sorteio.nextDouble(2, 10);
            FormaGeometrica nova = null;
            switch (tipo) {
                case 1 -> nova = new Quadrado(dimensao1);
                case 2 -> nova = new Circulo(dimensao1);
                case 3 -> nova = new Retangulo(dimensao1, dimensao2);
                case 4 -> nova = new TrianguloRetangulo(dimensao1, dimensao2);
            }
            colecao.add(nova);
        }
        return colecao;
    }

    public static Collection<FormaGeometrica> criarNovoConjunto(Collection<FormaGeometrica> colecao) {
        System.out.print("Qual o tamanho do conjunto a ser criado? ");
        int tamanho = Integer.parseInt(teclado.nextLine());
        return criaFormasAleatorias(tamanho, colecao);

    }

    static void criarColecoes(Collection<FormaGeometrica> lista,
                                Collection<FormaGeometrica> conjunto){

        lista = criarNovoConjunto(lista);
        conjunto.addAll(lista);
        for(FormaGeometrica forma : lista){
            hashFiguras.put(forma.hashCode(), forma);
        }
    }

    public static void main(String[] args) {
        Comparator<FormaGeometrica> compArea = (f1, f2) -> f1.area() > f2.area() ? 1 : -1;
        Comparator<FormaGeometrica> compDesc = (f1, f2) -> f1.toString().compareTo(f2.toString());

        int opcao;


        opcao = menuPrincipal();

        while (opcao != 0) {
            limparTela();
            System.out.println();
            switch (opcao) {
                case 1 -> criarColecoes(listaFormas, conjunto);
                case 2 -> adicionarFormasFixas(listaFormas, conjunto);

                case 3 -> mostrarColecoes(listaFormas, conjunto);
                case 4 -> {
                    Quadrado quad2 = new Quadrado(2);
                    int posicao = listaFormas.indexOf(quad2);
                    if(posicao != -1){
                        System.out.println(listaFormas.get(posicao));
                    }
                    boolean existe = conjunto.contains(quad2);
                    System.out.println("Conjunto: "+existe);
                    FormaGeometrica forma = hashFiguras.get(quad2.hashCode());
                    if(forma != null){
                        System.out.println(forma);
                    }
                    
                }
                case 5 -> {
                               
                }
                case 6 -> {
                    
                }
                case 7 -> {
                    
                               
                }
                case 8 -> {
                    
                }
                case 9 ->{
                    Comparator<FormaGeometrica> compAreaAula = 
                                (f1, f2) -> f1.area() > f2.area() ? 1 : -1;
                    FormaGeometrica forma = listaFormas.stream()
                                             .max(compAreaAula)
                                             .orElseThrow(() ->new IllegalStateException());
                    System.out.println("Maior pela área:\n"+forma);
                    
                }           
                case 10 ->{
                   Comparator<FormaGeometrica> compAreaAula = 
                                (f1, f2) -> f1.area() > f2.area() ? 1 : -1;
                    FormaGeometrica forma = listaFormas.stream()
                                             .max(compAreaAula)
                                             .orElseThrow(() ->new IllegalStateException());
                    System.out.println("Maior pela área:\n"+forma);
                    
                }      
                case 11 -> {
                    double soma = listaFormas.stream()
                                             .mapToDouble(f->f.area())
                                             .sum();
                    System.out.printf("Soma das áreas: %.2f\n", soma);
                }
                case 12 -> {
                    double media = listaFormas.stream()
                                             .mapToDouble(f->f.perimetro())
                                             .average()
                                             .orElse(0d);
                    System.out.printf("Média dos perímetros: %.2f\n", media);
               
                }

                case 13 -> {
                    
                }

                case 14 -> {
                    
                }
                case 15 -> {
                    
                }
                default -> opcao = 1;
            }
            pausa();
            opcao = menuPrincipal();
        }

        teclado.close();
    }

    private static void adicionarFormasFixas(LinkedList<FormaGeometrica> listaFormas,
            HashSet<FormaGeometrica> conjunto) {
                Quadrado quadradinho = new Quadrado(2);
                listaFormas.add(quadradinho);
                listaFormas.add(quadradinho);
                conjunto.add(quadradinho);
                conjunto.add(quadradinho);
                hashFiguras.put(quadradinho.hashCode(), quadradinho);
    }

    private static void mostrarColecoes(LinkedList<FormaGeometrica> listaFormas, HashSet<FormaGeometrica> conjunto) {
        limparTela();
        System.out.println("LISTA");
        for (FormaGeometrica formaGeometrica : listaFormas) {
            System.out.println(formaGeometrica);
        }    
        pausa();
        limparTela();
        System.out.println("CONJUNTO");
        for (FormaGeometrica formaGeometrica : conjunto) {
            System.out.println(formaGeometrica);
        }
        pausa();
        limparTela();
        System.out.println("MAPA");
        for (FormaGeometrica formaGeometrica : hashFiguras.values()) {
            System.out.println(formaGeometrica);
        }
    }
}
