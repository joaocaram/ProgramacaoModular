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

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

/**
 * Queremos criar um sistema simples que armazene formas geométricas.
 * Este sistema deve ser capaz de calcular área e perímetro destas formas.
 * Este sistema deve ser capaz de informar qual é a maior forma em área.
 * Inicialmente, iremos armazenar quadrados, retângulos, triângulos retângulos e círculos. 
 *
 * CLASSES ?
 *  Forma // Retangulo // Triangulo // Quadrado // Circulo
 * 
 * Comum a todas as classes
 *      - área
 *      - perímetro
 *      - descrição/nome
 * 
 * Comum os polígonos
 *      - base
 *      - altura
 *
 */


public class App {
    static Random sorteio = new Random(42);

     // #region utilidades
   
     /**
     * "Limpa" a tela (códigos de terminal VT-100)
     */
    public static void limparTela() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    /**
     * Pausa para leitura de mensagens em console
     * @param teclado Scanner de leitura
     */
    static void pausa(Scanner teclado) {
        System.out.println("\nEnter para continuar.");
        teclado.nextLine();
    }
    // #endregion

    // #region menus
    /**
     * Menu principal
     * 
     * @param teclado Scanner de leitura
     * @return Opção do usuário (int)
     */
    public static int menu(Scanner teclado) {

        System.out.println();
        System.out.println();

        System.out.println("XULAMBS Jr. SHAPES");
        System.out.println("==========================");
        System.out.println("1 - Listar formas");
        System.out.println("2 - Incluir forma no conjunto");
        System.out.println("3 - Maior forma em área");
        
        
        System.out.println("0 - Sair");
        System.out.print("Digite sua opção: ");
        try {
            int opcao = teclado.nextInt();
            teclado.nextLine();
            return opcao;
        } catch (InputMismatchException ie) {
            return -1;
        }
    }
    // #endregion

    /**
     * Retorna uma forma de acordo com o tipo relacionado no menu. As dimensões são aleatórias entre 1.0 e 4.0. Tipos inválidos são desconsiderados e gera retorno nulo.
     * @param tipo 0: círculo; 1: retângulo; 2: triângulo retângulo; 3: quadrado.
     * @return Uma forma do tipo escolhido ou nulo, em caso de tipo inválido
     */
    private static Forma novaForma(int tipo){
        Forma novaForma = null;
        switch(tipo){
            case 0: novaForma = (new Circulo(1+sorteio.nextInt(3)));
                break;
            case 1: novaForma = (new Retangulo(1.0+sorteio.nextInt(3), 1.0+sorteio.nextInt(3)));
                break;
            case 2: novaForma =(new TrianguloRetangulo(1+sorteio.nextInt(3), 1+sorteio.nextInt(3)));
                break;
            case 3: novaForma =(new Quadrado(1+sorteio.nextInt(3)));
                break;            
        }
        return novaForma;
    }

    /**
     * Menu e chamada para criação de uma nova forma geométrica (a ser efetivamente criada por novaForma(int)).
     * @param teclado Scanner para o teclado.
     * @return A forma criada, ou nulo em caso de opção inválida.
     */
    public static Forma criarForma(Scanner teclado){
        limparTela();
        System.out.println("XULAMBS Jr. SHAPES");
        System.out.println("==========================");
        System.out.println("1 - Círculo");
        System.out.println("2 - Retângulo");
        System.out.println("3 - Triângulo Retângulo");
        System.out.println("4 - Quadrado");
        System.out.print("Digite sua opção: ");
        try {
            int opcao = teclado.nextInt();
            teclado.nextLine();
            return novaForma(opcao-1);
        } catch (InputMismatchException ie) {
            return null;
        }

    }

    /**
     * Cria e retorna um conjunto aleatório de formas do tamanho solicitado e com a capacidade do dobro deste tamanho.
     * @param quantidade A quantidade inicial de elementos do conjunto (validada pelo construtor de ConjuntoGeometrico).
     * @return O conjunto com a quantidade de formas pedida.
     */
    public static ConjuntoGeometrico criarConjunto(int quantidade){

        ConjuntoGeometrico novoConjunto = new ConjuntoGeometrico(quantidade*2);

        for(int i=0; i<quantidade; i++){
            int tipo = sorteio.nextInt(4);
            novoConjunto.addForma(novaForma(tipo));
        }

        return novoConjunto;
    }

    public static void main(String[] args) throws Exception {
        int CAPACIDADE = 10;
        Scanner teclado = new Scanner(System.in);
        ConjuntoGeometrico minhasFormas  = criarConjunto(CAPACIDADE/2);
        int opcao = -1;
        System.out.println(minhasFormas);

        do{
            limparTela();
            opcao = menu(teclado);
            switch(opcao){
                case 1:
                    System.out.println();
                    System.out.println(minhasFormas);
                    break;
                case 2:
                    Forma novaForma = criarForma(teclado);
                    if(novaForma!=null) minhasFormas.addForma(novaForma);
                    break;
                case 3:
                    Forma maiorForma = minhasFormas.maiorArea();
                    System.out.println(maiorForma + " Área de "+String.format("%.2f", maiorForma.area()));
                    break;
            }
            pausa(teclado);

        }while(opcao!=0);

        System.out.println("Volte sempre.");

       
        
        
    }
}
