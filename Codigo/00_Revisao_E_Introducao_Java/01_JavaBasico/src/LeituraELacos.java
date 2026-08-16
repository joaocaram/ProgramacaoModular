/** 
 * MIT License
 *
 * Copyright(c) 2025-26 João Caram <caram@pucminas.br>
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


/** Java básico: E/S, repetição e decisão, vetores */
   
    /**
     * Recebe uma mensagem para exibir e pede a leitura de um número inteiro, retornando-o. 
     * Código sem robustez para valores não inteiros.
     * @param mensagem Mensagem a ser exibida ao usuário
     * @return Número inteiro lido a partir do teclado
     */
    int lerNumero(String mensagem){
        int valor;
        valor = Integer.parseInt( IO.readln("\t"+mensagem+": "));
        return valor;
    }

    /**
     * Preenche um vetor a partir da leitura de números inteiros
     * @param vetor Vetor a ser preenchido
     */
     void preencherVetor(int[] vetor){
        for (int i = 0; i < vetor.length; i++) {
            vetor[i] = lerNumero("Digite um valor inteiro");
        }
    }

    /**
     * Recebe um vetor de inteiros como parâmetro e retorna a quantidade de números pares deste vetor.
     * @param vetor O vetor a ser analisado.
     * @return int com a quantidade de pares do vetor (>=0)
     */
    int contaImpares(int[] vetor){
        int contadorImpar=0;
        for (int i = 0; i < vetor.length; i++) {
            contadorImpar += (vetor[i]%2);
        }
        return contadorImpar;
    }

    int somaVetor(int [] vetor){
        int soma = 0;
        for (int i = 0; i < vetor.length; i++) {
            soma = soma + vetor[i];
        }
        return soma;
    }

    int maioresQue(double valor, int[] vetor){
        int contador = 0;
        for (int i = 0; i < vetor.length; i++) {
            if(vetor[i] > valor){
                contador = contador+1;
            }
        }
        return contador;
    }

    void main(){
        
        int[] vetor;
        int tamanho = lerNumero("Quantidade de inteiros para ler");
        int quantImpares;
        int soma;
        double media;
        int maioresQueAMedia;

        vetor = new int[tamanho];
        preencherVetor(vetor);
        quantImpares = contaImpares(vetor);
        soma = somaVetor(vetor);
        media = soma / tamanho;
        maioresQueAMedia = maioresQue(media, vetor);

        IO.println("RESULTADOS:");
        IO.println("============");
        IO.println("O vetor tem "+quantImpares+" números ímpares e "+(tamanho-quantImpares)+" pares.");
        IO.println(
                String.format("A soma dos valores é de %d e sua média é de %f", soma, media)
                );
        IO.println(
                String.format("%d valores são maiores que a média", maioresQueAMedia)
                );
        
    }

