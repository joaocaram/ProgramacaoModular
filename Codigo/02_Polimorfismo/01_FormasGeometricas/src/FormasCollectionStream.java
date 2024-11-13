
import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;
import java.util.Scanner;

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
        
        ArrayList<FormaGeometrica> meuConjuntoGeometrico = new ArrayList<>();
        int opcao;

        meuConjuntoGeometrico = new ArrayList<>(criarNovoConjunto(meuConjuntoGeometrico));
        opcao = menuPrincipal();

        while (opcao != 0) {
            limparTela();
            System.out.println();
            switch (opcao) {
                case 1 -> {
                    meuConjuntoGeometrico.clear();
                    meuConjuntoGeometrico = new ArrayList<>(criarNovoConjunto(meuConjuntoGeometrico));
                }
                case 2 -> {
                    System.out.println("---------------------------");
                    System.out.println(meuConjuntoGeometrico);
                    System.out.println("---------------------------");
                }
                case 3 -> { // TODO
                    System.out.println("TO DO.");
                }
            }
            pausa();
            opcao = menuPrincipal();
        }

        teclado.close();
    }
}
