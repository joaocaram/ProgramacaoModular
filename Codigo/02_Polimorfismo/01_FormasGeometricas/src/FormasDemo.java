import java.util.Random;
import java.util.Scanner;

/** 
 * MIT License
 *
 * Copyright(c) 2023 João Caram <caram@pucminas.br>
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


public class FormasDemo {
    static Scanner teclado = new Scanner(System.in);
    static Random aleatorio = new Random(42);

    /** Simula a limpeza de tela em terminal VT-100 */
    static void limparTela() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    static int menu(){
            limparTela();
            System.out.println("1 - Mostrar conjunto");
            System.out.println("2 - Maior forma");
            System.out.println("3 - Criar novo conjunto manualmente");
            System.out.println("4 - Criar novo conjunto aleatoriamente");
            System.out.println("0 - Sair");
            System.out.print("Opção: ");
            return Integer.parseInt(teclado.nextLine());
    }
    /**
     * Encapsula o processo de construção de um conjunto, fazendo leituras de teclado do usuário. 
     * As formas geométricas estão sendo criadas com tamanho fixo. 
     * Sugestão: melhorar para que seja aleatório ou para ler do usuário.
     * @param tamanho Tamanho do conjunto a ser criado.
     * @return Um ConjuntoGeométrico com a quantidade de figuras especificadas em "tamanho"
     */
    public static ConjuntoGeometrico criaFormas(int tamanho){
        
        ConjuntoGeometrico conjunto = new ConjuntoGeometrico(tamanho+5);  // 5 a mais para caso de testes

        for (int i = 0; i < tamanho; i++) {
            System.out.println("Escolha a forma: ");
            System.out.print("1 - Quadrado | ");
            System.out.print("2 - Círculo | ");
            System.out.print("3 - Retângulo | ");
            System.out.print("4 - Triângulo Retângulo | ");
            int opcao = Integer.parseInt(teclado.nextLine());
            FormaGeometrica nova = null;
            double dimensao1 = aleatorio.nextDouble(1, 9);
            double dimensao2 = aleatorio.nextDouble(1, 9);
            switch(opcao){
                case 1-> nova = new Quadrado(dimensao1);
                case 2-> nova = new Circulo(dimensao1);
                case 3-> nova = new Retangulo(dimensao1, dimensao2);
                case 5-> nova = new RetanguloPequeno();
                case 4-> nova = new TrianguloRetangulo(dimensao1, dimensao2);
            }
            conjunto.addForma(nova);
        }
        return conjunto;
    }

    /**
     * Encapsula o processo de construção de um conjunto, fazendo leituras de teclado do usuário. 
     * As formas geométricas estão sendo criadas com tamanho fixo. 
     * Sugestão: melhorar para que seja aleatório ou para ler do usuário.
     * @param tamanho Tamanho do conjunto a ser criado.
     * @return Um ConjuntoGeométrico com a quantidade de figuras especificadas em "tamanho"
     */
    public static ConjuntoGeometrico criaFormasAleatorias(int tamanho){
        
        ConjuntoGeometrico conjunto = new ConjuntoGeometrico(tamanho+5);  // 5 a mais para caso de testes

        for (int i = 0; i < tamanho; i++) {
            
            int forma = aleatorio.nextInt(1,5);
            FormaGeometrica nova = null;
            double dimensao1 = aleatorio.nextDouble(1, 9);
            double dimensao2 = aleatorio.nextDouble(1, 9);
            switch(forma){
                case 1-> nova = new Quadrado(dimensao1);
                case 2-> nova = new Circulo(dimensao1);
                case 3-> nova = new Retangulo(dimensao1, dimensao2);
                case 4-> nova = new TrianguloRetangulo(dimensao1, dimensao2);
            }
            conjunto.addForma(nova);
        }
        return conjunto;
    }

    public static void main(String[] args) {
        int tamanho;
        ConjuntoGeometrico meuConjuntoGeometrico;
        int opcao=-1;

        System.out.print("Qual o tamanho do conjunto a ser criado? ");
        tamanho = Integer.parseInt(teclado.nextLine());
        meuConjuntoGeometrico = criaFormasAleatorias(tamanho);
        
        while(opcao!=0){
            
            opcao = menu();
            System.out.println("\n\n\n\n\n---------------------------");
            limparTela();
            switch(opcao){
                case 1 ->{
                    System.out.println(meuConjuntoGeometrico);
                    System.out.println("---------------------------");
                    teclado.nextLine();
                }
                case 2 ->{
                    System.out.println(meuConjuntoGeometrico.maiorArea());
                    System.out.println("---------------------------");
                    teclado.nextLine();
                }
                case 3 ->{
                    System.out.print("Qual o tamanho do conjunto a ser criado? ");
                    tamanho = Integer.parseInt(teclado.nextLine());
                    meuConjuntoGeometrico = criaFormas(tamanho);
                }
                case 4 ->{
                    System.out.print("Qual o tamanho do conjunto a ser criado? ");
                    tamanho = Integer.parseInt(teclado.nextLine());
                    meuConjuntoGeometrico = criaFormasAleatorias(tamanho);
                }
            }
        }
        teclado.close();
    }
}

