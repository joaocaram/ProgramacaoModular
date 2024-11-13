import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
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
 * Para demonstração simples do uso de coleções (JCF), mapas e fluxos de dados (streams)
 */
public class FormasCollectionStream {
    static Scanner teclado = new Scanner(System.in);

    /**
     * Cria um conjunto aleatório de formas geométricas. Para propósito de testes, 
     * estamos usando um gerador aleatório de semente fixa.  
     * @param tamanho Tamanho do conjunto a ser criado.
     * @return Uma Collection aleatório com a quantidade de figuras especificadas em "tamanho"
     */
    public static <T extends Collection<FormaGeometrica>> Collection<FormaGeometrica> criaFormasAleatorias(int tamanho, Class<T> colecao){
        Random sorteio = new Random(42);
        T conjunto;
        try {
            conjunto = colecao.getConstructor().newInstance();
            for (int i = 0; i < tamanho; i++) {
                int tipo = 1+ sorteio.nextInt(4);
                double dimensao1 = sorteio.nextDouble(2, 10);
                double dimensao2 = sorteio.nextDouble(2, 10);
                FormaGeometrica nova = null;
                switch(tipo){
                    case 1-> nova = new Quadrado(dimensao1);
                    case 2-> nova = new Circulo(dimensao1);
                    case 3-> nova = new Retangulo(dimensao1, dimensao2);
                    case 4-> nova = new TrianguloRetangulo(dimensao1, dimensao2);
                }
                conjunto.add(nova);
            }
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
                | NoSuchMethodException | SecurityException e) {
            // TODO Auto-generated catch block
            conjunto = null;
        }
        return conjunto;
        
    }

    public static Collection<FormaGeometrica> criarNovoConjunto(Collection<FormaGeometrica> colecao){
        System.out.print("Qual o tamanho do conjunto a ser criado? ");
        int tamanho = Integer.parseInt(teclado.nextLine());
        return criaFormasAleatorias(tamanho, colecao.getClass());
        
    }
    public static void main(String[] args) {
        int tamanho;
        ArrayList<FormaGeometrica> meuConjuntoGeometrico = new ArrayList<>();
        int opcao=-1;

        meuConjuntoGeometrico = new ArrayList<>(criarNovoConjunto(meuConjuntoGeometrico));
        
        while(opcao!=0){
            System.out.println("1 - Criar novo conjunto");
            System.out.println("2 - Mostrar conjunto");
            System.out.println("3 - Maior forma");
            System.out.println("0 - Sair");
            System.out.print("Opção: ");
            opcao = Integer.parseInt(teclado.nextLine());
            System.out.println("\n\n\n\n\n---------------------------");
            switch(opcao){
                case 1 ->{
                    meuConjuntoGeometrico = new ArrayList<>(criarNovoConjunto(meuConjuntoGeometrico));
                }
                case 2 ->{
                    System.out.println(meuConjuntoGeometrico);
                    System.out.println("---------------------------");
                    teclado.nextLine();
                }
                case 3 ->{
                    teclado.nextLine();
                }
                
            }
        }


        teclado.close();
    }
}

