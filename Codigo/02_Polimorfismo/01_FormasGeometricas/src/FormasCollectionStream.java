import java.nio.charset.Charset;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
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
    public static Collection<FormaGeometrica> criaFormasAleatorias(final int tamanho,
            final Collection<FormaGeometrica> colecao) {
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

    public static Collection<FormaGeometrica> criarNovoConjunto(final Collection<FormaGeometrica> colecao) {
        System.out.print("Qual o tamanho do conjunto a ser criado? ");
        int tamanho = Integer.parseInt(teclado.nextLine());
        return criaFormasAleatorias(tamanho, colecao);

    }

    public static void main(final String[] args) {
        Comparator<FormaGeometrica> compArea = (f1, f2) -> f1.area() > f2.area() ? 1 : -1;
        Comparator<FormaGeometrica> compDesc = (f1, f2) -> f1.toString().compareTo(f2.toString());

        LinkedList<FormaGeometrica> listaFormas = new LinkedList<>();
        TreeMap<Double, FormaGeometrica> arvore = new TreeMap<>();
        HashMap<Integer, FormaGeometrica> hashFiguras = new HashMap<>();
        int opcao;

        // meuConjuntoGeometrico = new LinkedList<>(criaFormasAleatorias(10,
        // meuConjuntoGeometrico));

        opcao = menuPrincipal();

        while (opcao != 0) {
            limparTela();
            System.out.println();
            switch (opcao) {
                case 1 -> {
                    int tamanho;
                    System.out.print("Qual o tamanho do conjunto? ");
                    tamanho = Integer.parseInt(teclado.nextLine());
                    listaFormas.clear();
                    listaFormas = new LinkedList<>(criaFormasAleatorias(tamanho, listaFormas));
                    for (FormaGeometrica formaGeometrica : listaFormas) {
                        hashFiguras.put(formaGeometrica.hashCode(), formaGeometrica);
                        arvore.put(formaGeometrica.perimetro(), formaGeometrica);
                    }

                }
                case 2 -> {
                    FormaGeometrica quadradinhoDe8 = new Quadrado(8);   
                    listaFormas.addFirst(quadradinhoDe8);
                    listaFormas.addLast(quadradinhoDe8);
                    listaFormas.add(listaFormas.size()/2, quadradinhoDe8);
                    hashFiguras.put(quadradinhoDe8.hashCode(), quadradinhoDe8);
                    arvore.put(quadradinhoDe8.perimetro(), quadradinhoDe8);
                }

                case 3 -> {
                    limparTela();
                    final String linha = "---------------------------";
                    System.out.println("LISTA:");
                    for (FormaGeometrica formaGeometrica : listaFormas) {
                        System.out.println(formaGeometrica);
                    }
                    pausa();
                    System.out.println(linha);
                    System.out.println("HASH MAP:");
                     for (FormaGeometrica formaGeometrica : hashFiguras.values()) {
                        System.out.println(formaGeometrica);
                     }
                    pausa();
                    System.out.println(linha);
                    System.out.println("ÁRVORE:");
                    for (FormaGeometrica formaGeometrica : arvore.values()) {
                        System.out.println(formaGeometrica);
                    }
                }
                case 4 -> {
                    System.out.print("Identificador/posição do elemento: ");
                    int idElemento = Integer.parseInt(teclado.nextLine());
                    FormaGeometrica f = hashFiguras.get(idElemento);
                    System.out.println("Por id no mapa: "+f);
                    f = listaFormas.get(idElemento);
                    System.out.println("Por posição na lista: "+f);
                }
                case 5 -> {
                    System.out.print("Qual é a área mínima do filtro? ");
                    double areaMinima = Double.parseDouble(teclado.nextLine());
                    
                    listaFormas.stream()
                               .filter(f -> f.area() >= areaMinima)
                               .forEach(f -> System.out.println(f));
                               
                }
                case 6 -> {
                    System.out.print("Qual é a área mínima do filtro? ");
                    double areaMinima = Double.parseDouble(teclado.nextLine());
                    
                    listaFormas.stream()
                               .filter(f -> f.area() >= areaMinima)
                               .map(f -> new Object(){public double area = f.area(); public String nome = f.nome(); })
                               .forEach(o -> System.out.println(o.nome+" - Área "+o.area));
                               
                }
                case 7 -> {
                    System.out.print("Qual é a área mínima do filtro? ");
                    double areaMinima = Double.parseDouble(teclado.nextLine());
                    System.out.print("Qual o tipo da forma geométrica? ");
                    String forma = teclado.nextLine().toLowerCase();

                    listaFormas.stream()
                               .filter(f -> f.area() >= areaMinima)
                               .filter(f -> f.nome().toLowerCase().equals(forma))
                               .forEach(f-> System.out.println(f));
                               
                }
                case 8 -> {
                    System.out.print("Qual é a área mínima do filtro? ");
                    double areaMinima = Double.parseDouble(teclado.nextLine());
                    System.out.println("Formas distintas:");
                    listaFormas.stream()
                               .filter(f -> f.area() >= areaMinima)
                               .map( f-> f.nome())
                               .distinct()
                               .forEach(f-> System.out.println(f));                               
                }
                case 9 ->{
                    System.out.println("Maior em área = " +
                            listaFormas.stream()
                                       .max(compArea)
                                       .orElse(null)
                    );
                }           
                case 10 ->{
                    System.out.println("Menor em perímetro = " +
                            listaFormas.stream()
                                       .min((f1,f2) -> f1.perimetro() > f2.perimetro() ? 1: -1)
                                       .orElse(null)
                    );
                }      
                case 11 -> {
                    double somaAreas = listaFormas.stream()
                            .mapToDouble(fg -> fg.area())
                            .sum();
                    System.out.println("Áreas somados: " + somaAreas);
                }
                case 12 -> {
                    double somaPerimetros = listaFormas.stream()
                            .mapToDouble(fg -> fg.perimetro())
                            .sum();
                    double mediaPerimetros = listaFormas.stream()
                            .mapToDouble(fg -> fg.perimetro())
                            .average()
                            .orElse(0d);
                    System.out.println("Média dos perímetros: " + mediaPerimetros + " (soma de "+ somaPerimetros+").");
                    
                    //dica: olhe o método .summaryStatistics() para a stream
                }

                case 13 -> {
                    System.out.print("Qual o tipo da forma geométrica? ");
                    String forma = teclado.nextLine().toLowerCase();
                    System.out.println("Média dos perímetros dos "+forma+"s = "+
                            listaFormas.stream()
                                       .filter(f -> f.nome().toLowerCase().equals(forma))
                                       .mapToDouble(fg -> fg.perimetro())
                                       .average()
                                       .orElse(0)
                    );

                }

                case 14 -> {
                    System.out.print("Ordem de área (1) ou alfabética (2) ? ");
                    int escolha  = Integer.parseInt(teclado.nextLine());
                    Comparator<FormaGeometrica> compEscolhido = escolha==1 ? compArea : compDesc;

                    System.out.println(
                            listaFormas.stream()
                                      .sorted(compEscolhido)
                                      .map(Object::toString)
                                      .reduce((f1, f2) -> f1.concat("\n" + f2))
                                      .orElse("Vazio")
                    );
                }
                case 15 -> {
                    System.out.print("Qual o tipo da forma geométrica? ");
                    String forma = teclado.nextLine().toLowerCase();

                    System.out.println(
                            listaFormas.stream()
                                      .filter(f -> f.nome().toLowerCase().equals(forma))
                                      .sorted(compArea)
                                      .map(Object::toString)
                                      .reduce((f1, f2) -> f1.concat("\n" + f2))
                                      .orElse("Vazio")
                    );
                }
                default -> opcao = 1;
            }
            pausa();
            opcao = menuPrincipal();
        }

        teclado.close();
    }
}
