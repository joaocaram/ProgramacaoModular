import java.util.Collection;
import java.util.Collections;
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
    static Scanner teclado = new Scanner(System.in);

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
        System.out.println("1 - Criar novo conjunto");
        System.out.println("2 - Mostrar conjunto");
        System.out.println("3 - Maior forma");
        System.out.println("4 - Mostrar conjunto ordenado");
        System.out.println("5 - Pegar elemento");
        System.out.println("6 - Filtrar formas por área");
        System.out.println("7 - Somar perímetros");
        System.out.println("8 - Média das áreas");
        System.out.println("9 - Média dos perímetros dos quadrados");
        System.out.println("10 - Média dos perímetros dos quadrados");


        System.out.println("0 - Sair");
        System.out.print("Opção: ");
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
    public static Collection<FormaGeometrica> criaFormasAleatorias(int tamanho, Collection<FormaGeometrica> colecao) {
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

    public static void main(String[] args) {
        Comparator<FormaGeometrica> compArea = (f1, f2) -> f1.area()>f2.area()? 1 : -1;
        Comparator<FormaGeometrica> compDesc = (f1, f2) -> f1.toString().compareTo(f2.toString());

        LinkedList<FormaGeometrica> meuConjuntoGeometrico = new LinkedList<>();
        TreeMap<Double,FormaGeometrica> arvore = new TreeMap<>();
        HashMap<Integer, FormaGeometrica> hashFiguras = new HashMap<>();
        int opcao;

       // meuConjuntoGeometrico = new LinkedList<>(criaFormasAleatorias(10, meuConjuntoGeometrico));
        
         opcao = menuPrincipal();

        while (opcao != 0) {
            limparTela();
            System.out.println();
            switch (opcao) {
                case 1 -> {
                    meuConjuntoGeometrico.clear();
                    meuConjuntoGeometrico = new LinkedList<>(criaFormasAleatorias(50, meuConjuntoGeometrico));
                    for (FormaGeometrica formaGeometrica : meuConjuntoGeometrico) {
                            hashFiguras.put(formaGeometrica.hashCode(), formaGeometrica);
                            arvore.put(formaGeometrica.perimetro(), formaGeometrica);
                    }
                    
                }
                case 2 -> {
                    final String linha = "---------------------------";  
                    System.out.println(linha);
                    System.out.println(linha);
                    for (FormaGeometrica formaGeometrica : meuConjuntoGeometrico) {
                        System.out.println(formaGeometrica);
                    }
                    System.out.println(linha);

                    // System.out.println("---------------------------");
                    // for (FormaGeometrica formaGeometrica : hashFiguras.values()) {
                    //     System.out.println(formaGeometrica);
                    // }
                    // System.out.println("---------------------------");
                    // System.out.println("---------------------------");
                    // for (FormaGeometrica formaGeometrica : arvore.values()) {
                    //     System.out.println(formaGeometrica);
                    // }
                    // System.out.println("---------------------------");

                }
                case 3 -> { 
                    String qualForma = "CÍRCULO";
                    double areaMinima = 55;
                   FormaGeometrica maior =  meuConjuntoGeometrico.stream()
                                            .filter(f -> f.toString().contains(qualForma))
                                            .filter(f -> f.area() >= areaMinima)
                                            .min(compArea)
                                            .orElse(null);
                    System.out.println(maior);
                }
                case 4 -> {
                    Collections.sort(meuConjuntoGeometrico, compArea);
                    for (FormaGeometrica formaGeometrica : meuConjuntoGeometrico) {
                        System.out.println(formaGeometrica);
                    }
                }
                case 5 ->{
                    System.out.print("Identificador do elemento: ");
                    int idElemento = Integer.parseInt(teclado.nextLine());
                    FormaGeometrica f = hashFiguras.get(idElemento);
                    
                    System.out.println(f);
                }
                case 6 ->{
                    System.out.print("Qual é a área mínima do filtro? ");
                    double areaMinima = Double.parseDouble(teclado.nextLine());
                   System.out.println( 
                    meuConjuntoGeometrico.stream()
                                         .filter( fg -> fg.area()>= areaMinima)
                                         .map(Object::toString)
                                         .reduce( (f1,f2) -> f1.concat("\n"+f2))
                   );
                }
                case 7 ->{
                    double somaPerimetro = meuConjuntoGeometrico.stream()
                                                     .mapToDouble(fg-> fg.perimetro())
                                                     .sum();
                    System.out.println("Perímetros somados: " + somaPerimetro);
                }
                case 8 ->{
                    System.out.println("Média das áreas: " +
                                          meuConjuntoGeometrico.stream()
                                                     .mapToDouble(fg-> fg.perimetro())
                                                     .average().getAsDouble()
                                        );
                     
                }
                case 9 ->{
                 System.out.println("Média dos perímetros dos quadrados: "+
                           meuConjuntoGeometrico.stream()
                                         .filter( fg -> fg.toString().contains("QUADRADO"))
                                         .mapToDouble(f -> f.perimetro())
                                         .average()
                                         .getAsDouble()
                 );

                }
            }
            pausa();
            opcao = menuPrincipal();
        }

        teclado.close();
    }
}
