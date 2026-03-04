import java.util.Scanner;

/** 
 * MIT License
 *
 * Copyright(c) 2024 João Caram <caram@pucminas.br>
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

/** Primeiro exemplo de Java: E/S e impressão de um retângulo no console */
public class Retangulo {
    
    /** Scaner para leitura do teclado */
    static Scanner teclado = new Scanner(System.in);
    
    /** 
     * Lê um número inteiro do teclado, sem validação, e o retorna
     * @return uma variável int
    */
    static int lerNumero(){
        int valor;
        System.out.print("\tDigite um numero positivo: ");
        valor = teclado.nextInt();
        return valor;
    }
    
    /**
     * Retorna uma string com uma linha de retângulo preenchida com 'X'. O tamanho é definido pelo usuário.
     * @param tamanho Tamanho da linha. Deve ser maior que 0 (não há validação no código)
     * @return String com uma linha cheia preenchida com 'X'
     */
    static String linhaCheia(int tamanho){        
        String linha="";
        for(int i=0; i<tamanho; i++){
            linha += 'X';
        }
        return linha;
    }
    
    /**
     * Retorna uma string com uma linha de retângulo vazada, com 'X' nas bordas. O tamanho é definido pelo usuário.
     * @param tamanho Tamanho da linha. Deve ser maior que 0 (não há validação no código)
     * @return String com uma linha de retângulo vazada, com 'X' nas bordas
     */
    static String linhaVazia(int tamanho){
        String linha = "X";
        for(int i=1; i<tamanho-1; i++){
            linha += ' ';
        }
        linha += 'X';
        return linha;
    }
    
    
    public static void main(String[] args) {
        int altura = lerNumero();
        int largura = lerNumero();
        
        System.out.println(linhaCheia(largura));
        
        for(int i=0 ; i < altura - 2; i++ ){
            System.out.println(linhaVazia(largura));
        }
        System.out.println(linhaCheia(largura));
    }
    
}


